package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.response.ProductBankByClient;
import com.nttdata.bankproducts.service.BankAccountService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import static com.nttdata.bankproducts.util.TestUtil.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {

  @InjectMocks
  BankAccountController bankAccountController;

  @Mock
  BankAccountService bankAccountService;

  @Test
  void getAll(){
    when(bankAccountService.getAll()).thenReturn(listBankAccountr());
    Flowable<BankAccountResponse> bankAccountResponse = (Flowable<BankAccountResponse>) bankAccountController.getAll();

    Assertions.assertEquals(2,bankAccountResponse.blockingStream().collect(Collectors.toList()).size());
  }

  @Test
  void create(){
    when(bankAccountService.create(Mockito.any(BankAccount.class))).thenReturn(createBankAccount2());
    Maybe<BankAccountResponse> maybe = (Maybe<BankAccountResponse>)
        bankAccountController.create(createBankAccount());

    Assertions.assertEquals("458796254", maybe.blockingGet().getAccountNumberId());
  }

  @Test
  void read(){
    when(bankAccountService.read(Mockito.anyString())).thenReturn(createBankAccount2());
    Maybe<BankAccountResponse> maybe = (Maybe<BankAccountResponse>)
        bankAccountController.read(createBankAccount().getAccountNumberId());

    Assertions.assertEquals("458796254", maybe.blockingGet().getAccountNumberId());
  }

  @Test
  void deposit(){
    when(bankAccountService.deposit(Mockito.anyString(),Mockito.anyDouble())).thenReturn(createBankAccount2());
    Maybe<BankAccountResponse> maybe = (Maybe<BankAccountResponse>)
        bankAccountController.deposit(createBankAccount().getAccountNumberId(), 500.0);

    Assertions.assertEquals(500.0, maybe.blockingGet().getTotalAmount());
  }

  @Test
  void withdraw(){
    when(bankAccountService.withdraw(Mockito.anyString(),Mockito.anyDouble())).thenReturn(createBankAccount2());
    Maybe<BankAccountResponse> maybe = (Maybe<BankAccountResponse>)
        bankAccountController.withdraw(createBankAccount().getAccountNumberId(), 500.0);

    Assertions.assertEquals(500.0, maybe.blockingGet().getTotalAmount());
  }

  @Test
  void transfers(){
    when(bankAccountService.transfers(Mockito.anyString(),Mockito.anyString(),Mockito.anyDouble())).thenReturn(createBankAccount2());
    Maybe<BankAccountResponse> maybe = (Maybe<BankAccountResponse>)
        bankAccountController.transfers("458785216","456321890",500.0);

    Assertions.assertEquals(500.0, maybe.blockingGet().getTotalAmount());
  }

  @Test
  void summary(){
    ProductBankByClient product = new ProductBankByClient("72221337", List.of(createBankAccountOther2()), List.of(createBankCredits()));
    when(bankAccountService.summary(Mockito.anyString())).thenReturn(product);
    ProductBankByClient productBankByClient = bankAccountController.summary("72221337");

    Assertions.assertEquals("72221337", productBankByClient.getClientId());
  }

  @Test
  void reportByDebitCard(){
    BankAccountByDebitCard debitCard = new BankAccountByDebitCard("458967377", List.of(createBankAccountOther2()));
    when(bankAccountService.reportByDebitCard(Mockito.anyString())).thenReturn(debitCard);
    BankAccountByDebitCard accountByDebitCard = bankAccountController.reportByDebitCard("458967377");

    Assertions.assertEquals(1,accountByDebitCard.getBankAccounts().size());
  }

  @Test
  void movements(){
    Maybe<BankAccountMovements> bankAccount = Maybe.just(BankAccountMovements
        .builder()
        .accountNumberId("45879623635")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build());
    when(bankAccountService.movements(Mockito.anyString())).thenReturn(bankAccount);
    Maybe<BankAccountMovements> movementsMaybe = bankAccountController.movements("45879623635");

    Assertions.assertEquals("45879623635",movementsMaybe.blockingGet().getAccountNumberId());
  }
}
