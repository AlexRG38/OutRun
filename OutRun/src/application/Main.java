package application;
	
import java.io.IOException;

import controlador.InicioController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
			InicioController controller = new InicioController(primaryStage);
			loader.setController(controller);
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,800,600);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:img/logo.png"));
			primaryStage.setTitle("OutRun.exe version 1986");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
