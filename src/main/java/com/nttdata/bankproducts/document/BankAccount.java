package com.nttdata.bankproducts.document;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class BankAccount {

  /**
   * Account number.
   */
  @Id
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
  /**
   * Number of transfers (deposit, withdraw).
   */
  private Integer totalTransfers = 0;
  /**
   * Number of commissions after 20 transfers.
   */
  private Integer countCommissions = 0;
  /**
   * List of transfers with Date a type of transfers by client.
   */
  private List<Transfers> transfersList = new ArrayList<>();
}
