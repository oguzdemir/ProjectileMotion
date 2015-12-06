import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @(#)ExperimentArea.java
 *
 * ProjectileMotion 
 * Main experiment panel
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class ExperimentArea extends JPanel implements  ExperimentViewer
{
	ParticleContainer collector;
	ViewOptionsPanel routeOption;
	
	public ExperimentArea(ParticleContainer collector,ViewOptionsPanel routeOption)
	{
		this.routeOption=routeOption;
		setBackground(new Color(255,180 , 0));
     	setPreferredSize (new Dimension(760, 700));
     	this.collector = collector;
	}
	/*
	public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
     	g.fillRect(0,450,700,50);
     	if(collector.getCollector() != null)
     	{
	     	for(Particle p : collector.getCollector())
	     	{
	     		int radius = (int)p.getRadius();
	     		int xPos = (int)p.getCenterX()-radius;
	     		int yPos = (int)p.getCenterY()-radius;
	     		g.fillOval( xPos,yPos,radius*2,radius*2);
	     	}
     	}     	  
	}*/
	
	//---------------------------------------------------
	// Paint every object and particle by iterating their holders
	//---------------------------------------------------
	public void paintComponent(Graphics g) 
	{
        
     	boolean show = collector.getRoutes();
     	super.paintComponent(g);
     	if(collector.getCollector() != null)
     	{
     		for(Obstacle o : collector.getObstacleCollector())
     		{
     			g.setColor(Color.black);
     			{
     				int xPos = (int)(o.getCenterX()- (o.getWidth()/2));
     				int yPos = (int)(o.getCenterY()- (o.getHeight()/2));
     				int width = (int)(o.getWidth());
     				int height = (int)(o.getHeight());
     				
     				g.fillRect(xPos,yPos,width,height);
     					
     			}
     		}
	     	     	
	     	for(Particle p : collector.getCollector())
	     	{
	     		g.setColor(new Color(p.r,p.g,p.r));
	     		int radius = (int)(p.getRadius());
	     		int xPos = (int)(p.getCenterX()- radius);
	     		int yPos = (int)(p.getCenterY()- radius);
	     		g.fillOval(xPos,yPos,radius*2,radius*2);
		     	if(show)
		     	{
		     		if(p.getPosList()!= null)
		     		{
		     			//ArrayList<Double> posList = p.getPosList();
		     			
     			    	for(int i = 0; i < p.getPosList().size();)
				    	{
				    		double d1 =	p.getPosList().get(i);
				    		double d2= p.getPosList().get(i+1);
				    		int i1 = (int)d1;
				    		int i2 = (int)d2;
				    		int i3 = (int)(p.getRadius());
				    		g.fillOval(i1, i2, 5 , 5);
				    		i = i +2 ;
						}
		     		}
		     	} 
	     	}
     	}
     	g.drawLine(0  ,0  ,0  ,700);
     	g.drawLine(0  ,0  ,760,0);
     	g.drawLine(760,0  ,760,700);
     	g.drawLine(0  ,700,760,700);	
     	  
	}
	
	public void updateView(ParticleContainer c)
	{
		//showRoutes.repaint();
		repaint();
	}
	/*public void addParticle(double xPos , double yPos , double radius, double xVel , double yVel)
	{
		Particle temp = new Particle(xPos , yPos , radius , xVel , yVel);
		collector.addParticle(temp);
	}*/
	
}
