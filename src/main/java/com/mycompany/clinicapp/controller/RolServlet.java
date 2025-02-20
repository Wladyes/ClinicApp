/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.RolDAO;
import com.mycompany.clinicapp.dao.impl.RolDAOImpl;
import com.mycompany.clinicapp.model.Rol;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/roles")
public class RolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RolDAO rolDAO = new RolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            listarRoles(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarRol(request, response);
                    break;
                default:
                    listarRoles(request, response);
                    break;
            }
        }
    }

    private void listarRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rol> roles = rolDAO.obtenerTodosLosRoles();
        request.setAttribute("roles", roles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/rol.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/rolForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Rol rol = rolDAO.obtenerRolPorId(id);
        request.setAttribute("rol", rol);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/rolForm.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminarRol(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        rolDAO.eliminarRol(id);
        response.sendRedirect("roles");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        Rol rol = new Rol();
        rol.setNombre(nombre);

        if (idStr == null || idStr.isEmpty()) {
            // Crear nuevo rol
            boolean creado = rolDAO.crearRol(rol);
            if (creado) {
                response.sendRedirect("roles");
            } else {
                request.setAttribute("error", "No se pudo crear el rol.");
                mostrarFormularioNuevo(request, response);
            }
        } else {
            // Actualizar rol existente
            rol.setId(Integer.parseInt(idStr));
            boolean actualizado = rolDAO.actualizarRol(rol);
            if (actualizado) {
                response.sendRedirect("roles");
            } else {
                request.setAttribute("error", "No se pudo actualizar el rol.");
                mostrarFormularioEditar(request, response);
            }
        }
    }
}