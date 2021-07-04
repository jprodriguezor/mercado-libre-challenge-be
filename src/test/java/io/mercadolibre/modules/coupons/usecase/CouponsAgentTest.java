package io.mercadolibre.modules.coupons.usecase;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import io.mercadolibre.crosscutting.domain.CouponMaximizerRequestVO;
import io.mercadolibre.crosscutting.domain.ItemVO;
import io.mercadolibre.crosscutting.exceptions.BusinessException;
import io.mercadolibre.modules.coupons.gateway.ItemsRetrieverGateway;

/**
 * CouponsAgentTest.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
@ExtendWith(MockitoExtension.class)
public class CouponsAgentTest {

  @Mock private transient ItemsRetrieverGateway itemsGateway;
  @Spy private transient CouponsMaximizerOperations maximizerOpr;
  @Spy private FavoriteProductsTracker tracker;
  @InjectMocks private transient CouponsAgent agent;

  @ParameterizedTest
  @MethodSource("defineTestCasesData")
  public void testMaximizeExpense(
      final List<ItemVO> items, final double amount, final String expectedOutput) {

    Mockito.when(itemsGateway.getItems(Mockito.any())).thenReturn(items);
    var result = agent.maximizeExpense(CouponMaximizerRequestVO.of(new String[] {}, amount));
    assertEquals(expectedOutput, result.toString());
  }

  @Test
  public void testMaximizeExpenseWithNoSolutionFound() {

    Mockito.when(itemsGateway.getItems(Mockito.any())).thenReturn(case1());

    assertThatExceptionOfType(BusinessException.class)
        .isThrownBy(
            () -> {
              agent.maximizeExpense(CouponMaximizerRequestVO.of(new String[] {}, 60d));
            });
  }

  private static Stream<Arguments> defineTestCasesData() {
    return Stream.of(
        Arguments.of(case1(), 500d, "(items=[MLA1, MLA2, MLA4, MLA5], total=480.0)"),
        Arguments.of(case1(), 300d, "(items=[MLA2, MLA5], total=300.0)"),
        Arguments.of(case1(), 600d, "(items=[MLA1, MLA2, MLA3], total=570.0)"),
        Arguments.of(case1(), 350d, "(items=[MLA3, MLA5], total=350.0)"));
  }

  private static List<ItemVO> case1() {
    return Arrays.asList(
        ItemVO.of("MLA1", 100d),
        ItemVO.of("MLA2", 210d),
        ItemVO.of("MLA3", 260d),
        ItemVO.of("MLA4", 80d),
        ItemVO.of("MLA5", 90d));
  }
}
