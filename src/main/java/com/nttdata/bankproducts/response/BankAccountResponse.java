package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountResponse {
  /**
   * Account number.
   */
  private String accountNumberId;
  /**
   * Identifier of each client.
   */
  private String clientId;
  /**
   * Debit Card Number.
   */
  private String numberDebitCard;
  /**
   * Type account (Savings account, Current account, Fixed Term Account).
   */
  private String nameTypeAccount;
  /**
   * Total amount in account.
   */
  private Double totalAmount;
}
