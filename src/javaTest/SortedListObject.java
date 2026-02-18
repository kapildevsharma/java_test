package javaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class User implements Comparable<User>{
    
    private String name;
    private int age;
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "name: "+ name + "age: "+ age;
	}
	
	@Override
	public int compareTo(User o) {
		return age - o.age;
	}

}

public class SortedListObject {
	public static void main(String[] args)
	{
	List<User> userList = new ArrayList<>(Arrays.asList(
	        new User("John", 33), 
	        new User("Robert", 26), 
	        new User("Mark", 26), 
	        new User("Brandon", 42)));

	Collections.sort(userList);
	System.out.println("Naturally Sorted List::"+userList);
	
	List<User> sortedList = userList.stream()
	        .sorted(Comparator.comparingInt(User::getAge))
	        .toList();

	sortedList.forEach(System.out::println);
	
	List<User> sortedList1 = userList.stream()
	        .sorted(Comparator.comparing(User::getName))
	        .collect(Collectors.toList());

	sortedList1.forEach(System.out::println);
	}
}
