<%-- 
    Document   : historiaClinica
    Created on : Feb 16, 2025, 11:24:41 PM
    Author     : wlady

--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <c:choose>
            <c:when test="${not empty paciente}">
                Historias Clínicas de ${paciente.nombre} ${paciente.apellido}
            </c:when>
            <c:otherwise>
                Todas las Historias Clínicas
            </c:otherwise>
        </c:choose>
    </title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 900px;">
        <!-- Encabezado dinámico -->
        <c:choose>
            <c:when test="${not empty paciente}">
                <h1 class="text-center mb-4">Historias Clínicas de ${paciente.nombre} ${paciente.apellido}</h1>
            </c:when>
            <c:otherwise>
                <h1 class="text-center mb-4">Todas las Historias Clínicas</h1>
            </c:otherwise>
        </c:choose>

        <!-- Enlace para agregar una nueva historia clínica (solo si hay un paciente específico) -->
        <c:if test="${not empty paciente}">
            <div class="mb-3 text-end">
                <a href="${pageContext.request.contextPath}/historiasClinicas?action=nueva&pacienteId=${paciente.id}" class="btn btn-primary">Agregar Historia Clínica</a>
            </div>
        </c:if>

        <!-- Tabla para listar las historias clínicas -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Paciente</th>
                    <th>Fecha</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="historia" items="${historias}">
                    <tr>
                        <td>${historia.id}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty paciente}">
                                    ${paciente.nombre} ${paciente.apellido}
                                </c:when>
                                <c:otherwise>
                                    <c:set var="pacienteHistoria" value="${mapaPacientes[historia.pacienteId]}" />
                                    ${pacienteHistoria.nombre} ${pacienteHistoria.apellido}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${historia.fecha}</td>
                        <td>${historia.descripcion}</td>
                        <td>
                            <!-- Enlace para editar -->
                            <a href="${pageContext.request.contextPath}/historiasClinicas?action=editar&id=${historia.id}&pacienteId=${historia.pacienteId}" class="btn btn-warning btn-sm">Editar</a>
                            <!-- Enlace para eliminar -->
                            <a href="${pageContext.request.contextPath}/historiasClinicas?action=eliminar&id=${historia.id}&pacienteId=${historia.pacienteId}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar esta historia clínica?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Botones de navegación -->
        <div class="mt-4 d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/pacientes" class="btn btn-secondary">Regresar</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>