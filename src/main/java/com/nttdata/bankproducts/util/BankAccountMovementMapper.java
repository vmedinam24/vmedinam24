package com.nttdata.bankproducts.util;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountMovements;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMovementMapper {

  BankAccountMovementMapper INSTANCE = Mappers.getMapper(
      BankAccountMovementMapper.class);

  /**
   * Maps a data type bank account movements to bank account.
   *
   * @param bankAccountMovements    an object to convert
   * @return bank account
   */
  default BankAccount map(BankAccountMovements bankAccountMovements) {
    return BankAccount.builder()
        .accountNumberId(bankAccountMovements.getAccountNumberId())
        .clientId(bankAccountMovements.getClientId())
        .numberDebitCard(bankAccountMovements.getNumberDebitCard())
        .nameTypeAccount(bankAccountMovements.getNameTypeAccount())
        .totalAmount(bankAccountMovements.getTotalAmount())
        .transfersList(bankAccountMovements.getTransfersList())
        .build();
  }

  /**
   * Maps a data type bank account to bank account movements.
   *
   * @param bankAccount   an object to convert
   * @return  bank account movements
   */
  default BankAccountMovements map(BankAccount bankAccount) {
    return BankAccountMovements.builder()
        .accountNumberId(bankAccount.getAccountNumberId())
        .clientId(bankAccount.getClientId())
        .numberDebitCard(bankAccount.getNumberDebitCard())
        .nameTypeAccount(bankAccount.getNameTypeAccount())
        .totalAmount(bankAccount.getTotalAmount())
        .transfersList(bankAccount.getTransfersList())
        .build();
  }

}
