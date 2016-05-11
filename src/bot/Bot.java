package bot;

import java.util.ArrayList;
import java.util.List;

import neural.PerceptronFourInARowNetwork;

public class Bot implements IBot {

    private static int COLS = 7;
    private static int ROWS = 6;

    private PerceptronFourInARowNetwork network;

    public Bot(double[] weights) {
        network = new PerceptronFourInARowNetwork(weights);
    }

    public int getMove(Field field) {
        double[] discs = new double[COLS * ROWS];

        int index = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                discs[index++] = field.getDisc(j, i);
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
