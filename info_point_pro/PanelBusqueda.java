package info_point_pro;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

public class PanelBusqueda extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelBusqueda() {
		
		JTextArea area= new JTextArea(5,20);
		JScrollPane scroll= new JScrollPane(area);
		
		UndoManager undo= new UndoManager();
		area.getDocument().addUndoableEditListener(undo);
		add(scroll);
		
	}
	
}
