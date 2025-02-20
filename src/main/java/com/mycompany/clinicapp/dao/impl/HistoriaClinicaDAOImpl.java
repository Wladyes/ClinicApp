/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicapp.dao.impl;

import com.mycompany.clinicapp.dao.HistoriaClinicaDAO;
import com.mycompany.clinicapp.model.HistoriaClinica;
import com.mycompany.clinicapp.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaDAOImpl implements HistoriaClinicaDAO {

    @Override
    public List<HistoriaClinica> obtenerHistoriasPorPaciente(int pacienteId) {
        List<HistoriaClinica> historias = new ArrayList<>();
        String sql = "SELECT * FROM historia_clinica WHERE paciente_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer el parámetro del pacienteId
            stmt.setInt(1, pacienteId);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Procesar los resultados
                while (rs.next()) {
                    HistoriaClinica historia = new HistoriaClinica();
                    historia.setId(rs.getInt("id"));
                    historia.setPacienteId(rs.getInt("paciente_id"));
                    historia.setFecha(rs.getDate("fecha"));
                    historia.setDescripcion(rs.getString("descripcion"));
                    historias.add(historia);
                }
            }

            // Log si no se encuentran resultados
            if (historias.isEmpty()) {
                System.out.println("No se encontraron historias clínicas para el paciente con ID: " + pacienteId);
            }

        } catch (SQLException e) {
            // Manejo de errores con un mensaje significativo
            System.err.println("Error al obtener las historias clínicas para el paciente con ID: " + pacienteId);
            e.printStackTrace();
        }

        return historias;
    }

    @Override
    public boolean crearHistoriaClinica(HistoriaClinica historia) {
        String sql = "INSERT INTO historia_clinica (paciente_id, fecha, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros
            stmt.setInt(1, historia.getPacienteId());
            stmt.setDate(2, historia.getFecha());
            stmt.setString(3, historia.getDescripcion());

            // Ejecutar la consulta
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al crear una nueva historia clínica.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarHistoriaClinica(HistoriaClinica historia) {
        String sql = "UPDATE historia_clinica SET fecha = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros
            stmt.setDate(1, historia.getFecha());
            stmt.setString(2, historia.getDescripcion());
            stmt.setInt(3, historia.getId());

            // Ejecutar la consulta
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al actualizar la historia clínica con ID: " + historia.getId());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarHistoriaClinica(int id) {
        String sql = "DELETE FROM historia_clinica WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer el parámetro
            stmt.setInt(1, id);

            // Ejecutar la consulta
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al eliminar la historia clínica con ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public HistoriaClinica obtenerHistoriaPorId(int id) {
        String sql = "SELECT * FROM historia_clinica WHERE id = ?";
        HistoriaClinica historia = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer el parámetro
            stmt.setInt(1, id);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    historia = new HistoriaClinica();
                    historia.setId(rs.getInt("id"));
                    historia.setPacienteId(rs.getInt("paciente_id"));
                    historia.setFecha(rs.getDate("fecha"));
                    historia.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al obtener la historia clínica con ID: " + id);
            e.printStackTrace();
        }

        return historia;
    }

    @Override
    public List<HistoriaClinica> obtenerTodasLasHistorias() {
        List<HistoriaClinica> historias = new ArrayList<>();
        String sql = "SELECT * FROM historia_clinica";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Procesar los resultados
            while (rs.next()) {
                HistoriaClinica historia = new HistoriaClinica();
                historia.setId(rs.getInt("id"));
                historia.setPacienteId(rs.getInt("paciente_id"));
                historia.setFecha(rs.getDate("fecha"));
                historia.setDescripcion(rs.getString("descripcion"));
                historias.add(historia);
            }

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al obtener todas las historias clínicas.");
            e.printStackTrace();
        }

        return historias;
    }
}