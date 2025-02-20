package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.HistoriaClinicaDAO;
import com.mycompany.clinicapp.dao.PacienteDAO;
import com.mycompany.clinicapp.dao.impl.HistoriaClinicaDAOImpl;
import com.mycompany.clinicapp.dao.impl.PacienteDAOImpl;
import com.mycompany.clinicapp.model.HistoriaClinica;
import com.mycompany.clinicapp.model.Paciente;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet({"/historiaClinica", "/historiasClinicas"}) // Maneja ambas URLs
public class HistoriaClinicaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final HistoriaClinicaDAO historiaDAO = new HistoriaClinicaDAOImpl();
    private final PacienteDAO pacienteDAO = new PacienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listarHistorias(request, response); // Acción predeterminada
        } else {
            switch (action) {
                case "nueva":
                    mostrarFormularioNueva(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarHistoria(request, response);
                    break;
                default:
                    listarHistorias(request, response); // Acción predeterminada si la acción no es válida
                    break;
            }
        }
    }

    private void listarHistorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pacienteIdStr = request.getParameter("pacienteId");
        List<HistoriaClinica> historias;

        if (pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            // No se proporcionó pacienteId, obtener todas las historias clínicas
            historias = historiaDAO.obtenerTodasLasHistorias();

            // Obtener información de pacientes para cada historia
            Map<Integer, Paciente> mapaPacientes = new HashMap<>();
            for (HistoriaClinica historia : historias) {
                int pid = historia.getPacienteId();
                if (!mapaPacientes.containsKey(pid)) {
                    Paciente p = pacienteDAO.obtenerPacientePorId(pid);
                    mapaPacientes.put(pid, p);
                }
            }

            request.setAttribute("historias", historias);
            request.setAttribute("mapaPacientes", mapaPacientes);
            request.getRequestDispatcher("/JSP/historiaClinica.jsp").forward(request, response);
        } else {
            // Se proporcionó pacienteId, obtener historias para ese paciente
            try {
                int pacienteId = Integer.parseInt(pacienteIdStr);
                historias = historiaDAO.obtenerHistoriasPorPaciente(pacienteId);
                Paciente paciente = pacienteDAO.obtenerPacientePorId(pacienteId);

                if (paciente == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paciente no encontrado.");
                    return;
                }

                request.setAttribute("historias", historias);
                request.setAttribute("paciente", paciente);
                request.getRequestDispatcher("/JSP/historiaClinica.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del paciente debe ser un número válido.");
            }
        }
    }

    private void mostrarFormularioNueva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pacienteIdStr = request.getParameter("pacienteId");

        if (pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID del paciente.");
            return;
        }

        try {
            int pacienteId = Integer.parseInt(pacienteIdStr);
            Paciente paciente = pacienteDAO.obtenerPacientePorId(pacienteId);

            if (paciente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paciente no encontrado.");
                return;
            }

            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("/JSP/historiaClinicaForm.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del paciente debe ser un número válido.");
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID de la historia clínica.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            HistoriaClinica historia = historiaDAO.obtenerHistoriaPorId(id);

            if (historia == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Historia clínica no encontrada.");
                return;
            }

            Paciente paciente = pacienteDAO.obtenerPacientePorId(historia.getPacienteId());

            if (paciente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paciente no encontrado.");
                return;
            }

            request.setAttribute("historia", historia);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("/JSP/historiaClinicaForm.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de la historia clínica debe ser un número válido.");
        }
    }

    private void eliminarHistoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String pacienteIdStr = request.getParameter("pacienteId");

        if (idStr == null || idStr.isEmpty() || pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID de la historia clínica y del paciente.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int pacienteId = Integer.parseInt(pacienteIdStr);

            boolean eliminada = historiaDAO.eliminarHistoriaClinica(id);
            if (!eliminada) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo eliminar la historia clínica.");
                return;
            }

            response.sendRedirect("historiasClinicas?pacienteId=" + pacienteId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los IDs deben ser números válidos.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String pacienteIdStr = request.getParameter("pacienteId");
        String fechaStr = request.getParameter("fecha");
        String descripcion = request.getParameter("descripcion");

        if (pacienteIdStr == null || pacienteIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el ID del paciente.");
            return;
        }

        try {
            int pacienteId = Integer.parseInt(pacienteIdStr);
            Paciente paciente = pacienteDAO.obtenerPacientePorId(pacienteId);

            if (paciente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paciente no encontrado.");
                return;
            }

            Date fecha = null;
            if (fechaStr != null && !fechaStr.isEmpty()) {
                fecha = Date.valueOf(fechaStr);
            }

            HistoriaClinica historia = new HistoriaClinica();
            historia.setPacienteId(pacienteId);
            historia.setFecha(fecha);
            historia.setDescripcion(descripcion);

            if (idStr == null || idStr.isEmpty()) {
                boolean creada = historiaDAO.crearHistoriaClinica(historia);
                if (!creada) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo crear la historia clínica.");
                    return;
                }
            } else {
                historia.setId(Integer.parseInt(idStr));
                boolean actualizada = historiaDAO.actualizarHistoriaClinica(historia);
                if (!actualizada) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo actualizar la historia clínica.");
                    return;
                }
            }

            response.sendRedirect("historiasClinicas?pacienteId=" + pacienteId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del paciente debe ser un número válido.");
        }
    }
}