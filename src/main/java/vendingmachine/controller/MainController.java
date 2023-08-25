package vendingmachine.controller;

import vendingmachine.domain.Customer;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.Service;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {
	private final Service service = new Service();
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
//		Customer customer = initCustomer();
		VendingMachine vendingMachine = initVendingMachine();
//		purchaseProduct(vendingMachine, customer);
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
		} catch (IllegalArgumentException e) {
			outputView.printError(e);
			initBalance(vendingMachine);
		}
	}

	private void initProducts(VendingMachine vendingMachine) {
//		try {
//			String productFormat = inputView.readProduct();
//			service.initProduct(vendingMachine, productFormat);
//		} catch (IllegalArgumentException e) {
//
//		}
	}


	private void purchaseProduct(VendingMachine vendingMachine, Customer customer) {

	}


	private void handleChange(VendingMachine vendingMachine, Customer customer) {

	}


}
