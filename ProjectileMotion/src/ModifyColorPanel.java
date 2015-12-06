import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
/**
 * @(#)ModifyColorPanel.java
 *
 * ProjectileMotion 
 * Panel for choosing color for objects
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class ModifyColorPanel extends JPanel
{
	private JColorChooser colorChooser;
  	private JButton activate;
   	private Color newColor;
   	
	public ModifyColorPanel()
	{
		//---------------------------------------------------
		// Initializing a color chooser and button for setting visibility of it
		//--------------------------------------------------
		newColor = new Color(0,0,0);
		colorChooser = new JColorChooser(newColor);
		setBackground(newColor);
		colorChooser.getSelectionModel().addChangeListener(new ColorListener());
		colorChooser.setPreferredSize(new Dimension (400,300));
		colorChooser.setPreviewPanel(new JPanel());
		add(colorChooser);
		
		activate = new  JButton("Click to Set Color");
		activate.setPreferredSize (new Dimension (100, 100));
		activate.addActionListener(new  activateListener());
		activate.setBackground(new  Color(255,255,255));
		setPreferredSize(new Dimension(400,300));
		
	}
	
	//---------------------------------------------------
	// Button for hiding/showing colorChooser
	//--------------------------------------------------
	private class activateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			if(colorChooser.isVisible())
			{
				colorChooser.setVisible(false);
				activate.setPreferredSize (new Dimension (300, 100));
			}
			else
			{
				activate.setText("Set this!");
				colorChooser.setVisible(true);
				activate.setPreferredSize (new Dimension (100, 100));
			}
				
		}
	}
	//---------------------------------------------------
	// Getting r,g,b values for addmenupanel
	//--------------------------------------------------	
	public int getR()
	{
		return newColor.getRed();
	}
	
	public int getG()
	{
		return newColor.getGreen();
	}
	
	public int getB()
	{
		return newColor.getBlue();
	}
	
	
	private class ColorListener implements ChangeListener
	{
		
      	//--------------------------------------------------------------
      	//  Gets the value of each slider, then updates the labels and
      	//  the color panel.
      	//--------------------------------------------------------------
      	public void stateChanged (ChangeEvent event)
      	{
      		//---------------------------------------------------
			// For every change, modify the property of color
			//--------------------------------------------------	
      		newColor = colorChooser.getColor();
         	setBackground(newColor);
      	}
	}
}
