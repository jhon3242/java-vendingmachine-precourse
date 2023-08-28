package vendingmachine.controller;

import vendingmachine.domain.VendingMachine;
import vendingmachine.service.Service;
import vendingmachine.view.OutputView;

public class ChangeController {

	private static final OutputView outputView = new OutputView();
	private static final Service service = new Service();

	public void handleChange(VendingMachine vendingMachine) {
		outputView.printInsertedMoney(vendingMachine);
		service.changeIntoCoins(vendingMachine);
		outputView.printChangeCoin(vendingMachine);
	}
}
