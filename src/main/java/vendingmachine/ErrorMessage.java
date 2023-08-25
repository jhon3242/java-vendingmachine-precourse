package vendingmachine;

public enum ErrorMessage {
	NUMBER_FORMAT("숫자가 아닙니다."),
	BLANK("빈 값입니다.");

	private String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
