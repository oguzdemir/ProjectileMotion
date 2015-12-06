import java.util.ArrayList;
/**
 * @(#)ParticleContainer.java
 *
 * ProjectileMotion 
 * Class for particle object
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
public class ParticleContainer
{
	ArrayList<Particle> collection;
	Particle temp;
	double gravity;
	ArrayList<ExperimentViewer> viewList;
	boolean showRoutes;
	ObstacleContainer obstacleCollector;
	public ParticleContainer()
	{
		viewList = new ArrayList<ExperimentViewer>();
		collection = new ArrayList<Particle>();
		gravity = -9.8;
		showRoutes = true;
		obstacleCollector = new ObstacleContainer();
	}
	
	public void addParticle(double xPos , double yPos , double radius, double xVel , double yVel,int r, int g , int b)
	{
		temp = new Particle(xPos , yPos , radius , xVel , yVel,r,g,b);
		collection.add(temp);
		notifyViewers();
	}
	
	public void addParticle (Particle c)
	{
		collection.add(c);
		notifyViewers();
	}
	
	public void addObstacle(double centerX, double centerY, double height, double width)
	{
		obstacleCollector.addObstacle(centerX,centerY,height, width);
		notifyViewers();
	}
	
	public void addObstacle(Obstacle o)
	{
		obstacleCollector.addObstacle(o);
		notifyViewers();
	}
	
	public boolean obstacleContains(Obstacle o)
	{
		return obstacleCollector.contains(o);
	}
	public void addView(ExperimentViewer view)
	{
		viewList.add(view);
		if ( view != null)
			view.updateView(this);
	}
	
	public ArrayList<Particle> getCollector()
	{
		return collection;
	}
	
	
	public void notifyViewers()
	{
		if ( viewList != null)
			for ( ExperimentViewer view : viewList)
				view.updateView(this);
	}
	
	public void move (double time)
	{
		//-------------------------------------------------------------------
		// Move every particle in collection
		//-------------------------------------------------------------------
		
		/*for(Particle p : collection)
		{

		}*/
		
		for(Particle p : collection)
		{
			if(obstacleCollector.hitAny(p)!=null)
			{
				
				p.move(time,gravity,obstacleCollector.hitAny(p));
			}
			else
			{
			
				p.move(time,gravity);

			}
			notifyViewers();
				
		}
		
		//-------------------------------------------------------------------
		// Check every pair's collision
		//-------------------------------------------------------------------
		for(int i = 0; i< collection.size(); i++)
		{
			for(int j = i+1; j < collection.size(); j++)
			{
				if(collection.get(i).isCollide(collection.get(j)))
				{
					
					collection.get(i).collide(collection.get(j));
						
				}
			}
					
		}
		
	
	}
	
	public boolean contains(Particle p1)
	{
		
		for(Particle p2: collection)
		{
			if(p2.contains(p1))
			{
				return true;
			}
		}
		return false;
	} 
	
	public void setGravity(double gravity)
	{
		this.gravity = gravity;
		notifyViewers();
	}
	
	public double getGravity()
	{
		notifyViewers();
		return gravity;
		
	}
	
	public boolean isEnd()
	{
		boolean finished = true;
		
		for(Particle p : collection)
		{
			if(!p.isStopped())
				finished = false;
		}
		return finished;
	}
	
	public int getNumberOfParticles()
	{
		return collection.size();
	}
	
	public void setRoutes(boolean b)
	{
		showRoutes = b;
	}
	
	public boolean getRoutes()
	{
		return showRoutes;
	}
	public int getWidth()
	{
		return Particle.WIDTH;
	}
	public int getHeight()
	{
		return Particle.HEIGHT;
	}
	public ArrayList<Obstacle> getObstacleCollector()
	{
		return obstacleCollector.getCollector();
	}
	
	public Particle getParticle(int index )
	{
		return collection.get(index);
	}

}
