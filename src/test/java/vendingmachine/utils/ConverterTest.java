package vendingmachine.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;
import vendingmachine.domain.Money;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

	// todo : add more testcase

	@Test
	void coinConverter1Test() {
		Map<Coin, Integer> coinIntegerMap = Converter.moneyToCoin(new Money(1000));
		Assertions.assertThat(coinIntegerMap.get(Coin.COIN_500)).isEqualTo(2);
	}

	@Test
	void coinConverter2Test() {
		Map<Coin, Integer> coinIntegerMap = Converter.moneyToCoin(new Money(1250));
		Assertions.assertThat(coinIntegerMap.get(Coin.COIN_500)).isEqualTo(2);
		Assertions.assertThat(coinIntegerMap.get(Coin.COIN_100)).isEqualTo(2);
		Assertions.assertThat(coinIntegerMap.get(Coin.COIN_50)).isEqualTo(1);
	}

}