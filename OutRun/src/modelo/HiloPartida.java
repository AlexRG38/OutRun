package modelo;

import java.io.IOException;

import javax.swing.text.GapContent;

import controlador.GameOverController;
import controlador.PartidaController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HiloPartida extends Thread {
	
	Label lTiempo, lScore, lStage;
	PartidaController controller;
	ImageView cocheChoque, coche, fondo;
	Stage stageV;
	int tiempo, score, stage, auxScore, auxTiempo, genCoche;
	boolean partida;

	public HiloPartida(Label lTiempo, Label lScore, Label lStage, PartidaController controller, 
			ImageView cocheChoque, ImageView coche, ImageView fondo, Stage stageV) {
		this.lTiempo = lTiempo;
		this.lScore = lScore;
		this.lStage = lStage;
		this.controller = controller;
		this.cocheChoque = cocheChoque;
		this.coche = coche;
		this.fondo = fondo;
		this.stageV = stageV;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				if (tiempo == 60) {
					irVictoria();
				} else {
					irGameOver();
					System.out.println("He terminado bb");
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
					genCoche = (int)(Math.random() * 3 + 1);
					cocheChoque.setVisible(true);
					cocheChoque.setY(0);
					cocheChoque.setX(0);
					cocheChoque.setFitHeight(13);
					cocheChoque.setFitWidth(30);
				} else if (cocheChoque.isVisible()) {
					if (genCoche==1) {
						cocheChoque.setImage(new Image("file:img/cocheCentro.png"));
						cocheChoque.setY(cocheChoque.getY()+6);
						cocheChoque.setX(cocheChoque.getX()-3);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight()+6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth()+6);
						if (cocheChoque.getY()>200) {
							cocheChoque.setVisible(false);
						}
					} else if (genCoche==2) {
						cocheChoque.setImage(new Image("file:img/cocheDerecha.png"));
						cocheChoque.setY(cocheChoque.getY()+6);
						cocheChoque.setX(cocheChoque.getX()+12);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight()+6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth()+6);
						if (cocheChoque.getY()>200) {
							cocheChoque.setVisible(false);
						}
					} else if (genCoche==3) {
						cocheChoque.setImage(new Image("file:img/cocheIzquierda.png"));
						cocheChoque.setY(cocheChoque.getY()+6);
						cocheChoque.setX(cocheChoque.getX()-18);
						cocheChoque.setFitHeight(cocheChoque.getFitHeight()+6);
						cocheChoque.setFitWidth(cocheChoque.getFitWidth()+6);
						if (cocheChoque.getY()>200) {
							cocheChoque.setVisible(false);
						}
					}
					
				}
				
				if (cocheChoque.getBoundsInParent().intersects(coche.getBoundsInParent())) {
					partida = false;
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
	
	public void irGameOver(){

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GameOver.fxml"));
			GameOverController controller = new GameOverController(stageV);
			loader.setController(controller);
			AnchorPane root;
			root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,800,600);
			stageV.setScene(scene);
			stageV.getIcons().add(new Image("file:img/logo.png"));
			stageV.setTitle("OutRun.exe version 1986");
			stageV.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void irVictoria() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PantallaVictoria.fxml"));
			GameOverController controller = new GameOverController(stageV);
			loader.setController(controller);
			AnchorPane root;
			root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,800,600);
			stageV.setScene(scene);
			stageV.getIcons().add(new Image("file:img/logo.png"));
			stageV.setTitle("OutRun.exe version 1986");
			stageV.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
