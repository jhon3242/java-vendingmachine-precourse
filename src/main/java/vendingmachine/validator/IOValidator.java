package vendingmachine.validator;

import vendingmachine.ErrorMessage;

public class IOValidator {
	public static void validateString(String value) {
		validateBlank(value);
	}

	private static void validateBlank(String value) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException(ErrorMessage.BLANK.getMessage());
		}
	}


}
