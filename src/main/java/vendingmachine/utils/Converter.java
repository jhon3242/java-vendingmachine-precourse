package vendingmachine.utils;

import vendingmachine.Coin;
import vendingmachine.ErrorMessage;
import vendingmachine.domain.Money;

import java.util.HashMap;
import java.util.Map;

public class Converter {

	public static int stringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT.getMessage());
		}
	}

	public static Map<Coin, Integer> moneyToCoin(Money money) {
		Map<Coin, Integer> coinRepository = new HashMap<>();
		Coin.getAll().forEach(coin -> {
			int moneyAmount = money.getMoneyAmount();
			int coinAmount = coin.getAmount();
			int count = moneyAmount / coinAmount;
			money.subtractMoney(new Money(coinAmount * count));
			coinRepository.put(coin, count);
		});
		return coinRepository;
	}
}
