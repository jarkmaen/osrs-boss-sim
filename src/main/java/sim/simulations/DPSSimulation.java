package sim.simulations;

import sim.bosses.Bloat;
import sim.util.DamageCalculator;
import sim.util.ScytheAttackResult;

public class DPSSimulation {

    private int attackRoll = 38142;
    private int maxHit = 52;

    public void run(int simulations) {
        Bloat bloat = new Bloat();
        int defenceRoll = bloat.getDefenceRoll();

        double totalHits = 0;
        double totalAttacks = 0;
        double totalDamage = 0;
        double totalMisses = 0;
        double totalTimeToKill = 0;

        for (int i = 0; i < simulations; i++) {
            int bossHP = bloat.getHP();
            int gameTicks = 0;

            while (bossHP > 0) {
                ScytheAttackResult scytheAttackResult = DamageCalculator.scytheHitStats(attackRoll, defenceRoll,
                        maxHit);
                int damage = scytheAttackResult.getDamage();

                bossHP -= damage;
                gameTicks += 5;
                totalAttacks++;
                totalDamage += damage;
                totalHits += scytheAttackResult.getHits();
                totalMisses += scytheAttackResult.getMisses();
            }

            totalTimeToKill += gameTicks * 0.6;
        }

        double accuracy = totalHits / (totalHits + totalMisses) * 100;
        double avgTimeToKill = totalTimeToKill / simulations;
        double dps = totalDamage / totalTimeToKill;
        double expectedHit = totalDamage / totalAttacks;

        System.out.println("Expected hit: " + expectedHit);
        System.out.println("DPS: " + dps);
        System.out.println("Avg. TTK: " + avgTimeToKill + "s");
        System.out.println("Accuracy: " + accuracy + "%");
    }
}
