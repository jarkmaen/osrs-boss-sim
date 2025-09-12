package sim;

import sim.simulations.BloatSimulation;
import sim.simulations.DPSSimulation;
import sim.simulations.SotetsegSimulation;
import sim.simulations.VerzikP2Simulation;

public class Main {
    public static void main(String[] args) {
        int simulations = 1000000;

        // BloatSimulation sim = new BloatSimulation();
        // boolean threeDown = false;
        // sim.run(simulations, threeDown);

        // DPSSimulation sim = new DPSSimulation();
        // sim.run(simulations);

        // SotetsegSimulation sim = new SotetsegSimulation();
        // boolean soulflameHorn = true;
        // sim.run(simulations, soulflameHorn);

        VerzikP2Simulation sim = new VerzikP2Simulation();
        sim.run(simulations);
    }
}
