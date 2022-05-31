package com.luxoft.olshevchenko;

import java.io.Serializable;

/**
 * @author Oleksandr Shevchenko
 */
public class TestPerson implements Serializable {

    private String name = "Person";
    private int age = 10;
    private String[] arrayOfPets = {"Emka","Luk","Mur"};
    private int numberOfPets = 3;
    private boolean isChild = true;
    boolean isDefaultConstructor;
    boolean isConstructor;


    public TestPerson() {
        isDefaultConstructor = true;
        isConstructor = false;
    }

    public TestPerson(String name, int age) {
        isConstructor = true;
        isDefaultConstructor = false;
    }

    public String getName() {
        return name;
    }

    public boolean isChild() {
        return isChild;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }


    public String[] getArrayOfPets() {
        return arrayOfPets;
    }

    final void incrementNumberAge() {
        age++;
    }

    final void incrementNumberOfPets() {
        numberOfPets++;
    }

}
