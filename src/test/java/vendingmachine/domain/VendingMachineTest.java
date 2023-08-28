package vendingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.Coin;

import java.util.HashMap;
import java.util.Map;

class VendingMachineTest {

	@Test
	@DisplayName("보유 금액을 동전으로 바꾼 뒤 더이상 동전으로 바꿀 수 없다.")
	void balanceToCoinSuccessTest() {
		VendingMachine vendingMachine = new VendingMachine();
		Money money = new Money(10_000);
		vendingMachine.insertBalance(money);
		vendingMachine.balanceToCoin();
		Assertions.assertThat(money.afford(Coin.COIN_10.getAmount())).isFalse();
	}

	@Test
	@DisplayName("보유 금액을 동전으로 바꾼 뒤 더이상 동전으로 바꿀 수 없다.")
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
			Map<String, Product> repository = new HashMap<>();
			repository.put("productA", new Product("productA", 1000, 10));
			repository.put("productB", new Product("productB", 1200, 10));
			repository.put("productC", new Product("productC", 1000, 0));
			vendingMachine.setProductRepository(repository);
		}

		@Test
		@DisplayName("투입 금액이 최소 가격 상품보다 작은 경우 더이상 구매를 못한다.")
		void hasPurchaseChanceFalseTest() {

			vendingMachine.setInsertMoney(new Money(500));
			Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isFalse();
		}

		@Test
		@DisplayName("투입 금액이 최소 가격 상품보다 큰 경우 구매할 수 있다.")
		void hasPurchaseChanceTrueTest() {
			vendingMachine.setInsertMoney(new Money(1000));
			Assertions.assertThat(vendingMachine.hasChanceToPurchase()).isTrue();
		}

		@Test
		@DisplayName("투입 금액이 최소 상품 구매보다 큰 경우 구매할 수 있다.")
		void canPurchaseTrueTest() {
			vendingMachine.setInsertMoney(new Money(1000));
			Assertions.assertThatNoException().isThrownBy(() -> {
				vendingMachine.purchase("productA");
			});
		}


		@Test
		@DisplayName("투입 금액이 상품 가격 보다 작은 경우 구매를 못한다.")
		void canPurchaseLowMoneyTest() {
			vendingMachine.setInsertMoney(new Money(1000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.purchase("productB");
			}).isInstanceOf(IllegalArgumentException.class);
		}


		@Test
		@DisplayName("선택 상품의 남은 수량이 없으면 구매할 수 없다..")
		void canPurchaseNoLeftTest() {
			vendingMachine.setInsertMoney(new Money(1000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.purchase("productC");
			}).isInstanceOf(IllegalArgumentException.class);
		}

		@ParameterizedTest
		@DisplayName("잘못된 형식의 상품명 입력시 구매할 수 없다.")
		@ValueSource(strings = {"  ", "productA,productB", "productA productB", "notMatch"})
		void canPurchaseIllegalFormatTest(String value) {
			vendingMachine.setInsertMoney(new Money(5000));
			Assertions.assertThatThrownBy(() -> {
				vendingMachine.purchase(value);
			}).isInstanceOf(IllegalArgumentException.class);
		}
	}

}