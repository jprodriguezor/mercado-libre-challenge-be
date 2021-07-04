package io.mercadolibre.crosscutting.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CouponMaximizerResponseVO.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class CouponMaximizerResponseVO {

  @JsonProperty("item_ids")
  private List<String> items;

  private Double total;

  @Override
  public String toString() {
    return "(items=" + items + ", total=" + total + ")";
  }
}
