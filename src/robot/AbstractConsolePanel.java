package robot;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public abstract class AbstractConsolePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public JTextArea outPutField;
	private JTextField sleepMilliSecsTextField;
	
	protected Room room;	
	protected ArrayList<Robot> robots;
	protected boolean isStopped;

	public AbstractConsolePanel() {
		super();
		// create Layout
		this.setLayout(new BorderLayout());
		
		outPutField = new JTextArea();
		outPutField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		this.add(outPutField, BorderLayout.CENTER);
		createControlPanel();
		// create Robot & Room
		init();
	}
	
	public int getSleepMilliSec() {
		return Integer.parseInt(sleepMilliSecsTextField.getText());
	}
	
	public abstract void startRobots();
	
	public abstract void createScenario();

	private void createControlPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(createStartButton());
		buttonPanel.add(createStopButton());
		buttonPanel.add(createResetButton());
		buttonPanel.add(createSleepMilliSecsLabel());
		buttonPanel.add(createSleepMilliSecsTextField());
		
		this.add(buttonPanel,BorderLayout.NORTH);
	}

	private JButton createStartButton() {
		final JButton runButton = new JButton("Start");
		runButton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				AbstractConsolePanel.this.isStopped = false;
				startRobots();
			}
		});
		return runButton;
	}

	private Component createStopButton() {
		final JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbstractConsolePanel.this.isStopped = true;
			}
		});
		return stopButton;
	}

	private JButton createResetButton() {
		final JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init();
			}
		});
		return resetButton;
	}

	private JLabel createSleepMilliSecsLabel() {
		final JLabel sleepMilliSecsLabel = new JLabel("Sleep Milli Secs");
		sleepMilliSecsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return sleepMilliSecsLabel;
	}
	
	private JTextField createSleepMilliSecsTextField() {
		sleepMilliSecsTextField = new JTextField(new Integer(CONSTANTS.SLEEP_MILLI_SEC).toString());
		sleepMilliSecsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		return sleepMilliSecsTextField;
	}

	protected void init() {
		this.robots = new ArrayList<Robot>();
		this.isStopped = false;
		createScenario();
		/*
		 * problematic call to room.paint(), 
		 * because if wait() appears in paint() for synchronous Threads
		 * Main Thread is paused and never woken up again :-(
		 * so:
		 * room.paint() 
		 * is moved into createScenario() IF needed
		 * try {
		 *		room.paint();
		 * } catch (InterruptedException e) {
		 *		CONSTANTS.printThreadInfo("reset():");
		 * }
		 */
	}
}
