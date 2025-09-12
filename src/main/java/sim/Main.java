package sim;

import sim.simulations.BloatSimulation;
import sim.simulations.DPSSimulation;

public class Main {
    public static void main(String[] args) {
        int simulations = 1000000;

        BloatSimulation sim = new BloatSimulation();
        boolean threeDown = false;
        sim.run(simulations, threeDown);

        /* DPSSimulation sim = new DPSSimulation();
        sim.run(simulations); */
    }
}
