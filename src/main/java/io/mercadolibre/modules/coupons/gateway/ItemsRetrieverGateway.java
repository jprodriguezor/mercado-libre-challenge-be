package io.mercadolibre.modules.coupons.gateway;

import java.util.List;
import io.mercadolibre.crosscutting.domain.ItemVO;
import io.mercadolibre.crosscutting.exceptions.ApplicationException;

/**
 * ItemsRetrieverGateway.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
public interface ItemsRetrieverGateway {

  List<ItemVO> getItems(final String[] ids) throws ApplicationException;
}
