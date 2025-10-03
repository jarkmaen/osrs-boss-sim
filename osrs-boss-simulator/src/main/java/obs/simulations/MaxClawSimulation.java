package obs.simulations;

import java.text.DecimalFormat;

import obs.bosses.Boss;
import obs.bosses.BossType;
import obs.util.DamageCalculator;

public class MaxClawSimulation {

    private int attackRoll = 23244;
    private int maxHit = 45;
    private int maxHitBloat = 51;

    public void run(int simulations) {
        Boss bloat = BossType.BLOAT.getBoss();
        Boss maiden = BossType.MAIDEN.getBoss();
        Boss nylocasMatomenos = BossType.NYLOCAS_MATOMENOS.getBoss();
        Boss nylocasPrinkipas = BossType.NYLOCAS_PRINKIPAS.getBoss();
        Boss nylocasVasilias = BossType.NYLOCAS_VASILIAS.getBoss();
        Boss verzik = BossType.VERZIK_P3.getBoss();
        Boss xarpus = BossType.XARPUS.getBoss();

        int maxDamage = 89;
        int maxDamageBloat = 100;

        double successfulRuns = 0;

        for (int i = 0; i < simulations; i++) {
            boolean maxClaw = false;

            if (DamageCalculator.claw(attackRoll, maiden.getDefenceRoll(), maxHit) == maxDamage) {
                maxClaw = true;
            }

            for (int j = 0; j < 3; j++) {
                if (DamageCalculator.claw(attackRoll, bloat.getDefenceRoll(), maxHitBloat) == maxDamageBloat) {
                    maxClaw = true;
                }
            }

            for (int j = 0; j < 2; j++) {
                if (DamageCalculator.claw(attackRoll, nylocasPrinkipas.getDefenceRoll(), maxHit) == maxDamage) {
                    maxClaw = true;
                }
            }

            if (DamageCalculator.claw(attackRoll, nylocasVasilias.getDefenceRoll(), maxHit) == maxDamage) {
                maxClaw = true;
            }

            if (DamageCalculator.claw(attackRoll, xarpus.getDefenceRoll(), maxHit) == maxDamage) {
                maxClaw = true;
            }

            if (DamageCalculator.claw(attackRoll, nylocasMatomenos.getDefenceRoll(), maxHit) == maxDamage) {
                maxClaw = true;
            }

            for (int j = 0; j < 4; j++) {
                if (DamageCalculator.claw(attackRoll, verzik.getDefenceRoll(), maxHit) == maxDamage) {
                    maxClaw = true;
                }
            }

            if (maxClaw == true) {
                successfulRuns++;
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double successPercentage = successfulRuns / simulations * 100;
        String successPercentageFormatted = decimalFormat.format(successPercentage);

        System.out.println("Chance of seeing at least one max claw: " + successPercentageFormatted + "%");
    }
}
