import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.*;
/**
 * @(#)Particle.java
 *
 * ProjectileMotion 
 * Class for particle object
 *
 * @author 4G
 * @version 1.00 2014/5/12
 */
 
public class Particle
{
	private double centerX,centerY;
	private double radius;
	double xVel,yVel;
	private ArrayList<Double> posList;
	private ArrayList<Double> velList;
	private boolean stopped;
	int r,g,b;
	static final int WIDTH = 760;
	static final int HEIGHT = 700;
	
	public Particle(double centerX , double centerY , double xVel , double yVel, double radius,int r, int g , int b)
	{
		this.centerX  = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.xVel = xVel;
		this.yVel = yVel;
		this.r=r;
		this.g=g;
		this.b=b;
		posList = new ArrayList<Double>();
		velList = new ArrayList<Double>();
		stopped = false;
	}
	
	public boolean contains(Particle p)
	{
		double centerX2 = p.getCenterX();
		double centerY2 = p.getCenterY();
		
		double distance = ((centerX-centerX2)*(centerX - centerX2)) + ((centerY-centerY2)) * (centerY-centerY2);
		distance = Math.sqrt(distance);
		double distance2 = (p.getRadius()+ radius);
		
		return distance2>= distance;
	}	
	public void seperateParticles(Particle p)
	{
		//---------------------------------------------------
		// To avoid from nesting bug, distance of nesting is measured and particles are seperated accordingly
		//--------------------------------------------------
		double centerX2 = p.getCenterX();
		double centerY2 = p.getCenterY();
		
		double distance = ((centerX-centerX2)*(centerX - centerX2)) + ((centerY-centerY2)) * (centerY-centerY2);
		distance = Math.sqrt(distance);
		double distance2 = (p.getRadius()+ radius);
		
		
		double extraDistance = distance2-distance;
		
		/*while(extraDistance<=0)
		{
			setCenterX(getCenterX()-(getXVel()*0.01));
			setCenterY(getCenterY()-(getYVel()*0.01));
			p.setCenterX(p.getCenterX()-(p.getXVel()*0.01));
			p.setCenterY(p.getCenterY()-(p.getYVel()*0.01));
		}
		
		*/	
		double sina = Math.abs((centerY2-centerY)/ Math.sqrt(distance));
		double cosa = Math.abs((centerX2-centerX)/ Math.sqrt(distance));
		
		extraDistance = extraDistance/2;
		
		
		if(centerX2 <= centerX && centerY2<= centerY)
		{
			p.setCenterX(centerX2 - (extraDistance*cosa));
			p.setCenterY(centerY2 - (extraDistance*sina));
			setCenterX(centerX + (extraDistance*cosa));
			setCenterY(centerY + (extraDistance*sina));
		}
		else if (centerX2 <= centerX && centerY2>= centerY)
		{
			p.setCenterX(centerX2 - (extraDistance*cosa));
			p.setCenterY(centerY2 + (extraDistance*sina));
			setCenterX(centerX + (extraDistance*cosa));
			setCenterY(centerY - (extraDistance*sina));
		}
		else if (centerX2 >= centerX && centerY2<= centerY)
		{
			p.setCenterX(centerX2 + (extraDistance*cosa));
			p.setCenterY(centerY2 - (extraDistance*sina));
			setCenterX(centerX - (extraDistance*cosa));
			setCenterY(centerY + (extraDistance*sina));
		}
		else
		{
			p.setCenterX(centerX2 + (extraDistance*cosa));
			p.setCenterY(centerY2 + (extraDistance*sina));
			setCenterX(centerX - (extraDistance*cosa));
			setCenterY(centerY - (extraDistance*sina));
		}
	}
	
	//---------------------------------------------------
	// Detection of particle to particle collision
	//--------------------------------------------------
	public boolean isCollide(Particle p)
	{
		/*
		//-----------------------------------------------------------
		// Checking previously collided for nesting bug
		//-----------------------------------------------------------
		if (!previouslyCollided && !p.previouslyCollided)
		{*/
			double distance = ((centerX-p.getCenterX())*(centerX-p.getCenterX())) + ((centerY-p.getCenterY()) * (centerY-p.getCenterY()));
			distance = Math.sqrt(distance);
			double distance2 = (p.getRadius()+ radius);
		
			
			//-----------------------------------------------------------
			// Checking the distance between particles
			//-----------------------------------------------------------
			if (distance2 == distance)
			{
				collide(p);
				return true;	
			}
			else if (distance2 > distance)
			{
				seperateParticles(p);
				return true;
			}
			else
				return false;
								
	}
	
	//---------------------------------------------------
	// Action of collision
	//--------------------------------------------------
	public void collide(Particle p)
	{
		double m1 = radius*radius;
		double m2 = p.getRadius()*p.getRadius();
		
		double yDistance = p.getCenterY() - getCenterY() ;
		double xDistance = p.getCenterX() - getCenterX() ;
		double angle = Math.atan2(yDistance,xDistance);
		
		
		//---------------------------------------------------
		// A new collision axis determined according to centers of particles
		// Veleocity components are measured with respect to new axis
		// Components on collision axis will effected by central collision
		// Components perpendicular to this axis will be saved
		// After collision, veleocities on collision coordinates are transformed to X-Y coordinates
		//--------------------------------------------------
		double direction2 = Math.atan2(p.getYVel(),p.getXVel());
		double direction1 = Math.atan2(getYVel(), getXVel());
		
		double vel1 = Math.sqrt((getYVel()*getYVel())+ (getXVel()*getXVel()));
		double vel2 = Math.sqrt((p.getYVel()*p.getYVel())+(p.getXVel()*p.getXVel()));
		
		double v1x = vel1*Math.cos(direction1-angle);
		double v1y = vel1*Math.sin(direction1-angle);
		double v2x = vel2*Math.sin(direction2-angle);
		double v2y = vel2*Math.cos(direction2-angle);
		
		v1x = vel1*Math.cos(direction1-angle);
		v1y = vel1*Math.sin(direction1-angle);
		v2x = vel2*Math.cos(direction2-angle);
		v2y = vel2*Math.sin(direction2-angle);
		
		double f1x = ((m1-m2)*v1x+(m2+m2)*v2x)/(m1+m2);
		double f2x = ((m1+m1)*v1x+(m2-m1)*v2x)/(m1+m2);
		double f1y = v1y;
		double f2y = v2y;
		
		xVel = Math.cos(angle)*f1x+Math.cos(angle+Math.PI/2)*f1y;
		yVel = Math.sin(angle)*f1x+Math.sin(angle+Math.PI/2)*f1y;
		p.setXVel(Math.cos(angle)*f2x+Math.cos(angle+Math.PI/2)*f2y);
		p.setYVel(Math.sin(angle)*f2x+Math.sin(angle+Math.PI/2)*f2y);
		
	}	
	public boolean outOfBounds()
	{
		//---------------------------------------------------
		// Checking the bounds not to allow particle to go out
		//--------------------------------------------------
		if(centerX-radius <= 0 || centerX + radius >= 760 || centerY-radius <= 0 || centerY + radius >= 700)
		{
			return true;
		}
		else
			return false;
	}
	//---------------------------------------------------
	// Normal move with caring boundries of area
	//--------------------------------------------------
	public void move (double time , double gravity)
	{
		//-----------------------------------------------------------
		// For each move, positions are saved.
		//-----------------------------------------------------------
		posList.add(centerX);
		posList.add(centerY);
		velList.add(Math.sqrt((xVel*xVel)+(yVel*yVel)));
		//-----------------------------------------------------------
		// Before moving the object, checking the continuity of move
		//-----------------------------------------------------------
		if(!stopped)
		{
			//-----------------------------------------------------------
			// Checking the collision with the boundries on x axis
			//-----------------------------------------------------------
			
			/*if(outOfBounds())
			{
				if(centerX - radius <= 0 && centerX-radius  >= 400)
				{
					
				}
			}*/
			if(centerX - radius >= 0 && centerX + radius  < WIDTH) 
			{
				time = time/100;
				centerX = centerX + xVel*time;
				time = time*100;
			}
			else
			{
				
				xVel= (xVel)*(-1);
				time = time/100;
				centerX = centerX  + xVel*time;
				time = time*100;
			}
			//-----------------------------------------------------------
			// Checking the collision with the boundries on y axis
			//-----------------------------------------------------------
			if(centerY - radius >= 0 && centerY + radius  < HEIGHT )
			{
				time = time/100;
				centerY = centerY - (yVel*time) + (0.5*gravity*time*time);
				yVel = yVel + time*gravity;
				time = time*100;
					
			}
			else
			{
				yVel = -yVel;
				time = time/100;
				centerY = centerY - (yVel*time) + (0.5*gravity*time*time);
				yVel = yVel + time*gravity;
				time = time*100;;
			}

			/*if((xPos <0 || xPos>400)&&(yPos < 0 || yPos >400))
			{
				stopped = false;	
			}*/	
		}
	}
	
	//---------------------------------------------------
	// This method will aplied in case of collision particle to obstacle
	// (Will be improved)
	//--------------------------------------------------
	public void move (double time , double gravity, Obstacle o)
    {
                //-----------------------------------------------------------
                // For each move, positions are saved.
                //-----------------------------------------------------------
                posList.add(centerX);
                posList.add(centerY);
				velList.add(Math.sqrt((xVel*xVel)+(yVel*yVel)));
                //-----------------------------------------------------------
                // Before moving the object, checking the continuity of move
                //-----------------------------------------------------------
                if(!stopped)
                {

                        /*if(outOfBounds())
                        {
                                if(centerX - radius <= 0 && centerX-radius  >= 400)
                                {

                                }
                        }*/
                        //-----------------------------------------------------------
                        // Checking collisin from vertical sides
                        //-----------------------------------------------------------

                        if((centerX <(o.getCenterX()-(o.getWidth()/2))) && ( centerY
						>(o.getCenterY()-(o.getHeight()/2))) && (centerY <
						(o.getCenterY()+(o.getHeight()/2))))
						                        {
                                xVel= (xVel)*(-1);
                                time = time/100;
                                centerX = centerX + xVel*time;
                                time = time*100;
                        }

                        if(centerX>(o.getCenterX()-(o.getWidth()/2)) &&
						(centerY>(o.getCenterY()-(o.getHeight()/2))) && (centerY<
						(o.getCenterY()+(o.getHeight()/2))))
                        {
                                xVel= (xVel)*(-1);
                                time = time/100;
                                centerX = centerX + xVel*time;
                                time = time*100;
                        }
                        /*if((centerX-radius<= o.getCenterX()+(o.getWidth()/2) ||
centerX+radius >= o.getCenterX()+(o.getWidth()/2)) && centerY+radius>
(o.getCenterY()-(o.getHeight()/2)) && centerY>
(o.getCenterY()+(o.getHeight()/2)))
                        {
                                xVel= (xVel)*(-1);
                                time = time/100;
                                centerX = centerX + xVel*time;
                                time = time*100;
                        }*/
                        //else if(centerX+radius >= o.getCenterX()+(o.getWidth()/2))

                        else
                        {
                                time = time/100;
                                centerX = centerX  + xVel*time;
                                time = time*100;
                        }
                        //-----------------------------------------------------------
                        // Checking the collision with the boundries on y axis
                        //-----------------------------------------------------------

                        if(centerY>(o.getCenterY()+(o.getHeight()/2)) && (centerX)<=
						(o.getCenterX()+(o.getWidth()/2)) && (centerX >=
						(o.getCenterX()-(o.getWidth()/2))))
                        {
                                yVel = -yVel;
                                time = time/100;
                                centerY = centerY - (yVel*time) + (0.5*gravity*time*time);
                                yVel = yVel + time*gravity;
                                time = time*100;;
                        }
                        /*if((o.getCenterY()-getCenterY())<=(o.getHeight()/2)+getRadius())
                        {
                                time = time/100;
                                centerY = centerY - (yVel*time) + (0.5*gravity*time*time);
                                yVel = yVel + time*gravity;
                                time = time*100;

                        }*/
                        if(centerY <(o.getCenterY()-(o.getHeight()/2)) && (centerX)<
						(o.getCenterX()+(o.getWidth()/2)) && (centerX >
						(o.getCenterX()-(o.getWidth()/2))) )
                        {
                                yVel = -yVel;
                                time = time/100;
                                centerY = centerY - (yVel*time) + (0.5*gravity*time*time);
                                yVel = yVel + time*gravity;
                                time = time*100;;
                        }

                        /*if((xPos <0 || xPos>400)&&(yPos < 0 || yPos >400))
                        {
                                stopped = false;
                        }*/
                }
        }
	//Get set xPos
	public double getCenterX()
	{
		return centerX;
	}

	public void setCenterX(double centerX)
	{
		this.centerX = centerX;
	}
	
	//Get set yPos
	public double getCenterY()
	{
		return centerY;
	}

	public void setCenterY(double centerY)
	{
		this.centerY  = centerY;
	}
	
	//Get set xVel
	public double getYVel()
	{
		return yVel;
	}

	public void setYVel(double yVel)
	{
		this.yVel  = yVel;
	}
	
	//Get set yVel
	public double getXVel()
	{
		return xVel;
	}

	public void setXVel(double xVel)
	{
		this.xVel  = xVel;
	}
	
	//Get set radius
	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public boolean isStopped()
	{
		return stopped;
	}
	
	public void setStopped(boolean stopped)
	{
		this.stopped = stopped;
	}
	
	public ArrayList<Double> getPosList()
	{
		return posList;
	}
	
	public ArrayList<Double> getVelList()
	{
		return velList;
	}
   
}	