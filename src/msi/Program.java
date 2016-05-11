package msi;

import java.io.IOException;

import evolution.Algorithm;
import evolution.IFitness;
import evolution.Population;
import evolution.TestEvaluator;
import tournament.ITournament;
import tournament.Tournament;

public class Program {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");

		System.out.println("1 - new training.");

		char c = '1';
		do {
			
			switch (c) {
			case '1':
				newTraining();
				break;

			default:
				break;
			}
			c = (char)System.in.read();
		} while (c != 'q');
		System.out.println("Bye");
	}

	static void newTraining(){
		System.out.println("Evolution start!");
        // Set a candidate solution
        //FitnessCalculator.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
		IFitness fitnessCalc = new TestEvaluator();// new FitnessCalculator();
        // Create an initial population
        Population myPop = new Population(10, true, fitnessCalc);
        ITournament tournament = new Tournament();
        Algorithm alg = new Algorithm(tournament);
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (generationCount<10)//(myPop.getFittest().getFitness(fitnessCalc) < FitnessCalculator.getMaxFitness(fitnessCalc))
        {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness(fitnessCalc));
            System.out.println(myPop.getFittest());
            myPop = alg.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
	}

}
