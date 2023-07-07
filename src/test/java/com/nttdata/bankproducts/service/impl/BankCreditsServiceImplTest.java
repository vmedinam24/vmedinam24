package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.BankCredits;
import com.nttdata.bankproducts.repository.BankCreditsRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.nttdata.bankproducts.util.TestUtil.createBankCredistOther;
import static com.nttdata.bankproducts.util.TestUtil.createBankCredits;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCreditsServiceImplTest {

  @InjectMocks
  BankCreditsServiceImpl bankCreditsService;

  @Mock
  BankCreditsRepository bankCreditsRepository;

  @Test
  void read(){
    when(bankCreditsRepository.findById(Mockito.anyString())).thenReturn(createBankCredistOther());
    Maybe<BankCredits> bankCreditsMaybe = bankCreditsService.read(createBankCredits().getCreditsId());

    Assertions.assertEquals("54879625",bankCreditsMaybe.blockingGet().getCreditsId());
  }
}
