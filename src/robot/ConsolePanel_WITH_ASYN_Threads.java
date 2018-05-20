package robot;

public class ConsolePanel_WITH_ASYN_Threads extends AbstractConsolePanel {

	private static final long serialVersionUID = 1L;
	
	public ConsolePanel_WITH_ASYN_Threads() {
		super();
	}

	@Override
	public void createScenario() {
		createSimpleScenario();
	}

	@Override
	public void startRobots() {
		for (Robot robot : robots) {
			CONSTANTS.printThreadInfo("start():");
			new Thread(robot).start();
		}
	}
	
	@Override
	protected void init() {
		room = new Room_WITH_ASYN_Threads(this);
		super.init();
	}
	
	private void createSimpleScenario() {
		robots.add(new Robot(2,3,CONSTANTS.DIRECTION_RIGHT,room));
		robots.add(new Robot(1,1,CONSTANTS.DIRECTION_DOWN,room));
	}
	
	private void createProblemScenario() {
		for (int col = 1; col < CONSTANTS.COLUMNS - 1; col++) {
			robots.add(new Robot(1,col,CONSTANTS.DIRECTION_DOWN,room));
			robots.add(new Robot(CONSTANTS.ROWS - 2,col,CONSTANTS.DIRECTION_UP,room));
		}
	}

}
