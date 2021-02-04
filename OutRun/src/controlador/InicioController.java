package controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.HiloOpacidadStart;

public class InicioController {
	
	@FXML ImageView imgPressStart, imgTitulo, imgFondo;
	
	@FXML
	public void initialize() {
		AudioClip audio = new AudioClip("file:audio/IntroMusic.mp3");
		audio.play();
		HiloOpacidadStart hos = new HiloOpacidadStart(imgPressStart, imgTitulo, imgFondo);
		hos.start();
	}
		
}
