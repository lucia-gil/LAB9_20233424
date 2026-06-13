<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Veterinaria - Mascotas</title>
    <style> <%--colorcito tode bonito x2--%>
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

        /* FILTRO Y BOTONES */
        .filtro {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        select {
            padding: 6px 12px;
            border: 2px solid #e8a0d8;
            border-radius: 20px;
            background: white;
            color: #4a2040;
            outline: none;
        }

        .btn {
            padding: 7px 18px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            font-weight: 600;
            transition: transform 0.1s, opacity 0.2s;
        }

        .btn:hover {
            opacity: 0.85;
            transform: scale(1.03);
        }

        .btn-primary {
            background: linear-gradient(135deg, #e06cb0, #b5479d);
            color: white;
        }

        .btn-danger {
            background: linear-gradient(135deg, #f48fb1, #e06cb0);
            color: white;
            font-size: 0.85rem;
            padding: 5px 12px;
        }

        /* TABLA */
        table {
            border-collapse: collapse;
            width: 100%;
            background: white;
            border-radius: 14px;
            overflow: hidden;
            box-shadow: 0 4px 16px rgba(180, 70, 157, 0.15);
            margin-top: 15px;
        }

        thead th {
            background: linear-gradient(90deg, #ce6fbb, #b5479d);
            color: white;
            padding: 12px 16px;
            text-align: left;
            font-weight: 600;
            letter-spacing: 0.04em;
        }

        tbody td {
            padding: 10px 16px;
            border-bottom: 1px solid #f5d0ee;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        tbody tr:hover {
            background: #fce4f6;
        }

        tbody tr:nth-child(even) {
            background: #fdf0fb;
        }

        tbody tr:nth-child(even):hover {
            background: #fce4f6;
        }
    </style>
</head>
<body>

<h2>Listado de Mascotas</h2>

<!-- filtro por especie (pregunta 4) -->
<div class="filtro">
    <form action="${pageContext.request.contextPath}/MascotaServlet" method="get">
        <input type="hidden" name="accion" value="filtrar"/>
        <label>Filtrar por especie:</label>
        <select name="especieId">
            <option value="0">-- Todas --</option>
            <c:forEach var="esp" items="${especies}">
                <option value="${esp.idespecie}"
                        <c:if test="${esp.idespecie == especieSeleccionada}">selected</c:if>>
                        ${esp.nombre}
                </option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">Filtrar</button>
    </form>
</div>

<!-- botoncito nuevo -->
<a href="${pageContext.request.contextPath}/MascotaServlet?accion=nuevo">
    <button class="btn btn-primary">+ Nueva Mascota</button>
</a>

<br/><br/>

<!-- tablita-->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Peso (kg)</th>
        <th>Especie</th>
        <th>Veterinario</th>
        <th>Dueño</th>
        <th>Acción</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="m" items="${mascotas}">
        <tr>
            <td>${m.idmascota}</td>
            <td>${m.nombre}</td>
            <td>${m.edad}</td>
            <td>${m.peso}</td>
            <td>${m.especie.nombre}</td>
            <td>${m.veterinario.nombre}</td>
            <td>${m.dueno.nombre}</td>
            <td>
                <a href="${pageContext.request.contextPath}/MascotaServlet?accion=borrar&id=${m.idmascota}"
                   onclick="return confirm('¿Eliminar mascota?')">
                    <button class="btn btn-danger">Borrar</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>