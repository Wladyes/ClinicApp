 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.clinicapp.controller;

import com.mycompany.clinicapp.dao.UsuarioDAO;
import com.mycompany.clinicapp.dao.impl.UsuarioDAOImpl;
import com.mycompany.clinicapp.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Redirigir a la página de inicio de sesión
        request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenaIngresada = request.getParameter("contrasena");

        if (nombreUsuario != null) nombreUsuario = nombreUsuario.trim();
        if (contrasenaIngresada != null) contrasenaIngresada = contrasenaIngresada.trim();

        // Mensajes de depuración
        System.out.println("Nombre de Usuario ingresado: " + nombreUsuario);
        System.out.println("Contrasena ingresada: " + contrasenaIngresada);

        Usuario usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);

        if (usuario != null) {
            System.out.println("Usuario encontrado en la base de datos.");
            System.out.println("Contrasena almacenada: " + usuario.getContrasena());

            if (usuario.getContrasena().equals(contrasenaIngresada)) {
                System.out.println("Autenticación exitosa.");
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                System.out.println("Contrasena incorrecta.");
                request.setAttribute("error", "Nombre de usuario o contrasena incorrectos.");
                request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
            }
        } else {
            System.out.println("Usuario no encontrado.");
            request.setAttribute("error", "Nombre de usuario o contrasena incorrectos.");
            request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
        }
    }
}