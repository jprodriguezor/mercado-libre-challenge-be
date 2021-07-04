package io.mercadolibre.crosscutting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ItemCountVO.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ItemCountVO {

  private String item;
  private int amount;

  public void track() {
    amount++;
  }
}
