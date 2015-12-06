import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * @(#)FirstWindowFrame.java
 *
 * ProjectileMotion 
 * Frame that welcomes users
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class FirstWindowFrame extends JFrame 
{
	JButton start,about,exit;
	JLabel exitLabel;
	JPanel panel;
	
	//--------------------------------------------
	// This frame is used in order to make seperate windows for AboutPanel and StartPanel
	//--------------------------------------------
	public FirstWindowFrame()
	{
		setTitle("Welcome");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,2));
		
		//--------------------------------------------
		// Ýnitializing Properties
		//--------------------------------------------
		start = new JButton("Start");
		about = new JButton("About");
		exit = new JButton("Exit");
		exitLabel = new JLabel();
		panel = new JPanel();
				
		//--------------------------------------------
		// Setting the Buttons Dimension
		//--------------------------------------------
		start.setPreferredSize(new Dimension( 300,200 ));
		about.setPreferredSize(new Dimension( 300,200 ));
		exit.setPreferredSize(new Dimension( 600,200 ));
		
		//--------------------------------------------
		// Adding Listeners to Buttons
		//--------------------------------------------
		ActionListener listener = new firstListener();
		start.addActionListener(listener);
		about.addActionListener(listener);
		exit.addActionListener(listener);
		
		//--------------------------------------------
		// Adding properties to panel
		//--------------------------------------------
		panel.add(start);
		panel.add(about);
		panel.add(exit);
		
		panel.setVisible(true);
		getContentPane().add(panel);
		add(panel);
			
		setVisible(true);
		setSize(700,500);
		panel.setBackground(new  Color(255,255,255));
	
	}
	
	//--------------------------------------------
	// Making new frames for Start and About
	//--------------------------------------------
	public void activateStart()
	{
		JFrame startFrame = new MainWindowFrame();
	}
	
	public void activateAbout()
	{
		JFrame aboutFrame = new AboutWindowFrame();
	}
	
	//--------------------------------------------
	// the button listener method
	//--------------------------------------------
	private class firstListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==start)
			{
				activateStart();
			}
			if(e.getSource()==about)
			{
				activateAbout();
			}
			if(e.getSource()==exit)
			{
				panel.setVisible(false);
				exitLabel.setText("Have a nice Day. Come Back Soon");
			}
		}
	}
}