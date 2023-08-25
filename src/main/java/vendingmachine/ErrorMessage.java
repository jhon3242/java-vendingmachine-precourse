package vendingmachine;

import vendingmachine.domain.GameOption;

public enum ErrorMessage {
	NUMBER_FORMAT("금액은 숫자여야 합니다."),
	NUMBER_TO_SMALL(GameOption.MONEY_MIN + " 이상의 숫자 이여야합니다."),
	NUMBER_TO_LARGE(GameOption.MONEY_MAX + " 이하의 숫자 이여야합니다."),
	PRODUCT_FORMAT("잘못된 형식의 상품입력입니다."),
	BLANK("빈 값입니다.");

	private String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
