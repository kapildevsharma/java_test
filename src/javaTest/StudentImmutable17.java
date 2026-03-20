package javaTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class StudentImmutable17 {

    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;
    private final Map<String, Address17> addresssData;
    private final List<Integer> marks;
    private final List<Address17> addresses;

    public StudentImmutable17(String name,
                              int regNo,
                              Map<String, String> metadata,
                              Map<String, Address17> addresssData,
                              List<Integer> marks,
                              List<Address17> addresses) {

        this.name = name;
        this.regNo = regNo;

        // Immutable Map (String → String)
        this.metadata = metadata == null ? Map.of() : Map.copyOf(metadata);

        // Deep copy for mutable Address17
        this.addresssData = addresssData == null ? Map.of()
                : addresssData.entrySet()
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        Map.Entry::getKey,
                        e -> new Address17(e.getValue()) // deep copy
                ));


        // Immutable List of Integer
        this.marks = marks == null ? List.of() : List.copyOf(marks);

        // Deep copy for mutable Address17 list
        this.addresses = addresses == null ? List.of()
                : addresses.stream()
                .map(Address17::new) // copy constructor
                .collect(Collectors.toUnmodifiableList());
    }

    // Getters

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public Map<String, Address17> getAddresssData() {
        return addresssData.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(
                        Map.Entry::getKey,
                        entry -> new Address17(entry.getValue())
                ));

    }
    // Return deep copy
    public List<Address17> getAddresses() {
        return addresses.stream()
                .map(Address17::new)
                .collect(Collectors.toUnmodifiableList());
    }
    public List<Integer> getMarks() {
        return marks;
    }

}

class Address17 {

    private String city;

    public Address17(String city) {
        this.city = city;
    }

    // Copy constructor (VERY IMPORTANT)
    public Address17(Address17 other) {
        this.city = other.city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}