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
	public void performDuel(){
			try{
		        FourInARow game = new FourInARow();
		        game.TEST_BOT_1 = new BotParser(new Bot(ind.getGenes()));
		        game.TEST_BOT_2 = new BotParser(bot);
		        
		        game.setupEngine(null);
		        game.runEngine();
		        String winner = game.getWinnerName();
		        if(winner==null)
		        	System.out.println("Remis");
		        else if(winner.equals("player1"))
		        	System.out.println("Wygra³ osobnik");
		        else if(winner.equals("player2"))
		        	System.out.println("Wygra³ bot");
		        else throw new Exception("Bad player name");
			}
			catch(Exception ex){
				System.err.println(ex.getStackTrace());
				throw new RuntimeException(ex);
			}
	}
}
