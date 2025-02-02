package vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin findByAmount(int amount) {
        return Arrays.stream(values())
                .filter(v -> v.amount == amount)
                .findAny()
                .orElse(null);
    }

    public int getAmount() {
        return amount;
    }

    public static List<Coin> getAll() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }
}
