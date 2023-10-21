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
public class Pet {

    private int identification;
    private String name, breed;
    private int age;
    private float weight;
    private String ownerCedula;
    private int ownerId;

    public Pet() {
    }

    public Pet(int identification, String name, String breed, int age, float weight, String ownerCedula, int ownerId) {
        this.identification = identification;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.ownerCedula = ownerCedula;
        this.ownerId = ownerId;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getOwnerCedula() {
        return ownerCedula;
    }

    public void setOwnerCedula(String ownerCedula) {
        this.ownerCedula = ownerCedula;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
