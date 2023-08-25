package vendingmachine;

public enum Message {
	READ_BALANCE("자판기가 보유하고 있는 금액을 입력해 주세요.");

	private String message;

	Message(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
