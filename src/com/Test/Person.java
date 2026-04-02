package com.Test;

import java.util.Objects;

public class Person implements Cloneable {
    private String firstName;
    private String lastName;
    private Person[] family;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        // Handles nulls automatically:  Objects.equals(null, null) → true
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName); // determines bucket
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
