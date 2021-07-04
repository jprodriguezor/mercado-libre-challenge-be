package io.mercadolibre.modules.coupons.usecase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import io.mercadolibre.crosscutting.domain.ItemCountVO;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;
import io.mercadolibre.crosscutting.stereotypes.UseCase;
import lombok.extern.slf4j.Slf4j;

/**
 * FavoriteProductsTracker.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Slf4j
@UseCase
public class FavoriteProductsTracker {

  private static final Map<String, ItemCountVO> TRACKER_CACHE = new ConcurrentHashMap<>();

  public synchronized void trackItems(final List<String> items) {

    log.info("use-case: tracking items {}", items);

    items
        .stream()
        .forEach(
            id -> {
              ItemCountVO itemTrack = null;

              if (TRACKER_CACHE.containsKey(id)) {
                itemTrack = TRACKER_CACHE.get(id);
              } else {
                itemTrack = ItemCountVO.of(id, 0);
              }

              itemTrack.track();
              TRACKER_CACHE.put(id, itemTrack);
            });
  }

  public Set<Entry<String, Integer>> getTopItems() throws ApplicationException {

    log.info("use-case: getting items top 5 {}");

    var items = TRACKER_CACHE.values().stream().collect(Collectors.toList());
    items.sort((c1, c2) -> c2.getAmount() - c1.getAmount());

    var topFiveMap = new HashMap<String, Integer>();
    items
        .stream()
        .limit(5)
        .forEach(
            t -> {
              topFiveMap.put(t.getItem(), t.getAmount());
            });

    return topFiveMap.entrySet();
  }
  
  public void clean() {
    TRACKER_CACHE.clear();
  }
}
