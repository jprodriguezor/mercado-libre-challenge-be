package io.mercadolibre.crosscutting.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * CandidateCaseVo.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@ToString
@Getter
@AllArgsConstructor(staticName = "of")
public class CandidateCaseVo {

  private List<ItemVO> items;
  private Double total;
}
