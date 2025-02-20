/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.dao;

import com.mycompany.clinicapp.model.Cita;
import java.util.List;

public interface CitaDAO {
    List<Cita> obtenerCitasPorPaciente(int pacienteId);
    boolean crearCita(Cita cita);
    boolean actualizarCita(Cita cita);
    boolean eliminarCita(int id);
    Cita obtenerCitaPorId(int id);
    
    
     // Nuevo método para obtener todas las citas
    List<Cita> obtenerTodasLasCitas();
}