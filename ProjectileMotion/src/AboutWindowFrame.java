/**
 * @(#)AboutWindowFrame.java
 *
 * Frame for open about panel in new window
 * @author 4G
 * @version 1.00 2014/5/12
 */

import java.awt.*;
import javax.swing.*;

class AboutWindowFrame extends JFrame{
	
	public AboutWindowFrame() 
    {

			AboutPanel panel = new AboutPanel();

			getContentPane().add(panel);
			pack();
			setVisible(true);	
	
    }
}