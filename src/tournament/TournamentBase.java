package tournament;

import bot.Bot;
import bot.BotParser;
import engine.fourinarow.FourInARow;
import evolution.Individual;
import evolution.Population;

public abstract class TournamentBase implements ITournament {
	public abstract Individual Perform(Population participants);
	
	protected Individual getWinner(Individual ind1, Individual ind2) {
		try{
	        FourInARow game = new FourInARow();
	        game.TEST_BOT_1 = new BotParser(new Bot(ind1.getGenes()));
	        game.TEST_BOT_2 = new BotParser(new Bot(ind2.getGenes()));
	        
	        game.setupEngine(null);
	        game.runEngine();
	        String winner = game.getWinnerName();
	        if(winner==null)return null;
	        else if(winner.equals("player1"))return ind1;
	        else if(winner.equals("player2")) return ind2;
	        throw new Exception("Bad player name");
		}
		catch(Exception ex){
			System.err.println(ex.getStackTrace());
			throw new RuntimeException(ex);
		}
	}
	
}
