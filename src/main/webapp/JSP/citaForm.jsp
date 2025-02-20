<%-- 
    Document   : citaForm
    Created on : Feb 16, 2025, 11:39:29 PM
    Author     : wlady
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${cita != null ? "Editar Cita" : "Nueva Cita"}</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 600px;">
        <h1 class="text-center mb-4">${cita != null ? "Editar Cita" : "Nueva Cita"}</h1>

        <!-- Formulario para crear o editar una cita -->
        <form action="${pageContext.request.contextPath}/citas" method="post">
            <c:if test="${cita != null}">
                <input type="hidden" name="id" value="${cita.id}">
            </c:if>

            <div class="mb-3">
                <label for="pacienteId" class="form-label">Paciente:</label>
                <c:choose>
                    <c:when test="${not empty paciente}">
                        <!-- Mostrar el nombre del paciente y un campo oculto -->
                        <input type="hidden" name="pacienteId" value="${paciente.id}">
                        <p>${paciente.nombre} ${paciente.apellido}</p>
                    </c:when>
                    <c:otherwise>
                        <!-- Mostrar un select para que el usuario elija un paciente -->
                        <select id="pacienteId" name="pacienteId" class="form-select" required>
                            <option value="">Seleccione un paciente</option>
                            <c:forEach var="p" items="${pacientes}">
                                <option value="${p.id}" ${cita != null && cita.pacienteId == p.id ? "selected" : ""}>
                                    ${p.nombre} ${p.apellido}
                                </option>
                            </c:forEach>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="mb-3">
                <label for="fecha" class="form-label">Fecha:</label>
                <input type="date" id="fecha" name="fecha" class="form-control" value="${cita != null ? cita.fecha.toLocalDateTime().toLocalDate() : ''}" required>
            </div>

            <div class="mb-3">
                <label for="hora" class="form-label">Hora:</label>
                <input type="time" id="hora" name="hora" class="form-control" value="${cita != null ? cita.fecha.toLocalDateTime().toLocalTime() : ''}" required>
            </div>

            <div class="mb-3">
                <label for="motivo" class="form-label">Motivo:</label>
                <textarea id="motivo" name="motivo" class="form-control" rows="5" required>${cita != null ? cita.motivo : ''}</textarea>
            </div>

            <div class="mb-3">
                <label for="estado" class="form-label">Estado:</label>
                <select id="estado" name="estado" class="form-select" required>
                    <option value="Programada" ${cita != null && cita.estado == 'Programada' ? 'selected' : ''}>Programada</option>
                    <option value="Cancelada" ${cita != null && cita.estado == 'Cancelada' ? 'selected' : ''}>Cancelada</option>
                    <option value="Completada" ${cita != null && cita.estado == 'Completada' ? 'selected' : ''}>Completada</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary w-100">Guardar</button>
        </form>

        <!-- Botones de navegación -->
        <div class="mt-4 d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/citas" class="btn btn-secondary">Regresar</a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-success">Home</a>
        </div>
    </div>

</body>
</html>