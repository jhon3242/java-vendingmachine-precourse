package vendingmachine.domain;

public class Money {
	private int money;

	public Money(int money) {
		validate(money);
		this.money = money;
	}

	private void validate(int money) {

	}
}
