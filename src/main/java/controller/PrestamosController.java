package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PrestamoModel;

import java.io.IOException;

import beans.prestamo;

/**
 * Servlet implementation class PrestamosController
 */
public class PrestamosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrestamosController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PrestamoModel modelo= new PrestamoModel();

	protected void requestprocces(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("op");

		if (operacion == null) {
			listar(request, response);
			return;
		}

		switch (operacion) {
		case "listar": {
			
			listar(request, response);
			break;
		}
		case "obtener":{
			obtener(request, response);
			break;
		}
		case "modificar":{
			modificar(request, response);
			break;
		}
		case "nuevo":{
			nuevo(request, response);
			break;
		}
		case "insertar":{
			insertar(request, response);
			break;
		}
		case "eliminar":{
			System.out.println("Entro a eliminar.");
			eliminar(request, response);
			break;
		}
		}

	}
	
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			String nombrec=request.getParameter("nombre");
			String idaux= request.getParameter("id");
			
			request.setAttribute("prestamos",modelo.listar(id));
			request.setAttribute("idcliente",idaux);
			request.setAttribute("nombre",nombrec);
			
			request.getRequestDispatcher("/prestamo/listarprestamo.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al listar desde el controlador: "+e.getMessage());
		}
		
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
						
			System.out.println(request.getParameter("id"));
			String id=request.getParameter("id");
			String nombrec=request.getParameter("nombre");
			
			request.setAttribute("idc",id);
			request.setAttribute("nombre",nombrec);
			
			request.getRequestDispatcher("/prestamo/nuevoprestamo.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al modificar desde controller: " + e.getMessage());
		}
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			
			prestamo prestamo= new prestamo();
			
			int idcliente=Integer.parseInt(request.getParameter("idc"));
			String nombrec=request.getParameter("nombre");
						
			prestamo.setFechaprestamo(java.sql.Date.valueOf(request.getParameter("fechaprestamo")));
			
			prestamo.setMonto(Double.parseDouble(request.getParameter("monto")));
			prestamo.setIdcliente(idcliente);
			prestamo.setInteres(Integer.parseInt(request.getParameter("interes")));
			prestamo.setNumcuota(Integer.parseInt(request.getParameter("numcuotas")));
			
			if(modelo.insertar(prestamo)>0) {				
				response.sendRedirect(request.getContextPath()+"/PrestamosController?op=listar&id="+prestamo.getIdcliente()+"&nombre="+nombrec);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar desde el controlador: "+e.getMessage());
		}
		
	}
	
	
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			prestamo prestamo= new prestamo();
			int idcliente=Integer.parseInt(request.getParameter("idc"));
			int idprestamo=Integer.parseInt(request.getParameter("idp"));
			String nombrec=request.getParameter("nombre");
			
			prestamo.setIdprestamo(idprestamo);
			prestamo.setFechaprestamo(java.sql.Date.valueOf(request.getParameter("fechaprestamo")));
			
			prestamo.setMonto(Double.parseDouble(request.getParameter("monto")));
			prestamo.setIdcliente(idcliente);
			prestamo.setInteres(Integer.parseInt(request.getParameter("interes")));
			prestamo.setNumcuota(Integer.parseInt(request.getParameter("numcuotas")));
			
			if(modelo.modificar(prestamo)>0) {				
				response.sendRedirect(request.getContextPath()+"/PrestamosController?op=listar&id="+prestamo.getIdcliente()+"&nombre="+nombrec);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al modificar desde el controlador: "+e.getMessage());
		}
		
	}
	
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int idcliente= Integer.parseInt(request.getParameter("idc"));
			int idprestamo= Integer.parseInt(request.getParameter("idp"));
			String nombrec=request.getParameter("nombre");
			request.setAttribute("nombre",nombrec);
			request.setAttribute("prestamo", modelo.obtener(idcliente, idprestamo));
			request.getRequestDispatcher("/prestamo/editarprestamo.jsp").forward(request, response);	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al obtener desde el controlador: "+e.getMessage());
		}
		
	}
    
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int idprestamo= Integer.parseInt(request.getParameter("idp"));
			int idcliente= Integer.parseInt(request.getParameter("idc"));
			String nombrec=request.getParameter("nombre");
			
			modelo.eliminar(idprestamo);
			response.sendRedirect(request.getContextPath()+"/PrestamosController?op=listar&id="+idcliente+"&nombre="+nombrec);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar desde el controlador: "+e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		requestprocces(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		requestprocces(request, response);
	}

}
