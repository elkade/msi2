package evolution;

import java.util.Random;

public class Individual {

    static int defaultGeneLength = 16;
    private double[] genes = new double[defaultGeneLength];
    // Cache
    private double fitness = 0;

    // Create a random individual
    public void generateIndividual() {
    	Random r = new Random();
        for (int i = 0; i < size(); i++) {
            double gene = 0;//r.nextInt(10);
            genes[i] = gene;
        }
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public double getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, double value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public double getFitness(IFitness fitnessCalculator) {
        if (fitness == 0) {
            fitness = fitnessCalculator.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            geneString.append(getGene(i));
            geneString.append(";");
        }
        return geneString.toString();
    }
}