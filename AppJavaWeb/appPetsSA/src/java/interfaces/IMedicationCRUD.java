/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Medication;

/**
 *
 * @author osmel
 */
public interface IMedicationCRUD {

    public List list();

    public Medication list(int id);

    public boolean add(Medication medication);

    public boolean edit(Medication medication);

    public boolean delete(int id);
}
