package sim.simulations;

import java.util.ArrayList;

import javafx.application.Application;

import sim.bosses.Nylo;
import sim.bosses.VerzikP2;
import sim.combat.DamageCalculator;
import sim.ui.VerzikP2Chart;
import sim.util.Pair;

public class VerzikP2Simulation {

    private int scytheAttackRoll = 33376;
    private int scytheMaxHit = 49;

    private int simulateFight(int simulations, int nyloThresholdPercentage) {
        Nylo nylo = new Nylo();
        int nyloDefenceRoll = nylo.getDefenceRoll();
        int nyloSize = nylo.getSize();

        VerzikP2 verzik = new VerzikP2();
        int verzikDefenceRoll = verzik.getDefenceRoll();
        int verzikSize = verzik.getSize();

        int totalNetDamage = 0;

        for (int i = 0; i < simulations; i++) {
            int nyloHP = nylo.getHP();
            int verzikHP = verzik.getHP();

            nyloHP -= DamageCalculator.scythe(scytheAttackRoll, nyloDefenceRoll, scytheMaxHit, nyloSize);
            nyloHP -= DamageCalculator.scythe(scytheAttackRoll, nyloDefenceRoll, scytheMaxHit, nyloSize);

            for (int j = 0; j < 6; j++) {
                double nyloHPPercentage = (double) nyloHP / nylo.getHP() * 100;

                if (nyloHPPercentage >= nyloThresholdPercentage) {
                    nyloHP -= DamageCalculator.scythe(scytheAttackRoll, nyloDefenceRoll, scytheMaxHit, nyloSize);
                } else {
                    verzikHP -= DamageCalculator.scythe(scytheAttackRoll, verzikDefenceRoll, scytheMaxHit,
                            verzikSize);
                }
            }

            if (nyloHP < 0) {
                nyloHP = 0;
            }

            totalNetDamage += verzik.getHP() - verzikHP - nyloHP;
        }

        return totalNetDamage;
    }

    public void run(int simulations) {
        ArrayList<Pair<Integer, Double>> results = new ArrayList<>();
        for (int nyloThresholdPercentage = 5; nyloThresholdPercentage <= 70; nyloThresholdPercentage++) {
            int totalNetDamage = simulateFight(simulations, nyloThresholdPercentage);

            double averageNetDamage = (double) totalNetDamage / simulations;

            if (nyloThresholdPercentage % 5 == 0) {
                System.out.printf("Nylo threshold: %2d%% | Avg net damage: %.2f%n", nyloThresholdPercentage,
                        averageNetDamage);
            }

            results.add(new Pair<>(nyloThresholdPercentage, averageNetDamage));
        }

        VerzikP2Chart.setData(results);
        Application.launch(VerzikP2Chart.class);
    }
}
