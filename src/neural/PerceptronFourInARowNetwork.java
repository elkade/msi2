package neural;

import java.util.ArrayList;
import java.util.List;

import org.neuroph.nnet.MultiLayerPerceptron;

public class PerceptronFourInARowNetwork extends MultiLayerPerceptron {
    private static final long serialVersionUID = -3767405275445338791L;

    private static int COLS = 7;
    private static int ROWS = 6;

    private static int INPUT_COUNT = COLS * ROWS;
    private static int HIDDEN_COUNT = 10;
    private static int OUTPUT_COUNT = COLS;

    private static List<Integer> layersCount;

    static {
        layersCount = new ArrayList<Integer>();
        layersCount.add(new Integer(INPUT_COUNT));
        layersCount.add(new Integer(HIDDEN_COUNT));
        layersCount.add(new Integer(OUTPUT_COUNT));
    }

    public PerceptronFourInARowNetwork(double[] weights) {
        super(layersCount);

        setWeights(weights);
    }

    public int getMove(double[] input, List<Integer> possibleMoves) {
        setInput(input);
        calculate();
        double[] output = getOutput();

        double bestNeuron = Double.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < output.length; i++) {
            if (possibleMoves.contains(new Integer(i)) && output[i] > bestNeuron) {
                bestMove = i;
                bestNeuron = output[i];
            }
        }
        return bestMove;
    }

}
