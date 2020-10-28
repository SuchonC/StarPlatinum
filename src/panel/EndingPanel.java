package panel;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EndingPanel extends VBox{
	private Controller controller;
	private HBox buttonPane;
	private Button menuButton;
	private Button restartButton;
	private Text message;
	private Text score;
	
	public EndingPanel(int score, Controller controller){
		// --- for test ---
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		// --------
		
		this.controller = controller;
		
		buttonPane = new HBox();
		menuButton = new Button("Menu");
		restartButton = new Button("Play again ?");
		this.score = new Text("Score : " + score);
		message = new Text("GAMEOVER !!");
		
		setUpMenuButtonEvent();
		setUpRestartButtonEvent();
		
		buttonPane.getChildren().addAll(restartButton, menuButton);
		getChildren().addAll(message, this.score, buttonPane);
		
		setAlignment(Pos.CENTER);
	}
	
	public void setPosition(double x, double y) {
		setTranslateX(x); setTranslateY(y);
	}
	
	private void setUpMenuButtonEvent() {
		menuButton.setOnAction(e -> handleMenu());
	}
	
	private void setUpRestartButtonEvent() {
		restartButton.setOnAction(e -> handleRestart());
	}
	
	private void handleMenu() {
		// Garkie
	}
	
	private void handleRestart() {
		controller.restartGame();
	}
}
