/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author osmel
 */
public class PetClient {

    private Client client;
    private Pet pet;

    public PetClient(Client client, Pet pet) {
        this.client = client;
        this.pet = pet;
    }

    public PetClient() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
