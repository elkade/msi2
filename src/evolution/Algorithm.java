package evolution;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tournament.ITournament;

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.0;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 3;
    private static final boolean elitism = false;

    /* Public methods */
    private ITournament tournament;
    
    public Algorithm(ITournament tournament){
    	
    	this.tournament = tournament;
    }
    
    // Evolve a population
    public Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false, pop.getFitnessCalculator());
		long t = System.nanoTime();

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover

//        ExecutorService exec = Executors.newFixedThreadPool(1);
//        try {
//        	
            for (int i = elitismOffset; i < pop.size(); i++) {
            	final int j = i;
//                exec.submit(new Runnable() {
//                    @Override
//                    public void run() {
                    	System.out.println(String.format("Tournament %d",j));
                        Individual indiv1 = tournamentSelection(pop);
                        //Individual indiv2 = tournamentSelection(pop);
                        Individual newIndiv = crossover(indiv1, indiv1);
                        newPopulation.saveIndividual(j, newIndiv);
//                    }
//                });
            }
//        } finally {
//            exec.shutdown();
//        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }
        System.err.println((System.nanoTime()-t)/100000 + "ms\n");
        return newPopulation;
    }

    // Crossover individuals
    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                Random r = new Random();
                double a = -1;
                int x = r.nextInt(3);
                if(x==1)
                	a = 0;
                else if(x==2)
                	a = 1;
                indiv.setGene(i, indiv.getGene(i) + a);
            }
        }
    }

    // Select individuals for crossover
    private Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population participants = new Population(tournamentSize, false, pop.getFitnessCalculator());
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            participants.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.Perform(participants);
        return fittest;
    }
}