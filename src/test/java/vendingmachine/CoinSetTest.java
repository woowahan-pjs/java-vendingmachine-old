package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoinSetTest {
    @Test
    void 동전을_생성한다() {
        CoinSet coin = CoinSet._100_COIN;
        assertThat(coin.value).isEqualTo(100);
    }

    @ParameterizedTest
    @MethodSource("coinSet")
    void 동전은_500원_100원_50원_10원이_있다(int value, CoinSet coin) {
        assertThat(coin.value).isEqualTo(value);
    }

    private static List<Arguments> coinSet() {
        return Arrays.asList(
                Arguments.of(500, CoinSet._500_COIN),
                Arguments.of(100, CoinSet._100_COIN),
                Arguments.of(50, CoinSet._50_COIN),
                Arguments.of(10, CoinSet._10_COIN)
        );
    }

    @Test
    void 동전을_반환한다() {
        CoinSet coin = CoinSet.valueOf(10);
        assertThat(coin).isEqualTo(CoinSet._10_COIN);
    }

    @DisplayName("전체 동전은 금액이 큰 순서대로 반환된다.")
    @Test
    void _1() {
        assertThat(CoinSet.highestOrder()).containsExactly(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._50_COIN, CoinSet._10_COIN);
    }
}
