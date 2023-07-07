package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.repository.BankAccountRepository;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.util.BankAccountMapper;
import com.nttdata.bankproducts.util.BankAccountMovementMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static com.nttdata.bankproducts.util.TestUtil.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

  @InjectMocks
  BankAccountServiceImpl bankAccountService;

  @Mock
  BankAccountRepository bankAccountRepository;

  @Test
  void getAll(){
    when(bankAccountRepository.findAll()).thenReturn(listBankAccount());
    Flowable<BankAccountResponse> bankAccountResponseFlowable = bankAccountService.getAll();

    Assertions.assertEquals(2, bankAccountResponseFlowable.blockingStream().collect(Collectors.toList()).size());
  }

  @Test
  void read(){
    when(bankAccountRepository.findById(Mockito.anyString())).thenReturn(createBankAccountOther());
    Maybe<BankAccountResponse> bankAccountResponseMaybe = bankAccountService.read(createBankAccount().getAccountNumberId());

    Assertions.assertEquals("458796254", bankAccountResponseMaybe.blockingGet().getAccountNumberId());
  }
}
