package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.Transfers;
import com.nttdata.bankproducts.feign.ClientFeign;
import com.nttdata.bankproducts.repository.BankAccountRepository;
import com.nttdata.bankproducts.repository.BankCreditsRepository;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.response.ProductBankByClient;
import com.nttdata.bankproducts.service.BankAccountService;
import com.nttdata.bankproducts.util.BankAccountMapper;
import com.nttdata.bankproducts.util.BankAccountMovementMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  /**
   * Bank Account repository.
   */
  @Autowired
  private BankAccountRepository bankAccountRepository;

  /**
   * Bank Credits repository.
   */
  @Autowired
  private BankCreditsRepository bankCreditsRepository;

  /**
   * Connection client.
   */
  @Autowired
  private ClientFeign clientFeign;

  /**
   * Returns all the bank accounts.
   *
   * @return  Flowable
   */
  @Override
  public Flowable<BankAccountResponse> getAll() {
    return this.bankAccountRepository
        .findAll()
        .map(BankAccountMapper.INSTANCE::map);
  }

  /**
   * Create a new bank account and check if the bank account exist.
   *
   * @param bankAccount   an object of bank account.
   * @return  Maybe
   */
  @Override
  public Maybe<BankAccountResponse> create(BankAccount bankAccount) {
    return this.bankAccountRepository
        .findById(bankAccount.getAccountNumberId())
        .flatMap(
            bankAccount1 -> Maybe.error(new Error("BankAccount exist")),
            error -> Maybe.error(error),
            () -> Maybe.just(bankAccount)
        )
        .flatMap(bankAccount1 -> {
          if (bankAccount1.getNameTypeAccount()
              .equalsIgnoreCase("ahorro")
              || bankAccount1.getNameTypeAccount()
              .equalsIgnoreCase("corriente")
              || bankAccount1.getNameTypeAccount()
              .equalsIgnoreCase("plazo fijo")) {
            return Maybe.just(bankAccount1);
          } else {
            return Maybe.error(
                new Error("Doesn't exist type of account"));
          }
        })
        .flatMapSingle(bankAccountRepository::save)
        .map(BankAccountMapper.INSTANCE::map);
  }

  /**
   * Displays the required bank account based on its identifier.
   *
   * @param bankAccountId   a string of bank account identifier
   * @return Maybe
   */
  @Override
  public Maybe<BankAccountResponse> read(String bankAccountId) {
    return bankAccountRepository
        .findById(bankAccountId)
        .map(BankAccountMapper.INSTANCE::map);
  }

  /**
   * Type of transfer that deposit an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @Override
  public Maybe<BankAccountResponse> deposit(String bankAccountId,
                                            Double amount) {
    Transfers transfers = new Transfers("deposit",
        DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
            .format(LocalDateTime.now()),
        amount);
    return this.bankAccountRepository
        .findById(bankAccountId)
        .switchIfEmpty(Maybe.error(new NotFoundException(
            "bankAccountId doesn't exist: " + bankAccountId)))
        .flatMap(bankAccount -> {
          bankAccount
              .setTotalAmount(
                  bankAccount.getTotalAmount() + amount);
          bankAccount
              .setTotalTransfers(
                  bankAccount.getTotalTransfers() + 1);
          bankAccount.getTransfersList().add(transfers);
          if (bankAccount.getTotalTransfers() >= 20) {
            bankAccount.setCountCommissions(
                bankAccount.getCountCommissions() + 1);
            bankAccount.setTotalAmount(
                bankAccount.getTotalAmount() - 4);
          }
          return Maybe.just(bankAccount);
        })
        .flatMapSingle(this.bankAccountRepository::save)
        .map(BankAccountMapper.INSTANCE::map);

  }

  /**
   * Type of transfer that withdraw an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @Override
  public Maybe<BankAccountResponse> withdraw(String bankAccountId,
                                             Double amount) {
    Transfers transfers = new Transfers("withdraw",
        DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
            .format(LocalDateTime.now()),
        amount);
    return this.bankAccountRepository
        .findById(bankAccountId)
        .switchIfEmpty(Maybe.error(new NotFoundException(
            "bankAccountId doesn't exist: " + bankAccountId)))
        .flatMap(bankAccount -> {
          if (bankAccount.getTotalAmount() > amount) {
            bankAccount.setTotalAmount(
                bankAccount.getTotalAmount() - amount);
            bankAccount
                .setTotalTransfers(
                    bankAccount.getTotalTransfers() + 1);
            bankAccount.getTransfersList().add(transfers);
            if (bankAccount.getTotalTransfers() >= 20) {
              bankAccount
                  .setCountCommissions(
                      bankAccount.getCountCommissions() + 1);
              bankAccount.setTotalAmount(
                  bankAccount.getTotalAmount() - 4);
            }
            return Maybe.just(bankAccount);
          } else {
            return Maybe.error(new Error("Unable to withdraw"));
          }
        })
        .flatMapSingle(this.bankAccountRepository::save)
        .map(BankAccountMapper.INSTANCE::map);
  }

  /*@Override
    public Flowable<BankAccount> readByClientId(String clientId) {
        return this.bankAccountRepository.findByclientId(clientId);
    }*/

  /**
   * Transfer an amount of money of bank account to other bank account.
   *
   * @param bankAccountId1    bank account to transfer money
   * @param bankAccountId2    bank account to which the money is transferred.
   * @param amount            an amount of money
   * @return Maybe
   */
  @Override
  public Maybe<BankAccountResponse> transfers(String bankAccountId1,
                                              String bankAccountId2,
                                              Double amount) {
    return this.bankAccountRepository
        .findById(bankAccountId1)
        .flatMap(bankAccount -> deposit(bankAccountId1, amount))
        .flatMap(
            bankAccount -> this.bankAccountRepository
                .findById(bankAccountId2)
                .flatMap(bankAccount1 -> withdraw(bankAccountId2, amount))
        );
  }

  /**
   * Summary of all products bank associate with a client identifier.
   *
   * @param clientId    a string of client identifier.
   * @return product bank by client
   */
  @Override
  public ProductBankByClient summary(String clientId) {
    ProductBankByClient productBankByClient = new ProductBankByClient();
    productBankByClient.setClientId(clientId);
    productBankByClient.setBankAccounts(this.bankAccountRepository
        .findByclientId(clientId)
        .toStream()
        .collect(Collectors.toList()));
    productBankByClient.setBankCredits(this.bankCreditsRepository
        .findByclientId(clientId)
        .toStream()
        .collect(Collectors.toList()));
    return productBankByClient;
  }

  /**
   * Report of all bank accounts associate with a number of debit card.
   *
   * @param numberDebitCard   a string of debit card.
   * @return Bank Account by debit card
   */
  @Override
  public BankAccountByDebitCard reportByDebitCard(String numberDebitCard) {
    BankAccountByDebitCard bankAccountByDebitCard =
        new BankAccountByDebitCard();
    bankAccountByDebitCard.setNumberDebitCard(numberDebitCard);
    bankAccountByDebitCard.setBankAccounts(this.bankAccountRepository
        .findBynumberDebitCard(numberDebitCard)
        .toStream()
        .collect(Collectors.toList()));
    return bankAccountByDebitCard;
  }

  /**
   * Report of movements of a bank account.
   *
   * @param bankAccountId   a string of bank account identifier.
   * @return Maybe
   */
  @Override
  public Maybe<BankAccountMovements> movements(String bankAccountId) {
    return this.bankAccountRepository.findById(bankAccountId)
        .map(BankAccountMovementMapper.INSTANCE::map);
  }

}
