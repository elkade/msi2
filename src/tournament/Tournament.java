package tournament;

import java.util.HashMap;
import java.util.Map;

import engine.fourinarow.FourInARow;
import evolution.Individual;
import evolution.Population;

public class Tournament implements ITournament {
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

	private Individual getWinner(Individual ind1, Individual ind2) {
		try{
	        FourInARow game = new FourInARow();
	        String path = "java -cp C:\\Users\\Lukas\\Documents\\workspace_msi\\msi\\bin;C:\\Users\\Lukas\\Documents\\workspace_msi\\msi\\lib\\neuroph-core-2.92.jar;C:\\Users\\Lukas\\Documents\\workspace_msi\\msi\\lib\\slf4j-api-1.7.21.jar bot.BotStarter ";
	        game.TEST_BOT_1 = path + ind1.toString();
	        game.TEST_BOT_2 = path + ind2.toString();
	
	        game.DEV_MODE = true;
	        
	        game.setupEngine(null);
	        game.runEngine();
	        String winner = game.getWinnerName();
	        
	        if(winner.equals("player1"))return ind1;
	        else if(winner.equals("player2")) return ind2;
	        throw new Exception("Bad player name");
		}
		catch(Exception ex){
			System.err.println(ex.getStackTrace());
			return ind1;
		}
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
