package vendingmachine.domain;

import vendingmachine.ErrorMessage;

public class Money {
	private int money;

	public Money(int money) {
		validate(money);
		this.money = money;
	}

	public boolean afford(int amount) {
		return money - amount >= 0;
	}

	public void withdraw(int amount) {
		this.money -= amount;
	}

	private void validate(int money) {
		validateRange(money);
	}

	private void validateRange(int money) {
		if (money < GameOption.MIN_MONEY) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_TO_SMALL.getMessage());
		}
		if (money > GameOption.MAX_MONEY) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_TO_LARGE.getMessage());
		}
	}
}
