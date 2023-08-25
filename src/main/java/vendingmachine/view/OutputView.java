package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.Message;
import vendingmachine.domain.GameOption;
import vendingmachine.domain.VendingMachine;

public class OutputView {
	public static final String ERROR_PREFIX = "[Error] : ";


	public void printError(IllegalArgumentException e) {
		System.out.println(ERROR_PREFIX + e.getMessage());
	}

	// TODO : DTO 로 리펙터링 가능
	public void printCoins(VendingMachine vendingMachine) {
		System.out.println(Message.COIN_RESULT_MESSAGE);
		for (Integer amount : GameOption.COIN_AMOUNTS) {
			Coin coin = Coin.findByAmount(amount);
			printCoin(vendingMachine, coin);
		}
	}

	private void printCoin(VendingMachine vendingMachine, Coin coin) {
		System.out.printf(Message.COIN_FORMAT.toString(), coin.getAmount(), vendingMachine.getCoinCount(coin));
	}

	public void printInsertedMoney(VendingMachine vendingMachine) {
		System.out.printf("투입 금액: %d원", vendingMachine.getInsertedMoney().getMoney());
	}
}
