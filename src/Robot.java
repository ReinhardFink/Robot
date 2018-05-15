
public class Robot extends AbstractRobot {

	public Robot(int row, int col, int direction, Room room) {
		super(row, col, direction, room);
	}

	public void work() throws InterruptedException {
		while (free())
			move();
		
		turnLeft();
		while (free())
			move();
			
	}
}
