import javax.swing.SwingUtilities;

public class Room {

	private char[][] grid;

	private ConsolePanel consolePanel;

	public Room(ConsolePanel consolePanel) {
		// char array for room
		grid = new char[CONSTANTS.ROWS][CONSTANTS.COLUMNS];
		fillAll();
		createTopAndBottomBorders();
		createFrontAndEndBorders();

		this.consolePanel = consolePanel;
	}

	public void setCharAt(int row, int col, char c) {
		grid[row][col] = c;
	}

	public char getCharAt(int row, int col) {
		return grid[row][col];
	}

	private void createFrontAndEndBorders() {
		for (int row = 0; row < grid.length; row++) {
			grid[row][0] = CONSTANTS.CHAR_WALL;
			grid[row][CONSTANTS.COLUMNS - 1] = CONSTANTS.CHAR_WALL;
		}
	}

	private void createTopAndBottomBorders() {
		for (int col = 0; col < grid[0].length; col++) {
			grid[0][col] = CONSTANTS.CHAR_WALL;
			grid[CONSTANTS.ROWS - 1][col] = CONSTANTS.CHAR_WALL;
		}
	}

	private void fillAll() {
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
				grid[row][col] = CONSTANTS.CHAR_FREE;
	}

	public void paint() throws InterruptedException {
		CONSTANTS.printThreadInfo("paint()");
		// we need synchronization, otherwise more than one thread will write to JTextArea
		synchronized(this) { 
			consolePanel.outPutField.setText(toString());
		}
		try {
			Thread.sleep(consolePanel.getSleepMilliSec());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		if (consolePanel.isStopped) {
			Thread.currentThread().interrupt();
			throw new InterruptedException();
		}
	}	
	
	/*
	 * in NOT_WORKING_0X_paint() because:
	 * repaint Event is placed on AWT-Event-Dispatcher Thread
	 * AFTER current actionPerformed() Event
	 * and therefore Robots finish there work and THEN the result is painted.
	 */
	public void NOT_WORKING_01_paint() {
		System.out.println("calling paint -> Thread Info: " + Thread.currentThread().toString());
		consolePanel.outPutField.setText(Room.this.toString());
		consolePanel.outPutField.repaint();
	}
	
	public void NOT_WORKING_02_paint() {
		System.out.println("calling paint -> Thread Info: " + Thread.currentThread().toString());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				consolePanel.outPutField.setText(Room.this.toString());
			}
			
		});
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++)
				buffer.append(grid[row][col]);
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}
}