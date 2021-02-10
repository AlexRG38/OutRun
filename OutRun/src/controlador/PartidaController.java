package controlador;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.HiloPartida;

public class PartidaController {
	
	@FXML Label lTiempo,lScore,lStage;
	@FXML ImageView coche;
	@FXML AnchorPane container;
	@FXML Rectangle rLeft, rRight;
	
	public PartidaController(Stage stage) {
		
	}
	
	@FXML
	public void initialize() {
		System.out.println(lTiempo.getText());
		HiloPartida hp = new HiloPartida(lTiempo, lScore, lStage, this);
		hp.start();
		coche.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					coche.setLayoutX(coche.getLayoutX() + 10);
				}
				
			}
		});
	}
	
	public void prueba(MouseEvent event) {
		System.out.println();
		((Node)event.getSource()).getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					coche.setLayoutX(coche.getLayoutX() - 5);
					coche.setImage(new Image("file:img/car3.png"));
					rLeft.setFill(Color.web("#d12121"));
				} else if (event.getCode() == KeyCode.RIGHT) {
					coche.setImage(new Image("file:img/car2.png"));
					coche.setLayoutX(coche.getLayoutX() + 5);
					rRight.setFill(Color.web("#d12121"));
				}
			}
		});
		
		((Node)event.getSource()).getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				coche.setImage(new Image("file:img/car1.png"));
				rRight.setFill(Color.web("#ffffff"));
				rLeft.setFill(Color.web("#ffffff"));
			}
		});
	}

}
