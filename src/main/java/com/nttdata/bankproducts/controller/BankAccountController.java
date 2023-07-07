package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.ProductBankByClient;
import com.nttdata.bankproducts.service.BankAccountService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/account")
public class BankAccountController {

  /**
   * Bank account service.
   */
  @Autowired
  private BankAccountService bankAccountService;

  /**
   * Returns all the bank accounts.
   *
   * @return  Flowable
   */
  @RequestMapping("/account")
  public Flowable<?> getAll() {
    return bankAccountService.getAll();
  }

  /**
   * Create a new bank account and check if the bank account exist.
   *
   * @param bankAccount   an object of bank account.
   * @return  Maybe
   */
  @PostMapping("/account")
  public Maybe<?> create(@RequestBody BankAccount bankAccount) {
    return bankAccountService.create(bankAccount);
  }

  /**
   * Displays the required bank account based on its identifier.
   *
   * @param bankAccountId   a string of bank account identifier
   * @return Maybe
   */
  @GetMapping("/account/get/{bankAccountId}")
  public Maybe<?> read(@PathVariable String bankAccountId) {
    return this.bankAccountService.read(bankAccountId);
  }

  /*@GetMapping("/account/get/client/{clientId}")
    public Flowable<BankAccount> readByClientId(@PathVariable String clientId) {
        return this.bankAccountService.readByClientId(clientId);
   }*/

  /**
   * Type of transfer that deposit an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @PutMapping("/account/deposit")
  public Maybe<?> deposit(@RequestParam String bankAccountId,
                          @RequestParam Double amount) {
    return this.bankAccountService.deposit(bankAccountId, amount);
  }

  /**
   * Type of transfer that withdraw an amount of money in a bank account.
   *
   * @param bankAccountId   a string of bank account identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @PutMapping("/account/withdraw")
  public Maybe<?> withdraw(@RequestParam String bankAccountId,
                           @RequestParam Double amount) {
    return this.bankAccountService.withdraw(bankAccountId, amount);
  }

  /**
   * Transfer an amount of money of bank account to other bank account.
   *
   * @param accountToTransfer    bank account to transfer money
   * @param account    bank account to which the money is transferred.
   * @param amount            an amount of money
   * @return Maybe
   */
  @PutMapping("/account/transfer")
  public Maybe<?> transfers(@RequestParam String accountToTransfer,
                            @RequestParam String account,
                            @RequestParam Double amount) {
    return this.bankAccountService
        .transfers(accountToTransfer, account, amount);
  }

  /**
   * Summary of all products bank associate with a client identifier.
   *
   * @param clientId    a string of client identifier.
   * @return product bank by client
   */
  @GetMapping("/summary")
  public ProductBankByClient summary(@RequestParam String clientId) {
    return this.bankAccountService.summary(clientId);
  }

  /**
   * Report of all bank accounts associate with a number of debit card.
   *
   * @param numberDebitCard   a string of debit card.
   * @return Bank Account by debit card
   */
  @GetMapping("/debitcard/report")
  public BankAccountByDebitCard reportByDebitCard(
      @RequestParam String numberDebitCard) {
    return this.bankAccountService.reportByDebitCard(numberDebitCard);
  }

  /**
   * Report of movements of a bank account.
   *
   * @param bankAccountId   a string of bank account identifier.
   * @return Maybe
   */
  @GetMapping("/movements")
  public Maybe<BankAccountMovements> movements(
      @RequestParam String bankAccountId) {
    return this.bankAccountService.movements(bankAccountId);
  }
}
