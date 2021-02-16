package modelo;

import java.io.IOException;
import controlador.GameOverController;
import controlador.PartidaController;
import controlador.VictoryController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class HiloPartida extends Thread {

	Label lTiempo, lScore, lStage;
	PartidaController controller;
	ImageView cocheChoque, coche, fondo;
	Stage stageV;
	Pane paneColision;
	int tiempo, score, stage, auxScore, auxTiempo, genCoche;
	boolean partida;
	AudioClip audio;

	public HiloPartida(Label lTiempo, Label lScore, Label lStage, PartidaController controller, ImageView cocheChoque,
			ImageView coche, ImageView fondo, AudioClip audio, Stage stageV) {
		this.lTiempo = lTiempo;
		this.lScore = lScore;
		this.lStage = lStage;
		this.controller = controller;
		this.cocheChoque = cocheChoque;
		this.coche = coche;
		this.fondo = fondo;
		this.stageV = stageV;
		this.audio = audio;
		tiempo = 0;
		score = 0;
		stage = 1;
		auxScore = 0;
		auxTiempo = 0;
		genCoche = 0;
	}

	@Override
	public void run() {
		partida = true;

		while (partida) {

			generarCoche();
			cancion();

			if (auxTiempo == 1000) {
				auxTiempo = 0;
				tiempo += 1;
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						lTiempo.setText(String.valueOf(tiempo));

					}
				});
			}

			cambiarStage(tiempo);

			if (tiempo == 60) {
				partida = false;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (tiempo == 60) {
					try {
						irVictoria();
					} catch (IOException e) {
						e.printStackTrace();

					}
				} else {
					try {
						irGameOver();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

	}

	public void generarCoche() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				lScore.setText(String.valueOf(score));
				score += 1000;
				auxScore += 100;
				auxTiempo += 100;
				if (!cocheChoque.isVisible()) {
					genCoche = (int) (Math.random() * 3 + 1);
					cocheChoque.setVisible(true);
					cocheChoque.setY(0);
					cocheChoque.setX(0);
					cocheChoque.setFitHeight(13);
					cocheChoque.setFitWidth(30);
				} else if (cocheChoque.isVisible()) {
					if (genCoche == 1) {
						cocheChoque.setImage(new Image("file:img/cocheCentro.png"));
						cocheChoque.setY(cocheChoque.getY() + 6);
						cocheChoque.setX(cocheChoque.getX() - 3);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight() + 6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth() + 6);
						if (cocheChoque.getY() > 200) {
							cocheChoque.setVisible(false);
						}
					} else if (genCoche == 2) {
						cocheChoque.setImage(new Image("file:img/cocheDerecha.png"));
						cocheChoque.setY(cocheChoque.getY() + 6);
						cocheChoque.setX(cocheChoque.getX() + 14);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight() + 6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth() + 6);
						if (cocheChoque.getY() > 200) {
							cocheChoque.setVisible(false);
						}
					} else if (genCoche == 3) {
						cocheChoque.setImage(new Image("file:img/cocheIzquierda.png"));
						cocheChoque.setY(cocheChoque.getY() + 6);
						cocheChoque.setX(cocheChoque.getX() - 18);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight() + 6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth() + 6);
						if (cocheChoque.getY() > 200) {
							cocheChoque.setVisible(false);
						}
					}

				}

				if (coche.getBoundsInParent().intersects(cocheChoque.getBoundsInParent())) {
					partida = false;
				} else if (coche.getLayoutX() > 670 || coche.getLayoutX() < -6) {
					partida = false;
				}

			}
		});
	}

	public void cancion() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				int musica = 0;
				if (audio == null) {
					musica = (int) (Math.random() * 3 + 1);
					if (musica == 1) {
						audio = new AudioClip("file:audio/miami_musik.mp3");
						audio.play();
					} else if (musica == 2) {
						audio = new AudioClip("file:audio/miami_safe.mp3");
						audio.play();
					} else if (musica == 3) {
						audio = new AudioClip("file:audio/miami_hydrogen.mp3");
						audio.play();
					}
				}

			}
		});
	}

	public void cambiarStage(int tiempo) {
		switch (tiempo) {
		case 15:
			stage = 2;
			fondo.setImage(new Image("file:img/800x600/carretera2.gif"));
			break;

		case 30:
			stage = 3;
			fondo.setImage(new Image("file:img/800x600/carretera3.gif"));
			break;

		case 45:
			stage = 4;
			fondo.setImage(new Image("file:img/800x600/carretera4.gif"));
			break;

		default:
			break;
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				lStage.setText(String.valueOf(stage));

			}
		});
	}

	public void irGameOver() throws IOException {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GameOver.fxml"));
			GameOverController controller = new GameOverController(stageV, audio);
			loader.setController(controller);
			AnchorPane root;
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 800, 600);
			stageV.setScene(scene);
			stageV.getIcons().add(new Image("file:img/logo.png"));
			stageV.setTitle("OutRun.exe version 1986");
			stageV.show();
			audio.stop();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void irVictoria() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaVictoria.fxml"));
			VictoryController controller = new VictoryController(stageV, audio);
			loader.setController(controller);
			AnchorPane root;
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 800, 600);
			stageV.setScene(scene);
			stageV.getIcons().add(new Image("file:img/logo.png"));
			stageV.setTitle("OutRun.exe version 1986");
			stageV.show();
			audio.stop();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
