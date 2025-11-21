package info_point_pro;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGestionLibros extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField texto_titulo;
	private JTextField texto_autor;
	private JTextField texto_anio;
	
	private JButton boton_guardar;
	private JButton boton_limpiar;
	
	public PanelGestionLibros (){
		
		setLayout(new BorderLayout(10,10));
		
		JPanel formulario= new JPanel(new GridLayout(3,2,8,8));
		formulario.setBorder(BorderFactory.createTitledBorder("Datos del libro."));
		
		formulario.add(new JLabel("Titulo: "));
		texto_titulo= new JTextField(15);
		formulario.add(texto_titulo);
		
		formulario.add(new JLabel("Autor: "));
		texto_autor= new JTextField(15);
		formulario.add(texto_autor);
		
		formulario.add(new JLabel("Año: "));
		texto_anio= new JTextField(10);
		formulario.add(texto_anio);
		
		JPanel botones= new JPanel();
		boton_guardar=new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardarLibros();
				
			}
		});
		boton_limpiar=new JButton("Limpiar");
		boton_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarCampos();
				
			}
		});
		
		botones.add(boton_guardar);
		botones.add(boton_limpiar);
		
		
		if(getRootPane()!= null) {
			getRootPane().setDefaultButton(boton_guardar);
		}
		
		add(formulario, BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);
	}

	protected void limpiarCampos() {
		
		texto_titulo.setText("");
		texto_autor.setText("");
		texto_anio.setText("");
		
		texto_titulo.requestFocus();
		
	}

	protected void guardarLibros() {
		
		String titulo = texto_titulo.getText().trim();
        String autor = texto_autor.getText().trim();
        String anioText = texto_anio.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || anioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anio = 0;
        
        try {
            anio = Integer.parseInt(anioText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ConexionBBDD.insertarLibro(titulo, autor, anio);
            JOptionPane.showMessageDialog(this, "Libro guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
