package robot;

public abstract class AbstractRobot implements Runnable {
	
	private static final int LEFT = 1;
	private static final int RIGHT = -1;
	
	private int row;
	private int col;
	private int direction;
	
	private Room room;

	public AbstractRobot(int row, int col, int direction, Room room) {
		this.row = row;
		this.col = col;
		this.direction = direction;
		this.room = room;
		this.room.setCharAt(row, col, CONSTANTS.DIRECTION[direction]);
	}
	
	public boolean free() {
		int row = this.row;
		int col = this.col;
		if (direction == CONSTANTS.DIRECTION_RIGHT) col++;
		else if (direction == CONSTANTS.DIRECTION_UP) row--;
		else if (direction == CONSTANTS.DIRECTION_LEFT) col--;
		else if (direction == CONSTANTS.DIRECTION_DOWN) row++;
		else System.out.println("wrong direction");
		return room.getCharAt(row, col) == CONSTANTS.CHAR_FREE;
	}
	
	public void move() throws InterruptedException {
		room.setCharAt(row, col, CONSTANTS.CHAR_FREE);
		if (direction == CONSTANTS.DIRECTION_RIGHT) col++;
		else if (direction == CONSTANTS.DIRECTION_UP) row--;
		else if (direction == CONSTANTS.DIRECTION_LEFT) col--;
		else if (direction == CONSTANTS.DIRECTION_DOWN) row++;
		else System.out.println("wrong direction");
		room.setCharAt(row, col, CONSTANTS.DIRECTION[direction]);
		room.paint();
	}
	
	public void turnLeft() throws InterruptedException {
		turn(LEFT);
	}
	
	public void turnRight() throws InterruptedException {
		turn(RIGHT);
	}	
	
	private void turn(int leftOrRight) throws InterruptedException {
		direction = (direction + leftOrRight + 4) % 4;
		room.setCharAt(row, col, CONSTANTS.DIRECTION[direction]);
		room.paint();
	}

	public abstract void work() throws InterruptedException; 

	@Override
	public void run() {
		try {
			work();
		} catch (InterruptedException e) {
			CONSTANTS.printThreadInfo("interrupt()");
		}
	}
}
