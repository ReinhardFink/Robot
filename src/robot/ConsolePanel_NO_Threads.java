package robot;

public class ConsolePanel_NO_Threads extends  AbstractConsolePanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void createScenario() {
		createSimpleScenario();
		/*
		 * a GUI repaint has to placed here, because of deadlock when call from 
		 * Main Thread for Room_WITH_SYNCED_Threads
		 */
		try {
			room.paint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startRobots() {
		for (Robot robot : robots)
			robot.run();
	}
	
	@Override
	protected void init() {
		room = new Room_NO_Threads(this);
		super.init();
	}

	private void createSimpleScenario() {
		robots.add(new Robot(2,3,CONSTANTS.DIRECTION_RIGHT,room));
		robots.add(new Robot(1,1,CONSTANTS.DIRECTION_DOWN,room));
	}
}
