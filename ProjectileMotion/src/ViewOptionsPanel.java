import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)ViewOptionsPanel.java
 *
 * ProjectileMotion 
 * Panel for hiding/showing trejectory
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class ViewOptionsPanel extends JPanel 
{
	private JRadioButton routesOn,routesOff;
	private boolean showRoutes;
	private ParticleContainer collector;
	
	public ViewOptionsPanel(ParticleContainer collector)
	{
		this.collector = collector;
		
		//------------------------------------------------------
		// Radio button and buttongroup for make user choice for one of two options
		//------------------------------------------------------	
		routesOn = new JRadioButton("Show routes", true);
		routesOff = new JRadioButton("Hide routes");
		
		routeListener listener = new routeListener();
		routesOn.addActionListener(listener);
		routesOff.addActionListener(listener);
				
		ButtonGroup group = new ButtonGroup();
		group.add (routesOff);
		group.add (routesOn);
		
		showRoutes = true;
		
		add (routesOff);
		add (routesOn);
		
		setBackground (Color.white);
		setPreferredSize (new Dimension(250, 100));
		setBorder (BorderFactory.createEmptyBorder (8, 8, 8, 8));
	}
	public boolean getRoutes()
	{
		return showRoutes;
	}
	
	private class routeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//---------------------------------------------------
			// Determine the user choice and show or hide routes
			//--------------------------------------------------	
			Object buttonSource = e.getSource();
			if(buttonSource == routesOn)
			{
				collector.setRoutes(true);
			}
			if(buttonSource == routesOff)
			{
				collector.setRoutes(false);
			}
		}
	}
}
