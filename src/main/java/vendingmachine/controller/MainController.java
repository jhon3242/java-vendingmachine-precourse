package vendingmachine.controller;

import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.Service;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {
	private final Service service = new Service();
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		VendingMachine vendingMachine = initVendingMachine();
		handlePurchase(vendingMachine);
		handleChange(vendingMachine);
	}

	private VendingMachine initVendingMachine() {
		VendingMachine vendingMachine = new VendingMachine();
		initBalance(vendingMachine);
		initProducts(vendingMachine);
		return vendingMachine;
	}

	private void initBalance(VendingMachine vendingMachine) {
		try {
			String balanceString = inputView.readVendingMachineBalance();
			service.initBalance(vendingMachine, balanceString);
			outputView.printBalanceAllCoin(vendingMachine);
		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			initBalance(vendingMachine);
		}
	}

	private void initProducts(VendingMachine vendingMachine) {
		try {
			String productFormat = inputView.readProduct();
			service.initProduct(vendingMachine, productFormat);

		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			initProducts(vendingMachine);
		}
	}

	private void handlePurchase(VendingMachine vendingMachine) {
		initMoney(vendingMachine);
		while (service.hasChanceToPurchase(vendingMachine)) {
			outputView.printInsertedMoney(vendingMachine);
			purchaseProduct(vendingMachine);
		}
	}

	private void initMoney(VendingMachine vendingMachine) {
		try {
			Money money = new Money(inputView.readMoney());
			service.insertMoney(vendingMachine, money);
		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			initMoney(vendingMachine);
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

	private void handleChange(VendingMachine vendingMachine) {
		outputView.printInsertedMoney(vendingMachine);
		service.changeIntoCoins(vendingMachine);
		outputView.printChangeCoin(vendingMachine);
	}


}
