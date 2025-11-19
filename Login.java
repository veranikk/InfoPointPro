package Desarrollo;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField usuario;
	private JPasswordField contrasenia;
	private JButton aceptar;
	private JButton cancelar;
	/**
	 * Create the panel.
	 */
	public Login(Frame parent) {
		super(parent,"Acceso al sistema", true);
		initComponents();
		
	}
	private void initComponents() {
		usuario=new JTextField(15);
		contrasenia=new JPasswordField(15);
		
		aceptar=new JButton("Iniciar sesión");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		cancelar=new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//cuando le das al Enter se ejcuta el boton aceptar por defecto
		getRootPane().setDefaultButton(aceptar);
		
		JPanel formulario=new JPanel(new GridLayout(2,2,5,5));
		formulario.add(new JLabel("Usuario:"));
		formulario.add(usuario);
		formulario.add(new JLabel("Contraseña:"));
		formulario.add(contrasenia);
		
		JPanel botones=new JPanel();
		botones.add(aceptar);
		botones.add(cancelar);
		
		getContentPane().setLayout(new BorderLayout(10,10));
		getContentPane().add(formulario,BorderLayout.CENTER);
		getContentPane().add(botones, BorderLayout.SOUTH);
		
		pack();
		
		
		
		
	}
	private void validar() {
		String user=usuario.getText().trim();
		String pass=new String(contrasenia.getPassword());
		
		if(user.equals("usuario")&& pass.equals("1234")||user.equals("admin")&& pass.equals("12345") ) {
			JOptionPane.showMessageDialog(this, "Acceso concedido","Bienvenido",JOptionPane.INFORMATION_MESSAGE);
			dispose();
			MainApp main=new MainApp();
			main.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectas","Erro",JOptionPane.ERROR_MESSAGE);
			usuario.setText("");
			contrasenia.setText("");
			//Recupera el foco havia el campo de txt de usuario
			usuario.requestFocus();
		}
		
	}

}
