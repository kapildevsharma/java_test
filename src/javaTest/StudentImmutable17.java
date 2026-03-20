package javaTest;

//Importing required classes

import java.util.List;
import java.util.Map;

//Class 1
//An immutable class
public final class StudentImmutable17 {

    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;
    private final List<Integer> marks;
    private final List<Address17> addresses;

    public StudentImmutable17(String name, int regNo,
                              Map<String, String> metadata,
                              List<Integer> marks,
                              List<Address17> addresses) {

        this.name = name;
        this.regNo = regNo;

        this.metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
        this.marks = marks == null ? List.of() : List.copyOf(marks);

        this.addresses = addresses == null ? List.of() :
                List.copyOf(addresses.stream()
                        .map(Address17::new) // defensive copy
                        .toList());
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

    public List<Address17> getAddresses() {
        return addresses;
    }
}

record Address17(String city) {
    // defensive copying
    public Address17(Address17 other) {
        this(other.city);
    }
}
