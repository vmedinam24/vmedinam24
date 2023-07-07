package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankproducts.document.BankCredits;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBankByClient {
  /**
   * Identifier of each client.
   */
  private String clientId;
  /**
   * List of Bank Account by client identifier.
   */
  private List<BankAccountResponse> bankAccounts;
  /**
   * List of Bank Credits by client identifier.
   */
  private List<BankCredits> bankCredits;

}
