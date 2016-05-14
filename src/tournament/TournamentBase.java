package tournament;

import engine.fourinarow.FourInARow;
import evolution.Individual;
import evolution.Population;

public abstract class TournamentBase implements ITournament {
	public abstract Individual Perform(Population participants);
	
	protected Individual getWinner(Individual ind1, Individual ind2) {
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
	
}
