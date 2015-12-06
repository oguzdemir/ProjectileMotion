import java.awt.*;
import javax.swing.*;
/**
 * @(#)MainWindowFrame.java
 *
 * ProjectileMotion 
 * Frame for main experiment view
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */

public class MainWindowFrame extends JFrame{
        

    public MainWindowFrame() 
    {
			

			ExperimentPanel panel = new ExperimentPanel();

			getContentPane().add(panel);
			pack();
			setVisible(true);	
	
    }

}
