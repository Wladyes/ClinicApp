<%-- 
    Document   : usuarioForm
    Created on : Feb 16, 2025, 11:58:03 PM
    Author     : wlady
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${usuario != null ? "Editar Usuario" : "Nuevo Usuario"}</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 600px;">
        <h1 class="text-center mb-4">${usuario != null ? "Editar Usuario" : "Nuevo Usuario"}</h1>

        <form action="usuarios" method="post">
            <c:if test="${usuario != null}">
                <input type="hidden" name="id" value="${usuario.id}">
            </c:if>

            <div class="mb-3">
                <label for="nombreUsuario" class="form-label">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" value="${usuario != null ? usuario.nombreUsuario : ''}" required>
            </div>

            <div class="mb-3">
                <label for="contrasena" class="form-label">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" class="form-control" value="${usuario != null ? usuario.contrasena : ''}" required>
            </div>

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" value="${usuario != null ? usuario.nombre : ''}" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" id="apellido" name="apellido" class="form-control" value="${usuario != null ? usuario.apellido : ''}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${usuario != null ? usuario.email : ''}">
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" class="form-control" value="${usuario != null ? usuario.telefono : ''}">
            </div>

            <div class="mb-3">
                <label for="idRol" class="form-label">Rol:</label>
                <select id="idRol" name="idRol" class="form-select" required>
                    <c:forEach var="rol" items="${roles}">
                        <option value="${rol.id}" ${usuario != null && usuario.idRol == rol.id ? 'selected' : ''}>${rol.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary w-100">Guardar</button>
        </form>

        <!-- Botones de navegación -->
        <div class="mt-4 d-flex justify-content-between">
            <a href="javascript:history.back()" class="btn btn-secondary">Regresar</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>