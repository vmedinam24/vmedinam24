package com.nttdata.bankproducts.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
  /**
   * annotation identifier.
   * Client identifier.
   */
  @Id
  private String clientId;
  /**
   * Client name.
   */
  private String name;
  /**
   * Client last name.
   */
  private String lastName;
  /**
   * Client phone.
   */
  private Integer phone;
  /**
   * Client personal email.
   */
  private String email;
  /**
   * Client type (personal, business).
   */
  private String type;
}
