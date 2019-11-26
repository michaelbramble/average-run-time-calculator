import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Averages{
	
	protected static String output;
	static File file = new File("resources/runs.txt");
	
	public Averages() {
		
		int hours = 0, minutes = 0, seconds = 0, runCount = 0;
		
		try {
			//scanner checks for colons in h:mm:ss format and new lines to separate runs
			Scanner scan = new Scanner(file);
			scan.useDelimiter(":|\\r\n");

			while(scan.hasNextLine()) {
				hours = hours + scan.nextInt();
				minutes = minutes + scan.nextInt();
				seconds = seconds + scan.nextInt();
				runCount = runCount + 1;
			}

			scan.close();

			//math
			int avgRun = ((hours * 3600) + (minutes * 60) + seconds) / runCount;
			int avgHours = avgRun / 3600;
			int avgMinutes = (avgRun - avgHours * 3600) / 60;
			int avgSeconds = (avgRun - avgHours * 3600) - avgMinutes * 60;

			output = "The average time of your " + runCount + " runs is " + avgHours + ":" + avgMinutes + ":" + avgSeconds + ".";

		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Average Run Calculator");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter each run on a new line in HH:MM:SS format.");
		
		//opens text file with run times
		JButton button1 = new JButton();
		button1.setText("Edit Run Times");
		button1.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					Desktop.getDesktop().open(file);
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//text field where result shows up
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		txt.setVisible(true);
		txt.setText(null);
		
		//calculate average button
		JButton button2 = new JButton();
		button2.setText("Calculate Average");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				new Averages();
				txt.setText(Averages.output);
			}
		});
		
		//add elements to panel
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		panel.add(txt);

		//add panel to frame and set window properties
		frame.add(panel);
		frame.setSize(310, 110);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}