package robot;

public class ConsolePanelNoThreads extends  ConsolePanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void createScenario() {
		createSimpleScenario();
	}

	@Override
	public void startRobots() {
		for (Robot robot : robots)
			robot.run();
	}

	private void createSimpleScenario() {
		robots.add(new Robot(2,3,CONSTANTS.DIRECTION_RIGHT,room));
		robots.add(new Robot(1,1,CONSTANTS.DIRECTION_DOWN,room));
	}
}
