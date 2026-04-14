package com.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Shubham {
    private Long helloId;
    private String myName;

    public Shubham(Long helloId, String myName){
        this.helloId = helloId;
        this.myName = myName;
    }

    public String getMyName(){
        return myName;
    }

    public Long getHelloId() {
        return helloId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;        // same reference
        if (o == null || getClass() != o.getClass()) return false;

        Shubham s = (Shubham) o;
        return  (Objects.equals(helloId, s.helloId)) ;

    }
    @Override
     public int hashCode(){
        return Objects.hashCode(helloId);
     }
    @Override
     public String toString(){
            return helloId + " " + myName;
     }
}



class PersonClone implements  Cloneable{
    private Long helloId;
    private String myName;
    private Address address;
    public PersonClone(Long helloId, String myName, Address address){
        this.helloId = helloId;
        this.myName = myName;
        this.address = address.clone();
    }

    public Long getHelloId() {
        return helloId;
    }

    public String getMyName() {
        return myName;
    }

    public Address getAddress() {
        return address.clone();
    }

    public void setAddress(Address address) {
        this.address = address.clone();
    }

    public void setHelloId(Long helloId) {
        this.helloId = helloId;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
    @Override
    public String toString(){
        return this.helloId + " " + this.myName;
    }
    @Override
    public PersonClone clone() {
        try {
            PersonClone clone = (PersonClone) super.clone();
            clone.address = address.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
class Address implements Cloneable{
    private Integer addressId;
    private String addressName;

    public Address(Integer addressId, String addressName){
        this.addressName = addressName;
        this.addressId = addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressName(){
        return addressName;
    }
    public Integer getAddressId() {
        return addressId;
    }

    @Override
    public String toString(){
        return this.addressId + " " + this.addressName;
    }
    @Override
    public Address clone() {
        try {
            Address clone = (Address) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

public class Duplicate {

    public static void main(String args[]){
        String s = "Kapil Sharma";
        Map<Character, Long> map = s.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity()
             , Collectors.counting()));

       List<Character> charList = map.entrySet().stream().filter(e -> e.getValue()>1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
       charList.forEach(System.out::println);

       List<Shubham> st = new ArrayList<>();
       st.add(new Shubham( 101L, "Test"));
       st.add(new Shubham( 102L, "Test1"));
       st.add(new Shubham( 111L, "Test2"));
       st.add(new Shubham( 111L, "Test3"));

       Set<Shubham> set = new HashSet<>();
       Set<Shubham> duplciate= st.stream().filter( e -> !set.add(e)).collect(Collectors.toSet());
       duplciate.forEach(System.out::println);

        Map<Long, List<Shubham>> mapduplicate = st.stream()
                .collect(Collectors.groupingBy(Shubham::getHelloId));

        Set<Shubham> duplicates = mapduplicate.values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        duplicates.forEach(System.out::println);

        Map<Long, Long> countMap = st.stream()
                .collect(Collectors.groupingBy(Shubham::getHelloId, Collectors.counting()));

        Set<Long> duplicateIds = countMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        duplicateIds.forEach(System.out::println);

        Address addr = new Address(1, "Jaipur");
        PersonClone s1 = new PersonClone(101L, "Test", addr);

        PersonClone s2 = s1.clone();

        // Change cloned object
        Address a2 = s2.getAddress();
        a2.setAddressName("Delhi");
        s2.setAddress(a2);
        System.out.println(s1.getAddress().getAddressName()); // Jaipur ✅
        System.out.println(s2.getAddress().getAddressName()); // Delhi ✅

    }


}
