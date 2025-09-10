package sim.simulations;

import sim.bosses.Bloat;
import sim.combat.DamageCalculator;

public class DPSSimulation {

    private int attackRoll = 38142;
    private int maxHit = 52;
    private int simulations = 1000000;

    public void run() {
        Bloat bloat = new Bloat();
        int bossHP = bloat.getHP();
        int defenceRoll = bloat.getDefenceRoll();

        double totalDamage = 0;
        double totalTime = 0;

        for (int i = 0; i < simulations; i++) {
            int currentHP = bossHP;
            int ticks = 0;

            while (currentHP > 0) {
                int damage = DamageCalculator.scythe(attackRoll, defenceRoll, maxHit);
                currentHP -= damage;
                ticks += 5;
                totalDamage += damage;
            }

            totalTime += ticks * 0.6;
        }

        double dps = totalDamage / totalTime;
        double avgTimeToKill = totalTime / simulations;

        System.out.println("DPS: " + dps);
        System.out.println("Avg. TTK: " + avgTimeToKill);
    }
}
