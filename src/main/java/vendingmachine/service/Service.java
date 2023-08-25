package vendingmachine.service;

import vendingmachine.ErrorMessage;
import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
	public void initBalance(VendingMachine vendingMachine, String balanceString) {
		Money balance = new Money(Converter.stringToInt(balanceString));
		vendingMachine.insertBalance(balance);
		vendingMachine.balanceToCoin();
	}


	// TODO : 리펙터링
	public void initProduct(VendingMachine vendingMachine, String productFormat) throws IllegalArgumentException {
		String[] split = productFormat.split(";");
		for (String productString : split) {
			String pattern = "^\\[(.*?),(\\d*?),(\\d*?)\\]$";

			Pattern regexPattern = Pattern.compile(pattern);
			Matcher matcher = regexPattern.matcher(productString);

			if (matcher.find()) {
				String productName = matcher.group(1);
				int price = Converter.stringToInt(matcher.group(2));
				int quantity = Converter.stringToInt(matcher.group(3));
				Product product = new Product(productName, price, quantity);
				System.out.println("상품명: " + productName);
				System.out.println("가격: " + price);
				System.out.println("수량: " + quantity);
				return;
			}
			throw new IllegalArgumentException(ErrorMessage.PRODUCT_FORMAT.getMessage());
		}
	}
}
