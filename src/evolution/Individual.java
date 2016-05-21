package evolution;

import java.io.Serializable;
import java.util.Random;

import neural.PerceptronFourInARowNetwork;

public class Individual implements Serializable {

	private static final long serialVersionUID = 1L;
	static int defaultGeneLength = PerceptronFourInARowNetwork.NEURON_COUNT;
    public double[] genes = new double[defaultGeneLength];
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

    public double[] getGenes() {
        return genes;
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
    public String toCode(){
    	StringBuilder geneString = new StringBuilder("new double[]{");
    	for (int i = 0; i < genes.length; i++) {
    		geneString.append(genes[i]);
    		geneString.append(",");
		}
    	geneString.append("}");
    	return geneString.toString();
    }
}