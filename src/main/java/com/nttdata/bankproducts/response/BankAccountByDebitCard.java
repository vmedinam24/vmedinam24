package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountByDebitCard {
  /**
   * Number of Debit Card.
   */
  private String numberDebitCard;
  /**
   * List of Bank Accounts.
   */
  private List<BankAccountResponse> bankAccounts;
}
