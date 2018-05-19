package robot;
import javax.swing.SwingUtilities;

public class Room_NO_Threads extends Room {
	
	public Room_NO_Threads(AbstractConsolePanel consolePanel) {
		super(consolePanel);
	}
	
	/*
	 * in NOT_WORKING_0X_paint() because:
	 * repaint Event is placed on AWT-Event-Dispatcher Thread
	 * AFTER current actionPerformed() Event
	 * and therefore Robots finish there work and THEN the result is painted.
	 */
	public void paint1() {
		CONSTANTS.printThreadInfo("paint()");
		System.out.println("paint() -> Thread Info: " + Thread.currentThread().toString());
		consolePanel.outPutField.setText(Room_NO_Threads.this.toString());
		consolePanel.outPutField.repaint();
		try {
			Thread.sleep(consolePanel.getSleepMilliSec());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void paint() {
		CONSTANTS.printThreadInfo("Show, what is happening in paint()");
		System.out.println("paint() -> Thread Info: " + Thread.currentThread().toString());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				consolePanel.outPutField.setText(Room_NO_Threads.this.toString());
			}
			
		});
		try {
			Thread.sleep(consolePanel.getSleepMilliSec());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}