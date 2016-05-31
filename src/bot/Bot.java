package bot;

import java.util.ArrayList;
import java.util.List;

import neural.PerceptronFourInARowNetwork;

public class Bot implements IBot {

	private static int COLS = 7;
	private static int ROWS = 6;

	private PerceptronFourInARowNetwork network;
	private int playerNumber;

	public Bot(double[] weights, int playerNumber) {
		network = new PerceptronFourInARowNetwork(weights);
		this.playerNumber = playerNumber;
	}

	public int getMove(Field field) {
		double[] discs = new double[COLS * ROWS];

		int index = 0;
		if (playerNumber == 1) {
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					discs[index++] = field.getDisc(j, i);
				}
			}
		}
		else{
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					int d = field.getDisc(j, i);
					if(d==1)d=2;
					else if(d==2)d=1;
					discs[index++] = d;
				}
			}
		}
		List<Integer> possibleMoves = new ArrayList<Integer>();
		for (int i = 0; i < COLS; i++) {
			if (field.isValidMove(i)) {
				possibleMoves.add(new Integer(i));
			}
		}

		return network.getMove(discs, possibleMoves);
	}
}
