package panel;

import java.util.ArrayList;

import controller.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GamePanel extends Pane{
	private final int GAME_WIDTH = Settings.GAME_WIDTH;
	private final int GAME_HEIGHT = Settings.GAME_HEIGHT;
	private Rectangle background;
	private Text score;
	
	public GamePanel() {
		setUpScoreText();
		
		background = new Rectangle(GAME_WIDTH, GAME_HEIGHT);

		
		this.getChildren().addAll(background, score);
		setBackground(new ImagePattern(Settings.BACKGROUND_IMAGE));
	}
	
	
	public void setBackground(Paint paint) {
		background.setFill(paint);
	}
	
	private void setUpScoreText() {
		score = new Text("Score : 0");
		score.setViewOrder(-100);
		score.setTranslateX(500);
		score.setTranslateY(100);
	}
	
	public void moveScreenDown(double speed) {
		score.setTranslateY(score.getTranslateY()-speed);
		background.setTranslateY(background.getTranslateY()-speed);
		setTranslateY(getTranslateY()+speed);
	}
	
	public void setScore(int score) {
		this.score.setText("Score : " + score);
	}
	
	public void addToScreen(EndingPanel endingPanel) {
		getChildren().add(endingPanel);
	}
	
	
}
