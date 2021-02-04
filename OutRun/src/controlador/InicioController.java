package controlador;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import modelo.HiloOpacidadStart;

public class InicioController {
	
	@FXML ImageView imgPressStart, imgTitulo, imgFondo;
	
	
	boolean activo = true;
	
	@FXML
	public void initialize() {
		AudioClip audio = new AudioClip("file:audio/IntroMusic.mp3");
		audio.play();
		HiloOpacidadStart hos = new HiloOpacidadStart(imgPressStart, imgTitulo, imgFondo);
		hos.start();
		
		imgPressStart.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					try {
						Stage primaryStage = new Stage();
						AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/PantallaJuego.fxml"));
						Scene scene = new Scene(root,800,600);
						primaryStage.setScene(scene);
						primaryStage.getIcons().add(new Image("file:img/logo.png"));
						primaryStage.setTitle("OutRun.exe version 1986");
						primaryStage.show();
						setActivo(false);
						System.out.println("algo nose");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		});
		
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
