package vendingmachine.view;

public class OutputView {
	public static final String ERROR_PREFIX = "[Error] : ";

	public void printError(IllegalArgumentException e) {
		System.out.println(ERROR_PREFIX + e.getMessage());
	}
}
