/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import interfaces.IClientCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Client;

/**
 *
 * @author osmel
 */
public class ClientDAO implements IClientCRUD {

    private ConnectionDB connDB;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List list() {
        ArrayList<Client> objListClient;
        objListClient = new ArrayList<>();
        String sql = "SELECT * FROM tclientes";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client objClient = new Client();
                objClient.setIdentification(rs.getInt("identificacion"));
                objClient.setCedula(rs.getString("cedula"));
                objClient.setName(rs.getString("nombre"));
                objClient.setSecondName(rs.getString("segundo_nombre"));
                objClient.setSurname(rs.getString("apellido_paterno"));
                objClient.setSecondSurname(rs.getString("apellido_materno"));
                objClient.setAddress(rs.getString("direccion"));
                objClient.setPhone(rs.getString("telefono"));
                objListClient.add(objClient);
            }
        } catch (SQLException e) {
        }
        return objListClient;
    }

    @Override
    public Client list(String cedula) {
        Client objClient = new Client();
        String sql = "SELECT * FROM tclientes WHERE cedula = ?";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                objClient.setIdentification(rs.getInt("identificacion"));
                objClient.setCedula(rs.getString("cedula"));
                objClient.setName(rs.getString("nombre"));
                objClient.setSecondName(rs.getString("segundo_nombre"));
                objClient.setSurname(rs.getString("apellido_paterno"));
                objClient.setSecondSurname(rs.getString("apellido_materno"));
                objClient.setAddress(rs.getString("direccion"));
                objClient.setPhone(rs.getString("telefono"));
            }
        } catch (SQLException e) {
        }
        return objClient;
    }

    @Override
    public boolean add(Client client) {
        String sql = "INSERT INTO tclientes(cedula, nombre, segundo_nombre, apellido_paterno, "
                + "apellido_materno, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, client.getCedula());
            ps.setString(2, client.getName());
            ps.setString(3, client.getSecondName());
            ps.setString(4, client.getSurname());
            ps.setString(5, client.getSecondSurname());
            ps.setString(6, client.getAddress());
            ps.setString(7, client.getPhone());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean edit(Client client) {
        String sql = "UPDATE tclientes SET nombre = ?, segundo_nombre = ?, apellido_paterno = ?, "
                + "apellido_materno = ?, direccion = ?, telefono = ? WHERE cedula = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSecondName());
            ps.setString(3, client.getSurname());
            ps.setString(4, client.getSecondSurname());
            ps.setString(5, client.getAddress());
            ps.setString(6, client.getPhone());
            ps.setString(7, client.getCedula());

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String cedula) {
        String sql = "DELETE FROM tclientes WHERE cedula = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
