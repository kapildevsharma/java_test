package javaTest;

//Importing required classes

import java.util.*;
import java.util.stream.Collectors;

//Class 1
//An immutable class
public final class StudentImmutable8 {

    // Member attributes of final class
    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;
    private final Map<String, Address> addresssData;
    private final List<Integer> marks;
    private final List<Address> addresses;
    private final Date dob; // 👈 added Date field
    // Constructor of immutable class
    // Parameterized constructor
    public StudentImmutable8(String name, int regNo, Map<String, String> metadata, Map<String, Address> addresssData,
                             List<Integer> marks, List<Address> addresses, Date dob) {

        // This keyword refers to current instance itself
        this.name = name;
        this.regNo = regNo;
        // Defensive copy of mutable Date object
        this.dob = dob == null ? null : new Date(dob.getTime());

        // Defensive copy + unmodifiable map
        this.metadata = metadata==null? Collections.emptyMap()
                 : Collections.unmodifiableMap(new HashMap<>(metadata));

        this.addresssData = addresssData == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(
                addresssData.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> new Address(e.getValue().getCity()) // deep copy
                        ))
        );

        Map<String, Address> temp = new HashMap<>();

        if (addresssData != null) {
            for (Map.Entry<String, Address> entry : addresssData.entrySet()) {
                temp.put(entry.getKey(), new Address(entry.getValue())); // deep copy
            }
        }

      //  this.addresssData = Collections.unmodifiableMap(temp);

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

    // Defensive copy in getter (VERY IMPORTANT)
    public Date getDob() {
        return dob == null ? null : new Date(dob.getTime());
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
