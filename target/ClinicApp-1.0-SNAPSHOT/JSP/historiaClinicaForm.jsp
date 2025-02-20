<%-- 
    Document   : historiaClinicaForm
    Created on : Feb 16, 2025, 11:25:21 PM
    Author     : wlady
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario Historia Clínica</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 600px;">
        <h1 class="text-center mb-4">
            <c:choose>
                <c:when test="${not empty historia}">
                    Editar Historia Clínica
                </c:when>
                <c:otherwise>
                    Nueva Historia Clínica
                </c:otherwise>
            </c:choose>
        </h1>

        <!-- Mostrar información del paciente -->
        <c:if test="${not empty paciente}">
            <p class="text-center"><strong>Paciente:</strong> ${paciente.nombre} ${paciente.apellido}</p>
        </c:if>

        <!-- Validación si el objeto paciente no está disponible -->
        <c:if test="${empty paciente}">
            <div class="alert alert-danger text-center">
                Error: No se encontró información del paciente.
            </div>
        </c:if>

        <!-- Formulario -->
        <form action="${pageContext.request.contextPath}/historiasClinicas" method="post">
            <!-- Campo oculto para el ID de la historia clínica (solo en edición) -->
            <c:if test="${not empty historia}">
                <input type="hidden" name="id" value="${historia.id}">
            </c:if>

            <!-- Campo oculto para el ID del paciente -->
            <input type="hidden" name="pacienteId" value="${paciente.id}">

            <!-- Fecha -->
            <div class="mb-3">
                <label for="fecha" class="form-label">Fecha</label>
                <input type="date" class="form-control" id="fecha" name="fecha" value="${historia.fecha}" required>
            </div>

            <!-- Descripción -->
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="4" required>${historia.descripcion}</textarea>
            </div>

            <!-- Botones -->
            <div class="d-flex justify-content-between">
                <a href="${pageContext.request.contextPath}/historiasClinicas?pacienteId=${paciente.id}" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </div>
        </form>
    </div>

</body>
</html>