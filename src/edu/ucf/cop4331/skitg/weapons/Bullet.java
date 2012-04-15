package edu.ucf.cop4331.skitg.weapons;
import java.awt.*;
import javax.swing.ImageIcon;
import com.badlogic.gdx.Gdx;


public class Bullet 
{
	
	int x, y;
	double v;
	Image img;
	boolean visible;
	
	public int getX()
	{
		return x;
	}

	public boolean getVisible()
	{
		return visible;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	
	public Bullet(int x1, int y1)
	{
		x = x1;
		y = y1;
		ImageIcon newBullet = new ImageIcon("Enter file here");
		img = newBullet.getImage();
		visible = true;
		
	}
	
	//Use Physics functions to make the bullets move.
	public void move()
	{
		
		
	}

}
