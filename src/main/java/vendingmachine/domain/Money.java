package vendingmachine.domain;

import vendingmachine.ErrorMessage;

public class Money {
	private int money;

	public Money(int money) {
		validate(money);
		this.money = money;
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
