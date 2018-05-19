package robot;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main extends JFrame {

	private static final long serialVersionUID = 3546365024081097528L;
	
	private static final boolean GUI_IN_OWN_THREAD = true;

	public static void main(String args[]) {
		// create GUI in its own thread
		if (GUI_IN_OWN_THREAD)
			createAndShowGUI();
		else
		// create GUI in AWT-Dispatching Thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	};

	static void createAndShowGUI() {
		JFrame aFrame = new JFrame("Roboter Simulator");
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.setSize(new Dimension(700, 600));
		
		// the easy way, to simulate a normal console WITHOU Threads
		//aFrame.setContentPane(new ConsolePanelNoThreads());
		
		// the easy way, to simulate a normal console WITH Threads
		aFrame.setContentPane(new ConsolePanelWithThreads());
		
		aFrame.setLocation(130, 30);
		aFrame.setVisible(true);
	}

	public void init() {
		setContentPane(null);
	}
}
