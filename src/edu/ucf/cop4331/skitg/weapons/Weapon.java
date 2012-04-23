package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Engine;
import edu.ucf.cop4331.skitg.Map;
import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;

/**
 * Abstract weapon class
 * @author Jeremy Mayeres
 *
 */
public abstract class Weapon {
	
	/**
	 * Position of shot
	 */
	protected Vector2 position = new Vector2();
	/**
	 * Velocity of shot
	 */
	protected Vector2 velocity = new Vector2();
	/**
	 * Shot completion status
	 */
	protected boolean done = false;
	/**
	 * Whether shot hit ground
	 */
	protected boolean hitGround = false;
	/**
	 * Whether shot hit a tank
	 */
	protected boolean hitTank = false;
	/**
	 * Shooting tank
	 */
	protected Tank shooter;
	/**
	 * Map reference
	 */
	protected Map map;
	/**
	 * Gravity
	 */
	protected final float GRAVITY = -30f;
	/**
	 * Multiplies with power to get velocity
	 */
	protected final float POWER_FACTOR = 2.0f;
	/**
	 * Collision hitbox
	 */
	protected Rectangle bounds;	
	/**
	 * Texture
	 */
	protected TextureRegion texture;

	/**
	 * Create the weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public Weapon(Tank shooter, TextureRegion tex){
		this.shooter = shooter;
		this.texture = tex;
		this.map = Engine.getInstance().getMap();
	}
	
	/**
	 * Get the status of the shot
	 * @return True if the shot finished, false otherwise
	 */
	public boolean isDone(){
		return done;
	}
	
	/**
	 * Update is called by the tank often when shot
	 * @param delta Time elapsed during update
	 */
	public abstract void update(float delta);
	
	/**
	 * Render the shot
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		batch.draw(texture, position.x, position.y);
	}
	
	/**
	 * Shoot the weapon
	 */
	public void shoot(){
		velocity = new Vector2(shooter.getPower()* MathUtils.cosDeg(shooter.getAngle()) * POWER_FACTOR , shooter.getPower() * MathUtils.sinDeg(shooter.getAngle()) * POWER_FACTOR);
		position = new Vector2(shooter.getPosition());
		bounds = new Rectangle(position.x,position.y,1,1);
	}
	
	/**
	 * Get the name of the weapon
	 * @return Name of the weapon
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Detects collisions with the ground and tank
	 * @return True if weapon collided with ground
	 */
	protected boolean detectGroundCollision(){
		if((position.y < shooter.getPosition().y - 1 || position.y > shooter.getPosition().y + 1) && (int)position.y <= map.getPeaksY((int)position.x)){
			return true;
		}
		return false;
	}
	
	/**
	 * Detects collision with the tank, given rectangular bounds
	 * @return True if hit tank
	 */
	protected boolean detectTankCollision(){
		Rectangle otherTankBounds = Engine.getInstance().getOtherTank(shooter).getBounds();
		if(bounds.overlaps(otherTankBounds)){
			return true;
		}
		return false;
	}
	
	/**
	 * Detects collision within a given radius
	 * @param radius Explosion radius
	 * @return True if tank is in radius
	 */
	protected boolean detectExplosionRadius(float radius){
		Circle temp = new Circle(position.x, position.y, radius);
		Rectangle otherTankBounds = Engine.getInstance().getOtherTank(shooter).getBounds();
		if(Intersector.overlapCircleRectangle(temp, otherTankBounds)){
			return true;
		}
		return false;
	}
	
	/**
	 * Update the position of the shot and checks if shot went off the map
	 * @param delta Time elapsed
	 * @param useGravity True to have shot affected by gravity, false otherwise
	 */
	protected void updatePosition(float delta, boolean useGravity){
		if(useGravity)
			velocity.y += GRAVITY * delta;
		
		position.add(velocity.x *delta * POWER_FACTOR, velocity.y *delta * POWER_FACTOR);
		bounds.setX(position.x);
		bounds.setY(position.y);
		
		if(position.x >= Skitg.WIDTH - 5 || position.x <= 0 || position.y <= 0 || position.y >= 1000)
		{
			done = true;
		}
	}
	
}
