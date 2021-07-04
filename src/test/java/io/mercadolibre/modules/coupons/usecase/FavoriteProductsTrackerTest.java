package io.mercadolibre.modules.coupons.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * FavoriteProductsTrackerTest.
 *
 * @author jenny.rodriguez
 * @version 1.0
 * @since 2021-07-03
 */
public class FavoriteProductsTrackerTest {

  private transient FavoriteProductsTracker tracker = new FavoriteProductsTracker();

  @ParameterizedTest
  @MethodSource("defineTestCasesData")
  public void getTopItems(List<String> items, int size, String response) {

    tracker.clean();
    tracker.trackItems(items);
    var r = tracker.getTopItems();
    assertEquals(size, r.size());
    assertEquals(response, r.toString());
  }

  private static Stream<Arguments> defineTestCasesData() {
    return Stream.of(
        Arguments.of(Arrays.asList("a", "b", "c", "d", "f"), 5, "[a=1, b=1, c=1, d=1, f=1]"),
        Arguments.of(
            Arrays.asList("a", "b", "c", "d", "f", "f", "a"), 5, "[a=2, b=1, c=1, d=1, f=2]"),
        Arguments.of(Arrays.asList("a", "a", "a", "d", "f"), 3, "[a=3, d=1, f=1]"));
  }
}
