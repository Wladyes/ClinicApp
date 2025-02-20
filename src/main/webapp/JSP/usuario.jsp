<%-- 
    Document   : usuario
    Created on : Feb 16, 2025, 8:31:32 PM
    Author     : wlady
--%>


<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 900px;">
        <h1 class="text-center mb-4">Gestión de Usuarios</h1>

        <!-- Enlace para agregar un nuevo usuario -->
        <div class="mb-3 text-end">
            <a href="usuarios?action=nuevo" class="btn btn-primary">Agregar Usuario</a>
        </div>

        <!-- Tabla para listar los usuarios -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre de Usuario</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>ID Rol</th> <!-- Cambiado de "Rol" a "ID Rol" -->
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombreUsuario}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.apellido}</td>
                        <td>${usuario.email}</td>
                        <td>${usuario.telefono}</td>
                        <td>${usuario.idRol}</td> <!-- Cambiado de "usuario.rol.nombre" a "usuario.idRol" -->
                        <td>
                            <a href="usuarios?action=editar&id=${usuario.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="usuarios?action=eliminar&id=${usuario.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">Eliminar</a>
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