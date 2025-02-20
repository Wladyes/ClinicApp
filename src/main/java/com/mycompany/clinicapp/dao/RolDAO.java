/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.dao;

import com.mycompany.clinicapp.model.Rol;
import java.util.List;

public interface RolDAO {
    List<Rol> obtenerTodosLosRoles();
    boolean crearRol(Rol rol);
    boolean actualizarRol(Rol rol);
    boolean eliminarRol(int id);
    Rol obtenerRolPorId(int id);
}