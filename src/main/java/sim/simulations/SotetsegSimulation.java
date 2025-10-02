package sim.simulations;

import java.text.DecimalFormat;

import sim.bosses.Sotetseg;
import sim.util.DamageCalculator;
import sim.util.Pair;
import sim.util.RandomNumberGenerator;

public class SotetsegSimulation {

    private int playerOneScytheAttackRoll = 33376;
    private int playerOneScytheMaxHit = 49;
    private int playerOneElderMaulAttackRoll = 34866;
    private int playerOneElderMaulMaxHit = 67;

    private int playerTwoScytheAttackRoll = 33415;
    private int playerTwoScytheMaxHit = 46;
    private int playerTwoElderMaulAttackRoll = 35045;
    private int playerTwoElderMaulMaxHit = 65;

    public void run(int simulations, boolean soulflameHorn) {
        Sotetseg sotetseg = new Sotetseg();
        int size = sotetseg.getSize();

        double successfulKills = 0;

        for (int i = 0; i < simulations; i++) {
            int ballAttacks = 1;
            int bossHP = sotetseg.getHP();
            int defenceRoll = sotetseg.getDefenceRoll();

            if (soulflameHorn == true) {
                bossHP -= DamageCalculator.hitGuaranteed(playerOneElderMaulMaxHit);
                bossHP -= DamageCalculator.hitGuaranteed(playerTwoElderMaulMaxHit);
                defenceRoll = 14606;
            } else {
                Pair<Integer, Boolean> playerOneElderMaulSpec = DamageCalculator.elderMaul(playerOneElderMaulAttackRoll,
                        defenceRoll, playerOneElderMaulMaxHit);

                if (playerOneElderMaulSpec.getValue() == true) {
                    bossHP -= playerOneElderMaulSpec.getKey();
                    defenceRoll = 18626;
                }

                Pair<Integer, Boolean> playerTwoElderMaulSpec = DamageCalculator.elderMaul(playerTwoElderMaulAttackRoll,
                        defenceRoll, playerTwoElderMaulMaxHit);

                if (playerTwoElderMaulSpec.getValue() == true) {
                    bossHP -= playerTwoElderMaulSpec.getKey();
                    if (playerOneElderMaulSpec.getValue() == true) {
                        defenceRoll = 14606;
                    } else {
                        defenceRoll = 18626;
                    }
                }
            }

            if (RandomNumberGenerator.getRandom(1, 3) <= 2) {
                ballAttacks++;
            }

            boolean bigBallTriggered = false;

            while (bossHP > 0) {
                bossHP -= DamageCalculator.scythe(playerOneScytheAttackRoll, defenceRoll, playerOneScytheMaxHit, size);
                bossHP -= DamageCalculator.scythe(playerTwoScytheAttackRoll, defenceRoll, playerTwoScytheMaxHit, size);

                if (RandomNumberGenerator.getRandom(1, 3) <= 2) {
                    ballAttacks++;

                    if (ballAttacks >= 11) {
                        bigBallTriggered = true;
                        break;
                    }
                }
            }

            if (bigBallTriggered == true) {
                bossHP -= DamageCalculator.scythe(playerOneScytheAttackRoll, defenceRoll, playerOneScytheMaxHit, size);
                bossHP -= DamageCalculator.scythe(playerOneScytheAttackRoll, defenceRoll, playerOneScytheMaxHit, size);
                bossHP -= DamageCalculator.scythe(playerTwoScytheAttackRoll, defenceRoll, playerTwoScytheMaxHit, size);
                bossHP -= DamageCalculator.scythe(playerTwoScytheAttackRoll, defenceRoll, playerTwoScytheMaxHit, size);
            }

            if (bossHP <= 0) {
                successfulKills++;
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double successPercentage = successfulKills / simulations * 100;
        String successPercentageFormatted = decimalFormat.format(successPercentage);

        if (soulflameHorn == true) {
            System.out.println("Chance of skipping phase one with soulflame horn: " + successPercentageFormatted + "%");
        } else {
            System.out.println("Chance of skipping phase one: " + successPercentageFormatted + "%");
        }
    }
}
