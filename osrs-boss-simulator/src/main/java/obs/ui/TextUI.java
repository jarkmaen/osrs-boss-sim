package obs.ui;

import java.util.Scanner;

import obs.simulations.BloatSimulation;
import obs.simulations.DPSSimulation;
import obs.simulations.SotetsegSimulation;
import obs.simulations.VerzikP2Simulation;

public class TextUI {
    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose a simulation to run:");
        System.out.println("1 - Bloat Simulation");
        System.out.println("2 - DPS Simulation");
        System.out.println("3 - Sotetseg Simulation");
        System.out.println("4 - Verzik P2 Simulation");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        boolean soulflameHorn = false;
        boolean threeDown = false;
        int simulations = 1000000;

        if (choice == 1) {
            System.out.print("Three down? [y/n]: ");
            threeDown = sc.nextLine().equalsIgnoreCase("y");
        } else if (choice == 3) {
            System.out.print("Use Soulflame Horn? [y/n]: ");
            soulflameHorn = sc.nextLine().equalsIgnoreCase("y");
        }

        System.out.println("Running " + simulations + " simulations...");

        if (choice == 1) {
            new BloatSimulation().run(simulations, threeDown);
        } else if (choice == 2) {
            new DPSSimulation().run(simulations);
        } else if (choice == 3) {
            new SotetsegSimulation().run(simulations, soulflameHorn);
        } else if (choice == 4) {
            new VerzikP2Simulation().run(simulations);
        }

        sc.close();
    }
}
