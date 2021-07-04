package io.mercadolibre.modules.coupons.gateway.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import io.mercadolibre.crosscutting.domain.ItemVO;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;
import io.mercadolibre.crosscutting.stereotypes.Gateway;
import io.mercadolibre.modules.coupons.gateway.ItemsRetrieverGateway;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ItemsRetrieverLocalStorageGateway.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@Slf4j
@Gateway
@AllArgsConstructor
public class ItemsRetrieverRestGateway implements ItemsRetrieverGateway {

  // local cache implementation
  private static final Map<String, ItemVO> ITEMS_CACHE = new ConcurrentHashMap<>();
  private RestTemplate rt;
  private Environment env;

  @Override
  public List<ItemVO> getItems(final String[] ids) throws ApplicationException {

    log.info("gateway - getting items for ids: {}", Arrays.toString(ids));

    return Arrays.stream(ids)
        .parallel()
        .map(
            id -> {
              if (ITEMS_CACHE.containsKey(id)) {
                return ITEMS_CACHE.get(id);
              }

              var optResult = getItemById(id);
              ItemVO item = null;

              if (optResult.isDefined()) {
                item = optResult.get();
                ITEMS_CACHE.put(item.getId(), item);
              }

              return item;
            })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private Option<ItemVO> getItemById(final String id) {
    return Try.of(
            () ->
                Option.of(
                    rt.getForObject(
                        env.getProperty("endpoints.mercado-libre.url") + id, ItemVO.class)))
        .onFailure(
            ex -> log.error("gateway - error while fetching item from mercado-libre api", ex))
        .getOrElse(Option.none());
  }
}
