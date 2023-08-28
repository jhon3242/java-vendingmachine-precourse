package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.Message;
import vendingmachine.domain.CoinRepository;
import vendingmachine.domain.GameOption;
import vendingmachine.domain.VendingMachine;

import java.util.Map;

public class OutputView {
	public static final String ERROR_PREFIX = "[Error] : ";


	public void printError(IllegalArgumentException e) {
		System.out.println(ERROR_PREFIX + e.getMessage());
	}

	// TODO : DTO 로 리펙터링 가능
	public void printBalanceAllCoin(VendingMachine vendingMachine) {
		System.out.println(Message.COIN_RESULT_MESSAGE);
		for (Integer amount : GameOption.COIN_AMOUNTS) {
			Coin coin = Coin.findByAmount(amount);
			printCoin(vendingMachine.getBalanceRepository(), coin);
		}
		System.out.println();
	}

	private void printCoin(CoinRepository repository, Coin coin) {
		System.out.printf(Message.COIN_FORMAT.toString(), coin.getAmount(), repository.getCoinCount(coin));
	}


	public void printInsertedMoney(VendingMachine vendingMachine) {
		System.out.printf("투입 금액: %d원\n", vendingMachine.getInsertedMoney().getMoneyAmount());
	}

	// TODO : stream refactor
	public void printChangeCoin(VendingMachine vendingMachine) {
		System.out.println("잔돈");
		CoinRepository changeRepository = vendingMachine.getChangeRepository();
		Coin.getAll().forEach(coin -> {
			if (changeRepository.getCoinCount(coin) > 0) {
				printCoin(changeRepository, coin);
			}
		});
		System.out.println();
	}
}
