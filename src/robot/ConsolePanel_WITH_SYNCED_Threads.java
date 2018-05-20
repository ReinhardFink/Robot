package robot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ConsolePanel_WITH_SYNCED_Threads extends AbstractConsolePanel {

	private static final long serialVersionUID = 1L;

	private Timer paintTimer;

	public ConsolePanel_WITH_SYNCED_Threads() {
		super();
		paintTimer = createPaintTimer();
	}

	@Override
	public void createScenario() {
		//createSimpleScenario();
		createProblemScenario();
	}

	@Override
	public void startRobots() {
		for (Robot robot : robots) {
			CONSTANTS.printThreadInfo("start():");
			new Thread(robot).start();
		}
		paintTimer.start();
	}

	@Override
	protected void init() {
		/*
		 * If init() is called from Reset Button we have to take care, 
		 * that all waiting Threads are notified, otherwise old room and waiting
		 * Threads will stay alive and wait and wait ... :-(
		 */
		if (room != null)
			synchronized (room) {
				room.notifyAll();
				CONSTANTS.printThreadInfo("Notify all from init()");
			}
		room = new Room_WITH_SYNCED_Threads(this);
		super.init();
	}

	private void createSimpleScenario() {
		robots.add(new Robot(2, 3, CONSTANTS.DIRECTION_RIGHT, room));
		robots.add(new Robot(1, 1, CONSTANTS.DIRECTION_DOWN, room));
	}

	private void createProblemScenario() {
		for (int col = 1; col < CONSTANTS.COLUMNS - 1; col++) {
			robots.add(new Robot(1, col, CONSTANTS.DIRECTION_DOWN, room));
			robots.add(new Robot(CONSTANTS.ROWS - 2, col, CONSTANTS.DIRECTION_UP, room));
		}
	}

	private Timer createPaintTimer() {
		Timer pTimer = new Timer(getSleepMilliSec(), new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// we have to set the Delay Time of paintTimer here,
				// because paintTimer is unknown out of this class.
				paintTimer.setDelay(getSleepMilliSec());
				outPutField.setText(room.toString());
				synchronized (room) {
					room.notifyAll();
					CONSTANTS.printThreadInfo("Notify all from Timer");
				}
				if (ConsolePanel_WITH_SYNCED_Threads.this.isStopped)
					paintTimer.stop();
			}
		});
		CONSTANTS.printThreadInfo("Timer created");
		return pTimer;
	}

}
