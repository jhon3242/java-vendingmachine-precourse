package vendingmachine.utils;

import vendingmachine.ErrorMessage;

public class Converter {

	public static int stringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT.getMessage());
		}
	}
}
