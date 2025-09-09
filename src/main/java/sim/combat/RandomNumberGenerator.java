package sim.combat;

import java.util.Random;

public class RandomNumberGenerator {

    private static final Random random = new Random();

    public static int getRandom(int from, int to) {
        return random.nextInt((to - from) + 1) + from;
    }
}
