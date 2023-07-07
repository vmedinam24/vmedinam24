package com.nttdata.bankproducts.service;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.BankCredits;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface BankCreditsService {

  /**
   * Create a new bank credits and check if the bank credits exist.
   *
   * @param bankCredits   an object of bank credits
   * @return Maybe
   */
  Maybe<BankCredits> create(BankCredits bankCredits);

  /**
   * Displays the required bank credits based on its identifier.
   *
   * @param bankCreditsId    a string of bank credits identifier
   * @return Maybe
   */
  Maybe<BankCredits> read(String bankCreditsId);

  /**
   * Payment of products credits of a client.
   *
   * @param bankCreditsId   a string of bank credits identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  Maybe<BankCredits> productPayment(String bankCreditsId, Double amount);

  /**
   *  Charge of products with a credit card of a client.
   *
   * @param bankCreditsId   a string of bank credits identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  Maybe<BankCredits> productCharge(String bankCreditsId, Double amount);
  //Flowable<BankCredits> reportByCredits(String clientId);
}
