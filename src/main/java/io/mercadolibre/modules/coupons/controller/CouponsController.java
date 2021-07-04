package io.mercadolibre.modules.coupons.controller;

import java.util.Map.Entry;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.mercadolibre.crosscutting.domain.CouponMaximizerRequestVO;
import io.mercadolibre.crosscutting.domain.CouponMaximizerResponseVO;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;
import io.mercadolibre.modules.coupons.usecase.CouponsAgent;
import io.mercadolibre.modules.coupons.usecase.FavoriteProductsTracker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CouponController.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/coupon")
@AllArgsConstructor
public class CouponsController {

  private CouponsAgent agent;
  private FavoriteProductsTracker tracker;

  @PostMapping
  public ResponseEntity<CouponMaximizerResponseVO> maximizeExpense(
      @RequestBody final CouponMaximizerRequestVO request) throws ApplicationException {

    log.info("controller: maximizing request for {}", request);

    return ResponseEntity.ok(agent.maximizeExpense(request));
  }

  @GetMapping("/stats")
  public ResponseEntity<Set<Entry<String, Integer>>> getTopItems() throws ApplicationException {

    log.info("controller: retrieving top 5 items {}");

    return ResponseEntity.ok(tracker.getTopItems());
  }
}
