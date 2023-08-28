package vendingmachine.domain;

import vendingmachine.Coin;
import vendingmachine.utils.Converter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

	private Money balance;
	private Money insertedMoney;
	// TODO : 원시값 포장 가능
	private Map<String, Product> productRepository = new HashMap<>();
	private CoinRepository balanceRepository;
	private CoinRepository changeRepository;

	public void insertBalance(Money balance) {
		if (this.balance == null) {
			this.balance = balance;
		}
	}

	public void balanceToCoin() {
		Map<Coin, Integer> randomCoin = Converter.moneyToRandomCoin(balance);
		this.balanceRepository = new CoinRepository(randomCoin);
	}

	// TODO : 기존 상품이 있으면 수량 추가(옵션)
	public void addProduct(Product product) {
		productRepository.put(product.getName(), product);
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
		Map<Coin, Integer> byMoney = balanceRepository.findByMoney(insertedMoney);
		this.changeRepository = new CoinRepository(byMoney);
	}

	public CoinRepository getBalanceRepository() {
		return balanceRepository;
	}

	public CoinRepository getChangeRepository() {
		return changeRepository;
	}
}
