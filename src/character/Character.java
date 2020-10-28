package character;

import java.util.ArrayList;
import java.util.HashMap;

import controller.Settings;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Character{
	private Rectangle player;
	
	private int vY;
	private int y;
	
	private boolean isTouchTheGround;
	private boolean canJump;
	
	private Image characterImage;
	private Image character_move_right_image;
	private Image character_move_left_image;
	private Image character_jump_image;

	public Character(Image img, Image left, Image right, Image up) {
		// (x, y) = (350, 750)
		player = new Rectangle(Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
		
		characterImage = img;
		character_move_right_image = right;
		character_move_left_image = left;
		character_jump_image = up;
		setCharacterImage(img);
		
		player.setTranslateX(350);
		player.setTranslateY(650);
		y = 150;
		
		isTouchTheGround = true;
		canJump = true;
	}
	
	public Rectangle getPlayerBox() {
		return player;
	}
	
	public void updateImage(HashMap<KeyCode, Boolean> map) {
		if(map.getOrDefault(KeyCode.W, false)) setCharacterImage(character_jump_image);
		else if(map.getOrDefault(KeyCode.A, false)) setCharacterImage(character_move_left_image);
		else if(map.getOrDefault(KeyCode.D, false)) setCharacterImage(character_move_right_image);
		else {
			setCharacterImage(characterImage);
		}
	}
	
	protected void setCharacterImage(Image img) {
		player.setFill(new ImagePattern(img));
	}
	
	public int getVy() {
		return vY;
	}
	
	public void reSpawn() {
		y = 150;
		player.setTranslateX(350);
		player.setTranslateY(650);
	}
	
	public int getY() { return y; }
	
	public void setVelocityY(int y) { vY = y; }
	
	public void jump() {
		if(canJump && isTouchTheGround) {
			setVelocityY(-22);
			canJump = false;
			isTouchTheGround = false;
		}
	}
	
	public void setCanJump(boolean x) {
		canJump = x;
	}
	
	public void moveX(double val, ArrayList<Node> nodes1, ArrayList<Node> nodes2) {

		boolean movingRight = val > 0;
		
		setCharacterImage(movingRight? character_move_right_image:character_move_left_image);
		
		for(int i = 0; i < Math.abs(val); i++) {
			double pX = player.getTranslateX();
			double pY = player.getTranslateY();
			for(Node n : nodes1) {
				
				double nX = n.getTranslateX();
				double nY = n.getTranslateY();
			
				//Hit left and right side of the screen
				if(movingRight && pX == Settings.GAME_WIDTH - Settings.PLAYER_WIDTH) return;
				else if(!movingRight && pX == 0) return;
				
				if(player.getBoundsInParent().intersects(n.getBoundsInParent())) {
					if( !(pY == nY + Settings.OBSTACLE_SIZE) && !(pY + Settings.PLAYER_HEIGHT == nY)) {
						if(movingRight) {
							if(pX + Settings.PLAYER_WIDTH == nX) return;
						}else {
							if(nX + Settings.OBSTACLE_SIZE == pX) return;
						}
					}
				}
			}
			
			for(Node n : nodes2) {
				
				double nX = n.getTranslateX();
				double nY = n.getTranslateY();
			
				//Hit left and right side of the screen
				if(movingRight && pX == Settings.GAME_WIDTH - Settings.PLAYER_WIDTH) return;
				else if(!movingRight && pX == 0) return;
				
				if(player.getBoundsInParent().intersects(n.getBoundsInParent())) {
					if( !(pY == nY + Settings.OBSTACLE_SIZE) && !(pY + Settings.PLAYER_HEIGHT == nY)) {
						if(movingRight) {
							if(pX + Settings.PLAYER_WIDTH == nX) return;
						}else {
							if(nX + Settings.OBSTACLE_SIZE == pX) return;
						}
					}
				}
			}
			
			player.setTranslateX(player.getTranslateX() + (movingRight? 1 : -1));
		}
	}
	
	public void moveY(double val, ArrayList<Node> nodes1, ArrayList<Node> nodes2) {
		
		boolean movingUp = val < 0;
		setCharacterImage(character_jump_image);
		
		for(int i = 0; i < Math.abs(val); i++) {
			double pX = player.getTranslateX();
			double pY = player.getTranslateY();
			
			for(Node n : nodes1) {
				double nX = n.getTranslateX();
				double nY = n.getTranslateY();
				
				if(player.getBoundsInParent().intersects(n.getBoundsInParent())) {
					if( !(nX + Settings.OBSTACLE_SIZE == pX) && !(pX + Settings.PLAYER_WIDTH == nX) ) {
						if(movingUp) {
							if(nY + Settings.OBSTACLE_SIZE == pY) {
								setVelocityY(1);
								return;
							}
						}else {
							// Touch the ground
							if(pY + Settings.PLAYER_HEIGHT == nY) {
								isTouchTheGround = true;
								return;
							}
						}
					}
				}else isTouchTheGround = false;
			}
			
			
			for(Node n : nodes2) {
				double nX = n.getTranslateX();
				double nY = n.getTranslateY();
				
				if(player.getBoundsInParent().intersects(n.getBoundsInParent())) {
					if( !(nX + Settings.OBSTACLE_SIZE == pX) && !(pX + Settings.PLAYER_WIDTH == nX) ) {
						if(movingUp) {
							if(nY + Settings.OBSTACLE_SIZE == pY) {
								setVelocityY(1);
								return;
							}
						}else {
							// Touch the ground
							if(pY + Settings.PLAYER_HEIGHT== nY) {
								isTouchTheGround = true;
								return;
							}
						}
					}
				}else isTouchTheGround = false;
			}
			player.setTranslateY(pY + (movingUp? -1 : 1));
			y = y + (movingUp? 1:-1);
		}
	}
}
