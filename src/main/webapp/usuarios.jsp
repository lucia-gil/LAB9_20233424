<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Lista de Usuarios</title>
</head>
<body>
<jsp:include page="includes/navbar.jsp" />

<h2>Directorio de Usuarios</h2>
<a href="${pageContext.request.contextPath}/usuarios?action=formulario"><button>Registrar Nuevo Usuario</button></a>
<br><br>

<table border="1" cellpadding="8" style="border-collapse: collapse;">
  <tr style="background-color: #f2f2f2;">
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>DNI</th>
    <th>Correo</th>
  </tr>
  <c:forEach var="u" items="${listaUsuarios}">
    <tr>
      <td>${u.idusuarios}</td>
      <td>${u.nombre}</td>
      <td>${u.apellido}</td>
      <td>${u.dni}</td>
      <td>${u.correo}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
