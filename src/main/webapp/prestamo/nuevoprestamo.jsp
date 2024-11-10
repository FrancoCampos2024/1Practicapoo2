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
		return confirm("¿Está seguro de que desea realizar este prestamo?");
	}
</script>

</head>
<body>


<%
	String url="http://localhost:8080/ejerPractica/";

	String idc= (String)request.getAttribute("idc");
	String nombrec= (String)request.getAttribute("nombre");
	
	//System.out.println(idc);
%>
<div class="container">
        <h2 class="mb-4">REALIZAR PRESTAMO</h2>
        <h5>Cliente, Sr./Sra. <%=nombrec%></h5><br>
        <form  action="<%=url%>PrestamosController" method="post" onsubmit="return confirmarInsercion()">
            <input type="hidden" name="op" value="insertar">
            
            <input type="hidden" name="idc" value="<%=idc%>">
            
            <input type="hidden" name="nombre" value="<%=nombrec%>">
            
            
            <div class="mb-3">
                <label for="fechaprestamo" class="form-label">Fecha de Préstamo</label>
                <input type="date" class="form-control" id="fechaprestamo" name="fechaprestamo" required>
            </div>
            
           
            <div class="mb-3">
                <label for="monto" class="form-label">Monto</label>
                <input type="number" class="form-control" id="monto" name="monto" step="0.01"  required>
            </div>
                                  
            
            <div class="mb-3">
                <label for="interes" class="form-label">Interés (%)</label>
                <input type="number" class="form-control" id="interes" name="interes" required>
            </div>
            
            
            <div class="mb-3">
                <label for="numcuotas" class="form-label">Número de Cuotas</label>
                <input type="number" class="form-control" id="numcuotas" name="numcuotas"  required>
            </div>
            
            
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="<%=url%>PrestamosController?op=listar&id=<%=idc%>&nombre=<%=nombrec%>">Volver</a>
            <a></a>
        </form>
    </div>



</body>
</html>