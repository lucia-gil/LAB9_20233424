<%--
  Created by IntelliJ IDEA.
  User: labtel
  Date: 12/06/2026
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registrar Usuario</title>
</head>
<body>
<jsp:include page="includes/navbar.jsp" />

<h2>Registrar Nuevo Usuario</h2>
<p style="color:red;">${error}</p>

<form action="${pageContext.request.contextPath}/usuarios" method="POST">
  <input type="hidden" name="action" value="crear">

  <label>Nombre:</label>
  <input type="text" name="nombre" required><br><br>

  <label>Apellido:</label>
  <input type="text" name="apellido" required><br><br>

  <label>DNI:</label>
  <input type="number" name="dni" required maxlength="8" minlength="8" title="Debe ser numérico de 8 dígitos"><br><br>

  <label>Correo:</label>
  <input type="email" name="correo" required><br><br>

  <label>Contraseña:</label>
  <input type="password" name="pass" required><br>
  <small>(Mínimo 8 caracteres, contener letras y números)</small><br><br>

  <button type="submit">Guardar Usuario</button>
  <a href="${pageContext.request.contextPath}/usuarios"><button type="button">Cancelar</button></a>
</form>
</body>
</html>
