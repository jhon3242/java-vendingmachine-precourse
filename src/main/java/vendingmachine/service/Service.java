package vendingmachine.service;

import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.Converter;

import java.util.Map;

public class Service {
	public void initBalance(VendingMachine vendingMachine, String balanceString) {
		Money balance = new Money(Converter.stringToInt(balanceString));
		vendingMachine.insertBalance(balance);
		vendingMachine.balanceToCoin();
	}


	public void initProduct(VendingMachine vendingMachine, String productFormat) throws IllegalArgumentException {
		Map<String, Product> products = Converter.stringToProductDictionary(productFormat);
		vendingMachine.setProductRepository(products);
	}

	public void insertMoney(VendingMachine vendingMachine, Money money) {
		vendingMachine.insertMoney(money);
	}

	public boolean hasChanceToPurchase(VendingMachine vendingMachine) {
		return vendingMachine.hasChanceToPurchase();
	}


	public void purchase(VendingMachine vendingMachine, String productName) {
		vendingMachine.purchase(productName);
	}


	public void changeIntoCoins(VendingMachine vendingMachine) {
		vendingMachine.insertedMoneyToChange();
	}
}
