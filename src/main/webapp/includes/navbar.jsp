<%--
  Created by IntelliJ IDEA.
  User: labtel
  Date: 12/06/2026
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="background-color: #004d99; padding: 15px; color: white; font-family: Arial;">
  <h3 style="display:inline; margin-right: 30px;">Gestión de Gastos - ${sessionScope.usuarioLogueado.nombre}</h3>
  <a href="${pageContext.request.contextPath}/usuarios" style="color: white; margin-right: 15px; text-decoration: none;">Usuarios</a>
  <a href="${pageContext.request.contextPath}/transacciones" style="color: white; margin-right: 15px; text-decoration: none;">Transacciones</a>
  <a href="${pageContext.request.contextPath}/logout" style="color: #ffcccc; float: right; text-decoration: none;">Cerrar Sesión</a>
</div>
<hr>
