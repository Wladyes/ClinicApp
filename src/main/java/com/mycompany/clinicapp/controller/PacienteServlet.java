/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.PacienteDAO;
import com.mycompany.clinicapp.dao.impl.PacienteDAOImpl;
import com.mycompany.clinicapp.model.Paciente;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PacienteDAO pacienteDAO = new PacienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Verificar si la sesión está activa y si el usuario está autenticado
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            listarPacientes(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarPaciente(request, response);
                    break;
                default:
                    listarPacientes(request, response);
                    break;
            }
        }
    }

    private void listarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> pacientes = pacienteDAO.obtenerTodosLosPacientes();
        request.setAttribute("pacientes", pacientes);
        request.getRequestDispatcher("/JSP/paciente.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/pacienteForm.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = pacienteDAO.obtenerPacientePorId(id);
        request.setAttribute("paciente", paciente);
        request.getRequestDispatcher("/JSP/pacienteForm.jsp").forward(request, response);
    }

    private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pacienteDAO.eliminarPaciente(id);
        response.sendRedirect("pacientes");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        LocalDate fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            // Parsear la fecha desde el formato del formulario
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);

        if (idStr == null || idStr.isEmpty()) {
            // Crear nuevo paciente
            pacienteDAO.crearPaciente(paciente);
        } else {
            // Actualizar paciente existente
            paciente.setId(Integer.parseInt(idStr));
            pacienteDAO.actualizarPaciente(paciente);
        }

        response.sendRedirect("pacientes");
    }
}