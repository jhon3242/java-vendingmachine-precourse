package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.*;

public class VendingMachine {
	private Money balance;
	private List<Product> products = new ArrayList<>();
	private Map<Coin, Integer> coinMap = new HashMap<>();

	public void insertBalance(Money balance) {
		if (this.balance == null) {
			this.balance = balance;
		}
	}

	public void balanceToCoin() {
		while (canChange()) {
			Coin pickedCoin = pickCoin();
			if (balance.afford(pickedCoin.getAmount())) {
				balance.withdraw(pickedCoin.getAmount());
				coinMap.put(pickedCoin, coinMap.getOrDefault(pickedCoin, 0) + 1);
			}
		}
	}

	private Coin pickCoin() {
		int pickedCoinAmount = Randoms.pickNumberInList(Arrays.asList(10, 50, 100, 500));
		return Coin.findByAmount(pickedCoinAmount);
	}

	private boolean canChange() {
		return balance.afford(Coin.COIN_10.getAmount());
	}
}
