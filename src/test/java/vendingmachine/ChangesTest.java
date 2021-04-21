package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Red(질문) -> Green(응답) -> Refactor(정제)
 */
class ChangesTest {
    @ParameterizedTest
    @CsvSource({"10,_10_COIN", "50,_50_COIN", "100,_100_COIN", "500,_500_COIN"})
    void n원이_남아_있다면_n원짜리_동전_1개를_돌려_준다(int amount, CoinSet coin) {
        Changes changes = new Changes(amount);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(coin);
    }

    @Test
    void _1000원이_남아_있다면_500원_동전_2개를_돌려준다() {
        Changes changes = new Changes(1000);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._500_COIN);
    }

    @Test
    void _20원이_남아_있다면_10원_동전_2개를_돌려준다() {
        Changes changes = new Changes(20);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._10_COIN, CoinSet._10_COIN);
    }

    @Test
    void _600원이_남아_있다면_500원_동전_1개와_100원_동전_1를_돌려준다() {
        Changes changes = new Changes(600);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN);
    }

    @Test
    void _650원이_남아_있다면_500원_동전_1개와_100원_동전_1와_50원_동전_1개를_돌려준다() {
        Changes changes = new Changes(650);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._50_COIN);
    }

    @Test
    void 동전으로_잔돈을_거슬러_줄_수_없는_경우에도_최소_개수의_동전을_잔돈으로_돌려준다() {
        Changes changes = new Changes(1);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).isEmpty();

        changes = new Changes(659);
        coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._50_COIN);
    }
}
