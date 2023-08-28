package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;
import vendingmachine.utils.Converter;

import java.util.*;

public class VendingMachine {

	private Money balance;
	private Money insertedMoney;
	// TODO : 원시값 포장 가능
	private Map<String, Product> productRepository = new HashMap<>();
	private Map<Coin, Integer> coinMap;
	private Map<Coin, Integer> changeRepository;

	public void insertBalance(Money balance) {
		if (this.balance == null) {
			this.balance = balance;
		}
	}

	private Map<Coin, Integer> moneyToRandomCoin(Money money) {
		Map<Coin, Integer> result = new HashMap<>();
		while (canChange(money)) {
			Coin pickedCoin = pickCoin();
			if (money.afford(pickedCoin.getAmount())) {
				money.withdraw(pickedCoin.getAmount());
				result.put(pickedCoin, result.getOrDefault(pickedCoin, 0) + 1);
			}
		}
		return result;
	}

	public void balanceToCoin() {
		Map<Coin, Integer> coinIntegerMap = moneyToRandomCoin(balance);
		this.coinMap = coinIntegerMap;
	}

	// TODO : 기존 상품이 있으면 수량 추가(옵션)
	public void addProduct(Product product) {
		productRepository.put(product.getName(), product);
	}

	private Coin pickCoin() {
		int pickedCoinAmount = Randoms.pickNumberInList(GameOption.COIN_AMOUNTS);
		return Coin.findByAmount(pickedCoinAmount);
	}

	private boolean canChange(Money money) {
		return money.afford(Coin.COIN_10.getAmount());
	}

	public Map<Coin, Integer> getCoinMap() {
		return coinMap;
	}

	public void insertMoney(Money money) {
		this.insertedMoney = money;
	}

	// TODO : 리펙터링 가능한지 확인
	public Money getInsertedMoney() {
		return this.insertedMoney;
	}

	public boolean hasChanceToPurchase() {
		Product minCostProduct = getMinCostProduct();
		if (minCostProduct == null || notEnoughMoney(minCostProduct)) {
			return false;
		}
		return true;
	}

	private Product getMinCostProduct() {
		return productRepository.values().stream()
				.min(Comparator.comparing(Product::getCost))
				.orElse(null);
	}

	private boolean notEnoughMoney(Product minCostProduct) {
		return minCostProduct.getCost().compareTo(insertedMoney) > 0;
	}

	public void validateCanPurchase(String productName) {
		Product product = productRepository.get(productName);
		validateExist(product);
		validateCount(product);
		validateAfford(product);
	}

	private void validateExist(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("존재하지 않는 상품명입니다.");
		}
	}

	private void validateCount(Product product) {
		if (product.getCount() <= 0) {
			throw new IllegalArgumentException("해당 상품은 품절입니다.");
		}
	}

	private void validateAfford(Product product) {
		if (!product.canPurchase(insertedMoney)) {
			throw new IllegalArgumentException("해당 상품을 구매하기에 돈이 부족합니다.");
		}
	}

	public void purchase(String productName) {
		Product product = productRepository.get(productName);
		product.subtractCount();
		insertedMoney.subtractMoney(product.getCost());
	}

	public void insertedMoneyToChange() {
		Map<Coin, Integer> coinRepository = new HashMap<>();
		for (Coin coin : coinMap.keySet()) {
			if (coinMap.get(coin) > 0 && insertedMoney.afford(coin.getAmount())) {
				int moneyAmount = insertedMoney.getMoneyAmount();
				int count = Math.min(moneyAmount / coin.getAmount(), coinMap.get(coin));
				insertedMoney.subtractMoney(new Money(coin.getAmount() * count));
				coinRepository.put(coin, count);
			}
		}
		this.changeRepository = coinRepository;
	}

	public Map<Coin, Integer> getChangeRepository() {
		return changeRepository;
	}

}
