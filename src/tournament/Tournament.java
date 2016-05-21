package tournament;

import java.util.HashMap;
import java.util.Map;

import evolution.Individual;
import evolution.Population;

public class Tournament extends TournamentBase {
	private boolean log = false;
	public Tournament(){
		
	}
	public Tournament(boolean log){
		this.log = log;
	}
	@Override
	public Individual Perform(Population participants){
		Map<Individual, Integer> scores = new HashMap<Individual, Integer>();
		int k = 0, m = participants.size() - 1, n = participants.size(), s = m*n/2;
		for (int i = 0; i < m; i++) {
			for (int j = i+1; j < n; j++) {
				if(log)
					System.out.println("Tournament: "+ (++k) +" of "+s);
				long t = System.nanoTime();
				Individual winner = getWinner(participants.getIndividual(i), participants.getIndividual(j));
				//System.err.println((System.nanoTime()-t)/100000 + "ms\n");
				Integer score = scores.get(winner);
				if(score==null)scores.put(winner, 1);
				else {
					if(score==participants.size())
						return winner;
					scores.put(winner, score + 1);
				}
			}
		}
		return getBest(scores);
	}
	
	private Individual getBest(Map<Individual, Integer> map){
		Map.Entry<Individual, Integer> maxEntry = null;

		for (Map.Entry<Individual, Integer> entry : map.entrySet()){
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
		        maxEntry = entry;
		    }
		}
		return maxEntry.getKey();
	}
}
