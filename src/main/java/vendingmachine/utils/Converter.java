package vendingmachine.utils;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;
import vendingmachine.ErrorMessage;
import vendingmachine.domain.GameOption;
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

	public static Map<Coin, Integer> moneyToMinCountCoin(Money money) {
		Map<Coin, Integer> result = new HashMap<>();
		Coin.getAll().forEach(coin -> {
			int moneyAmount = money.getMoneyAmount();
			int coinAmount = coin.getAmount();
			int count = moneyAmount / coinAmount;
			money.subtractMoney(new Money(coinAmount * count));
			result.put(coin, count);
		});
		return result;
	}

	public static Map<Coin, Integer> moneyToRandomCoin(Money money) {
		Map<Coin, Integer> result = new HashMap<>();
		while (canChange(money)) {
			Coin pickedCoin = pickRandomCoin();
			if (money.afford(pickedCoin.getAmount())) {
				money.withdraw(pickedCoin.getAmount());
				result.put(pickedCoin, result.getOrDefault(pickedCoin, 0) + 1);
			}
		}
		return result;
	}

	private static boolean canChange(Money money) {
		return money.afford(Coin.COIN_10.getAmount());
	}

	private static Coin pickRandomCoin() {
		int pickedCoinAmount = Randoms.pickNumberInList(GameOption.COIN_AMOUNTS);
		return Coin.findByAmount(pickedCoinAmount);
	}
}
