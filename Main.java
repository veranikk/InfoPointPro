package info_point_pro;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		ConexionBBDD.crearBaseDatos();
		ConexionBBDD.crearTablas();
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				SplashScreen pantalla_carga = new SplashScreen();
				pantalla_carga.setVisible(true);
			}
			
		});

	}

}
