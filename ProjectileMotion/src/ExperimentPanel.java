import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)ExperimentPanel.java
 *
 * ProjectileMotion 
 * Panel for main view of experiment
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */


public class ExperimentPanel extends JPanel
{
	/*public	double xPos,yPos;
	public double rad;
	public double xVel,yVel;*/
	
	
	private JButton b1,b2,b3,b4,b5;
	ExperimentArea area;
	AddMenuPanel menu;
	ParticleContainer collector;
	Timer t;
	int delay = 1;
	ViewOptionsPanel optionPanel;
	GravityPanel gravityPanel;
	ShowRoutesPanel routePanel;
	ModifyColorPanel colorModifiyer;
	AddObstaclePanel obstaclePanel;
	GraphicPanel graphics; 
		
	
    public ExperimentPanel()
    {
    	
    	//---------------------------------------------------
		// Creating instances of model class
		//--------------------------------------------------
        collector = new ParticleContainer();
        graphics=new GraphicPanel(collector,0);
		//---------------------------------------------------
		// Experiments navigator buttons
		//--------------------------------------------------	
		graphics.setVisible(false);
        b1 = new JButton ("Fire");
        startListener listener = new startListener();
        b1.addActionListener(listener);
        b2 = new JButton ("Add object");
        b2.addActionListener(new showListener());
        b3 = new JButton ("Closemenu");
        b3.addActionListener(new closeListener());
        b4 = new JButton("Grapchical result");
        b4.addActionListener(new GraphicalListener());
        b5 = new JButton("Stop");
   		b5.addActionListener(listener);
   		//---------------------------------------------------
		// Creating and adding panels to the main experiment area
		//---------------------------------------------------
        colorModifiyer = new ModifyColorPanel();
        menu = new AddMenuPanel(collector,colorModifiyer);
        menu.setVisible(false);
		gravityPanel = new GravityPanel(collector);
		obstaclePanel = new AddObstaclePanel(collector);
        routePanel = new ShowRoutesPanel(collector);
        optionPanel = new ViewOptionsPanel(collector);
        area = new ExperimentArea(collector,optionPanel);
        collector.addView(area);
        collector.addView(menu);
        collector.addView(graphics);
        setLayout(new BorderLayout());
	
		JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(1,4));
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        panel2.add(b4);
        panel2.add(b5);
        panel.add(panel2, BorderLayout.NORTH);
		panel.add(obstaclePanel, BorderLayout.CENTER);
		panel.add(colorModifiyer, BorderLayout.SOUTH);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(optionPanel, BorderLayout.SOUTH);
        panel1.add(menu, BorderLayout.CENTER);
		panel1.add(gravityPanel, BorderLayout.NORTH);
		
		panel.setPreferredSize(new Dimension(350,350));
		
        add(panel, BorderLayout.EAST);
        add(panel1, BorderLayout.WEST);
        //JPanel centerPanel= new JPanel();
        //centerPanel.setPreferredSize (new Dimension(700, 500));
        //centerPanel.add(routePanel);;
        //centerPanel.add(area);
        //area.add(routePanel);
        //add(centerPanel, BorderLayout.CENTER);
		//add(colorModifiyer, BorderLayout.CENTER);
		add(area, BorderLayout.CENTER);

        t = new Timer(delay, new fireListener());

        
        setBackground(new Color(255, 255 , 0));
     	setPreferredSize (new Dimension(1500, 700));
     	
     	
    }
	
	public void changeGravity(double gravity)
	{
		collector.setGravity(gravity);
	}
	
	//---------------------------------------------------
	// Listeners for navigation buttons
	//--------------------------------------------------	
	private class startListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			if(event.getSource() == b1)
			{
				graphics.setVisible(false);
				menu.setVisible(false);
				area.setVisible(true);
			
				t.start();
			}
			if(event.getSource() == b5)
			{
				add(graphics, BorderLayout.CENTER);
				area.setVisible(false);
				graphics.setVisible(true);
				t.stop();
				graphics.updateView(collector);
			}
			
		}
	}
	
	private class showListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			menu.setVisible(true);
		}
	}
	private class closeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			menu.setVisible(false);
			area.setVisible(true);
		}
	}

	private class fireListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
      	{
      		collector.move(delay);
      		area.repaint();
      		
      		if(collector.isEnd())
      		{
      			t.stop();
      		}
      		
      	}
	}
	
	private class GraphicalListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			add(graphics, BorderLayout.CENTER);
			area.setVisible(false);
			graphics.setVisible(true);
			t.stop();
			graphics.updateView(collector);
		}
	} 
      		
}