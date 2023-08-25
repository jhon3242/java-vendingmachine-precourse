package vendingmachine.controller;

import vendingmachine.domain.Customer;
import vendingmachine.domain.VendingMachine;

public class MainController {

	public void run() {
		Customer customer = new Customer();
		VendingMachine vendingMachine = initVendingMachine();
		purchaseProduct(vendingMachine, customer);
		handleChange(vendingMachine, customer);
	}

	private VendingMachine initVendingMachine() {


	}
	private void purchaseProduct(VendingMachine vendingMachine, Customer customer) {

	}


	private void handleChange(VendingMachine vendingMachine, Customer customer) {

	}


}
