package evolution;


public class Population {

    Individual[] individuals;
    
    IFitness fitnessCalculator;
    
    IFitness getFitnessCalculator(){
    	
    	return fitnessCalculator;
    	
    }
    
    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise, IFitness fitnessCalculator) {
        individuals = new Individual[populationSize];
        this.fitnessCalculator = fitnessCalculator;
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness(fitnessCalculator) <= getIndividual(i).getFitness(fitnessCalculator)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}