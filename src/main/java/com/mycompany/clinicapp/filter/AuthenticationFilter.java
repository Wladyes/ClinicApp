/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
package com.mycompany.clinicapp.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        // Inicialización si es necesario
    }

    public void destroy() {
        // Limpieza si es necesario
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Convertir a HttpServletRequest y HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener la sesión actual
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("usuario") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/resources/");

        if (loggedIn || loginRequest || resourceRequest) {
            // El usuario está autenticado, o está solicitando el login, o un recurso estático
            chain.doFilter(request, response);
        } else {
            // El usuario no está autenticado, redirigir al login
            res.sendRedirect(loginURI);
        }
    }
}
*/

package com.mycompany.clinicapp.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("🔍 AuthenticationFilter inicializado.");
    }

    @Override
    public void destroy() {
        System.out.println("🛑 AuthenticationFilter destruido.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Convertir a HttpServletRequest y HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener la sesión actual
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("usuario") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/resources/");

        System.out.println("🔎 Filtro ejecutado en: " + req.getRequestURI());
        System.out.println("➡️ Usuario autenticado: " + loggedIn);
        System.out.println("➡️ Solicitud de login: " + loginRequest);
        System.out.println("➡️ Solicitud de recurso: " + resourceRequest);

        if (loggedIn || loginRequest || resourceRequest) {
            System.out.println("✅ Acceso permitido a: " + req.getRequestURI());
            chain.doFilter(request, response);
        } else {
            System.out.println("❌ Usuario no autenticado, redirigiendo a login.");
            res.sendRedirect(loginURI);
        }
    }
}
