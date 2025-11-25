package info_point_pro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SplashScreen extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JProgressBar barra;
	private JLabel estado;
	private Image backgroundImage;
	
	public SplashScreen() {
		
		setUndecorated(true);
		setSize(400,150);
		setLocationRelativeTo(null);
		
		try {
		    backgroundImage = ImageIO.read(new java.io.File("info_pro_recursos/background.png"));
		} catch (Exception ex) {
		    backgroundImage = null;
		    System.out.println("No se pudo cargar la imagen: " + ex.getMessage());
		}
		 
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			protected void paintComponent(Graphics g) {
			    if(backgroundImage != null) {
			        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			    } else {
			        super.paintComponent(g);
			    }
			}

		};
		
		panel.setLayout(new BorderLayout(10,10));
		panel.setPreferredSize(new Dimension(500,300));
		
		estado= new JLabel("Iniciando app...");
		estado.setHorizontalAlignment(SwingConstants.CENTER);
		estado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		barra= new JProgressBar(0,100);
		barra.setStringPainted(true);
		barra.setPreferredSize(new Dimension(480,24));
		
		panel.add(estado, BorderLayout.CENTER);
		panel.add(barra, BorderLayout.SOUTH);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
		
		cargar();
		
	}

	private void cargar() {
		Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] mensajes = { "Conectando a la base de datos...", "Cargando interfaz...",
                        "Aplicando estilos...", "Inicializando componentes...", "Optimización final..." };

                try {
                	
                	ConexionBBDD.crearTablas();
                	
                    int total = 100;
                    int valor = 0;
                    int msgIndex = 0;

                    while (valor < total) {
                        final String texto = mensajes[msgIndex % mensajes.length];
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                estado.setText(texto);
                            }
                        });

                        valor += 2;
                        final int v = valor;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                barra.setValue(Math.min(v, 100));
                            }
                        });

                        Thread.sleep(350); // más lento

                        if (valor % 20 == 0) {
                            msgIndex++;
                        }
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            estado.setText("¡LISTO!");
                            estado.setFont(new Font("SansSerif", Font.BOLD, 16));
                            barra.setValue(100);
                        }
                    });

                    Thread.sleep(800);

                } catch (Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null,
                                    "Error al cargar la app: " + e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                } finally {
                    dispose();
                    Login login = new Login(null);
                    login.setVisible(true);
                }
            }
        });
        hilo.start();
    }
}
