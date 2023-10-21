/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Pet;

/**
 *
 * @author osmel
 */
public interface IPetCRUD {

    public List list();

    public Pet list(String idC, int idP);

    public List listByOwner(String cedula);

    public int qtyPet(String cedula);

    public boolean add(Pet pet);

    public boolean edit(Pet pet);

    public boolean delete(int id);

}
