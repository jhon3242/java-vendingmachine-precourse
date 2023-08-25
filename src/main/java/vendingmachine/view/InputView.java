package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.Message;
import vendingmachine.utils.Converter;
import vendingmachine.validator.IOValidator;

public class InputView {

	public String readVendingMachineBalance() {
		System.out.println(Message.READ_BALANCE);
		String input = Console.readLine();
		IOValidator.validateString(input);
		return input;
	}
//
//	public String readProductInfo() {
//
//	}
//
//	public String readInsertedMoney() {
//
//	}

	public String readProduct() {
		System.out.println(Message.READ_PRODUCT);
		String input = Console.readLine();
		IOValidator.validateString(input);
		return input;
	}


	public int readMoney() {
		System.out.println(Message.READ_MONEY);
		String input = Console.readLine();
		return Converter.stringToInt(input);
	}

	public String readProductName() {
		System.out.println(Message.READ_PRODUCT_NAME);
		String input = Console.readLine();
		IOValidator.validateString(input);
		return input;
	}
}
