package vendingmachine.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.domain.VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

	@DisplayName("0이상 1,000,000,000 이하의 보유 금액 입력하면 예외가 발생하지 않는다.")
	@ParameterizedTest
	@ValueSource(strings = {"1000", "10000000", "10", "987654321", "0", "1000000000"})
	void initBalanceSuccessTest(String value) {
		Service service = new Service();
		VendingMachine vendingMachine = new VendingMachine();

		Assertions.assertThatNoException()
				.isThrownBy(() -> service.initBalance(vendingMachine, value));
	}

	@DisplayName("0미만 이거나 1,000,000,000 초과의 보유 금액 입력하면 예외가 발생한다.")
	@ParameterizedTest
	@ValueSource(strings = {"-1", "-999999", "1000000001"})
	void initBalanceFailTest(String value) {
		Service service = new Service();
		VendingMachine vendingMachine = new VendingMachine();

		Assertions.assertThatThrownBy(() -> {
			service.initBalance(vendingMachine, value);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("올바른 형식의 상품 입력시 예외가 발생하지 않는다.")
	@ParameterizedTest
	@ValueSource(strings = {"[콜라,1500,20];[사이다,1000,10]", "[사이다,500,0]"})
	void initProductSuccessTest(String value) {
		Service service = new Service();
		VendingMachine vendingMachine = new VendingMachine();

		Assertions.assertThatNoException()
				.isThrownBy(() -> service.initProduct(vendingMachine, value));
	}

	@DisplayName("잘못된 형식의 상품 입력시 예외가 발생한다.")
	@Nested
	class initProductFailTest {

		@DisplayName("상품 리스트 형식이 잘못된 경우")
		@ParameterizedTest
		@ValueSource(strings = {
				"[콜라,1500,20][사이다,1000,10]",
				"[콜라,1500,20],[사이다,1000,10]",
				"[콜라,1500,20]:[사이다,1000,10]",
				"[콜라,1500,20] [사이다,1000,10]",
				"  ",
				"",
		})
		void productListFailTest(String value) {
			Service service = new Service();
			VendingMachine vendingMachine = new VendingMachine();

			Assertions.assertThatThrownBy(() -> {
				service.initProduct(vendingMachine, value);
			}).isInstanceOf(IllegalArgumentException.class);
		}

		@DisplayName("상품명 형식이 잘못된 경우")
		@ParameterizedTest
		@ValueSource(strings = {
				"[500,사이다,2]",
				"[ ,500,2]",
				"[사이,다, 500,2]"
		})
		void productNameFailTest(String value) {
			Service service = new Service();
			VendingMachine vendingMachine = new VendingMachine();

			Assertions.assertThatThrownBy(() -> {
				service.initProduct(vendingMachine, value);
			}).isInstanceOf(IllegalArgumentException.class);
		}

		@DisplayName("상품 가격 형식이 잘못된 경우")
		@ParameterizedTest
		@ValueSource(strings = {
				"[사이다,99,2]",
				"[사이다,11001,2]",
				"[사이다,1109,2]",
				"[사이다,10,2]",
				"[사이다,-1000,2]",
				"[사이다,0,2]",
				"[사이다, ,2]",
				"[사이다,1000원,2]",
				"[사이다,asd,2]",
		})
		void productCostFailTest(String value) {
			Service service = new Service();
			VendingMachine vendingMachine = new VendingMachine();

			Assertions.assertThatThrownBy(() -> {
				service.initProduct(vendingMachine, value);
			}).isInstanceOf(IllegalArgumentException.class);
		}

		@DisplayName("상품 수량 형식이 잘못된 경우")
		@ParameterizedTest
		@ValueSource(strings = {
				"[사이다,1000,-1]",
				"[사이다,1000,-100]",
				"[사이다,1000, ]",
				"[사이다,1000,3개]",
				"[사이다,1000,three]",
		})
		void productCountFailTest(String value) {
			Service service = new Service();
			VendingMachine vendingMachine = new VendingMachine();

			Assertions.assertThatThrownBy(() -> {
				service.initProduct(vendingMachine, value);
			}).isInstanceOf(IllegalArgumentException.class);
		}
	}
}