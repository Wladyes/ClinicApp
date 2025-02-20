/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.CitaDAO;
import com.mycompany.clinicapp.dao.PacienteDAO;
import com.mycompany.clinicapp.dao.impl.CitaDAOImpl;
import com.mycompany.clinicapp.dao.impl.PacienteDAOImpl;
import com.mycompany.clinicapp.model.Cita;
import com.mycompany.clinicapp.model.Paciente;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/citas")
public class CitaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CitaDAO citaDAO = new CitaDAOImpl();
    private PacienteDAO pacienteDAO = new PacienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            listarCitas(request, response);
        } else {
            switch (action) {
                case "nueva":
                    mostrarFormularioNueva(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarCita(request, response);
                    break;
                default:
                    listarCitas(request, response);
                    break;
            }
        }
    }

    private void listarCitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pacienteIdStr = request.getParameter("pacienteId");
        List<Cita> citas;

        if (pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            // No se proporcionó pacienteId, obtener todas las citas
            citas = citaDAO.obtenerTodasLasCitas();

            // Obtener información de pacientes para cada cita
            Map<Integer, Paciente> mapaPacientes = new HashMap<>();
            for (Cita cita : citas) {
                int pid = cita.getPacienteId();
                if (!mapaPacientes.containsKey(pid)) {
                    Paciente p = pacienteDAO.obtenerPacientePorId(pid);
                    mapaPacientes.put(pid, p);
                }
            }

            request.setAttribute("citas", citas);
            request.setAttribute("mapaPacientes", mapaPacientes);
            request.getRequestDispatcher("/JSP/cita.jsp").forward(request, response);
        } else {
            // Se proporcionó pacienteId, obtener citas para ese paciente
            int pacienteId = Integer.parseInt(pacienteIdStr);
            citas = citaDAO.obtenerCitasPorPaciente(pacienteId);
            Paciente paciente = pacienteDAO.obtenerPacientePorId(pacienteId);

            request.setAttribute("citas", citas);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("/JSP/cita.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioNueva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pacienteIdStr = request.getParameter("pacienteId");
        List<Paciente> pacientes = pacienteDAO.obtenerTodosLosPacientes(); // Obtener lista de pacientes

        if (pacienteIdStr != null && !pacienteIdStr.isEmpty()) {
            int pacienteId = Integer.parseInt(pacienteIdStr);
            Paciente paciente = pacienteDAO.obtenerPacientePorId(pacienteId);
            request.setAttribute("paciente", paciente);
        }

        request.setAttribute("pacientes", pacientes);
        request.getRequestDispatcher("/JSP/citaForm.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID de la cita.");
            return;
        }

        int id = Integer.parseInt(idStr);
        Cita cita = citaDAO.obtenerCitaPorId(id);
        if (cita == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "La cita no existe.");
            return;
        }

        Paciente paciente = pacienteDAO.obtenerPacientePorId(cita.getPacienteId());

        request.setAttribute("cita", cita);
        request.setAttribute("paciente", paciente);
        request.getRequestDispatcher("/JSP/citaForm.jsp").forward(request, response);
    }

    private void eliminarCita(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID de la cita.");
            return;
        }

        int id = Integer.parseInt(idStr);

        citaDAO.eliminarCita(id);

        response.sendRedirect("citas");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String pacienteIdStr = request.getParameter("pacienteId");
        String fechaStr = request.getParameter("fecha");
        String horaStr = request.getParameter("hora");
        String motivo = request.getParameter("motivo");
        String estado = request.getParameter("estado");

        if (pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID del paciente.");
            return;
        }

        int pacienteId = Integer.parseInt(pacienteIdStr);

        Timestamp fechaHora = null;
        if (fechaStr != null && !fechaStr.isEmpty() && horaStr != null && !horaStr.isEmpty()) {
            fechaHora = Timestamp.valueOf(fechaStr + " " + horaStr + ":00");
        }

        Cita cita = new Cita();
        cita.setPacienteId(pacienteId);
        cita.setFecha(fechaHora);
        cita.setMotivo(motivo);
        cita.setEstado(estado);

        if (idStr == null || idStr.isEmpty()) {
            // Crear nueva cita
            citaDAO.crearCita(cita);
        } else {
            // Actualizar cita existente
            cita.setId(Integer.parseInt(idStr));
            citaDAO.actualizarCita(cita);
        }

        response.sendRedirect("citas");
    }
}