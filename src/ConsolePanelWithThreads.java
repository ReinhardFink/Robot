
public class ConsolePanelWithThreads extends ConsolePanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void createScenario() {
		createProblemScenario();
	}

	@Override
	public void startRobots() {
		for (Robot robot : robots) {
			CONSTANTS.printThreadInfo("start():");
			new Thread(robot).start();
		}
	}
	
	private void createProblemScenario() {
		for (int col = 1; col < CONSTANTS.COLUMNS - 1; col++) {
			robots.add(new Robot(1,col,CONSTANTS.DIRECTION_DOWN,room));
			robots.add(new Robot(CONSTANTS.ROWS - 2,col,CONSTANTS.DIRECTION_UP,room));
		}
	}

}
