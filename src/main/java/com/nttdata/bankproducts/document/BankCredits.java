package com.nttdata.bankproducts.document;

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
@Document(collection = "credits")
public class BankCredits {

  /**
   * Credits identifier.
   */
  @Id
  private String creditsId;
  /**
   * Identifier of each client.
   */
  private String clientId;
  /**
   * Type credits (Personal, Business, Credit Card).
   */
  private String nameTypeCredits;
  /**
   * Amount of client.
   */
  private Double creditsAmount;
  /**
   * Total Amount of credit.
   */
  private Double totalAmount;
}
