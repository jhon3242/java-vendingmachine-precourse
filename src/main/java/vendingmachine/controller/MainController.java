package vendingmachine.controller;

import vendingmachine.domain.Customer;
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
//		handleChange(vendingMachine, customer);
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
			outputView.printCoins(vendingMachine);
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
		while (service.canPurchase(vendingMachine)) {
			outputView.printInsertedMoney(vendingMachine);
			inputView.readProductName();
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




	private void handleChange(VendingMachine vendingMachine, Customer customer) {

	}


}
