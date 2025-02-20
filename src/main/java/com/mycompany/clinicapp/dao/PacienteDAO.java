/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.dao;

import com.mycompany.clinicapp.model.Paciente;
import java.util.List;

public interface PacienteDAO {
    // Método para obtener todos los pacientes
    List<Paciente> obtenerTodosLosPacientes();

    // Método para crear un nuevo paciente
    boolean crearPaciente(Paciente paciente);

    // Método para actualizar un paciente existente
    boolean actualizarPaciente(Paciente paciente);

    // Método para eliminar un paciente por su ID
    boolean eliminarPaciente(int id);

    // Método para obtener un paciente por su ID
    Paciente obtenerPacientePorId(int id);
}