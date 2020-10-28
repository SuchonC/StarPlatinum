package controller;

import javafx.scene.image.Image;

public class Settings {
	public static final int PLAYER_VELOCITY_X = 5;
	public static final int GRAVITY = 2; // Down direction
	public static final int PLAYER_WIDTH = 30;
	public static final int PLAYER_HEIGHT = 50;
	public static final int OBSTACLE_SIZE = 50;
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 800;
	public static final int OBSTACLE_VERTICAL_INTERVAL = 4;
	public static final int MAX_OBSTACLE = 5;
	public static final int MIN_OBSTACLE = 2;
	
	public static final Image OBSTACLE_IMAGE = new Image(ClassLoader.getSystemResource("grass_block1.png").toString());
	public static final Image OBSTACLE2_IMAGE = new Image(ClassLoader.getSystemResource("grass_block2.png").toString());
	public static final Image BACKGROUND_IMAGE = new Image(ClassLoader.getSystemResource("sky.gif").toString());
	public static final Image SNOW_BACKGROUND_IMAGE = new Image(ClassLoader.getSystemResource("snow_background.gif").toString());
	public static final Image SNOW_BLOCK_IMAGE = new Image(ClassLoader.getSystemResource("snow_block.png").toString());
	
	public static final Image CHAR1_IMAGE = new Image(ClassLoader.getSystemResource("char1_default.gif").toString());
	public static final Image CHAR1_LEFT = new Image(ClassLoader.getSystemResource("char1_move_left.gif").toString());
	public static final Image CHAR1_RIGHT = new Image(ClassLoader.getSystemResource("char1_move_right.gif").toString());
	public static final Image CHAR1_JUMP = new Image(ClassLoader.getSystemResource("char1_jump.png").toString());
}
