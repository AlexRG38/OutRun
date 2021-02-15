package controlador;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameOverController {
	
	Stage stage;
	
	public GameOverController(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void initialize() {
		System.out.println("He llegado aqui");
	}
	
	public void prueba(MouseEvent event) {
		((Node)event.getSource()).getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println("Funcionas??????");
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaJuego.fxml"));
					PartidaController controller = new PartidaController(stage);
					loader.setController(controller);
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root,800,600);
					stage.setScene(scene);
					stage.getIcons().add(new Image("file:img/logo.png"));
					stage.setTitle("OutRun.exe version 1986");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}

}
