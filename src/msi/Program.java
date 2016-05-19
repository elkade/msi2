package msi;

import java.io.IOException;

import bot.IBot;
import bot.PassiveBot;
import bot.RandomBot;
import evolution.Algorithm;
import evolution.Population;
import tournament.FinalTournament;
import tournament.ITournament;
import tournament.Tournament;

public class Program {
	
	private static final String SERIALIZED_FILE_NAME = "best_population.xml";
	private static final int POPULATION_COUNT = 100;
	
	private static Population myPop = null;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");

		System.out.println("1 - new training.");
		System.out.println("2 - read population.");
		System.out.println("3 - test population and get best.");
		
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
				test();
				break;
			default:
				break;
			}
		} while (c != 'q');
		System.out.println("Bye");
	}

	private static void test() {
		if(myPop==null)
			return;
		IBot[] bots = new IBot[2];
		bots[0] = new RandomBot();
		bots[1] = new PassiveBot();//trzeba mu ustawiæ id
		ITournament finalTournament = new FinalTournament(bots);
		
		Tester tester = new Tester(myPop, finalTournament);
		
		
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
        while (generationCount<1000)
        {
        	System.out.println("Generation: "+generationCount);
            generationCount++;
            myPop = alg.evolvePopulation(myPop);
            myPop.save(SERIALIZED_FILE_NAME);
            System.out.println(myPop.getIndividual(0));
        }
        System.out.println("Solution found!");
	}

}
