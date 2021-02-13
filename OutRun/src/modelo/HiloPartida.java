package modelo;

import controlador.PartidaController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HiloPartida extends Thread {
	
	Label lTiempo, lScore, lStage;
	PartidaController controller;
	ImageView cocheChoque, coche;
	int tiempo, score, stage, auxScore, auxTiempo, genCoche;
	boolean partida;

	public HiloPartida(Label lTiempo, Label lScore, Label lStage, PartidaController controller, ImageView cocheChoque, ImageView coche) {
		this.lTiempo = lTiempo;
		this.lScore = lScore;
		this.lStage = lStage;
		this.controller = controller;
		this.cocheChoque = cocheChoque;
		this.coche = coche;
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
		//PartidaController controller = PartidaController.getPartidaController();
		
		while (partida) {
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					lScore.setText(String.valueOf(score));
					score += 1000;
					auxScore += 100;
					auxTiempo += 25;
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
			
			if (auxScore == 5000) {
				auxScore = 0;
				stage += 1;
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						lStage.setText(String.valueOf(stage));
						
					}
				});
			}
			
			if (auxTiempo == 100) {
				auxTiempo = 0;
				tiempo += 1;
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						lTiempo.setText(String.valueOf(tiempo));
						
					}
				});
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
