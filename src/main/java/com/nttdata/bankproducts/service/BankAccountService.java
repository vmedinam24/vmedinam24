package com.nttdata.bankproducts.service;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.response.ProductBankByClient;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface BankAccountService {

  /**
   * Returns all the bank accounts.
   *
   * @return  Flowable
   */
  Flowable<BankAccountResponse> getAll();

  /**
   * Create a new bank account and check if the bank account exist.
   *
   * @param bankAccount   an object of bank account.
   * @return  Maybe
   */
  Maybe<BankAccountResponse> create(BankAccount bankAccount);

  /**
   * Displays the required bank account based on its identifier.
   *
   * @param bankAccountId   a string of bank account identifier
   * @return Maybe
   */
  Maybe<BankAccountResponse> read(String bankAccountId);

  /**
   * Type of transfer that deposit an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  Maybe<BankAccountResponse> deposit(String bankAccountId, Double amount);

  /**
   * Type of transfer that withdraw an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  Maybe<BankAccountResponse> withdraw(String bankAccountId, Double amount);
  //Flowable<BankAccount> readByClientId(String clientId);

  /**
   * Transfer an amount of money of bank account to other bank account.
   *
   * @param bankAccountId1    bank account to transfer money
   * @param bankAccountId2    bank account to which the money is transferred.
   * @param amount            an amount of money
   * @return Maybe
   */
  Maybe<BankAccountResponse> transfers(String bankAccountId1, String bankAccountId2, Double amount);

  /**
   * Summary of all products bank associate with a client identifier.
   *
   * @param clientId    a string of client identifier.
   * @return product bank by client
   */
  ProductBankByClient summary(String clientId);

  /**
   * Report of all bank accounts associate with a number of debit card.
   *
   * @param numberDebitCard   a string of debit card.
   * @return Bank Account by debit card
   */
  BankAccountByDebitCard reportByDebitCard(String numberDebitCard);

  /**
   * Report of movements of a bank account.
   *
   * @param bankAccountId   a string of bank account identifier.
   * @return Maybe
   */
  Maybe<BankAccountMovements> movements(String bankAccountId);
}
