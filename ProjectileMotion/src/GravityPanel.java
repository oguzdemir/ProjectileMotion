import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)GravityPanel.java
 *
 * ProjectileMotion 
 * Panel for regulating gravity
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class GravityPanel extends JPanel implements ExperimentViewer
{
	JLabel currentLabel;
	JTextField gravityField;	
	ParticleContainer collector;
		
	public GravityPanel(ParticleContainer collector	)
	{
		//---------------------------------------------------
		// Label for showing, field for getting gravity
		//--------------------------------------------------
		this.collector = collector;
		currentLabel = new JLabel("");
		gravityField = new JTextField(5);
		gravityField.addActionListener(new gravityListener());
		add(currentLabel);
		add(gravityField);
		updateView(collector);
		setPreferredSize(new Dimension (300,200));
	}
	
	//---------------------------------------------------
	// Class is viewer for viewing gravity
	//--------------------------------------------------
	public void updateView(ParticleContainer collector)
	{
		currentLabel.setText("Current gravity is: " + collector.getGravity());
	}
	
	private class gravityListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(gravityField.getText()!= null)
			{
				String text1 = gravityField.getText();
				double newGravity = Integer.parseInt(text1);
				if(newGravity != collector.getGravity() )
				{
					collector.setGravity(newGravity);
				}
			}
			updateView(collector);		
		}
	}
}
