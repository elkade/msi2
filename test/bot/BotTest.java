package bot;

import java.util.Random;

import neural.PerceptronFourInARowNetwork;

import org.junit.Assert;
import org.junit.Test;

public class BotTest {

    double[] weights;

    @Test
    public void botConstructionTest() {
        weights = new double[PerceptronFourInARowNetwork.NEURON_COUNT];
        for (int i = 0; i < PerceptronFourInARowNetwork.NEURON_COUNT; i++) {
            weights[i] = new Random().nextDouble();
        }
        Bot bot = new Bot(weights);

        Field field = new Field(7, 6);

        int result = bot.getMove(field);
        Assert.assertTrue(result < 7);
        Assert.assertTrue(result >= 0);
    }
}
