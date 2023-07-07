package com.nttdata.bankproducts.repository;

import com.nttdata.bankproducts.document.BankCredits;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankCreditsRepository
    extends RxJava3CrudRepository<BankCredits, String> {

  /**
   * Find Bank Credits by client identifier.
   *
   * @param clientId    a string identifier.
   * @return Flux.
   */
  Flux<BankCredits> findByclientId(String clientId);
}
