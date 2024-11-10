<%@page import="beans.prestamo"%>
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
	function eliminar(idprestamo, idcliente, nombrec) {
		if (confirm("¿Desea eliminar el prestamo?") == true) {
			location.href = "PrestamosController?op=eliminar&idp=" + idprestamo+"&idc=" + idcliente + "&nombre=" + nombrec;
		}
	}
</script>

<%
String url = "http://localhost:8080/ejerPractica/";

List<prestamo> prestamo = (List<prestamo>) request.getAttribute("prestamos");
String idc = (String) request.getAttribute("idcliente");
String nombrec = (String) request.getAttribute("nombre");

%>
</head>
<body>
	

	<div class="container">
		<h3>PRESTAMOS</h3>
		<h5>
			Cliente, Sr./Sra.
			<%=nombrec%></h5>
		<br>
		<table class="table">
			<thead>
				<tr>
					<th>Id prestamo</th>
					<th>Fecha de Prestamo</th>
					<th>Monto</th>
					<th>Interes</th>
					<th>N° Cuotas</th>
				</tr>

			</thead>
			<tbody>
				<%
				if (prestamo != null) {
					for (prestamo p : prestamo) {
				%>

				<tr>
					<td><%=p.getIdprestamo()%></td>
					<td><%=p.getFechaprestamo()%></td>
					<td><%=p.getMonto()%></td>
					<td><%=p.getInteres()%></td>
					<td><%=p.getNumcuota()%></td>
					<td><a
						href="<%=url%>PrestamosController?op=obtener&idc=<%=p.getIdcliente()%>&idp=<%=p.getIdprestamo()%>&nombre=<%=nombrec%>"
						class="btn btn-primary">Modificar</a> <a
						href="javascript:eliminar(<%=p.getIdprestamo()%>,<%=p.getIdcliente()%>,'<%=nombrec%>')"
						 class="btn btn-danger">Eliminar</a></td>


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
	<a
		href="<%=url%>PrestamosController?op=nuevo&id=<%=idc%>&nombre=<%=nombrec%>"
		class="btn btn-light">Nuevo Prestamo</a> <a
		href="<%=url%>ClienteController?op=listar">Volver</a>
</div>

</html>