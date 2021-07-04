package io.mercadolibre.crosscutting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CouponMaximizerRequestVO.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CouponMaximizerRequestVO {

  @JsonProperty("item_ids")
  private String[] items;

  private double amount;
}
