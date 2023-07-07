package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankCredits;
import com.nttdata.bankproducts.feign.ClientFeign;
import com.nttdata.bankproducts.repository.BankCreditsRepository;
import com.nttdata.bankproducts.service.BankCreditsService;
import io.reactivex.rxjava3.core.Maybe;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCreditsServiceImpl implements BankCreditsService {

  /**
   * Bank Credits Repository.
   */
  @Autowired
  private BankCreditsRepository bankCreditsRepository;

  /**
   * Connection client.
   */
  @Autowired
  private ClientFeign clientFeign;

  /**
   * Create a new bank credits and check if the bank credits exist.
   *
   * @param bankCredits   an object of bank credits
   * @return Maybe
   */
  @Override
  public Maybe<BankCredits> create(BankCredits bankCredits) {
    return this.bankCreditsRepository.findById(bankCredits.getCreditsId())
        .flatMap(
            bankCredits1 -> Maybe.error(
                new Error("BankCredits exist")),
            (error) -> Maybe.error(error),
            () -> Maybe.just(bankCredits)
        )
        .flatMapSingle(bankCreditsRepository::save);
  }

  /**
   * Displays the required bank credits based on its identifier.
   *
   * @param bankCreditsId    a string of bank credits identifier
   * @return Maybe
   */
  @Override
  public Maybe<BankCredits> read(String bankCreditsId) {
    return bankCreditsRepository.findById(bankCreditsId);
  }

  /**
   * Payment of products credits of a client.
   *
   * @param bankCreditsId   a string of bank credits identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @Override
  public Maybe<BankCredits> productPayment(String bankCreditsId,
                                           Double amount) {
    return this.bankCreditsRepository
        .findById(bankCreditsId)
        .switchIfEmpty(Maybe.error(new NotFoundException(
            "bankCreditsId doesn't exist: " + bankCreditsId
        )))
        .flatMap(bankCredits -> {
          if (bankCredits.getTotalAmount() >= bankCredits.getCreditsAmount()) {
            bankCredits.setCreditsAmount(
                bankCredits.getCreditsAmount() + amount);
            return Maybe.just(bankCredits);
          } else {
            return Maybe.error(new Error("No Payments"));
          }
        })
        .flatMapSingle(this.bankCreditsRepository::save);
  }

  /**
   *  Charge of products with a credit card of a client.
   *
   * @param bankCreditsId   a string of bank credits identifier
   * @param amount          an amount of money
   * @return Maybe
   */
  @Override
  public Maybe<BankCredits> productCharge(String bankCreditsId,
                                          Double amount) {
    return this.bankCreditsRepository.findById(bankCreditsId)
        .switchIfEmpty(Maybe.error(new NotFoundException(
            "bankCreditsId doesn't exist: " + bankCreditsId
        )))
        .flatMap(bankCredits -> {
          if (bankCredits.getNameTypeCredits()
              .equalsIgnoreCase("tarjeta de credito")) {
            if (bankCredits.getCreditsAmount() > amount) {
              bankCredits.setCreditsAmount(
                  bankCredits.getCreditsAmount() - amount);
              return Maybe.just(bankCredits);
            } else {
              return Maybe.error(new Error("No credit"));
            }
          } else {
            return Maybe.error(new Error("Doesn't have credit card"));
          }
        })
        .flatMapSingle(this.bankCreditsRepository::save);
  }

  /*@Override
    public Flowable<BankCredits> reportByCredits(String clientId) {
        return this.bankCreditsRepository.findByclientId(clientId);
    }*/
}
