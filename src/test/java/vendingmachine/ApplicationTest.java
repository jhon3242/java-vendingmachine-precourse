package vendingmachine;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomNumberInListTest(
            () -> {
                run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                assertThat(output()).contains(
                    "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                    "투입 금액: 3000원", "투입 금액: 1500원"
                );
            },
            100, 100, 100, 100, 50
        );
    }


    @DisplayName("잔돈 반환 기능 테스트")
    @Nested
    class ChangeTest {

        @DisplayName("잔돈 반환 금액이 동전으로 나누어 떨어지지 않는 경우 가능한 잔돈 만큼 반환한다.")
        @Test
        void notEnoughTest() {
            assertRandomNumberInListTest(
                    () -> {
                        run("455", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                        assertThat(output()).contains(
                                "잔돈", "100원 - 3개", "50원 - 3개"
                        );
                    },
                    100, 100, 50, 50, 50, 100
            );
        }

        @DisplayName("잔돈 반환시 동전 개수가 최대한 적게 반환한다.")
        @Test
        void lessCoinTest() {
            assertRandomNumberInListTest(
                    () -> {
                        run("1000", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                        assertThat(output()).contains(
                                "잔돈", "500원 - 1개", "100원 - 5개"
                        );
                    },
                     100, 100, 100, 100, 100, 500
            );
        }

        @DisplayName("잔돈 반환시 동전 개수가 최대한 적게 반환한다.")
        @Test
        void lessCoinTest2() {
            assertRandomNumberInListTest(
                    () -> {
                        run("2000", "[콜라,1200,20]", "2000", "콜라");
                        assertThat(output()).contains(
                                "잔돈", "500원 - 1개", "100원 - 3개"
                        );
                    },
                    500, 500, 100, 100, 100, 100, 100, 50, 50, 50, 50,50, 50, 50, 50, 50, 10, 10, 10,10,10
            );
        }

        @DisplayName("잔돈 반환시 동전 개수가 최대한 적게 반환한다.")
        @Test
        void lessCoinTest3() {
            assertRandomNumberInListTest(
                    () -> {
                        run("2000", "[콜라,1200,50]", "2000", "콜라");
                        assertThat(output()).contains(
                                "잔돈", "500원 - 1개", "100원 - 3개"
                        );
                    },
                    500,500, 500, 100, 100, 100, 50, 50, 50, 10, 10, 10, 10, 10
                    );
        }
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(
            () -> {
                runException("-1");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
