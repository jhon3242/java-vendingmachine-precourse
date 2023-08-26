package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

	@Test
	@DisplayName("보유 금액을 동전으로 바꾼뒤 더이상 동전으로 바꿀 수 없다.")
	void balanceToCoinSuccessTest() {
		VendingMachine vendingMachine = new VendingMachine();
		Money money = new Money(10_000);
		vendingMachine.insertBalance(money);
		vendingMachine.balanceToCoin();
		Assertions.assertThat(money.afford(Coin.COIN_10.getAmount())).isFalse();
	}

	@Test
	@DisplayName("보유 금액을 동전으로 바꾼뒤 더이상 동전으로 바꿀 수 없다.")
	void balanceToCoinSuccess2Test() {
		VendingMachine vendingMachine = new VendingMachine();
		Money money = new Money(100);
		vendingMachine.insertBalance(money);
		vendingMachine.balanceToCoin();
		Assertions.assertThat(money.afford(Coin.COIN_10.getAmount())).isFalse();
	}

	@Test
	@DisplayName("투입 금액이 최소 가격 상품보다 작은 경우 더이상 구매를 못한다.")
	void hasPurchaseChanceFalseTest() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.addProduct(new Product("productA", 1000, 10));
		vendingMachine.addProduct(new Product("productB", 1200, 10));
		vendingMachine.insertMoney(new Money(500));
		Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isFalse();
	}

	@Test
	@DisplayName("투입 금액이 최소 가격 상품보다 큰 경우 구매할 수 있다.")
	void hasPurchaseChanceTrueTest() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.addProduct(new Product("productA", 1000, 10));
		vendingMachine.addProduct(new Product("productB", 1200, 10));
		vendingMachine.insertMoney(new Money(1000));
		Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isTrue();
	}

	@Test
	@DisplayName("투입 금액이 상품 가격 보다 작은 경우 구매를 못한다.")
	void canPurchaseFalseTest() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.addProduct(new Product("productA", 1000, 10));
		vendingMachine.addProduct(new Product("productB", 1200, 10));
		vendingMachine.insertMoney(new Money(1000));
		Assertions.assertThatThrownBy(() -> {
			vendingMachine.validateCanPurchase("productB");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("투입 금액이 최소 상품 구매보다 큰 경우 구매할 수 있다.")
	void canPurchaseTrueTest() {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.addProduct(new Product("productA", 1000, 10));
		vendingMachine.addProduct(new Product("productB", 1200, 10));
		vendingMachine.insertMoney(new Money(1000));
		Assertions.assertThatNoException().isThrownBy(() -> {
			vendingMachine.validateCanPurchase("productA");
		});
	}
}