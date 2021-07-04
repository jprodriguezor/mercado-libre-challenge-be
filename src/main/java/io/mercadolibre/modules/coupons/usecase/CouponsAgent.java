package io.mercadolibre.modules.coupons.usecase;

import java.util.ArrayList;
import io.mercadolibre.crosscutting.domain.CandidateCaseVo;
import io.mercadolibre.crosscutting.domain.CouponMaximizerRequestVO;
import io.mercadolibre.crosscutting.domain.CouponMaximizerResponseVO;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;
import io.mercadolibre.crosscutting.stereotypes.UseCase;
import io.mercadolibre.modules.coupons.gateway.ItemsRetrieverGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CouponsAgent.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Slf4j
@UseCase
@AllArgsConstructor
public class CouponsAgent {

  private ItemsRetrieverGateway itemsGateway;
  private CouponsMaximizerOperations maximizerOpr;
  private FavoriteProductsTracker tracker;

  public CouponMaximizerResponseVO maximizeExpense(final CouponMaximizerRequestVO request)
      throws ApplicationException {

    log.info("use-case: maximizing request for {}", request);

    var items = request.getItems();
    var loadedItems = itemsGateway.getItems(items);
    var candidates = new ArrayList<CandidateCaseVo>();

    for (var i = 1; i <= loadedItems.size(); ++i) {
      maximizerOpr.evaluateCombinations(candidates, loadedItems, request.getAmount(), i);
    }

    var optimalCase = maximizerOpr.determineOptimalCase(candidates);
    tracker.trackItems(optimalCase.getItems());

    return optimalCase;
  }
}
