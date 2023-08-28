package vendingmachine.controller;

import vendingmachine.domain.VendingMachine;

public class MainController {
	private static final InitController initController = new InitController();
	private static final PurchaseController purchaseController = new PurchaseController();
	private static final ChangeController changeController = new ChangeController();

	public void run() {
		VendingMachine vendingMachine = initController.initVendingMachine();
		purchaseController.handlePurchase(vendingMachine);
		changeController.handleChange(vendingMachine);
	}
}
