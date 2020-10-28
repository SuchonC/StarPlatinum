package panel;

import java.util.ArrayList;

import controller.Settings;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameLevel extends Pane{
	
	private ArrayList<Node> nodes;
	
	public GameLevel(int x, int y) {
//		xOrigin = x;
//		yOrigin = y;
		nodes = new ArrayList<>();
		createLevel(x, y);
	}
	
	private void createLevel(int x, int y){
		int begin_row = y/Settings.OBSTACLE_SIZE;
		
		int row_count = 0;
		for(int row = begin_row; row < begin_row + Settings.GAME_HEIGHT/Settings.OBSTACLE_SIZE; row++) {
			row_count++;
			if(y == 0 && (row == 15 || row == 14)) {
				for(int col = 0; col < Settings.GAME_WIDTH/Settings.OBSTACLE_SIZE; col++) {
					nodes.add(createObstacle(col, row));
				}
			}
			if(row_count == Settings.OBSTACLE_VERTICAL_INTERVAL) {	
					int obstacle_count = 0;
					for(int col = 0; col < Settings.GAME_WIDTH/Settings.OBSTACLE_SIZE; col++) {
						double random = Math.floor(Math.random() * 10);
						if(random % 3 == 0 && obstacle_count < Settings.MAX_OBSTACLE) {
							nodes.add(createObstacle(col, row));
							obstacle_count++;
						}
					}
					while(obstacle_count < Settings.MIN_OBSTACLE) {
						for(int col = 0; col < Settings.GAME_WIDTH/Settings.OBSTACLE_SIZE; col++) {
							double random = Math.floor(Math.random() * 10);
							if(random % 3 == 0 && obstacle_count < Settings.MAX_OBSTACLE) {
								nodes.add(createObstacle(col, row));
								obstacle_count++;
							}
						}
					}
			}
			if(row_count == Settings.OBSTACLE_VERTICAL_INTERVAL) row_count = 0;
		}
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	private Node createObstacle(int col, int row) {
		ImageView obstacle = new ImageView(Settings.SNOW_BLOCK_IMAGE);
		if(row == 15) obstacle = new ImageView(Settings.SNOW_BLOCK_IMAGE);
		
		obstacle.setFitHeight(Settings.OBSTACLE_SIZE);
		obstacle.setFitWidth(Settings.OBSTACLE_SIZE);
		obstacle.setTranslateX(col * Settings.OBSTACLE_SIZE);
		obstacle.setTranslateY(row * Settings.OBSTACLE_SIZE);
		
		getChildren().add(obstacle);
		
		return obstacle;
	}
}
