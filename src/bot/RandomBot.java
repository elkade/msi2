package bot;

import java.util.Random;

public class RandomBot implements IBot {

	@Override
	public int getMove(Field field) {
		Random r = new Random();
		return r.nextInt(7);
	}

}
