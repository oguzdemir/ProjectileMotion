import java.awt.*;
import javax.swing.*;
/**
 * @(#)AboutPanel.java
 *
 * ProjectileMotion 
 * Panel for About info
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class AboutPanel extends JPanel
{
	
	JLabel person1,person2,person3,person4,person5;
	JPanel panel1,panel2,panel3,panel4,panel5;
	Timer timer;
	int DELAY = 100;
	int random1,random2,random3;
	//--------------------------------------------
	// This is the panel that we used to indroduce ourselves.
	//--------------------------------------------
	public AboutPanel(){
		
		//--------------------------------------------
		// Setting the label's features
		//--------------------------------------------
		person1 = new JLabel( "" );
		
		
		//--------------------------------------------
		// Getting new Panels to in order to add frame
		//--------------------------------------------
		panel1 = new JPanel();
		//--------------------------------------------
		// Setting the Layout
		//--------------------------------------------
		setLayout( new GridLayout( 5,1 ) );
		
		//--------------------------------------------
		// Adding objects to the panel
		//--------------------------------------------
		panel1.add( person1 );
		//--------------------------------------------
		// Adding panels to the frames
		//--------------------------------------------
		add ( panel1 );
		
		
		setPreferredSize(new Dimension(700,500));
	}
	
}