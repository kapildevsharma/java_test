package javaTest;

//Importing required classes

import java.util.*;

//Class 1
//An immutable class
public final class StudentImmutable8 {

    // Member attributes of final class
    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;
    private final List<Integer> marks;
    private final List<Address> addresses;

    // Constructor of immutable class
    // Parameterized constructor
    public StudentImmutable8(String name, int regNo, Map<String, String> metadata,
                             List<Integer> marks, List<Address> addresses) {

        // This keyword refers to current instance itself
        this.name = name;
        this.regNo = regNo;

        // Defensive copy + unmodifiable map
        Map<String, String> tempMap = new HashMap<>();

        // Iterating using for-each loop
        if (metadata != null) {
            for (Map.Entry<String, String> entry :
                    metadata.entrySet()) {
                tempMap.put(entry.getKey(), entry.getValue());
            }
        }
        this.metadata = Collections.unmodifiableMap(tempMap);
        // Defensive copy + unmodifiable list
        this.marks = marks == null ? Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(marks));

        // Defensive copy + unmodifiable list
        List<Address> tempList = new ArrayList<>();
        if (addresses != null) {
            for (Address address : addresses) {
                tempList.add(new Address(address));
            }
        }
        this.addresses = Collections.unmodifiableList(tempList);
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}

final class Address {
    private final String city;

    public Address(String city) {
        this.city = city;
    }

    // copy constructor
    public Address(Address other) {
        this.city = other.city;
    }

    public String getCity() {
        return city;
    }
}
