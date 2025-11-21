package info_point_pro;

import java.sql.*;

public class ConexionBBDD {

	
	 private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
	 private static final String USUARIO = "root";
	 private static final String CONTRASENIA = "";
	 
	 public static void crearBaseDatos() {
        Connection conexion = null;
        Statement sentencia = null;
        try {
            // Conectamos a MySQL sin especificar la BBDD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USUARIO, CONTRASENIA);
            sentencia = conexion.createStatement();
            String sqlCrearBD = "CREATE DATABASE IF NOT EXISTS biblioteca;";
            sentencia.executeUpdate(sqlCrearBD);
            System.out.println("Base de datos 'biblioteca' creada o ya existía.");
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        } finally {
            try {
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Hay algun null por ahi: " + e.getMessage());
            }
        }
	  }
	 
	 public static void crearTablas() {
		 
		 Connection conexion = null;
	     Statement sentencia = null;
	     
	     String sqlCrearLibros= "CREATE TABLE IF NOT EXISTS libros ("
	                + "id INT AUTO_INCREMENT PRIMARY KEY,"
	                + "titulo VARCHAR(100) NOT NULL,"
	                + "autor VARCHAR(100) NOT NULL,"
	                + "anio INT NOT NULL"
	                + ");";
	     
	     String sqlCrearUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
	                + "id INT AUTO_INCREMENT PRIMARY KEY,"
	                + "usuario VARCHAR(50) NOT NULL UNIQUE,"
	                + "password VARCHAR(50) NOT NULL,"
	                + "rol VARCHAR(20) NOT NULL"
	                + ");";
	     
	     try {
	    	 conexion= DriverManager.getConnection(URL,USUARIO,CONTRASENIA);
	    	 sentencia= conexion.createStatement();
	    	 
	    	 sentencia.executeUpdate(sqlCrearLibros);
	    	 sentencia.executeUpdate(sqlCrearUsuarios);
	    	 
	    	 System.out.println("Tabla de libros creada correctamente.");
	    	 
	    	 insertarUsuarioSiNoExiste("admin", "12345", "admin");
	         insertarUsuarioSiNoExiste("usuario", "1234", "usuario");	    
	         
	     }catch( SQLException e) {
	    	 System.out.println("Error al crear la tabla:" +e.getMessage());
	     }finally {
	    	 try {
	    		 if(sentencia != null) {
	    			 sentencia.close();
	    		 }
	    		 if(conexion !=null) {
	    			 conexion.close();
	    		 }
	    	 }catch(SQLException e) {
	    		 System.out.println("Error cerrando recursos: " +e.getMessage());
	    	 }catch(NullPointerException e) {
	    		 System.out.println("Hay algun null por ahi: " + e.getMessage());
	    	 }
	     }
		 
	 }
	 
	 public static Connection getConnection() throws SQLException {
		 
		 return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
	    
	 }
	 
	 public static void insertarLibro(String titulo, String autor, int anio) {
		Connection conexion = null;
        Statement sentencia = null;

        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
            sentencia = conexion.createStatement();
            String sqlInsertLibro = "INSERT INTO libros (titulo, autor, anio) VALUES ('"
                    + titulo + "', '" + autor + "', " + anio + ");";
            sentencia.executeUpdate(sqlInsertLibro);
            System.out.println("Libro insertado correctamente: " + titulo);
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
        } finally {
            try {
                if (sentencia != null) {
                	sentencia.close();
                }
                if (conexion != null) {
                	conexion.close();
                }
            }catch(SQLException e) {
	    		 System.out.println("Error cerrando recursos: " +e.getMessage());
	    	 }catch(NullPointerException e) {
	    		 System.out.println("Hay algun null por ahi: " + e.getMessage());
	    	 }
        }
	 }
	
	 
	 private static void insertarUsuarioSiNoExiste(String usuario, String password, String rol) {
	        Connection conexion = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
	            ps = conexion.prepareStatement("SELECT id FROM usuarios WHERE usuario = ?");
	            ps.setString(1, usuario);
	            rs = ps.executeQuery();
	            if (!rs.next()) { // No existe -> insertamos
	                ps = conexion.prepareStatement("INSERT INTO usuarios (usuario, password, rol) VALUES (?, ?, ?)");
	                ps.setString(1, usuario);
	                ps.setString(2, password);
	                ps.setString(3, rol);
	                ps.executeUpdate();
	                System.out.println("Usuario por defecto creado: " + usuario);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al insertar usuario: " + e.getMessage());
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException e) {
	                System.out.println("Error cerrando recursos: " + e.getMessage());
	            }
	        }
	    }
	 
	 
	 
	 
	 
	 
	 
	
}
