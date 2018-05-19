package robot;

public class Room_WITH_ASYN_Threads extends Room {

	public Room_WITH_ASYN_Threads(AbstractConsolePanel consolePanel) {
		super(consolePanel);
	}

	@Override
	public void paint() throws InterruptedException {
		// we need synchronization, otherwise more than one thread will write to JTextArea
		synchronized(this) { 
			consolePanel.outPutField.setText(toString());
		}
		// send Threads/Robots to sleep
		try {
			Thread.sleep(consolePanel.getSleepMilliSec());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// if program is interrupted, we interrupt/stop Threads/Robots
		if (consolePanel.isStopped) {
			Thread.currentThread().interrupt();
			throw new InterruptedException();
		}
	}	
}