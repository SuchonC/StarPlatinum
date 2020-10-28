package controller;

import java.util.ArrayList;
import java.util.HashMap;

import character.Character;
import character.Jumper;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import panel.EndingPanel;
import panel.GameLevel;
import panel.GamePanel;

public class Controller {
	
	private double speedIncrease;
	private double levelSpeed;
	private int score;
	private int level;
	private GameLevel currentLevel, nextLevel;
	private ArrayList<Node> currentLevelNodes, nextLevelNodes;
	private Character player;
	private AnimationTimer timer;
	private HashMap<KeyCode, Boolean> keyMap;
	private GamePanel gamePanel;
	private Scene gameScene;
	
	public Controller() {
		gamePanel = new GamePanel();
		gamePanel.setPrefHeight(800);
		gamePanel.setPrefWidth(600);
		
		player = new Jumper();
		
		setUp();
		
		gameScene = new Scene(gamePanel);
		
		setSceneEvent(gameScene);
		
		startGame();
	}
	
	private void setUp() {
		speedIncrease = 0.5;
		levelSpeed = 1;
		score = 0;
		level = 1;
		
		keyMap = new HashMap<>();
		
		currentLevel = new GameLevel(0, 0);
		nextLevel = new GameLevel(0, -800);
		
		currentLevelNodes = currentLevel.getNodes();
		nextLevelNodes = nextLevel.getNodes();
		
		gamePanel.getChildren().add(player.getPlayerBox());
		gamePanel.getChildren().addAll(currentLevel, nextLevel);
		
	}
	
	private void setSceneEvent(Scene scene) {
		scene.setOnKeyPressed(e -> keyMap.put(e.getCode(),true));
		scene.setOnKeyReleased(e -> {
			keyMap.put(e.getCode(), false);
			if(e.getCode() == KeyCode.W) player.setCanJump(true);
		});
	}
	
	private void startGame() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				updateKeyPressed();
				
				setPlayerFall();

				updateScore();
				
				updatePlayerImage();
				
				if(isDead()) {
					timer.stop();
					handleEnding();
				}
				
				moveLevel();
			}
			
		};
		
		timer.start();
	}
	
	public Scene getScene() {
		return gameScene;
	}
	
	private boolean isPressed(KeyCode k) {
		return keyMap.getOrDefault(k, false);
	}
	
	private void handleEnding() {
		EndingPanel endingPanel = new EndingPanel(score, this);
		
		// Need implement on position
		endingPanel.setPosition(230, 350-gamePanel.getTranslateY());
		
		gamePanel.addToScreen(endingPanel);
	}
	
	public void restartGame() {
		gamePanel = new GamePanel();
		gameScene.setRoot(gamePanel);
		setUp();
		player.reSpawn();
		startGame();
	}
	
	private void updateScore() {
		gamePanel.setScore(score);
	}
	
	private void updatePlayerImage() {
		player.updateImage(keyMap);
	}
	
	private void moveLevel() {
		score++;
		// Increasing Level Speed
		// Every 500 score
		if(score % 500 == 0 && score != 0) levelSpeed+=speedIncrease;
		
		if(gamePanel.getTranslateY() >= level*800 && gamePanel.getTranslateY() != 0) {
			level++;
			
			gamePanel.getChildren().remove(currentLevel);
			
			GameLevel newLevel = new GameLevel(0, level*(-800));
			gamePanel.getChildren().add(newLevel);
			
			currentLevelNodes = nextLevelNodes;
			nextLevelNodes = newLevel.getNodes();
			
		}
		gamePanel.moveScreenDown(levelSpeed);
		
	}

	private void setPlayerFall() {
		if(player.getVy() < 10) player.setVelocityY(player.getVy() + 1);
		player.moveY(player.getVy(), currentLevelNodes, nextLevelNodes);
	}
	
	private boolean isDead() {
		return player.getY() <= gamePanel.getTranslateY();
	}
	
	private void updateKeyPressed() {
		if(isPressed(KeyCode.D)) player.moveX(Settings.PLAYER_VELOCITY_X, currentLevelNodes, nextLevelNodes);
		if(isPressed(KeyCode.A)) player.moveX(- Settings.PLAYER_VELOCITY_X, currentLevelNodes, nextLevelNodes);
		if(isPressed(KeyCode.W)) player.jump();
	}
}
