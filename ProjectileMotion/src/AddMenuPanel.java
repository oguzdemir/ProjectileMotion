import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)AddMenuPanel.java
 *
 * ProjectileMotion 
 * Panel for adding particles
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class AddMenuPanel extends JPanel implements ExperimentViewer
{
	private JLabel xPositionLabel , yPositionLabel , radiusLabel , xVelocityLabel ,yVelocityLabel , resultLabel;
	private JButton addButton;
	public ParticleContainer collector;
	private JSlider xPosSlider, yPosSlider, radiusSlider, xVelSlider,yVelSlider;
	int r,g,b;
	ModifyColorPanel colorPanel;
	
	public AddMenuPanel(ParticleContainer collector , ModifyColorPanel colorPanel)
	{
		this.collector = collector;
		this.colorPanel = colorPanel;
		
		//---------------------------------------------------
		// Labels to indicate inputs
		//--------------------------------------------------
		xPositionLabel = new JLabel ("Enter xCoordinate:");
    	yPositionLabel = new JLabel ("Enter yCoordinate:");
    	radiusLabel  = new JLabel ("Enter radius");
    	xVelocityLabel = new JLabel ("Enter x Velocity");
    	yVelocityLabel = new JLabel ("Enter y Velocity");
    	
    	//---------------------------------------------------
		// Slider for xPosition
		//--------------------------------------------------
    	xPosSlider = new JSlider (JSlider.HORIZONTAL, 0, 400, 200);
      	xPosSlider.setMajorTickSpacing (100);
      	xPosSlider.setPaintTicks (true);
      	xPosSlider.setPaintLabels (true);
      	xPosSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for yPosition
		//--------------------------------------------------
      	yPosSlider = new JSlider (JSlider.HORIZONTAL, 0, 400, 200);
      	yPosSlider.setMajorTickSpacing (100);
      	yPosSlider.setPaintTicks (true);
      	yPosSlider.setPaintLabels (true);
      	yPosSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for radius
		//--------------------------------------------------
      	radiusSlider = new JSlider (JSlider.HORIZONTAL, 6, 36, 21);
      	radiusSlider.setMajorTickSpacing (6);
      	radiusSlider.setPaintTicks (true);
      	radiusSlider.setPaintLabels (true);
      	radiusSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for xVelocity
		//--------------------------------------------------
      	xVelSlider = new JSlider (JSlider.HORIZONTAL, -30, 30, 0);
      	xVelSlider.setMajorTickSpacing (10);
      	xVelSlider.setPaintTicks (true);
      	xVelSlider.setPaintLabels (true);
      	xVelSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for yVelocity
		//--------------------------------------------------
      	yVelSlider = new JSlider (JSlider.HORIZONTAL, -30, 30, 0);
      	yVelSlider.setMajorTickSpacing (10);
      	yVelSlider.setPaintTicks (true);
      	yVelSlider.setPaintLabels (true);
      	yVelSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Adding sliders and labels to panel
		//--------------------------------------------------
    	add (xPositionLabel);
		add (xPosSlider);
		add (yPositionLabel);
		add (yPosSlider);
		add (radiusLabel);
		add (radiusSlider);
		add (xVelocityLabel);
		add (xVelSlider);
		add (yVelocityLabel);
		add (yVelSlider);
		
		
		resultLabel = new JLabel("0 particle was added.");
		
		add(resultLabel);
		
		setPreferredSize (new Dimension(200, 350));
		setBackground (Color.white);
		setLayout(new GridLayout(10,1));
				
		addButton = new JButton ("Add object");
		addButton.addActionListener(new inputListener());
		
		add(addButton);
	}
	public void updateView(ParticleContainer collector )
	{
		int number = collector.getNumberOfParticles();
		
		//---------------------------------------------------
		// This class has a viewer for number of particles
		//--------------------------------------------------
		if(number ==1)
		{
			resultLabel.setText("1 element was added");
		}
		else
		{
			resultLabel.setText(number+ "   elements were added" );
		}
		
	}
	private class inputListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			double xPos,yPos ,rad , xVel,yVel;
				
			xPos = xPosSlider.getValue();
			yPos = yPosSlider.getValue();
			xVel = xVelSlider.getValue();
			yVel = yVelSlider.getValue();
			rad = radiusSlider.getValue();
			
			//---------------------------------------------------
			// Getting the rgb values for color of object
			//--------------------------------------------------
			r = colorPanel.getR();
			g = colorPanel.getG();
			b = colorPanel.getB(); 
			
			//---------------------------------------------------
			// Checking the bounds of object
			//--------------------------------------------------	
			if(xPos-rad <=0)
			{
				JOptionPane.showMessageDialog(null, "Particle is out of bounds!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (xPos-rad <=0)
			{
				JOptionPane.showMessageDialog(null, "Particle is out of bounds!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else
			{
				
				Particle temp = new Particle (xPos,yPos, xVel,  yVel,  rad,r,g,b);
				
				//---------------------------------------------------
				// Checking is there any other particle
				//--------------------------------------------------
				if(collector.contains(temp))
				{
					JOptionPane.showMessageDialog(null, "There is already a particle!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					collector.addParticle(temp);
				}
			}
			
		}		
	}
}
