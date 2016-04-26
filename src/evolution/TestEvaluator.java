package evolution;

public class TestEvaluator implements IFitness {

	@Override
	public double getFitness(Individual ind) {
		double v = 0;
		for (int i = 2; i < ind.size(); i++) {
			if(ind.getGene(i)>ind.getGene(i-1))
				v+=0.5;

			if(ind.getGene(i)>0)
				v+=0.5;
		}
		return v;
	}

}
