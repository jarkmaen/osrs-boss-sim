package obs.simulations;

import java.text.DecimalFormat;

import obs.bosses.Bloat;
import obs.util.DamageCalculator;

public class BloatSimulation {

    private int playerOneScytheAttackRoll = 38263;
    private int playerOneScytheMaxHit = 56;
    private int playerOneClawAttackRoll = 26104;
    private int playerOneClawMaxHit = 51;

    private int playerTwoScytheAttackRoll = 38142;
    private int playerTwoScytheMaxHit = 52;
    private int playerTwoClawAttackRoll = 24841;
    private int playerTwoClawMaxHit = 46;

    public void run(int simulations, boolean threeDown) {
        Bloat bloat = new Bloat();
        int defenceRoll = bloat.getDefenceRoll();
        int size = bloat.getSize();

        int scytheAttacks = 0;
        double successfulKills = 0;

        if (threeDown == true) {
            scytheAttacks = 14;
        } else {
            scytheAttacks = 18;
        }

        for (int i = 0; i < simulations; i++) {
            int bossHP = bloat.getHP();

            bossHP -= DamageCalculator.claw(playerOneClawAttackRoll, defenceRoll, playerOneClawMaxHit);
            bossHP -= DamageCalculator.claw(playerOneClawAttackRoll, defenceRoll, playerOneClawMaxHit);
            bossHP -= DamageCalculator.claw(playerTwoClawAttackRoll, defenceRoll, playerTwoClawMaxHit);
            bossHP -= DamageCalculator.claw(playerTwoClawAttackRoll, defenceRoll, playerTwoClawMaxHit);

            for (int j = 0; j < scytheAttacks; j++) {
                bossHP -= DamageCalculator.scythe(playerOneScytheAttackRoll, defenceRoll, playerOneScytheMaxHit, size);
                bossHP -= DamageCalculator.scythe(playerTwoScytheAttackRoll, defenceRoll, playerTwoScytheMaxHit, size);
            }

            if (bossHP <= 0) {
                successfulKills++;
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double successPercentage = successfulKills / simulations * 100;
        long oneInOdds = Math.round(simulations / successfulKills);

        if (threeDown == true) {
            System.out.println("Chance of a three down: 1 in " + oneInOdds);
        } else {
            System.out.println("Chance of a four down: " + decimalFormat.format(successPercentage) + "%");
        }
    }
}
