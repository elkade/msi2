package msi;

import evolution.Individual;
import evolution.Population;
import tournament.ITournament;

public class Tester {
	private Population pop;
	private ITournament tournament;
	public Tester(Population pop, ITournament tournament) {
		this.pop = pop;
		this.tournament = tournament;
	}
	public Individual getBest(){
		return tournament.Perform(pop);
	}
}
