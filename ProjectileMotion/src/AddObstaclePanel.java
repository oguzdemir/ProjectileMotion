import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)AddObstaclePanel.java
 *
 * ProjectileMotion 
 * Panel for adding obstacles
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class AddObstaclePanel extends JPanel implements ExperimentViewer
{
	private JLabel xPositionLabel , yPositionLabel , heightLabel ,widthLabel , resultLabel;
	
	private JButton addButton;
	
	public ParticleContainer collector;
	
	private JSlider xPositionSlider, yPositionSlider, heightSlider,widthSlider;
	int r,g,b;
	
	
	public AddObstaclePanel(ParticleContainer collector)
	{
		this.collector = collector;
		
		//---------------------------------------------------
		// Labels to indicate inputs
		//--------------------------------------------------
		xPositionLabel = new JLabel ("Enter X Coordinate:");
    	yPositionLabel = new JLabel ("Enter Y Coordinate:");
    	heightLabel = new JLabel ("Enter Height:");
    	widthLabel = new JLabel ("Enter Width:");
    	
    	//---------------------------------------------------
		// Slider for xPosition
		//--------------------------------------------------
    	xPositionSlider = new JSlider (JSlider.HORIZONTAL, 0, 400, 200);
      	xPositionSlider.setMajorTickSpacing (100);
      	xPositionSlider.setPaintTicks (true);
      	xPositionSlider.setPaintLabels (true);
      	xPositionSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for yPosition
		//--------------------------------------------------
      	yPositionSlider = new JSlider (JSlider.HORIZONTAL, 0, 400, 200);
      	yPositionSlider.setMajorTickSpacing (100);
      	yPositionSlider.setPaintTicks (true);
      	yPositionSlider.setPaintLabels (true);
      	yPositionSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
    	
    	//---------------------------------------------------
		// Slider for width
		//--------------------------------------------------
    	widthSlider = new JSlider (JSlider.HORIZONTAL, 0, 100, 50);
      	widthSlider.setMajorTickSpacing (100);
      	widthSlider.setPaintTicks (true);
      	widthSlider.setPaintLabels (true);
      	widthSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Slider for height
		//--------------------------------------------------
      	heightSlider = new JSlider (JSlider.HORIZONTAL, 0, 100, 50);
      	heightSlider.setMajorTickSpacing (100);
      	heightSlider.setPaintTicks (true);
      	heightSlider.setPaintLabels (true);
      	heightSlider.setAlignmentX (Component.LEFT_ALIGNMENT);
      	
      	//---------------------------------------------------
		// Adding sliders to panel to modify sizes
		//--------------------------------------------------
    	JPanel panel1 = new JPanel();
    	JPanel panel2 = new JPanel();
    	JPanel panel3 = new JPanel();
    	panel1.setLayout(new GridLayout(4,1));
    	panel2.setLayout(new GridLayout(4,1));
    	panel3.setLayout(new GridLayout(1,2));
    	panel1.setPreferredSize(new Dimension(100,250));
    	panel2.setPreferredSize(new Dimension(100,250));    	
    	panel3.setPreferredSize(new Dimension(200,250));
    	
    	panel1.add (xPositionLabel);
		panel2.add (xPositionSlider);
		panel1.add (yPositionLabel);
		panel2.add (yPositionSlider);
		panel1.add (heightLabel);
		panel2.add (heightSlider);
		panel1.add (widthLabel);
		panel2.add (widthSlider);
		
		panel3.add(panel1);
		panel3.add(panel2);
				
		setPreferredSize (new Dimension(200, 350));
		setBackground (Color.white);
		setLayout(new BorderLayout());
		
		add(panel3, BorderLayout.NORTH);
		
		resultLabel = new JLabel("0 obstacle was added.");
		resultLabel.setPreferredSize(new Dimension(200,30));		
		add(resultLabel, BorderLayout.CENTER);
				
		addButton = new JButton ("Add object");
		addButton.setPreferredSize(new Dimension(200,30));
		addButton.addActionListener(new addListener());
		
		add(addButton, BorderLayout.SOUTH);
		
	}
	public void updateView(ParticleContainer collector )
	{
		int number = collector.getNumberOfParticles();
		
		//---------------------------------------------------
		// This class has a viewer for number of obstacles
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
	private class addListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			double xPos, yPos , width,height;
			
			//---------------------------------------------------
			// Getting values,checking the bounds and creating a obstacle
			//--------------------------------------------------	
			xPos = xPositionSlider.getValue();
			yPos = yPositionSlider.getValue();
			width = widthSlider.getValue();
			height = heightSlider.getValue();
			
			if(xPos <= 0 || xPos+width >= collector.getWidth())
			{
				JOptionPane.showMessageDialog(null, "Particle is out of bounds!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (yPos <= 0 || yPos+height >= collector.getHeight())
			{
				JOptionPane.showMessageDialog(null, "Particle is out of bounds!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else
			{
				
				Obstacle temp = new Obstacle(xPos,yPos,height,width);
				
			
				if(collector.obstacleContains(temp))
				{
					JOptionPane.showMessageDialog(null, "There is already a particle!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					collector.addObstacle(temp);
				}
			}
			
		}		
	}
}
