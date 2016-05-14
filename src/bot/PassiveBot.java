package bot;

import java.util.Random;

public class PassiveBot implements IBot {

	private static int COLS = 7;
	private static int ROWS = 6;

	private int id;
	private int enemyId;
	
	public void setId(int id) {
		this.id = id;
		if(id==1)
			enemyId = 2;
		else enemyId = 1;
	}

	@Override
	public int getMove(Field field) {
		int bestMove = -1;
		int enemyCounter = 0;
		int myCounter = 0;
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; i++) {
				int disc = field.getDisc(i, j);
				
				if(disc == 0){
					int move = searchFourVertical(field, i, j);
					if(move==id)
						return i;
					else if(move==enemyId)
						bestMove = i;
					move = searchFourHorizontal(field, i, j);
					if(move==id)
						return i;
					else if(move==enemyId)
						bestMove = i;
					move = searchFourDiagonal(field, i, j);
					if(move==id)
						return i;
					else if(move==enemyId)
						bestMove = i;
					break;
				}
				
//				if(myCounter == 3 && disc == 0)
//					return i;
//				else if(enemyCounter == 3 && disc == 0){
//					bestMove = i;
//					enemyCounter = 0;
//				}
//				else if (disc == enemyId){
//					enemyCounter++;
//					myCounter = 0;
//				}
//				else if (disc == id){
//					enemyCounter = 0;
//					myCounter++;
//				}
//				else{
//					myCounter = 0;
//					enemyCounter = 0;
//					break;
//				}
			}
		}
		
		if(bestMove==-1){
			Random r = new Random();
			return r.nextInt(7);
		}
		else return bestMove;
	}

	private int searchFourVertical(Field field, int i, int j) {
		if(j > 3){
			int d1 = field.getDisc(i, j - 1);
			int d2 = field.getDisc(i, j - 2);
			int d3 = field.getDisc(i, j - 3);
			if(d1 == d2 && d2 == d3){
				if(d1 == id)
					return id;
				else if(d1 == enemyId)
					return enemyId; 
			}
		}
		return 0;
	}
	private int searchFourHorizontal(Field field, int i, int j) {
		int k = i - 1;
		int discTypeLeft = -1;
		while(k >= 0){
			int disc = field.getDisc(k, j);
			if(disc==0) break;
			if(discTypeLeft != -1)
				discTypeLeft = disc;
			else if(disc==discTypeLeft)
				k--;
			else break;
		}
		int leftCounter = i-k - 1;
		if(leftCounter>=3 && discTypeLeft==id)
			return id;

		int discTypeRight = -1;
		while(k < COLS){
			int disc = field.getDisc(k, j);
			if(disc==0) break;
			if(discTypeRight != -1)
				discTypeRight = disc;
			else if(disc==discTypeRight)
				k++;
			else break;
		}
		int rightCounter = k - i - 1;
		if(rightCounter>=3 && discTypeRight==id)
			return id;
		if(discTypeRight == discTypeLeft)
			if(rightCounter + leftCounter >=3 )
				return discTypeRight;
		return 0;
	}
	private int searchFourDiagonal(Field field, int i, int j) {
		return 0;
	}
}
