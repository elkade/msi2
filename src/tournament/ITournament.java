package tournament;

import evolution.Individual;
import evolution.Population;

public interface ITournament {
	Individual Perform(Population participants);
}
