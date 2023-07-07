package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankCredits;
import com.nttdata.bankproducts.service.BankCreditsService;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.nttdata.bankproducts.util.TestUtil.createBankCredistOther;
import static com.nttdata.bankproducts.util.TestUtil.createBankCredits;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCreditsControllerTest {

  @InjectMocks
  BankCreditsController bankCreditsController;

  @Mock
  BankCreditsService bankCreditsService;

  @Test
  void create(){
    when(bankCreditsService.create(Mockito.any(BankCredits.class))).thenReturn(createBankCredistOther());
    Maybe<BankCredits> maybe = bankCreditsController.create(createBankCredits());

    Assertions.assertEquals("54879625", maybe.blockingGet().getCreditsId());
  }

  @Test
  void read(){
    when(bankCreditsService.read(Mockito.anyString())).thenReturn(createBankCredistOther());
    Maybe<BankCredits> maybe = bankCreditsController.read(createBankCredits().getCreditsId());

    Assertions.assertEquals("54879625", maybe.blockingGet().getCreditsId());
  }

  @Test
  void productPayment(){
    when(bankCreditsService.productPayment(Mockito.anyString(),Mockito.anyDouble())).thenReturn(createBankCredistOther());
    Maybe<BankCredits> maybe = bankCreditsController.productPayment("54879625",700.5);

    Assertions.assertEquals(700.5, maybe.blockingGet().getCreditsAmount());
  }

  @Test
  void productCharge(){
    when(bankCreditsService.productCharge(Mockito.anyString(),Mockito.anyDouble())).thenReturn(createBankCredistOther());
    Maybe<BankCredits> maybe = bankCreditsController.productCharge("54879625",700.5);

    Assertions.assertEquals(700.5, maybe.blockingGet().getCreditsAmount());
  }
}
