package model;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.cliente;
import beans.prestamo;

public class PrestamoModel extends Conexion {
	
	
	CallableStatement cs;
	ResultSet rs;
	Statement st;
	
	public List<prestamo> listar(int idcliente){
		try {
			List<prestamo> prestamos= new ArrayList<>();
			String sql="CALL sp_listarprestamo(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			rs=cs.executeQuery();
			while (rs.next()) {
				prestamo p= new prestamo();
				p.setIdcliente(idcliente);
				p.setIdprestamo(rs.getInt("p.idprestamo"));
				p.setFechaprestamo(rs.getDate("p.fechaprestamo"));
				p.setMonto(rs.getDouble("p.monto"));
				p.setCliente(rs.getString("c.nombre"));
				p.setInteres(rs.getInt("p.interes"));
				p.setNumcuota(rs.getInt("p.numcuotas"));
				prestamos.add(p);			
			}
			this.cerrarConexion();
			return prestamos;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error listar prestamo desde model: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
		
	}
	
	public int modificar(prestamo prestamo) {
		try {
			int filasafect=0;
			String sql="CALL sp_modificarprestamo(?,?,?,?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1,prestamo.getIdprestamo());
			cs.setDate(2, (Date)prestamo.getFechaprestamo());
			cs.setDouble(3,prestamo.getMonto());
			cs.setInt(4,prestamo.getIdcliente());
			cs.setInt(5, prestamo.getInteres());
			cs.setInt(6, prestamo.getNumcuota());
			filasafect= cs.executeUpdate();
			this.cerrarConexion();
			return filasafect;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al modificar desde model: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
		
	}
	
	public int insertar(prestamo prestamo) {
		try {
			int filasafect=0;
			String sql="CALL sp_insertarprestamo(?,?,?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);			
			cs.setDate(1, (Date)prestamo.getFechaprestamo());
			cs.setDouble(2,prestamo.getMonto());
			cs.setInt(3,prestamo.getIdcliente());
			cs.setInt(4, prestamo.getInteres());
			cs.setInt(5, prestamo.getNumcuota());
			filasafect= cs.executeUpdate();
			this.cerrarConexion();
			return filasafect;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar desde model: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
		
	}
	
	
	public prestamo obtener(int idcliente,int idprestamo){
		try {
			prestamo p= new prestamo();
			String sql="CALL sp_obtenerPrestamo(?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, idcliente);
			cs.setInt(2, idprestamo);
			rs=cs.executeQuery();
			while (rs.next()) {			
				p.setIdcliente(rs.getInt("p.idcliente"));				
				p.setIdprestamo(rs.getInt("p.idprestamo"));
				p.setFechaprestamo(rs.getDate("p.fechaprestamo"));
				p.setMonto(rs.getDouble("p.monto"));
				p.setCliente(rs.getString("c.nombre"));
				p.setInteres(rs.getInt("p.interes"));
				p.setNumcuota(rs.getInt("p.numcuotas"));
			
			}
			this.cerrarConexion();
			return p;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error obtener prestamo desde model: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
		
	}
	
	
	public void eliminar(int idprestamo) {
		try {
			String sql="CALL sp_eliminarprestamo(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, idprestamo);
			rs=cs.executeQuery();
			this.cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No se logro eliminar desde model: "+e.getMessage());
			this.cerrarConexion();
		}
		
	}

}
