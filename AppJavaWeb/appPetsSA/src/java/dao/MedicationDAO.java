/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import interfaces.IMedicationCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Medication;

/**
 *
 * @author osmel
 */
public class MedicationDAO implements IMedicationCRUD {

    private ConnectionDB connDB;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List list() {
        ArrayList<Medication> objListMedication;
        objListMedication = new ArrayList<>();
        String sql = "SELECT * FROM tmedicamentos";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medication objMedication = new Medication();
                objMedication.setIdentification(rs.getInt("identificacion"));
                objMedication.setName(rs.getString("nombre"));
                objMedication.setDescription(rs.getString("descripcion"));
                objListMedication.add(objMedication);
            }
        } catch (SQLException e) {
        }
        return objListMedication;
    }

    @Override
    public Medication list(int id) {
        Medication objMedication = new Medication();
        String sql = "SELECT * FROM tmedicamentos WHERE identificacion = ?";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                objMedication.setIdentification(rs.getInt("identificacion"));
                objMedication.setName(rs.getString("nombre"));
                objMedication.setDescription(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
        }
        return objMedication;
    }

    @Override
    public boolean add(Medication medication) {
        String sql = "INSERT INTO tmedicamentos(nombre, descripcion) VALUES (?, ?)";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, medication.getName());
            ps.setString(2, medication.getDescription());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean edit(Medication medication) {
        String sql = "UPDATE tmedicamentos SET nombre = ?, descripcion = ? WHERE identificacion = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, medication.getName());
            ps.setString(2, medication.getDescription());
            ps.setInt(3, medication.getIdentification());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tmedicamentos WHERE identificacion = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
