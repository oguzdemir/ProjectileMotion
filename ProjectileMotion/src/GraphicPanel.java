import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.ArrayList;


public class GraphicPanel extends JPanel implements ExperimentViewer
{
	ParticleContainer particleContainer;
	public static int WIDTH = 760;
	public static int HEIGHT= 700;
	int index;
	Particle p;
	
	public GraphicPanel(ParticleContainer c, int index)
	{
		particleContainer = c;
		this.index = index;
		setPreferredSize (new Dimension(760, 700));
		setBackground(Color.white);
		
	}
	
	public void updateView(ParticleContainer c)
	{
		repaint();
	}
	
public void paintComponent(Graphics g)
	{
		if(particleContainer.getNumberOfParticles() != 0){
			p = particleContainer.getParticle(index);}
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawLine(5              ,20            ,5  ,(WIDTH/2) - 25);
		g.drawLine((HEIGHT/2) - 20,(WIDTH/2) - 25,5  ,(WIDTH/2) - 25);  
		g.drawString("X Position",5,15);
		g.drawString("Time",(HEIGHT/2) - 20,(WIDTH/2) - 25);
		
		g.drawLine((HEIGHT/2) + 20,15            ,(HEIGHT/2) + 20,(WIDTH/2) - 25);
		g.drawLine( HEIGHT        ,(WIDTH/2) - 25,(HEIGHT/2) + 20,(WIDTH/2) - 25);
		g.drawString("Y Position", (HEIGHT/2) + 10,15);
		g.drawString("Time",HEIGHT,(WIDTH/2) - 25);
		
		g.drawLine(5            ,(WIDTH/2) + 10,5  ,WIDTH);
		g.drawLine(HEIGHT/2 - 20, WIDTH        ,5  ,WIDTH);
		g.drawString("Velocity",5,(WIDTH/2) + 10);
		g.drawString("Time",HEIGHT/2 - 20,WIDTH);
		
		g.drawLine((HEIGHT/2) + 20,(WIDTH/2) + 10 ,(HEIGHT/2) + 20, WIDTH);
		g.drawLine( HEIGHT        , WIDTH         ,(HEIGHT/2) + 20, WIDTH);
		g.drawString("Accelaration",(HEIGHT/2) + 20,(WIDTH/2) + 10);
		g.drawString("Time",HEIGHT,WIDTH);
		
		int a = particleContainer.getCollector().get(0).getPosList().size();
		
		double maxX = 0;
		double maxY = 0;
		double maxV = 0;
		double maxA = 0;
		
		//------------------------------------------------------
		// xPosition
		//------------------------------------------------------
		for(int i = 0; i<a ;)
		{
			if(p.getPosList().get(i) > maxX)
			{
				maxX = p.getPosList().get(i);
			}
			i= i +2;
		}
		double k = 15;
		
		//iterating all x point to paint them
		for(int i = 0; i < p.getPosList().size(); )
		{
			//350px is magnitude of vertical axis
			//Maxvalue adopted maxHeight, and other regulated accordingly
			double d1 = p.getPosList().get(i);
			d1 = (d1*350)/maxX;
			
			int i1 = (int)d1;
			
			g.fillOval(274-(int)k, i1 , 2 , 2);
			i = i + 2;
			k = k + ((250-5)*(1.0)/(a/2));
		}
		
		//------------------------------------------------------
		// yPosition
		//------------------------------------------------------
		k = 15;
		for(int i = 0; i < a ; i++)
		{
			if(p.getPosList().get(i) > maxY)
			{
				maxY = p.getPosList().get(i);
			}
			i= i + 2;
		}
		for(int i = 0; i < p.getPosList().size(); )
		{
			double d1 = p.getPosList().get(i+1);
			d1 = (d1*350)/maxY;
			
			int i1 = (int)d1;
			
			g.fillOval((int)k, i1+350 , 2 , 2);
			i = i + 2;
			k = k + ((250-5)*(1.0)/(a/2));
		}
		for(int i = 0; i < p.getVelList().size() ; i++)
		{
			if(p.getVelList().get(i) > maxV)
			{
				maxV = p.getVelList().get(i);
			}
			i= i + 2;
		}
		for(int i = 0; i < p.getVelList().size(); )
		{
			
			double d1 = p.getVelList().get(i);
			d1 = (d1*350)/maxV;
			
			int i1 = (int)d1;
			
			g.fillOval(115+(int)k, 300-i1 , 2 , 2);
			i = i + 1;
			k = k + ((250-5)*(1.0)/(p.getVelList().size()/2));
		}
		//------------------------------------------------------
		// Accelaration
		//------------------------------------------------------
		for(int i = 0; i < p.getVelList().size()-1 ; i++)
		{
			if(p.getVelList().get(i+1)-p.getVelList().get(i) > maxA)
			{
				maxA = p.getVelList().get(i+1)-p.getVelList().get(i);
			}
			i= i + 1;
		}
		
		for(int i = 0; i < p.getVelList().size()-1; )
		{
			double d1 = (p.getVelList().get(i+1))-(p.getVelList().get(i));
			//d1 = (d1*350)/maxA;
			
			int i1 = (int)d1;
			
			g.fillOval(1430-(int)k, i1+500 , 2 , 2);
			i = i + 1;
			k = k + ((150-5)*(1.0)/(p.getVelList().size()/2));
		}
	}
}

