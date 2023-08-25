package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

	@Test
	@DisplayName("투입 금액을 동전으로 바꾼뒤 더이상 동전으로 바꿀 수 없다.")
	void balanceToCoinSuccessTest() {
		VendingMachine vendingMachine = new VendingMachine();
		Money money = new Money(10_000);
		vendingMachine.insertBalance(money);
		vendingMachine.balanceToCoin();
		Assertions.assertThat(money.afford(Coin.COIN_10.getAmount())).isFalse();
	}

	@Test
	@DisplayName("투입 금액을 동전으로 바꾼뒤 더이상 동전으로 바꿀 수 없다.")
	void balanceToCoinSuccess2Test() {
		VendingMachine vendingMachine = new VendingMachine();
		Money money = new Money(100);
		vendingMachine.insertBalance(money);
		vendingMachine.balanceToCoin();
		Assertions.assertThat(money.afford(Coin.COIN_10.getAmount())).isFalse();
	}
}