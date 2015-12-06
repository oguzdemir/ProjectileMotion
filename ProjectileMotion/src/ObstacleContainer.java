import java.util.ArrayList;
/**
 * @(#)ObstacleContainer.java
 *
 * ProjectileMotion 
 * Class holding obstacles
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
 
public class ObstacleContainer
{
	ArrayList<Obstacle> obstacleCollector;
	Obstacle temp;
	public ObstacleContainer()
	{
		obstacleCollector = new ArrayList<Obstacle>();
	}
	
	public void addObstacle(double centerX, double centerY, double height, double width)
	{
		temp = new Obstacle(centerX , centerY , height , width);
		obstacleCollector.add(temp);
	}
	
	public void addObtacle(Obstacle o)
	{
		obstacleCollector.add(o);
	}
	
	//---------------------------------------------------
	// Iteration for contains and isHit method of Obstacle class to handle more than one obstacle situations
	//--------------------------------------------------
	public boolean contains(Obstacle o)
	{
		for(Obstacle o1 : obstacleCollector)
		{
			if(o.contains(o1))
			{
				return true;
			}
		}
		return false;
	}
	
	public Obstacle hitAny(Particle p)
	{
		for(Obstacle o : obstacleCollector)
		{
			if(o.isHit(p))
			{
				return o;
			}
		}
		return null;		
	}
	
	public void addObstacle (Obstacle c)
	{
		obstacleCollector.add(c);
	}
	
	public ArrayList<Obstacle> getCollector()
	{
		return obstacleCollector;
	}
	
	public int size()
	{
		return obstacleCollector.size();
	}

}
