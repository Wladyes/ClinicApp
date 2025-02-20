/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.dao;

import com.mycompany.clinicapp.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    List<Usuario> obtenerTodosLosUsuarios();
    boolean crearUsuario(Usuario usuario);
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
    Usuario obtenerUsuarioPorId(int id);
    Usuario buscarPorNombreUsuario(String nombreUsuario);
}