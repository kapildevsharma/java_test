package com.Test;

public class Person implements Cloneable {
    private String firstName;
    private String lastName;
    private Person[] family;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // omitting other methods for brevity

    public Person[] getFamily() { return family; }
    public void setFamily(Person[] family) { this.family = family; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person personClone = (Person) super.clone();
        Person[] familyClone = family.clone();
        personClone.setFamily(familyClone);
        return personClone;
    }
}
