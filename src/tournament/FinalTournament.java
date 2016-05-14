package tournament;

import java.util.HashMap;
import java.util.Map;

import bot.IBot;
import evolution.Individual;
import evolution.Population;

public class FinalTournament extends TournamentBase {

	private IBot[] testBots;
	
	public FinalTournament(IBot[] testBots) {
		this.testBots = testBots;
	}

	public Individual Perform(Population participants){
		Map<Individual, Integer> scores = new HashMap<Individual, Integer>();
		for (int i = 0; i < participants.size(); i++) {
			for (int j = 1; j < participants.size(); j++) {
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
