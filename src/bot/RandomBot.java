package bot;

import java.io.IOException;
import java.util.Random;

public class RandomBot implements IBot {

	@Override
	public int getMove(Field field) {
		System.out.println(field.visualize());
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random r = new Random();
		return r.nextInt(7);
	}

}
