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
		return confirm("¿Está seguro de que desea insertar este cliente?");
	}
</script>

</head>
<body>

	<%
	String url = "http://localhost:8080/ejerPractica/";
	%>



	<div class="container">
		<h3>INSERTAR CLIENTE</h3>
		<form action="<%=url%>ClienteController" method="post"
			class="p-4 border rounded" onsubmit="return confirmarInsercion()">
			<input type="hidden" name="op" value="insertar">

			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre" required>
			</div>

			<div class="form-group">
				<label for="apellidos">Apellidos:</label> <input type="text"
					class="form-control" id="apellidos" name="apellidos" required>
			</div>

			<div class="form-group">
				<label for="dni">DNI:</label> <input type="text"
					class="form-control" id="dni" name="dni" required>
			</div>

			<div class="form-group">
				<label for="fechanacimiento">Fecha de Nacimiento:</label> <input
					type="date" class="form-control" id="fechanacimiento"
					name="fechanacimiento" required>
			</div>

			<div class="form-group">
				<label for="direccion">Dirección:</label> <input type="text"
					class="form-control" id="direccion" name="direccion" required>
			</div>

			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="<%=url%>ClienteController?op=listar">Volver</a>
		</form>

	</div>




</body>
</html>