package io.mercadolibre.crosscutting.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ItemVO.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ItemVO {

  private String id;
  private double price;

  @Override
  public String toString() {
    return "(id=" + id + ", price=" + price + ")";
  }
}
