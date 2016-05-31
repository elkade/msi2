package msi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import bot.PassiveBot;
import evolution.Algorithm;
import evolution.Individual;
import evolution.Population;
import tournament.ITournament;
import tournament.Tournament;

public class Program {
	
	private static final String SERIALIZED_FILE_NAME = "best_population.xml";
	private static final int POPULATION_COUNT = 100;
	private static final int GENERATIONS_MAX = 1000;
	private static final int SINGLE_TEST_ITERATIONS = 1000;
	private static final int TEST_INTERVAL = 10;
	
	private static Population myPop = null;
	private static Individual best = null;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Hello");

		System.out.println("1 - new training.");
		System.out.println("2 - read population.");
		System.out.println("3 - test population and get best.");
		System.out.println("4 - test best with smart bot.");
		System.out.println("5 - perform full test.");
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
				findBest();
				break;
			case '4':
				testBest();
				break;
			case '5':
				fullTest();
				break;
			default:
				break;
			}
		} while (c != 'q');
		System.out.println("Bye");
	}
	private static void fullTest() throws IOException {
		System.out.println("Evolution start!");
		if(myPop == null)
			myPop = new Population(POPULATION_COUNT, true);
        ITournament tournament = new Tournament();
        Algorithm alg = new Algorithm(tournament);
        int generationCount = 0;
        ArrayList<Double> scores = new ArrayList<Double>(); 
        final Reader rdr = new InputStreamReader(System.in);
        final Scanner s = new Scanner(rdr);
        while (generationCount<GENERATIONS_MAX)
        {
        	System.out.println("Generation: "+generationCount);
            generationCount++;
            myPop = alg.evolvePopulation(myPop);
            System.err.println(myPop.getIndividual(0).toCode());
            if(rdr.ready() && s.nextLine().equals("q"))
            	break;
            if(generationCount%TEST_INTERVAL==0)
            {
            	findBest();
            	int score = 0;
            	for (int i = 0; i < SINGLE_TEST_ITERATIONS; i++) {
					score += testBest();
				}
            	scores.add((double)score/(3*SINGLE_TEST_ITERATIONS));
                for (Double sc : scores) {
                	System.out.print(sc + " ");
        		}
                System.out.print("\n");
            }
        }
        myPop.save(SERIALIZED_FILE_NAME);
        System.out.println("Solution found! Scores:");
        for (Double sc : scores) {
        	System.out.print(sc + " ");
		}
       
	}
	
	
	
	
	private static int testBest() {
		if(myPop==null)
			return 0;
		PassiveBot bot = new PassiveBot();
		bot.setId(2);

		Tester tester = new Tester(best, bot);
		
		return tester.performDuel();

	}
	private static void findBest() {
		if(myPop==null)
			return;
		ITournament tournament = new Tournament(false);

		best = tournament.Perform(myPop);
		if(best==null)
			best = myPop.getIndividual(new Random().nextInt(myPop.size()));
		System.out.println("Best:");
		System.out.println(best.toCode());
	}

	private static void readPopulation() {
		myPop = Population.read(SERIALIZED_FILE_NAME);
		System.out.println("Population read!");
	}

	static void newTraining() throws IOException{
		System.out.println("Evolution start!");
		if(myPop == null)
			myPop = new Population(POPULATION_COUNT, true);
        ITournament tournament = new Tournament();
        Algorithm alg = new Algorithm(tournament);
        int generationCount = 0;
        
        final Reader rdr = new InputStreamReader(System.in);
        final Scanner s = new Scanner(rdr);
        while (generationCount<GENERATIONS_MAX)
        {
        	System.out.println("Generation: "+generationCount);
            generationCount++;
            myPop = alg.evolvePopulation(myPop);
            System.err.println(myPop.getIndividual(0).toCode());
            if(rdr.ready() && s.nextLine().equals("q"))
            	break;
        }
        myPop.save(SERIALIZED_FILE_NAME);
        System.out.println("Solution found!");
	}

}
