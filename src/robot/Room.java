package robot;

public class Room {
	
	// not needed here, but in all subclasses
	protected AbstractConsolePanel consolePanel;

	protected char[][] grid;

	public Room(AbstractConsolePanel consolePanel) {
		this.consolePanel = consolePanel;
		// char array for room
		grid = new char[CONSTANTS.ROWS][CONSTANTS.COLUMNS];
		fillAll();
		createTopAndBottomBorders();
		createFrontAndEndBorders();
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

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++)
				buffer.append(grid[row][col]);
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}
	
	public void paint() throws InterruptedException {
		CONSTANTS.printThreadInfo("paint()");
	}
}
