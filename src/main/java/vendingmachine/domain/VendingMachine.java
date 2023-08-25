package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	private Money balance;
	private List<Product> products = new ArrayList<>();
	private Map<Coin, Integer> coinMap = new HashMap<>();

	public void insertBalance(Money balance) {
		if (this.balance == null) {
			this.balance = balance;
		}
	}



}
