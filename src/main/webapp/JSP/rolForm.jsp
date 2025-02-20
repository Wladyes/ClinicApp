<%-- 
    Document   : rolForm
    Created on : Feb 17, 2025, 12:02:48 AM
    Author     : wlady
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${rol != null ? "Editar Rol" : "Nuevo Rol"}</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 600px;">
        <h1 class="text-center mb-4">${rol != null ? "Editar Rol" : "Nuevo Rol"}</h1>

        <!-- Mensaje de error si existe -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>

        <form action="roles" method="post">
            <c:if test="${rol != null}">
                <input type="hidden" name="id" value="${rol.id}">
            </c:if>

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del Rol:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" value="${rol != null ? rol.nombre : ''}" required>
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