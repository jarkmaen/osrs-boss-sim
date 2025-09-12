package sim.combat;

import sim.util.Pair;

import static sim.combat.RandomNumberGenerator.getRandom;

public class DamageCalculator {
    public static int hit(int attack, int defence, int maxHit) {
        int attackRoll = getRandom(0, attack);
        int defenceRoll = getRandom(0, defence);

        if (attackRoll > defenceRoll) {
            int damage = getRandom(0, maxHit);

            if (damage == 0) {
                return 1;
            } else {
                return damage;
            }
        } else {
            return 0;
        }
    }

    public static int hitGuaranteed(int maxHit) {
        int damage = getRandom(0, maxHit);

        if (damage == 0) {
            return 1;
        } else {
            return damage;
        }
    }

    public static int claw(int attack, int defence, int maxHit) {
        int damage = 0;
        int totalDamage = 0;

        if (getRandom(0, attack) > getRandom(0, defence)) {
            damage = getRandom(0, maxHit - 1) + maxHit;
            totalDamage = damage / 2 + damage / 4 + damage / 8 + damage / 8 + 1;
        } else if (getRandom(0, attack) > getRandom(0, defence)) {
            damage = getRandom(0, maxHit - 1) + maxHit * 3 / 4;
            totalDamage = damage / 2 + damage / 4 + damage / 4 + 1;
        } else if (getRandom(0, attack) > getRandom(0, defence)) {
            damage = getRandom(0, maxHit - 1) + maxHit / 2;
            totalDamage = damage / 2 + damage / 2 + 1;
        } else if (getRandom(0, attack) > getRandom(0, defence)) {
            damage = getRandom(0, maxHit - 1) + maxHit / 4;
            totalDamage = damage + 1;
        } else if (getRandom(0, 2) < 2) {
            totalDamage = 2;
        }

        return totalDamage;
    }

    public static Pair<Integer, Boolean> elderMaul(int attack, int defence, int maxHit) {
        boolean specLanded = false;
        int damage = hit(attack, defence, maxHit);

        if (damage > 0) {
            specLanded = true;
        }

        return new Pair<>(damage, specLanded);
    }

    public static int scythe(int attack, int defence, int maxHit, int size) {
        int damage = 0;

        damage += hit(attack, defence, maxHit);

        if (size > 1) {
            damage += hit(attack, defence, maxHit / 2);
        }

        if (size > 2) {
            damage += hit(attack, defence, maxHit / 4);
        }

        return damage;
    }

    public static ScytheAttackResult scytheHitStats(int attack, int defence, int maxHit) {
        int damage = 0;
        int damageDivisor = 1;
        int hits = 0;
        int misses = 0;

        for (int i = 0; i < 3; i++) {
            int hit = hit(attack, defence, maxHit / damageDivisor);

            if (hit > 0) {
                damage += hit;
                hits++;
            } else {
                misses++;
            }

            damageDivisor *= 2;
        }

        return new ScytheAttackResult(damage, hits, misses);
    }
}
