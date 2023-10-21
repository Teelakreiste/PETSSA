/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Client;

/**
 *
 * @author osmel
 */
public interface IClientCRUD {

    public List list();

    public Client list(String cedula);

    public boolean add(Client client);

    public boolean edit(Client client);

    public boolean delete(String cedula);
}
