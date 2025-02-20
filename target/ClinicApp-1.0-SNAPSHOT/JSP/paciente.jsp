<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.clinicapp.model.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Verificar si hay sesión activa
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login"); // Redirigir al login si no hay sesión activa
        return;
    }
    // Obtener el usuario autenticado de la sesión
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Pacientes</title>

    <!-- Enlace a Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Enlace a tu hoja de estilos CSS personalizada -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styles.css">
</head>
<body class="d-flex flex-column align-items-center" style="background: linear-gradient(135deg, #f4f4f4, #e0e0e0); min-height: 100vh;">

    <div class="container p-4 border rounded shadow bg-white mt-5" style="max-width: 900px;">
        <h1 class="text-center mb-4">Gestión de Pacientes</h1>

        <!-- Mostrar un mensaje de bienvenida al usuario autenticado -->
        <p class="text-center">Bienvenido, <strong><%= usuario.getNombre() %> <%= usuario.getApellido() %></strong></p>

        <!-- Enlace para agregar un nuevo paciente -->
        <div class="mb-3 text-end">
            <a href="pacientes?action=nuevo" class="btn btn-primary">Agregar Paciente</a>
        </div>

        <!-- Tabla para listar los pacientes -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Fecha Nacimiento</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="paciente" items="${pacientes}">
                    <tr>
                        <td>${paciente.id}</td>
                        <td>${paciente.nombre}</td>
                        <td>${paciente.apellido}</td>
                        <td>${paciente.fechaNacimiento}</td>
                        <td>${paciente.direccion}</td>
                        <td>${paciente.telefono}</td>
                        <td>${paciente.email}</td>
                        <td>
                            <a href="pacientes?action=editar&id=${paciente.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="pacientes?action=eliminar&id=${paciente.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este paciente?');">Eliminar</a>
                            <a href="historiaClinica?pacienteId=${paciente.id}" class="btn btn-info btn-sm">Historias Clínicas</a>
                            <a href="citas?pacienteId=${paciente.id}" class="btn btn-success btn-sm">Citas</a>
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