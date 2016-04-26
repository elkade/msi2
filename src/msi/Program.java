package msi;

import java.io.IOException;

import evolution.*;

public class Program {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");

		System.out.println("1 - new training.");

		char c = '1';
		do {
			c = (char)System.in.read();
			switch (c) {
			case '1':
				newTraining();
				break;

			default:
				break;
			}
		} while (c != 'q');
		System.out.println("Bye");
	}

	static void newTraining(){
		System.out.println("Evolution start!");
        // Set a candidate solution
        //FitnessCalculator.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
		IFitness fitnessCalc = new TestEvaluator();// new FitnessCalculator();
        // Create an initial population
        Population myPop = new Population(1000, true, fitnessCalc);
        Algorithm alg = new Algorithm();
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (generationCount<10000)//(myPop.getFittest().getFitness(fitnessCalc) < FitnessCalculator.getMaxFitness(fitnessCalc))
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
