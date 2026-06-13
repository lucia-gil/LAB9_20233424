<%--
  Created by IntelliJ IDEA.
  User: labtel
  Date: 12/06/2026
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registrar Transaccion</title>
</head>
<body>
<jsp:include page="includes/navbar.jsp" />

<h2>Registrar Transacción</h2>
<p style="color:red;">${error}</p>

<form action="${pageContext.request.contextPath}/transacciones" method="POST">
  <input type="hidden" name="action" value="crear">

  <label>Título:</label>
  <input type="text" name="titulo" required><br><br>

  <label>Tipo:</label>
  <select name="tipo" required>
    <option value="ingreso">Ingreso</option>
    <option value="egreso">Egreso</option>
  </select><br><br>

  <label>Monto:</label>
  <input type="number" step="0.01" name="monto" min="0.01" required><br><br>

  <label>Descripción (Opcional):</label><br>
  <textarea name="descripcion" rows="3" cols="30"></textarea><br><br>

  <button type="submit">Guardar Transacción</button>
  <a href="${pageContext.request.contextPath}/transacciones"><button type="button">Cancelar</button></a>
</form>
</body>
</html>
