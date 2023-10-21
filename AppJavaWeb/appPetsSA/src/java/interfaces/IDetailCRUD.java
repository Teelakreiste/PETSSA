/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Detail;

/**
 *
 * @author osmel
 */
public interface IDetailCRUD {
    
    public List list(int id);

    public boolean add(Detail detail);

    public boolean edit(Detail detail);

    public boolean delete(int petId, int medId);
}
