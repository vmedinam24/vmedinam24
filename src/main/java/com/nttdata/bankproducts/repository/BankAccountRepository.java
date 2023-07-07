package com.nttdata.bankproducts.repository;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountResponse;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankAccountRepository
    extends RxJava3CrudRepository<BankAccount, String> {

  /**
   * Find Bank Accounts by client identifier.
   *
   * @param clientId    a string identifier.
   * @return Flux.
   */
  Flux<BankAccountResponse> findByclientId(String clientId);

  /**
   * Find Bank Accounts by debit card number.
   *
   * @param numberDebitCard   a string.
   * @return Flux
   */
  Flux<BankAccountResponse> findBynumberDebitCard(String numberDebitCard);
}
