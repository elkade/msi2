package msi;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import evolution.Algorithm;
import evolution.IFitness;
import evolution.Population;
import evolution.TestEvaluator;
import tournament.ITournament;
import tournament.Tournament;

public class Program {
	
	private static final String SERIALIZED_FILE_NAME = "best_population.xml";
	private static final int POPULATION_COUNT = 7;
	
	private static Population myPop = null;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");

		System.out.println("1 - new training.");
		System.out.println("2 - read population.");
		
		char c = '1';
		do {
			c = (char)System.in.read();
			switch (c) {
			case '1':
				newTraining();
				break;
			case '2':
				readPopulation();
				break;
			case '3':
				//getBestIndividual();
				break;
			default:
				break;
			}
		} while (c != 'q');
		System.out.println("Bye");
	}

	private static void readPopulation() {
		myPop = Population.read(SERIALIZED_FILE_NAME);
		System.out.println("Population read!");
	}

	static void newTraining(){
		System.out.println("Evolution start!");
		if(myPop == null)
			myPop = new Population(POPULATION_COUNT, true);
        ITournament tournament = new Tournament();
        Algorithm alg = new Algorithm(tournament);
        int generationCount = 0;
        while (generationCount<10)
        {
            generationCount++;
            myPop = alg.evolvePopulation(myPop);
            myPop.save(SERIALIZED_FILE_NAME);
        }
        System.out.println("Solution found!");
	}

}
