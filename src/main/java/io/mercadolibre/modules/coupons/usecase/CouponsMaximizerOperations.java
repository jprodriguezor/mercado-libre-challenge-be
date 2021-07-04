package io.mercadolibre.modules.coupons.usecase;

import java.util.List;
import java.util.stream.Collectors;
import org.paukov.combinatorics3.Generator;
import io.mercadolibre.crosscutting.domain.CandidateCaseVo;
import io.mercadolibre.crosscutting.domain.CouponMaximizerResponseVO;
import io.mercadolibre.crosscutting.domain.ItemVO;
import io.mercadolibre.crosscutting.domain.constants.ErrorCode;
import io.mercadolibre.crosscutting.exceptions.ExceptionBuilder;
import io.mercadolibre.crosscutting.stereotypes.Interactor;

/**
 * CouponsMaximizerOperations.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Interactor
public class CouponsMaximizerOperations {

  public CandidateCaseVo createCandidate(final List<ItemVO> items) {

    var total = items.stream().map(i -> i.getPrice()).reduce(0d, (a, b) -> a + b);
    return CandidateCaseVo.of(items, total);
  }

  public void evaluateCombinations(
      final List<CandidateCaseVo> candidates,
      final List<ItemVO> loadedItems,
      final Double amount,
      final int size) {

    Generator.combination(loadedItems)
        .simple(size)
        .stream()
        .map(combination -> createCandidate(combination))
        .filter(c -> c.getTotal() <= amount)
        .forEach(c -> candidates.add(c));
  }

  public CouponMaximizerResponseVO determineOptimalCase(final List<CandidateCaseVo> candidates) {

    candidates.sort((c1, c2) -> c2.getTotal().intValue() - c1.getTotal().intValue());

    if (!candidates.isEmpty()) {
      var expectedCase = candidates.get(0);
      var expectedItems =
          expectedCase.getItems().stream().map(c -> c.getId()).collect(Collectors.toList());
      return CouponMaximizerResponseVO.of(expectedItems, expectedCase.getTotal());
    }

    throw ExceptionBuilder.builder()
        .withCode(ErrorCode.NO_SOLUTION_FOUND)
        .withMessage("no products found")
        .buildBusinessException();
  }
}
