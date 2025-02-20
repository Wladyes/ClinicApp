package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.UsuarioDAO;
import com.mycompany.clinicapp.dao.RolDAO;
import com.mycompany.clinicapp.dao.impl.UsuarioDAOImpl;
import com.mycompany.clinicapp.dao.impl.RolDAOImpl;
import com.mycompany.clinicapp.model.Usuario;
import com.mycompany.clinicapp.model.Rol;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private RolDAO rolDAO = new RolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            listarUsuarios(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarUsuario(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
                    break;
            }
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/JSP/usuario.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rol> roles = rolDAO.obtenerTodosLosRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/JSP/usuarioForm.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        List<Rol> roles = rolDAO.obtenerTodosLosRoles();
        request.setAttribute("usuario", usuario);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/JSP/usuarioForm.jsp").forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect("usuarios");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String idRolStr = request.getParameter("idRol");
        String roleIdStr = request.getParameter("roleId");

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setIdRol(Integer.parseInt(idRolStr));
        if (roleIdStr != null && !roleIdStr.isEmpty()) {
            usuario.setRoleId(Integer.parseInt(roleIdStr));
        } else {
            usuario.setRoleId(null);
        }

        if (idStr == null || idStr.isEmpty()) {
            // Crear nuevo usuario
            boolean creado = usuarioDAO.crearUsuario(usuario);
            if (creado) {
                response.sendRedirect("usuarios");
            } else {
                request.setAttribute("error", "No se pudo crear el usuario. Verifique que el nombre de usuario no esté duplicado.");
                mostrarFormularioNuevo(request, response);
            }
        } else {
            // Actualizar usuario existente
            usuario.setId(Integer.parseInt(idStr));
            boolean actualizado = usuarioDAO.actualizarUsuario(usuario);
            if (actualizado) {
                response.sendRedirect("usuarios");
            } else {
                request.setAttribute("error", "No se pudo actualizar el usuario.");
                mostrarFormularioEditar(request, response);
            }
        }
    }
}