package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import panel.GamePanel;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Controller controller = new Controller();
		
		stage.setScene(controller.getScene());
		stage.setTitle("MyGame");
		stage.show();
	}

}
