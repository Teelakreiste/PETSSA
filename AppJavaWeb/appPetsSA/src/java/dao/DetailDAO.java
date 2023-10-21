/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import interfaces.IDetailCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Detail;

/**
 *
 * @author osmel
 */
public class DetailDAO implements IDetailCRUD {

    private ConnectionDB connDB;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List list(int id) {
        ArrayList<Detail> objListDetailMedication;
        objListDetailMedication = new ArrayList<>();
        String sql = "SELECT * FROM tdetalles INNER JOIN tmedicamentos ON tdetalles.tmedicamentos_identificacion = tmedicamentos.identificacion WHERE tmascotas_identificacion = ?";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detail objDetail = new Detail();
                objDetail.setIdentification(rs.getInt("identificacion"));
                objDetail.setName(rs.getString("nombre"));
                objDetail.setDescription(rs.getString("descripcion"));
                objDetail.setRegistrationNumber(rs.getInt("registro"));
                objDetail.setPetIdentification(rs.getInt("tmascotas_identificacion"));
                objDetail.setMedicationIdentification(rs.getInt("tmedicamentos_identificacion"));
                objDetail.setDosage(rs.getString("dosis"));
                objListDetailMedication.add(objDetail);
            }
        } catch (SQLException e) {
        }
        return objListDetailMedication;
    }

    @Override
    public boolean add(Detail detail) {
        String sql = "CALL InsertarActualizarDetalle(?, ?, ?)";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareCall(sql);
            ps.setInt(1, detail.getPetIdentification());
            ps.setInt(2, detail.getMedicationIdentification());
            ps.setString(3, detail.getDosage());
            ps.execute();

            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean edit(Detail detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int petId, int medId) {
        String sql = "DELETE FROM tdetalles WHERE tmascotas_identificacion = ? and tmedicamentos_identificacion = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, petId);
            ps.setInt(2, medId);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
