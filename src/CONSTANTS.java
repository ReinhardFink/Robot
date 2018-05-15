public class CONSTANTS {
	
	public static final boolean THREAD_MESSAGE = false;
	public static final int SLEEP_MILLI_SEC = 100;
	
	// Dimension of our room
	// rows
	public static final int ROWS = 30;
	// columns
	public static final int COLUMNS = 90; 
	
	// characters to show room
	public static final char CHAR_WALL = '#';
	public static final char CHAR_FREE = '.';
	
	// directions rotate mathematical positiv
	public static final int DIRECTION_RIGHT = 0;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_DOWN = 3;
	
	// characters to show robot
	public static final char CHAR_ROBOT_RIGHT = '>';
	public static final char CHAR_ROBOT_UP = '^';
	public static final char CHAR_ROBOT_LEFT = '<';
	public static final char CHAR_ROBOT_DOWN = 'v';
	
	// Array to access CHAR_ROBOT_* via DIRECTION_*
	public static final char DIRECTION[] ={CHAR_ROBOT_RIGHT, CHAR_ROBOT_UP, CHAR_ROBOT_LEFT, CHAR_ROBOT_DOWN};
	
	public static void printThreadInfo(String message) {
		if (THREAD_MESSAGE) {
			System.out.println(message + " -> Thread Info: " + Thread.currentThread().toString());
			int numberOfRunningThreads = 0;
			for (Thread t : Thread.getAllStackTraces().keySet()) {
				if (t.getState()==Thread.State.RUNNABLE) numberOfRunningThreads++;
			}
			System.out.println("All Threads: "+ Thread.getAllStackTraces().keySet().size());
			System.out.println("Running Threads: "+ numberOfRunningThreads);
		}
	}
}
