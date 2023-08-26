package vendingmachine.domain;

import vendingmachine.ErrorMessage;

public class Money implements Comparable<Money>{
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
		if (money < GameOption.MONEY_MIN) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_TO_SMALL.getMessage());
		}
		if (money > GameOption.MONEY_MAX) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_TO_LARGE.getMessage());
		}
	}

	public int getMoney() {
		return money;
	}

	@Override
	public int compareTo(Money o) {
		return money - o.money;
	}

	public boolean isLargerThen(Money o) {
		return money > o.money;
	}
}
