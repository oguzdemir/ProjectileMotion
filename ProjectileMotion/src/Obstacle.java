import java.util.ArrayList;
/**
 * @(#)Obstacle.java
 *
 * ProjectileMotion 
 * Class for obstacle objects
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
 
public class Obstacle {
	private double centerX,centerY;
	private double height, width;
	
	public Obstacle (double centerX, double centerY, double height, double width)
	{
		this.centerX = centerX;
		this.centerY = centerY;
		this.height = height;
		this.width = width;
	}
	public boolean isHit (Particle p) 
	{
		
		/*Another try
		 double angle = Math.atan2(Math.abs(centerY-p.getCenterY()) ,Math.abs(centerX-p.getCenterX()));
		
		
		double distance1 = (Math.abs(centerX-p.getCenterX())- (width/2))*Math.cos(angle);
		double distance2 = (width/2)*Math.cos(angle);
			
		double distance = ((centerX-p.getCenterX())*(centerX-p.getCenterX())) + ((centerY-p.getCenterY()) * (centerY-p.getCenterY()));
		distance = Math.sqrt(distance);
		
		if(distance1+distance2>= distance)
		{
			return true;
		}
		else
		{
			return false;
		}*/
		
		//---------------------------------------------------
		// Checking distance between centers and compare it with radius,width,height
		//--------------------------------------------------
		double distance = ((centerX-p.getCenterX())*(centerX-p.getCenterX())) + ((centerY-p.getCenterY()) * (centerY-p.getCenterY()));
		distance = Math.sqrt(distance);
		double dist = ((width/2)*(width/2))+((height/2)*(height/2));
		dist = Math.sqrt(dist);
		double distance2 = (p.getRadius() + dist);
		if(distance <= distance2) {
			hit(p);
			return true;
		}
		return false;

	}
	
	public void hit(Particle p) 
	{
		//---------------------------------------------------
		// Do the hit action for particle and obstacle
		//--------------------------------------------------	
		if(Math.abs(centerY-p.getCenterY())<=(height/2)+p.getRadius())
		{
			double a = p.getYVel();
			p.setYVel(-a);
			
		}
		if(Math.abs(centerX-p.getCenterX())<=(width/2)+p.getRadius())
		{
			double b = p.getXVel();
			p.setXVel(-b);
		}
		else
		{
			p.setXVel(p.getXVel());
			p.setYVel(p.getYVel());
		}	
	}
	
	public boolean contains(Obstacle o)
	{
		//---------------------------------------------------
		// Not to add two obstacle for same location
		//--------------------------------------------------	
		double distanceY = o.getCenterY() - centerY;
		double distanceX = o.getCenterX() - centerX;
		
		double distanceWidth = ( o.getWidth()+ width ) / 2;
		double distanceHeight = ( o.getHeight()+ height ) / 2;
		
		if(distanceWidth<= distanceX && distanceHeight <= distanceY)
		{
			return true;
		}
		else
			return false;
	}
	public double getCenterX()
	{
		return centerX;
	}

	public void setCenterX(double centerX)
	{
		this.centerX  = centerX;
	}
	
	public double getCenterY()
	{
		return centerY;
	}

	public void setCenterY(double centerY)
	{
		this.centerY  = centerY;
	}
	
	public double getHeight() 
	{
		return height;
	}
	public void setHeight (double height)
	{
		this.height = height;
	}
	public double getWidth ()
	{
		return width;
	}
	public void setWidth(double width)
	{
		this.width = width;
	}
}
