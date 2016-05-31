package msi;

import bot.Bot;
import bot.BotParser;
import bot.IBot;
import engine.fourinarow.FourInARow;
import evolution.Individual;

public class Tester {
	private Individual ind;
	private IBot bot;
	public Tester(Individual ind, IBot bot) {
		this.ind = ind;
		this.bot = bot;
	}
	public int performDuel(){
			try{
		        FourInARow game = new FourInARow();
		        game.TEST_BOT_1 = new BotParser(new Bot(ind.getGenes(), 1));
		        game.TEST_BOT_2 = new BotParser(bot);
		        
		        game.setupEngine(null);
		        game.runEngine();
		        String winner = game.getWinnerName();
		        if(winner==null){
		        	//System.out.println("Remis");
		        	return 1;
		        }
		        else if(winner.equals("player1")){
		        	//System.out.println("Wygra³ osobnik");
		        	return 3;
		        }
		        else if(winner.equals("player2")){
		        	//System.out.println("Wygra³ bot");
		        	return 0;
		        }
		        else throw new Exception("Bad player name");
			}
			catch(Exception ex){
				System.err.println(ex.getStackTrace());
				throw new RuntimeException(ex);
			}
	}
}
