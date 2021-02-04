package modelo;

import javafx.scene.image.ImageView;

public class HiloOpacidadStart extends Thread {
	
	ImageView imgPressStart, imgTitulo, imgFondo;
	
	 public HiloOpacidadStart(ImageView imgPressStart, ImageView imgTitulo, ImageView imgFondo) {
		this.imgPressStart = imgPressStart;
		this.imgTitulo = imgTitulo;
		this.imgFondo = imgFondo;
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

		
		
		while (true) {
			if (imgPressStart.isVisible()) {
				imgPressStart.setVisible(false);
				System.out.println("Funciono en el true");
			} else {
				imgPressStart.setVisible(true);
				System.out.println("Funciono en el false");
			}
			System.out.println("Funciono en el final");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
