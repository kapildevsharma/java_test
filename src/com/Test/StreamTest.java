package com.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {
	private String s ;
	public StreamTest(String s) {
		this.s = s;
	}

	public void abc(String st) {
		System.out.println(s + " string : " + st);
	}
	
	public void abc(Object st) {
		System.out.println(s + "  object : " + st);
	}
	
	public static void main(String[] args) {
		
		new Test("kapil").abc(null);
		List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(111, "Nataraja G Accounts", 32, "Female", "HR", 2011, 25000.0));
        empList.add(new Employee(122, "Nagesh Y Admin", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        empList.add(new Employee(133, "Vasu V Security", 29, "Male", "Infrastructure", 2012, 18000.0));
        empList.add(new Employee(144, "Amar Entertinment", 28, "Male", "Product Development", 2014, 32500.0));

        // print all employees having salary greater than 10000
        System.out.println("Employees having salary greater than 10000 :");
        empList.stream().filter( emp -> emp.getSalary()>10000).forEach(System.out:: println);
        System.out.println();
        empList.stream().filter( emp -> emp.getSalary()>10000).forEach(emp -> System.out.print("emp name :"+ emp.getName()+" "));
	        
        System.out.println();
        empList.forEach(entry -> {
            if (entry.getSalary() > 10000) {
                System.out.println(entry.getName() + ": " + entry.getId());
                }
            }
        );

        empList =  empList.stream().filter( emp -> emp.getSalary()<10000).collect(Collectors.toList());
        empList.forEach(emp -> System.out.println("name : "+ emp.getName()));
        System.out.println();

        System.out.println();
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Guava");
        fruits.add("Pineapple");
        fruits.add("Apple");

        Optional<String> reduced = fruits.stream().reduce((s1,s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        String firstMatchedName = fruits.stream()
                .filter(s -> s.startsWith("B"))
                .findFirst().get();
	     
	    System.out.println(firstMatchedName);

        // converting list to hashmap
        HashMap<String, Integer> res = fruits.stream().collect(Collectors.toMap(
                Function.identity(), String::length,
                (e1, e2) -> e1, HashMap::new));
        // printing the elements of the hashmap
        System.out.println("Elements in HashMap are : "  + res);

        List<Student> list = Arrays.asList(
                new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

        List<Student> lstStuName = list.stream().filter(student -> student.getFirstName().startsWith("A"))
              //  .collect(Collectors.toList()); // java 8 onwards
                .toList(); // java 16 onwards

        System.out.println("List of students whose name starts with letter A : "+lstStuName);

        Map<String, List<Student>> mapData = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println("Students grouped by the department names : "+mapData);

        OptionalInt maxAge = list.stream().mapToInt(Student::getAge).max();
        System.out.println("Max age of student : "+ maxAge.getAsInt());

        List<String> lstDepartments = list.stream().map(Student::getDepartmantName).distinct().toList();
        System.out.println("All distinct department names : "+lstDepartments);

        Map<String, Long> countStudentInDeeptech = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        System.out.println("Student count in each department : "+countStudentInDeeptech);

        Map<String, Double> mapAvgAge = list.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.println("Average age of male and female students : "+mapAvgAge);
        Map<String, Optional<Student>> mapMinAge = list.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.minBy(Comparator.comparingInt(Student::getAge))));
        System.out.println("Min age of male and female students : "+mapAvgAge);

        // filtering and sorting
        List<Student> dehliStudentByNameList =
                list.stream().filter(student -> student.getCity().equals("Delhi"))
                        .sorted(Comparator.comparing(Student::getFirstName)).toList();

        System.out.println("List of students who stays in Delhi and sort them by their names : "+dehliStudentByNameList);
        // finding second-highest rank student
        Student student = list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst().get();
        System.out.println("Second highest rank student : "+student);

        student = list.stream().sorted(Comparator.comparing(Student::getRank).reversed()).skip(1).findFirst().get();
        System.out.println("Second last highest rank student : "+student);

        Student maxStudent = null;
        Student secondStudent = null;
        int maxRank = Integer.MIN_VALUE;
        int secondRank = Integer.MIN_VALUE;

        for (Student s : list) {
            int r = s.getRank();
            if (r > maxRank) {
                secondRank = maxRank;
                secondStudent = maxStudent;
                maxRank = r;
                maxStudent = s;
            } else if (r > secondRank && r != maxRank) {
                secondRank = r;
                secondStudent = s;
            }
        }

        System.out.println("Highest rank student: " + maxStudent + "  Second highest rank: " + secondRank + " " + secondStudent);

        // using lambda expression in place of comparator object
        Collections.sort(list, (o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        list.sort(Comparator.comparing(Student::getFirstName));
        System.out.println("Sort list  : "+list);

	}
	
}
