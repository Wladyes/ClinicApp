<%-- 
    Document   : rol
    Created on : Feb 16, 2025, 9:56:58 PM
    Author     : wlady
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Roles</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 800px;">
        <h1 class="text-center mb-4">Gestión de Roles</h1>

        <!-- Enlace para agregar un nuevo rol -->
        <div class="mb-3 text-end">
            <a href="roles?action=nuevo" class="btn btn-primary">Agregar Rol</a>
        </div>

        <!-- Tabla para listar los roles -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="rol" items="${roles}">
                    <tr>
                        <td>${rol.id}</td>
                        <td>${rol.nombre}</td>
                        <td>
                            <a href="roles?action=editar&id=${rol.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="roles?action=eliminar&id=${rol.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este rol?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Botones de navegación -->
        <div class="mt-4 d-flex justify-content-between">
            <a href="javascript:history.back()" class="btn btn-secondary">Regresar</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>