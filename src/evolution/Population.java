package evolution;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Population {

    Individual[] individuals;
    
    public Population(Individual[] individuals){
    	this.individuals = new Individual[individuals.length];
    	for (int i = 0; i < individuals.length; i++) {
    		saveIndividual(i, individuals[i]);
		}
    }
    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
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

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
    	Individual newInd = new Individual();
        for (int i = 0; i < indiv.size(); i++) {
        	newInd.setGene(i, indiv.getGene(i));
        }
        individuals[index] = newInd;
    }
    
	public void save(String filename) {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File "+filename);
		}
		encoder.writeObject(individuals);
		encoder.close();
	}
	
	public static Population read(String filename){
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File "+filename+" not found");
		}
		Individual[] inds=(Individual[])decoder.readObject();
		return new Population(inds);
	}
}