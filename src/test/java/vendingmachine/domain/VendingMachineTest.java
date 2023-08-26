package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

	@DisplayName("자판기 구매 테스트")
	@Nested
	class PurchaseTest {

		VendingMachine vendingMachine;

		@BeforeEach
		void beforeEach() {
			vendingMachine = new VendingMachine();
			vendingMachine.addProduct(new Product("productA", 1000, 10));
			vendingMachine.addProduct(new Product("productB", 1200, 10));
			vendingMachine.addProduct(new Product("productC", 1000, 0));
		}

		@Test
		@DisplayName("투입 금액이 최소 가격 상품보다 작은 경우 더이상 구매를 못한다.")
		void hasPurchaseChanceFalseTest() {

			vendingMachine.insertMoney(new Money(500));
			Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isFalse();
		}

		@Test
		@DisplayName("투입 금액이 최소 가격 상품보다 큰 경우 구매할 수 있다.")
		void hasPurchaseChanceTrueTest() {
			vendingMachine.insertMoney(new Money(1000));
			Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isTrue();
		}

		@Test
		@DisplayName("투입 금액이 최소 상품 구매보다 큰 경우 구매할 수 있다.")
		void canPurchaseTrueTest() {
			vendingMachine.insertMoney(new Money(1000));
			Assertions.assertThatNoException().isThrownBy(() -> {
				vendingMachine.validateCanPurchase("productA");
			});
		}


		@Test
		@DisplayName("투입 금액이 상품 가격 보다 작은 경우 구매를 못한다.")
		void canPurchaseLowMoneyTest() {
			vendingMachine.insertMoney(new Money(1000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.validateCanPurchase("productB");
			}).isInstanceOf(IllegalArgumentException.class);
		}


		@Test
		@DisplayName("선택 상품의 남은 수량이 없으면 구매할 수 없다..")
		void canPurchaseNoLeftTest() {
			vendingMachine.insertMoney(new Money(1000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.validateCanPurchase("productC");
			}).isInstanceOf(IllegalArgumentException.class);
		}

		@ParameterizedTest
		@DisplayName("잘못된 형식의 상품명 입력시 구매할 수 없다.")
		@ValueSource(strings = {"  ", "productA,productB", "productA productB", "notMatch"})
		void canPurchaseIllegalFormatTest(String value) {
			vendingMachine.insertMoney(new Money(5000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.validateCanPurchase(value);
			}).isInstanceOf(IllegalArgumentException.class);
		}
	}
}