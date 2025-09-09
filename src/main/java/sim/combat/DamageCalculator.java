package sim.combat;

public class DamageCalculator {
    public static int hit(int attack, int defence, int maxHit) {
        int attackRoll = RandomNumberGenerator.getRandom(0, attack);
        int defenceRoll = RandomNumberGenerator.getRandom(0, defence);

        if (attackRoll > defenceRoll) {
            int damage = RandomNumberGenerator.getRandom(0, maxHit);

            if (damage == 0) {
                return 1;
            } else {
                return damage;
            }
        } else {
            return 0;
        }
    }
}
