package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static String url = "jdbc:mysql://localhost:3306/bdpractica"; // URL de la base de datos
	private static String usuario = "root"; // Usuario de la base de datos
	private static String contrasena = "123456"; // Contraseña de la base de datos
	protected static Connection conexion;

	// Método para abrir la conexión
	public static Connection abrirConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
			conexion = DriverManager.getConnection(url, usuario, contrasena); // Establecer la conexión
			System.out.println("Conexión exitosa a la base de datos.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexion;
	}

	// Método para cerrar la conexión
	public static Connection cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close(); // Cierra la conexión
				System.out.println("Conexión cerrada.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}
}
