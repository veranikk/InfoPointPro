package info_point_pro;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Login login = new Login(null);
				login.setVisible(true);
			}
			
		});

	}

}
