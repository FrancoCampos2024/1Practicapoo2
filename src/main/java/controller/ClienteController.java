package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteModel;

import java.io.IOException;
import java.util.Date;

import beans.cliente;

/**
 * Servlet implementation class ClienteController
 */
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	ClienteModel modelo = new ClienteModel();

	protected void requestprocces(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");

		if (op == null) {
			listar(request, response);
			return;
		} else {
			switch (op) {
			case "listar": {
				listar(request, response);
				break;
			}
			case "nuevo": {
				request.getRequestDispatcher("/cliente/nuevocliente.jsp").forward(request, response);
				break;
			}
			case "insertar": {
				insertar(request, response);
				break;
			}
			case "modificar": {
				modificar(request, response);				
				break;
			}
			case "obtener":{
				obtener(request, response);
				break;
			}
			case "eliminar":{
				eliminar(request, response);
				break;
			}

			}
		}

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listarcliente", modelo.listar());
			request.getRequestDispatcher("/cliente/listarcliente.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al listar desde controller: " + e.getMessage());
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			cliente cliente = new cliente();

			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellidos"));
			cliente.setDni(request.getParameter("dni"));
			cliente.setFechanacimiento(java.sql.Date.valueOf(request.getParameter("fechanacimiento")));
			cliente.setDireccion(request.getParameter("direccion"));

			if (modelo.insertar(cliente) > 0) {
				response.sendRedirect(request.getContextPath() + "/ClienteController?op=listar");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar desde controller: " + e.getMessage());
		}
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int codigo= Integer.parseInt(request.getParameter("id"));
			request.setAttribute("cliente", modelo.obtener(codigo));
			
			request.getRequestDispatcher("/cliente/editarcliente.jsp").forward(request, response);			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al obtener desde controller: " + e.getMessage());
		}
	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			cliente cliente = new cliente();
			cliente.setIdcliente(Integer.parseInt(request.getParameter("id")));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellidos"));
			cliente.setDni(request.getParameter("dni"));
			cliente.setFechanacimiento(java.sql.Date.valueOf(request.getParameter("fechanacimiento")));
			cliente.setDireccion(request.getParameter("direccion"));

			if (modelo.modificar(cliente) > 0) {
				response.sendRedirect(request.getContextPath() + "/ClienteController?op=listar");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al modificar desde controller: " + e.getMessage());
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			modelo.eliminar(id);
			
			response.sendRedirect(request.getContextPath() + "/ClienteController?op=listar");
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar desde controller: " + e.getMessage());
		}
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		requestprocces(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		requestprocces(request, response);
	}

}
