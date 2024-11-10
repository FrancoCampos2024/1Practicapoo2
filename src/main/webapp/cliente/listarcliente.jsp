<%@page import="beans.cliente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js">
	
</script>

<script>
	function eliminar(id) {
		if (confirm("¿Desea eliminar el cliente?, tener en cuenta que se eliminara toda operacion de prestamo de este cliente.") == true) {
			location.href = "ClienteController?op=eliminar&id=" + id;

		} else {

		}
	}
</script>

</head>
<body>
	<%
	String url = "http://localhost:8080/ejerPractica/";

	List<cliente> client = (List<cliente>) request.getAttribute("listarcliente");
	%>

	<div class="container">
		<h3>CLIENTES</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Id Cliente</th>
					<th>Nombres</th>
					<th>Apellidos</th>
					<th>DNI</th>
					<th>Fecha Nacimiento</th>
					<th>Direccion</th>
				</tr>

			</thead>
			<tbody>
				<%
				if (client != null) {
					for (cliente c : client) {
				%>

				<tr>
					<td><%=c.getIdcliente()%></td>
					<td><%=c.getNombre()%></td>
					<td><%=c.getApellido()%></td>
					<td><%=c.getDni()%></td>
					<td><%=c.getFechanacimiento()%></td>
					<td><%=c.getDireccion()%></td>
					<td><a
						href="<%=url%>ClienteController?op=obtener&id=<%=c.getIdcliente()%>"
						class="btn btn-primary">Modificar</a></td>
					<td><a href="javascript:eliminar(<%=c.getIdcliente()%>) "
						class="btn btn-danger">Eliminar</a></td>
					<td><a
						href="<%=url%>PrestamosController?op=listar&id=<%=c.getIdcliente()%>&nombre=<%=c.getNombre() + " " + c.getApellido()%>"
						class="btn btn-info">Prestamos</a></td>

				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

</body>
<div class="container">
	<a href="<%=url%>ClienteController?op=nuevo" class="btn btn-light">Nuevo</a>
</div>

</html>