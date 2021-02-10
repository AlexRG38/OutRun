package modelo;

import controlador.PartidaController;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class HiloPartida extends Thread {
	
	Label lTiempo, lScore, lStage;
	PartidaController controller;
	int tiempo, score, stage, auxScore, genCoche;

	public HiloPartida(Label lTiempo, Label lScore, Label lStage, PartidaController controller) {
		this.lTiempo = lTiempo;
		this.lScore = lScore;
		this.lStage = lStage;
		this.controller = controller;
		tiempo = 0;
		score = 0;
		stage = 1;
		auxScore = 0;
		genCoche = 0;
	}
	
	@Override
	public void run() {
		
		while (true) {
			genCoche = (int)(Math.random() * 2 + 1);
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					lTiempo.setText(String.valueOf(tiempo));
					lScore.setText(String.valueOf(score));					
					tiempo += 1;
					score += 1000;
					auxScore += 1000;
					
				}
			});
			if (auxScore == 5000 ) {
				auxScore = 0;
				stage += 1;
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						lStage.setText(String.valueOf(stage));
						
					}
				});
			}
			if (genCoche == 2) {
				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
