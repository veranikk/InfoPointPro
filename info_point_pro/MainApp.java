package info_point_pro;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainApp() {
		
		super("BIBLIOTECA NOCTURNA");
		setSize(800,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabular= new JTabbedPane();
		tabular.addTab("Gestión libros", new PanelGestionLibros());
		tabular.addTab("Terminal publico", new PanelBusqueda());
		
		add(tabular, BorderLayout.CENTER);
		
	}

}
