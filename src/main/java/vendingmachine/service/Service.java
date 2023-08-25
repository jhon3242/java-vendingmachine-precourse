package vendingmachine.service;

import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.Converter;

public class Service {
	public void initBalance(VendingMachine vendingMachine, String balanceString) {
		Money balance = new Money(Converter.stringToInt(balanceString));
		vendingMachine.insertBalance(balance);
		vendingMachine.balanceToCoin();
	}

	public void initProduct(VendingMachine vendingMachine, String productFormat) {
		String[] split = productFormat.split(";");
		for (String s : split) {
			String regex = "[\\w]+,[0-9]+,[0-9]+";
		}
	}
}
