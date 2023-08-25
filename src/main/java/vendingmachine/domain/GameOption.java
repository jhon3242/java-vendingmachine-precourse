package vendingmachine.domain;

import java.util.Arrays;
import java.util.List;

public class GameOption {
	public static final int COST_MIN = 100;
	public static final int COST_UNIT = 10;
	public static final int COUNT_MIN = 0;
	public static final String PRODUCT_NAME_FORMAT = "[\\w가-힣]+";
	public static int MONEY_MAX = 1_000_000_000;
	public static int MONEY_MIN = 0;
	public static final List<Integer> COIN_AMOUNTS = Arrays.asList(500, 100, 50, 10);
}
