<%@page import="beans.cliente"%>
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
	function confirmarInsercion() {
		return confirm("¿Está seguro de que desea modificar este cliente?");
	}
</script>

</head>
<body>

	<%
	String url = "http://localhost:8080/ejerPractica/";
	cliente c = (cliente) request.getAttribute("cliente");
	%>

	<div class="container">
		<h3>EDITAR CLIENTE</h3>
		<form action="<%=url%>ClienteController" method="post" onsubmit="return confirmarInsercion()"
			class="p-4 border rounded">
			<input type="hidden" name="op" value="modificar">

			<div class="form-group">
				<label for="id">Id Cliente:</label> <input type="text"
					class="form-control" id="id" name="id"
					value="<%=c.getIdcliente()%>" required>
			</div>

			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre"
					value="<%=c.getNombre()%>" required>
			</div>

			<div class="form-group">
				<label for="apellidos">Apellidos:</label> <input type="text"
					class="form-control" id="apellidos" name="apellidos"
					value="<%=c.getApellido()%>" required>
			</div>

			<div class="form-group">
				<label for="dni">DNI:</label> <input type="text"
					class="form-control" id="dni" name="dni" value="<%=c.getDni()%>"
					required>
			</div>

			<div class="form-group">
				<label for="fechanacimiento">Fecha de Nacimiento:</label> <input
					type="date" class="form-control" id="fechanacimiento"
					name="fechanacimiento" value="<%=c.getFechanacimiento()%>" required>
			</div>

			<div class="form-group">
				<label for="direccion">Dirección:</label> <input type="text"
					class="form-control" id="direccion" name="direccion"
					value="<%=c.getDireccion()%>" required>
			</div>

			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="<%=url%>ClienteController?op=listar">Volver</a>
		</form>



	</div>



</body>
</html>