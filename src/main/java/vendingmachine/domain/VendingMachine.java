package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.*;

public class VendingMachine {

	private Money balance;
	private Money insertedMoney;
	// TODO : 원시값 포장 가능
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


	public void insertMoney(Money money) {
		this.insertedMoney = money;
	}

	// TODO : 리펙터링 가능한지 확인
	public Money getInsertedMoney() {
		return this.insertedMoney;
	}

	public boolean hasChanceToPurchase() {
		Product minCostProduct = productRepository.values().stream()
				.min(Comparator.comparing(Product::getCost))
				.orElse(null);
		if (minCostProduct == null) {
			return false;
		}
		if (notEnoughMoney(minCostProduct)) {
			return false;
		}
		return true;
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



//	public boolean canPurchase() {
//
//		return insertedMoney.afford(minCost.getMoney());
//	}
//
//	public boolean hasProduct(String productName) {
//		Product product = productRepository.get(productName);
//		if (product == null) {
//			return false;
//		}
//		if (!product.canPurchase()) {
//			return false;
//		}
//		return true;
//	}
//
//	public void purchaseProduct(String productName) {
//
//	}
//
//	public boolean affordProduct(String productName) {
//		productRepository
//	}
}
