package com.Test;

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
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        return lastName.equals(person.lastName);
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
