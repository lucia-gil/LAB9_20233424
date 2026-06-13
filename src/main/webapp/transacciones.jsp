
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Mis Transacciones</title>
</head>
<body>
<jsp:include page="includes/navbar.jsp" />

<h2>Mis Transacciones Registrada :)s</h2>
<a href="${pageContext.request.contextPath}/transacciones?action=formulario"><button>Nueva Transacción</button></a>
<br><br>

<table border="1" cellpadding="8" style="border-collapse: collapse;">
  <tr style="background-color: #f2f2f2;">
    <th>ID</th>
    <th>Título</th>
    <th>Monto</th>
    <th>Tipo</th>
    <th>Descripción</th>
    <th>Fecha</th>
    <th>Acciones</th>
  </tr>
  <c:forEach var="t" items="${listaTransacciones}">
    <tr>
      <td>${t.idtransacciones}</td>
      <td>${t.titulo}</td>
      <td>S/ ${t.monto}</td>
      <td style="color: ${t.tipo == 'ingreso' ? 'green' : 'red'}; font-weight: bold;">${t.tipo}</td>
      <td>${t.descripcion}</td>
      <td>${t.fecha}</td>
      <td>
        <a href="${pageContext.request.contextPath}/transacciones?action=borrar&id=${t.idtransacciones}" onclick="return confirm('¿etsas seguro de que deseas eliminar esta transacción?');">
          <button style="color: white; background-color: red; border: none; padding: 5px 10px; cursor: pointer;">Borrar</button>
        </a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

