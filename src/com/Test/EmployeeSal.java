package com.Test;

public class EmployeeSal {

	int id;
	String name;
	int age;
	String department;
    boolean active;
	int yearOfJoining;
	double salary;

	public EmployeeSal(int id, String name, int age, boolean active, String department, int yearOfJoining, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.active = active;
		this.department = department;
		this.yearOfJoining = yearOfJoining;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
    public boolean isActive() {return active;}
	public String getDepartment() {
		return department;
	}
	public int getYearOfJoining() {
		return yearOfJoining;
	}
	public double getSalary() {
		return salary;
	}
	
	@Override
	public int hashCode() {
		return (id+name+age+active+department).hashCode();
	}

	@Override
	public String toString() {
		return "Id : " + id + ", Name : " + name + ", age : " + age + ", active : " + active + ", Department : "
				+ department + ", Year Of Joining : " + yearOfJoining + ", Salary : " + salary;
	}

}
