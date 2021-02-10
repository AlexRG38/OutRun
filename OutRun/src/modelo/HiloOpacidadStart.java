package modelo;

import controlador.InicioController;
import javafx.scene.image.ImageView;

public class HiloOpacidadStart extends Thread {
	
	ImageView imgPressStart, imgTitulo, imgFondo;
	InicioController controller;
	
	 public HiloOpacidadStart(ImageView imgPressStart, ImageView imgTitulo, ImageView imgFondo, InicioController controller) {
		this.imgPressStart = imgPressStart;
		this.imgTitulo = imgTitulo;
		this.imgFondo = imgFondo;
		this.controller = controller;
	}

	@Override
	public void run () {
		System.out.println(imgTitulo.getLayoutX());
		
		while(imgTitulo.getLayoutX() != 165) {
			imgTitulo.setLayoutX(imgTitulo.getLayoutX() + 30);
			imgTitulo.setOpacity(imgTitulo.getOpacity() + 0.05);
			imgFondo.setOpacity(imgTitulo.getOpacity() + 0.05);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		while (controller.isActivo()==true) {
			if (imgPressStart.isVisible()) {
				imgPressStart.setVisible(false);
			} else {
				imgPressStart.setVisible(true);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Funciono en el final");
	}
}
