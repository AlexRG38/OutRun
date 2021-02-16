package controlador;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import modelo.HiloOpacidadStart;

public class InicioController {
	
	@FXML ImageView imgPressStart, imgTitulo, imgFondo;
	@FXML AnchorPane container;
	Stage stage;
	AudioClip audio;

	boolean activo = true;
	
	 public InicioController(Stage stage) {
		this.stage = stage;
	}
		
	@FXML
	public void initialize() {
		audio = new AudioClip("file:audio/IntroMusic.mp3");
		audio.setVolume(1.0);
		audio.play();
		
		HiloOpacidadStart hos = new HiloOpacidadStart(imgPressStart, imgTitulo, imgFondo, this);
		hos.start();
		
	}
		
	
	public void prueba(MouseEvent event) {
		((Node)event.getSource()).getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaJuego.fxml"));
					PartidaController controller = new PartidaController(stage);
					loader.setController(controller);
					AnchorPane root = (AnchorPane)loader.load();
					Scene scene = new Scene(root,800,600);
					stage.setScene(scene);
					stage.getIcons().add(new Image("file:img/logo.png"));
					stage.setTitle("OutRun.exe version 1986");
					setActivo(false);
					stage.show();
					audio.stop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
