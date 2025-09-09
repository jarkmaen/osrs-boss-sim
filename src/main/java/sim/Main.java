package sim;

import sim.combat.DamageCalculator;
import sim.combat.RandomNumberGenerator;

public class Main {
    public static void main(String[] args) {
        // System.out.println(RandomNumberGenerator.getRandom(0, 10));
        System.out.println(DamageCalculator.hit(38263, 9156, 56));
    }
}