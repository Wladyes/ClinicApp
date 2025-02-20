<%-- 
    Document   : cita
    Created on : Feb 16, 2025, 11:38:57 PM
    Author     : wlady
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${not empty paciente}">
                Citas de ${paciente.nombre} ${paciente.apellido}
            </c:when>
            <c:otherwise>
                Todas las Citas
            </c:otherwise>
        </c:choose>
    </title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 800px;">
        <h2 class="text-center mb-4">
            <c:choose>
                <c:when test="${not empty paciente}">
                    Citas de ${paciente.nombre} ${paciente.apellido}
                </c:when>
                <c:otherwise>
                    Todas las Citas
                </c:otherwise>
            </c:choose>
        </h2>

        <!-- Botón para agregar una nueva cita -->
        <div class="mb-3 text-end">
            <a href="${pageContext.request.contextPath}/citas?action=nueva" class="btn btn-primary">Agregar Cita</a>
        </div>

        <!-- Tabla de citas -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Paciente</th>
                    <th>Fecha y Hora</th>
                    <th>Motivo</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cita" items="${citas}">
                    <tr>
                        <td>${cita.id}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty paciente}">
                                    ${paciente.nombre} ${paciente.apellido}
                                </c:when>
                                <c:otherwise>
                                    <c:set var="pacienteCita" value="${mapaPacientes[cita.pacienteId]}" />
                                    ${pacienteCita.nombre} ${pacienteCita.apellido}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${cita.fecha}</td>
                        <td>${cita.motivo}</td>
                        <td>${cita.estado}</td>
                        <td>
                            <!-- Enlace para editar -->
                            <a href="${pageContext.request.contextPath}/citas?action=editar&id=${cita.id}" class="btn btn-warning btn-sm">Editar</a>
                            <!-- Enlace para eliminar -->
                            <a href="${pageContext.request.contextPath}/citas?action=eliminar&id=${cita.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar esta cita?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Botones de navegación -->
        <div class="mt-4 d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Regresar</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>