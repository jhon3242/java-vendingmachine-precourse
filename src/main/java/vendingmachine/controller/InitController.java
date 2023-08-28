package vendingmachine.controller;

import vendingmachine.domain.VendingMachine;
import vendingmachine.service.Service;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class InitController {
	private static final InputView inputView = new InputView();
	private static final OutputView outputView = new OutputView();
	private static final Service service = new Service();

	public VendingMachine initVendingMachine() {
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
}
