package info_point_pro;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		setSize(400,200);
		initComponents();
		setLocationRelativeTo(null);
		
	}
	private void initComponents() {
		usuario=new JTextField(15);
		contrasenia=new JPasswordField(15);
		
		aceptar=new JButton("Iniciar sesión");
		aceptar.setMnemonic('I'); //Alt + I
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		cancelar=new JButton("Cancelar");
		cancelar.setMnemonic('C'); // Alt+ C
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
		
		if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            usuario.requestFocus();
            return;
        }
		
		try (Connection conn = ConexionBBDD.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT rol FROM usuarios WHERE usuario = ? AND password = ?")) {
	            ps.setString(1, user);
	            ps.setString(2, pass);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String rol = rs.getString("rol");
                JOptionPane.showMessageDialog(this, "Acceso concedido (" + rol + ")", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                dispose();

                MainApp main = new MainApp();
                main.setVisible(true);
            } else {
                // Fallo: limpiar campos y mantener foco (no cerrar ventana)
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                usuario.setText("");
                contrasenia.setText("");
                usuario.requestFocus();
            }
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al validar credenciales: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
		
	}

}
