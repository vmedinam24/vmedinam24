package com.nttdata.bankproducts.util;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.BankCredits;
import com.nttdata.bankproducts.response.BankAccountResponse;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import net.sf.saxon.trans.SymbolicName;

public class TestUtil {

  public static Flowable<BankAccountResponse> listBankAccountr(){
    BankAccountResponse bankAccount = BankAccountResponse
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    Flowable<BankAccountResponse> responseFlowable = Flowable.just(bankAccount,bankAccount);
    return responseFlowable;
  }
  public static Flowable<BankAccount> listBankAccount(){
    BankAccount bankAccount = BankAccount
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    Flowable<BankAccount> responseFlowable = Flowable.just(bankAccount,bankAccount);
    return responseFlowable;
  }

  public static BankAccount createBankAccount(){
    BankAccount bankAccount = BankAccount
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    return bankAccount;
  }

  public static BankAccountResponse createBankAccountOther2(){
    BankAccountResponse bankAccount = BankAccountResponse
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    return bankAccount;
  }

  public static Maybe<BankAccountResponse> createBankAccount2(){
    BankAccountResponse bankAccount = BankAccountResponse
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    Maybe<BankAccountResponse> bankAccountMaybe = Maybe.just(bankAccount);
    return bankAccountMaybe;
  }

  public static Maybe<BankAccount> createBankAccountOther(){
    BankAccount bankAccount = BankAccount
        .builder()
        .accountNumberId("458796254")
        .clientId("7221334")
        .numberDebitCard("48759632")
        .nameTypeAccount("ahorros")
        .totalAmount(500.0)
        .build();

    Maybe<BankAccount> bankAccountMaybe = Maybe.just(bankAccount);
    return bankAccountMaybe;
  }

  public static BankCredits createBankCredits(){
    BankCredits bankCredits = BankCredits
        .builder()
        .creditsId("54879625")
        .clientId("7221337")
        .nameTypeCredits("personal")
        .creditsAmount(700.5)
        .totalAmount(700.5)
        .build();

    return bankCredits;
  }

  public static Maybe<BankCredits> createBankCredistOther(){
    BankCredits bankCredits = BankCredits
        .builder()
        .creditsId("54879625")
        .clientId("7221337")
        .nameTypeCredits("personal")
        .creditsAmount(700.5)
        .totalAmount(700.5)
        .build();

    Maybe<BankCredits> bankCreditsMaybe = Maybe.just(bankCredits);
    return bankCreditsMaybe;
  }

}
