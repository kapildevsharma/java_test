package com.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeQueryMgmt {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		Map<String, Long> noOfMaleAndFemaleEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

		System.out.println(noOfMaleAndFemaleEmployees);

		Stream<String> uniqueDeprtMap = employeeList.stream().map(Employee::getDepartment).distinct();

		uniqueDeprtMap.forEach(System.out::println);

		Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

		System.out.println(avgAgeOfMaleAndFemaleEmployees);

		Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream()
				.max(Comparator.comparingDouble(Employee::getSalary));

		highestPaidEmployeeWrapper = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));

		if (highestPaidEmployeeWrapper.isPresent()) {
			Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
			System.out.println("Name : " + highestPaidEmployee.getName());
		}

		Stream<String> empNameMap = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015)
				.map(Employee::getName);
		empNameMap.forEach(System.out::println);

		Map<String, Long> noOfDeprtEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("Each Remaining loop: ");
		noOfDeprtEmployees.keySet().iterator().forEachRemaining(System.out::println);
		System.out.println("Each loop : ");
		noOfDeprtEmployees.keySet().stream().forEach(System.out::println);
		System.out.println("keyset array: ");
		Stream.of(noOfDeprtEmployees.keySet().toArray()).forEach(System.out::println);
		System.out.println(noOfDeprtEmployees);

		// get name and salary in map
		Map<String, Double> nameSalaryMap = employeeList.stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary));

		for (Entry<String, Double> entry : nameSalaryMap.entrySet()) {
			System.out.println("**** Emp name : " + entry.getKey() + " , salary: " + entry.getValue());
		}

		Map<String, List<Employee>> deptList = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		// approch1
		Map<String, Optional<Employee>> maxSalaryEmpInEachdept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		for (Entry<String, Optional<Employee>> entry : maxSalaryEmpInEachdept.entrySet()) {
			System.out.println(
					"**** maxSalary Emp In Each dept name : " + entry.getKey() + " , emp: " + entry.getValue());
		}

		// approch2
		Map<String, Employee> maxSalaryEmpInEachdept1 = employeeList.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment, 
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), 
								Optional::get)));
		for (Entry<String, Employee> entry : maxSalaryEmpInEachdept1.entrySet()) {
			System.out.println(
					"****2nd maxSalary Emp In Each dept name : " + entry.getKey() + " , emp: " + entry.getValue());
		}

		Map<String, List<String>> deptNameList = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));

		Map<String, Double> avgSalaryOfDepartments = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		avgSalaryOfDepartments.keySet().stream().forEach(System.out::println);
		Set<Entry<String, Double>> entrySet = avgSalaryOfDepartments.entrySet();

		for (Entry<String, Double> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper = employeeList.stream()
				.filter(e -> e.getGender() == "Male" && e.getDepartment() == "Product Development")
				.min(Comparator.comparingInt(Employee::getAge));
		Employee youngestMaleEmployeeInProductDevelopment = youngestMaleEmployeeInProductDevelopmentWrapper.get();

		System.out.println(" Youngest Male Employe ID : " + youngestMaleEmployeeInProductDevelopment.getId()
				+ " Name : " + youngestMaleEmployeeInProductDevelopment.getName());

		Optional<Employee> seniorMostEmployeeWrapper = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();

		Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();

		System.out.println(
				"Senior Most Employee ID : " + seniorMostEmployee.getId() + " Name : " + seniorMostEmployee.getName());

		DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));

		System.out.println("Average Salary = " + employeeSalaryStatistics.getAverage());

		System.out.println("Total Salary = " + employeeSalaryStatistics.getSum());

        Map<String, List<Employee>> result = employeeList.stream()
                .filter(e -> e.getYearOfJoining() == 2015 && e.getAge() > 30)
                .collect(Collectors.groupingBy(
                        Employee::getDepartment, Collectors.collectingAndThen(
                                Collectors.toList(), empList -> empList.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .limit(2)
                                        .collect(Collectors.toList())
                        )
                ));

        // Group by department and get employee with minimum age
        Map<String, Optional<Employee>> minAgeEmployeeByDept = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.minBy(Comparator.comparingInt(Employee::getAge))
                ));

        // Step 1: Group employees by department
        Map<String, List<Employee>> employeesByDept = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // Step 2: Find employees with minimum age in each department
        Map<String, List<Employee>> minAgeByDept = employeesByDept.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> {
                            List<Employee> empList = entry.getValue();
                            int minAge = empList.stream()
                                    .mapToInt(Employee::getAge)
                                    .min()
                                    .orElse(Integer.MAX_VALUE);
                            return empList.stream()
                                    .filter(e -> e.getAge() == minAge)
                                    .collect(Collectors.toList());
                        }
                ));

        for( Entry<String, List<Employee>> entry : minAgeByDept.entrySet()) {
            System.out.println("Department: " + entry.getKey());
            for (Employee emp : entry.getValue()) {
                System.out.println(" - Employee ID: " + emp.getId() + ", Name: " + emp.getName() + ", Age: " + emp.getAge());
            }
        }
	}

}
