package vendingmachine.controller;

import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.Service;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class PurchaseController {
	private static final InputView inputView = new InputView();
	private static final OutputView outputView = new OutputView();
	private static final Service service = new Service();

	public void handlePurchase(VendingMachine vendingMachine) {
		initInsertMoney(vendingMachine);
		while (service.hasChanceToPurchase(vendingMachine)) {
			outputView.printInsertedMoney(vendingMachine);
			purchaseProduct(vendingMachine);
		}
	}

	private void initInsertMoney(VendingMachine vendingMachine) {
		try {
			Money money = new Money(inputView.readMoney());
			service.insertMoney(vendingMachine, money);
		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			initInsertMoney(vendingMachine);
		}
	}

	private void purchaseProduct(VendingMachine vendingMachine) {
		try {
			String productName = inputView.readProductName();
			service.purchase(vendingMachine, productName);
		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			purchaseProduct(vendingMachine);
		}
	}
}
