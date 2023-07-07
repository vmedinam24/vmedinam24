package com.nttdata.bankproducts.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transfers {

  /**
   * Type of transfer (deposit, withdraw).
   */
  private String type;
  /**
   * Date and hour of transfer.
   */
  private String date;
  /**
   * Amount of transfer.
   */
  private Double amount;

}
