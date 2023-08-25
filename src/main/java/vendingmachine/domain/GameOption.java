package vendingmachine.domain;

import java.util.Arrays;
import java.util.List;

public class GameOption {
	public static int MAX_MONEY = 1_000_000_000;
	public static int MIN_MONEY = 0;
	public static final List<Integer> COIN_AMOUNTS = Arrays.asList(500, 100, 50, 10);
}
