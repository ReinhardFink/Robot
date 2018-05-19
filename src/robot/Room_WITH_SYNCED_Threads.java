package robot;

public class Room_WITH_SYNCED_Threads extends Room {

	public Room_WITH_SYNCED_Threads(AbstractConsolePanel consolePanel) {
		super(consolePanel);
	}

	@Override
	public void paint() throws InterruptedException {
		/*
		 * If Robot Thread reaches paint(), it has to wait until the paintTimer
		 * calls notifyAll() after GUI was repainted
		 */
		synchronized (this) {
			this.wait();
		}
		/*
		 * All Threads are interrupted, if STOP button is presssed
		 */
		if (consolePanel.isStopped) {
			Thread.currentThread().interrupt();
			throw new InterruptedException();
		}
	}
}