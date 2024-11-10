package beans;

import java.util.Date;

public class prestamo {
	
	private int idprestamo;
	private Date fechaprestamo;
	private double monto;
	private int idcliente;
	private int interes;
	private int numcuota;
	
	//Para mostrar el nombre del cliente
	private String cliente;
	
	
	public prestamo(){
		super();
	}


	public int getIdprestamo() {
		return idprestamo;
	}


	public void setIdprestamo(int idprestamo) {
		this.idprestamo = idprestamo;
	}


	public Date getFechaprestamo() {
		return fechaprestamo;
	}


	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}


	public double getMonto() {
		return monto;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}


	public int getIdcliente() {
		return idcliente;
	}


	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}


	public int getInteres() {
		return interes;
	}


	public void setInteres(int interes) {
		this.interes = interes;
	}


	public int getNumcuota() {
		return numcuota;
	}


	public void setNumcuota(int numcuota) {
		this.numcuota = numcuota;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	
	

}
