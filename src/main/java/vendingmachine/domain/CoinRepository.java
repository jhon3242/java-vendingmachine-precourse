package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.HashMap;
import java.util.Map;

public class CoinRepository {
	private Map<Coin, Integer> repository;

	public CoinRepository(Map<Coin, Integer> repository) {
		this.repository = repository;
	}

	public int getCoinCount(Coin coin) {
		return repository.getOrDefault(coin, 0);
	}

	public Map<Coin, Integer> findByMoney(Money insertedMoney) {
		Map<Coin, Integer> newCoinRepository = new HashMap<>();
		for (Coin coin : Coin.getAll()) {
			if (getCoinCount(coin) > 0 && insertedMoney.afford(coin.getAmount())) {
				int moneyAmount = insertedMoney.getMoneyAmount();
				int count = Math.min(moneyAmount / coin.getAmount(), repository.get(coin));
				insertedMoney.subtractMoney(new Money(coin.getAmount() * count));
				newCoinRepository.put(coin, count);
			}
		}
		return newCoinRepository;
	}


}
