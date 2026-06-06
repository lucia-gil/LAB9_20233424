<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nueva Mascota</title>
    <style> <%--para darle colorcito uwu--%>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #fdf0f8;
            margin: 30px;
            color: #4a2040;
        }

        h2 {
            color: #b5479d;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 12px;
            font-weight: 600;
            color: #7d3070;
        }

        input, select {
            padding: 8px 14px;
            width: 320px;
            margin-top: 5px;
            border: 2px solid #e8a0d8;
            border-radius: 20px;
            outline: none;
            color: #4a2040;
            transition: border-color 0.2s;
        }

        input:focus, select:focus {
            border-color: #b5479d;
        }

        .btn-primary {
            margin-top: 20px;
            background: linear-gradient(135deg, #e06cb0, #b5479d);
            color: white;
            border: none;
            padding: 10px 24px;
            border-radius: 20px;
            cursor: pointer;
            font-weight: 600;
            font-size: 1rem;
            transition: opacity 0.2s, transform 0.1s;
        }

        .btn-primary:hover {
            opacity: 0.87;
            transform: scale(1.03);
        }

        .btn-back {
            display: inline-block;
            margin-top: 12px;
            color: #b5479d;
            text-decoration: none;
            font-weight: 600;
        }

        .btn-back:hover {
            text-decoration: underline;
        }

        form {
            background: white;
            padding: 24px 30px;
            border-radius: 16px;
            box-shadow: 0 4px 16px rgba(180, 70, 157, 0.13);
            display: inline-block;
            min-width: 380px;
        }
    </style>
</head>
<body>

<h2>Registrar Nueva Mascota</h2>

<form action="${pageContext.request.contextPath}/MascotaServlet" method="post">

    <label>Nombre:</label>
    <input type="text" name="nombre" required/>

    <label>Edad (años):</label>
    <input type="number" name="edad" min="0" required/>

    <label>Peso (kg):</label>
    <input type="number" name="peso" step="0.01" min="0" required/>

    <!-- Combobox Especie -->
    <label>Especie:</label>
    <select name="especieId" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="esp" items="${especies}">
            <option value="${esp.idespecie}">${esp.nombre}</option>
        </c:forEach>
    </select>

    <!-- Combobox Vet -->
    <label>Veterinario:</label>
    <select name="veterinarioId" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="vet" items="${veterinarios}">
            <option value="${vet.idveterinario}">${vet.nombre} (${vet.especialidad})</option>
        </c:forEach>
    </select>

    <!-- Combobox Dueño -->
    <label>Dueño:</label>
    <select name="duenoId" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="due" items="${duenos}">
            <option value="${due.iddueno}">${due.nombre}</option>
        </c:forEach>
    </select>

    <br/>
    <button type="submit" class="btn-primary">Registrar</button>
</form>

<br/>
<a class="btn-back" href="${pageContext.request.contextPath}/MascotaServlet?accion=listar">
    <- Cancelar :c
</a>

</body>
</html>