package vendingmachine.domain;

public class Customer {
	private int money;

	public Customer(int money) {
		validateMoney(money);
		this.money = money;
	}

	public void validateMoney(int money) {

	}
}
