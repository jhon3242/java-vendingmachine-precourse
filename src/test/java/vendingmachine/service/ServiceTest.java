package vendingmachine.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
		});
	}
}