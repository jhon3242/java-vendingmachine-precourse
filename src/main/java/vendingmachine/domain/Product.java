package vendingmachine.domain;

import java.util.Objects;

public class Product {
	private String name;
	private Money cost;
	private int count;

	public Product(String name, int cost, int count) {
		validate(name, cost, count);
		this.name = name;
		this.cost = new Money(cost);
		this.count = count;
	}

	private void validate(String name, int cost, int count) {
		validateName(name);
		validateCost(cost);
		validateCount(count);
	}


	private void validateName(String name) {
		if (!name.matches(GameOption.PRODUCT_NAME_FORMAT)) {
			throw new IllegalArgumentException("상품명은 숫자와 한글, 영어만 가능합니다.");
		}
	}

	private void validateCost(int cost) {
		validateMinCost(cost);
		validateUnit(cost);
	}

	private void validateUnit(int cost) {
		if (cost % GameOption.COST_UNIT != 0) {
			throw new IllegalArgumentException("상품 가격은 " + GameOption.COST_UNIT + "원으로 나누어 떨어져야합니다.");
		}
	}

	private void validateMinCost(int cost) {
		if (cost < GameOption.COST_MIN) {
			throw new IllegalArgumentException("상품 가격은 " + GameOption.COST_MIN + "원 보다 높아야합니다.");
		}
	}

	private void validateCount(int count) {
		if (count < GameOption.COUNT_MIN) {
			throw new IllegalArgumentException("개수는 0 이상 숫자를 입력해야합니다.");
		}
	}


	public String getName() {
		return name;
	}
}
