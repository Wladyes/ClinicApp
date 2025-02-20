<%-- 
    Document   : home
    Created on : Feb 17, 2025, 12:09:59 AM
    Author     : wlady
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="${pageContext.request.contextPath}/login" />
</c:if>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a ClinicApp</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 800px;">
        <h1 class="text-center mb-4">Bienvenido, ${sessionScope.usuario.nombre} ${sessionScope.usuario.apellido}</h1>
        <p class="text-center">Has iniciado sesión como: <strong>${sessionScope.usuario.nombreUsuario}</strong></p>

        <h2 class="text-center mt-4">Menú Principal</h2>
        <ul class="list-group mt-3">
            <!-- Opciones disponibles para todos los usuarios -->
            <li class="list-group-item">
                <a href="${pageContext.request.contextPath}/pacientes" class="text-decoration-none">Gestión de Pacientes</a>
            </li>
            <li class="list-group-item">
                <a href="${pageContext.request.contextPath}/citas" class="text-decoration-none">Gestión de Citas</a>
            </li>

            <!-- Opciones adicionales para médicos -->
            <c:if test="${sessionScope.usuario.idRol == 9}">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/historiasClinicas" class="text-decoration-none">Historias Clínicas</a>
                </li>
            </c:if>

            <!-- Opciones adicionales para administradores -->
            <c:if test="${sessionScope.usuario.idRol == 8}">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/usuarios" class="text-decoration-none">Gestión de Usuarios</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/roles" class="text-decoration-none">Gestión de Roles</a>
                </li>
            </c:if>
        </ul>

        <div class="mt-4 d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Cerrar Sesión</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>