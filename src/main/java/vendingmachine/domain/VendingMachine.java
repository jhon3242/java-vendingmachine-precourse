package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.*;

public class VendingMachine {

	private Money balance;
	private Map<String, Product> productRepository = new HashMap<>();
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

	// TODO : 기존 상품이 있으면 수량 추가(옵션)
	public void addProduct(Product product) {
		productRepository.put(product.getName(), product);
	}

	private Coin pickCoin() {
		int pickedCoinAmount = Randoms.pickNumberInList(GameOption.COIN_AMOUNTS);
		return Coin.findByAmount(pickedCoinAmount);
	}

	private boolean canChange() {
		return balance.afford(Coin.COIN_10.getAmount());
	}

	public int getCoinCount(Coin coin) {
		return coinMap.getOrDefault(coin, 0);
	}


}
