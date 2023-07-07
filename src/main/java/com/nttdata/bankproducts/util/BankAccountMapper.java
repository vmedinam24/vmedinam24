package com.nttdata.bankproducts.util;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {
  BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

  /**
   * Maps a data type bank account response to bank account.
   *
   * @param bankAccountResponse   an object to convert
   * @return  bank account
   */
  default BankAccount map(BankAccountResponse bankAccountResponse) {
    return BankAccount.builder()
        .accountNumberId(bankAccountResponse.getAccountNumberId())
        .clientId(bankAccountResponse.getClientId())
        .numberDebitCard(bankAccountResponse.getNumberDebitCard())
        .nameTypeAccount(bankAccountResponse.getNameTypeAccount())
        .totalAmount(bankAccountResponse.getTotalAmount())
        .build();
  }

  /**
   * Maps a data type bank account to bank account response.
   *
   * @param bankAccount   an object to convert
   * @return  bank account response
   */
  default BankAccountResponse map(BankAccount bankAccount) {
    return BankAccountResponse.builder()
        .accountNumberId(bankAccount.getAccountNumberId())
        .clientId(bankAccount.getClientId())
        .numberDebitCard(bankAccount.getNumberDebitCard())
        .nameTypeAccount(bankAccount.getNameTypeAccount())
        .totalAmount(bankAccount.getTotalAmount())
        .build();
  }
}
