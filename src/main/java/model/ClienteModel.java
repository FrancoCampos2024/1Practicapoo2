package model;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.cliente;

public class ClienteModel extends Conexion {

	CallableStatement cs;
	ResultSet rs;
	Statement st;

	public List<cliente> listar() {
		try {
			List<cliente> client = new ArrayList<>();
			String sql = "CALL sp_listarcliente()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				cliente c = new cliente();
				c.setIdcliente(rs.getInt("idcliente"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellidos"));
				c.setDni(rs.getString("dni"));
				c.setFechanacimiento(rs.getDate("fechanacimiento"));
				c.setDireccion(rs.getString("direccion"));
				client.add(c);
			}
			this.cerrarConexion();
			return client;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error listar cliente desde model: " + e.getMessage());
			this.cerrarConexion();
			return null;
		}

	}

	public int insertar(cliente cliente) {
		try {
			int filasafect = 0;
			String sql = "CALL sp_insertarcliente(?,?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, cliente.getNombre());
			cs.setString(2, cliente.getApellido());
			cs.setString(3, cliente.getDni());
			cs.setDate(4, (Date) cliente.getFechanacimiento());
			cs.setString(5, cliente.getDireccion());
			filasafect = cs.executeUpdate();
			this.cerrarConexion();
			return filasafect;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar desde model: " + e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}

	public cliente obtener(int idcliente) {
		try {

			String sql = "CALL sp_obtenercliente(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			rs = cs.executeQuery();
			cliente c = new cliente();
			while (rs.next()) {
				c.setIdcliente(rs.getInt("idcliente"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellidos"));
				c.setDni(rs.getString("dni"));
				c.setFechanacimiento(rs.getDate("fechanacimiento"));
				c.setDireccion(rs.getString("direccion"));

			}
			this.cerrarConexion();
			return c;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error listar cliente desde model: " + e.getMessage());
			this.cerrarConexion();
			return null;
		}

	}

	public int modificar(cliente cliente) {
		try {
			int filasafect = 0;
			String sql = "CALL sp_modificarcliente(?,?,?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, cliente.getIdcliente());
			cs.setString(2, cliente.getNombre());
			cs.setString(3, cliente.getApellido());
			cs.setString(4, cliente.getDni());
			cs.setDate(5, (Date) cliente.getFechanacimiento());
			cs.setString(6, cliente.getDireccion());
			filasafect = cs.executeUpdate();
			this.cerrarConexion();
			return filasafect;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al modificar desde model: " + e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}

	public void eliminar(int idcliente) {

		try {
			String sql = "CALL sp_eliminarcliente(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			
			rs=cs.executeQuery();
			this.cerrarConexion();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar desde model: "+e.getMessage());
			this.cerrarConexion();
			
		}

	}

}
