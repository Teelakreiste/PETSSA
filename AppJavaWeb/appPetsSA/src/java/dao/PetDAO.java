/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;
import interfaces.IPetCRUD;
import model.Client;
import model.PetClient;

/**
 *
 * @author osmel
 */
public class PetDAO implements IPetCRUD {

    private ConnectionDB connDB;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
//    private Pet pet = new Pet();

    @Override
    public List list() {
        ArrayList<PetClient> objListPet = new ArrayList<>();
        String sql = "SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, "
                + "tmascotas.edad, tmascotas.peso, tclientes.nombre as clienteNombre, "
                + "tclientes.segundo_nombre, tclientes.apellido_paterno, tclientes.apellido_materno "
                + "FROM tmascotas INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pet objPet = new Pet();
                objPet.setIdentification(rs.getInt("identificacion"));
                objPet.setName(rs.getString("nombre"));
                objPet.setBreed(rs.getString("raza"));
                objPet.setAge(rs.getInt("edad"));
                objPet.setWeight(rs.getFloat("peso"));

                Client objClient = new Client();
                objClient.setName(rs.getString("clienteNombre"));
                objClient.setSecondName(rs.getString("segundo_nombre"));
                objClient.setSurname(rs.getString("apellido_paterno"));
                objClient.setSecondSurname(rs.getString("apellido_materno"));
                objListPet.add(new PetClient(objClient, objPet));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return objListPet;
    }

    @Override
    public Pet list(String idC, int idP) {
        Pet objPet = new Pet();
        String sql = "SELECT * FROM tmascotas WHERE tmascotas.tclientes_cedula = ? AND tmascotas.identificacion = ?";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idC);
            ps.setInt(2, idP);
            rs = ps.executeQuery();
            if (rs.next()) {
                objPet.setIdentification(rs.getInt("identificacion"));
                objPet.setName(rs.getString("nombre"));
                objPet.setBreed(rs.getString("raza"));
                objPet.setAge(rs.getInt("edad"));
                objPet.setWeight(rs.getFloat("peso"));
                objPet.setOwnerId(rs.getInt("tclientes_identificacion"));
                objPet.setOwnerCedula(rs.getString("tclientes_cedula"));
            }
        } catch (SQLException e) {
        }
        return objPet;
    }

    @Override
    public List listByOwner(String cedula) {
        ArrayList<PetClient> objListPet = new ArrayList<>();
        String sql = "SELECT tmascotas.identificacion, tmascotas.nombre, tmascotas.raza, tmascotas.edad, "
                + "tmascotas.peso, tmascotas.tclientes_identificacion as idCliente, tmascotas.tclientes_cedula "
                + "as cedula, tclientes.nombre AS clienteNombre, tclientes.segundo_nombre, "
                + "tclientes.apellido_paterno, tclientes.apellido_materno FROM tmascotas "
                + "INNER JOIN tclientes ON tmascotas.tclientes_cedula = tclientes.cedula "
                + "WHERE tmascotas.tclientes_cedula = ?";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pet objPet = new Pet();
                objPet.setIdentification(rs.getInt("identificacion"));
                objPet.setName(rs.getString("nombre"));
                objPet.setBreed(rs.getString("raza"));
                objPet.setAge(rs.getInt("edad"));
                objPet.setWeight(rs.getFloat("peso"));
                objPet.setOwnerId(rs.getInt("idCliente"));
                objPet.setOwnerCedula(rs.getString("cedula"));

                Client objClient = new Client();
                objClient.setName(rs.getString("clienteNombre"));
                objClient.setSecondName(rs.getString("segundo_nombre"));
                objClient.setSurname(rs.getString("apellido_paterno"));
                objClient.setSecondSurname(rs.getString("apellido_materno"));
                objListPet.add(new PetClient(objClient, objPet));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return objListPet;
    }

    @Override
    public boolean add(Pet pet) {
        String sql = "INSERT INTO tmascotas (nombre, raza, edad, peso, tclientes_cedula, tclientes_identificacion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getBreed());
            ps.setInt(3, pet.getAge());
            ps.setFloat(4, pet.getWeight());
            ps.setString(5, pet.getOwnerCedula());
            ps.setInt(6, pet.getOwnerId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean edit(Pet pet) {
        String sql = "UPDATE tmascotas SET nombre = ?, raza = ?, edad = ?, "
                + "peso = ?, tclientes_cedula = ?, tclientes_identificacion = ? WHERE identificacion = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getBreed());
            ps.setInt(3, pet.getAge());
            ps.setFloat(4, pet.getWeight());
            ps.setString(5, pet.getOwnerCedula());
            ps.setInt(6, pet.getOwnerId());
            ps.setInt(7, pet.getIdentification());
            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "CALL EliminarMascotaYDetalles(?)";
        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareCall(sql);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public int qtyPet(String cedula) {
        int qty = 0;
        String sql = "SELECT COUNT(*) AS qty FROM tmascotas WHERE tclientes_cedula = ?";

        try {
            connDB = new ConnectionDB();
            conn = connDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();

            if (rs.next()) {
                qty = rs.getInt("qty");
            }
        } catch (SQLException e) {
        }

        return qty;
    }

}
