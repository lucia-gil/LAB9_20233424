<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio de Sesion uwu</title>
</head>
<body>
<h2>Gestión de Gastos - Login</h2>
<p style="color:red;">${error}</p>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <label>Correo:</label>
    <input type="email" name="correo" required>
    <br><br>
    <label>Contraseña:</label>
    <input type="password" name="pass" required>
    <br><br>
    <button type="submit">Ingresar</button>
</form>
</body>
</html>
