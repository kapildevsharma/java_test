package com.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
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

        // Count Male and Female Employees
		Map<String, Long> noOfMaleAndFemaleEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(noOfMaleAndFemaleEmployees);

        // List all Unique departments in organization
		Stream<String> uniqueDeprtMap = employeeList.stream().map(Employee::getDepartment).distinct();
		uniqueDeprtMap.forEach(System.out::println);

        // Average age of male and female employees
		Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println(avgAgeOfMaleAndFemaleEmployees);

        // Get the details of highest paid employee in the organization
		Optional<Employee> highestPaidEmployeeWrapper = employeeList.stream()
				.max(Comparator.comparingDouble(Employee::getSalary));

		highestPaidEmployeeWrapper = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));

		if (highestPaidEmployeeWrapper.isPresent()) {
			Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
			System.out.println("Name : " + highestPaidEmployee.getName());
		}

        // Get names of all employees who have joined after 2015
		Stream<String> empNameMap = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015)
				.map(Employee::getName);
		empNameMap.forEach(System.out::println);

        // Count number of employees in each department
		Map<String, Long> noOfDeprtEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("Each Remaining loop: ");
		noOfDeprtEmployees.keySet().iterator().forEachRemaining(System.out::println);
		System.out.println("Each loop : ");
		noOfDeprtEmployees.keySet().forEach(System.out::println);
		System.out.println("keyset array: ");
		Stream.of(noOfDeprtEmployees.keySet().toArray()).forEach(System.out::println);
		System.out.println(noOfDeprtEmployees);

		// get name and salary in map
		Map<String, Double> nameSalaryMap = employeeList.stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary));
		for (Entry<String, Double> entry : nameSalaryMap.entrySet()) {
			System.out.println("**** Emp name : " + entry.getKey() + " , salary: " + entry.getValue());
		}

        // Group employees by department
		Map<String, List<Employee>> deptList = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

        // Get the details of highest paid employee in each department
		// approch1
		Map<String, Optional<Employee>> maxSalaryEmpInEachdept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));

        maxSalaryEmpInEachdept = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

        for (Entry<String, Optional<Employee>> entry : maxSalaryEmpInEachdept.entrySet()) {
			System.out.println(
					"**** maxSalary Emp In Each dept name : " + entry.getKey() + " , emp: " + entry.getValue());
		}

        // Get the details of highest paid employee in each department
		// approch2
		Map<String, Employee> maxSalaryEmpInEachdept1 = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.collectingAndThen(
                            Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                            Optional::get)));
        maxSalaryEmpInEachdept1 =
                employeeList.stream()
                        .collect(Collectors.toMap(
                                Employee::getDepartment,
                                e -> e,
                                BinaryOperator.maxBy(Comparator.comparingDouble(Employee::getSalary))
                        ));

       maxSalaryEmpInEachdept1 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                            Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
            opt -> opt.orElse(null))));

		for (Entry<String, Employee> entry : maxSalaryEmpInEachdept1.entrySet()) {
			System.out.println(
					"****2nd maxSalary Emp In Each dept name : " + entry.getKey() + " , emp: " + entry.getValue());
		}

        List<Employee> updateemployeeList = new ArrayList<>(employeeList);
        updateemployeeList.add(new Employee(31, "Anuj Test", 31, "Male", "Product Development", 2012, 35700.0));

        Map<String, List<Employee>> deptList1 = updateemployeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.collectingAndThen(Collectors.toList(),
                        list-> {
                        double max = list.stream().mapToDouble(Employee::getSalary).max().orElse(0);
                        return list.stream().filter(e -> e.getSalary() == max)
                                .collect(Collectors.toList());
                    })
                ));

        // Get the names of all employees in each department
		Map<String, List<String>> deptNameList = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        deptNameList.forEach((k,v)->System.out.println(k+" : "+v));
        // Get the average salary of each department
		Map<String, Double> avgSalaryOfDepartments = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		avgSalaryOfDepartments.keySet().forEach(System.out::println);
		Set<Entry<String, Double>> entrySet = avgSalaryOfDepartments.entrySet();
		for (Entry<String, Double> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

        // Get the details of youngest male employee in the product development department
		Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper = employeeList.stream()
				.filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
			//	.min(Comparator.comparingInt(Employee::getAge));
                .collect(Collectors.minBy(Comparator.comparingInt(Employee::getAge)));

        Employee youngestMaleEmployeeInProductDevelopment = youngestMaleEmployeeInProductDevelopmentWrapper.get();
        System.out.println(" Youngest Male Employe ID : " + youngestMaleEmployeeInProductDevelopment.getId()
				+ " Name : " + youngestMaleEmployeeInProductDevelopment.getName());

        // Get the details of senior most employee in the organization
		Optional<Employee> seniorMostEmployeeWrapper = employeeList.stream()
		//		.sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
            .min(Comparator.comparingInt(Employee::getYearOfJoining)).stream().findFirst();
		Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();

		System.out.println("Senior Most Employee ID : " + seniorMostEmployee.getId() + " Name : " + seniorMostEmployee.getName());

        // Get the average salary and total salary of the whole organization
        DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));

		System.out.println("Average Salary: " + employeeSalaryStatistics.getAverage());
		System.out.println("Total Salary: " + employeeSalaryStatistics.getSum());
        System.out.println("Salary Statistics: " + employeeSalaryStatistics);

        // Get the details of employees who have joined in the year 2015 and above and are older than 30 years, then group them by their department
        // Top 2 Salaries Per Department (After Filter)
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
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .collect(Collectors.collectingAndThen(
                                        Collectors.groupingBy(Employee::getAge),
                                        ageMap -> {
                                            int minAge = Collections.min(ageMap.keySet());
                                            return ageMap.get(minAge);
                                        }
                                ))
                       /* entry -> {
                            List<Employee> empList = entry.getValue();
                            int minAge = empList.stream()
                                    .mapToInt(Employee::getAge)
                                    .min()
                                    .orElse();
                            return empList.stream()
                                    .filter(e -> e.getAge() == minAge)
                                    .collect(Collectors.toList());
                        }*/
                ));

        for( Entry<String, List<Employee>> entry : minAgeByDept.entrySet()) {
            System.out.println("Department: " + entry.getKey());
            for (Employee emp : entry.getValue()) {
                System.out.println(" - Employee ID: " + emp.getId() + ", Name: " + emp.getName() + ", Age: " + emp.getAge());
            }
        }

        // Second-Highest Salary in Each Department
        Map<String, Optional<Employee>> secondHighest = employeeList.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,  Collectors.collectingAndThen(
                        Collectors.toList(), list -> list.stream()
                            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                            .skip(1)
                            .findFirst()
                            )
                        ));

        for( Entry<String, Optional<Employee>> entry : secondHighest.entrySet()) {
            System.out.println("Department: " + entry.getKey());
            Employee emp = entry.getValue().get();
            System.out.println(" Second Highest Salary empId: " + emp.getId() + ", Name: " + emp.getName() + ", Age: " + emp.getAge());
        }

        //  Multi-level Grouping (Department → Gender → Count)
        Map<String, Map<String, Long>> multiLevel =
                employeeList.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.groupingBy(
                                        Employee::getGender,
                                        Collectors.counting()
                                )
                        ));

        for( Entry<String, Map<String, Long>> entry : multiLevel.entrySet()) {
            System.out.println("Department: " + entry.getKey());
            Map<String, Long> emp = entry.getValue();
            for( Entry<String, Long> genderCount : emp.entrySet()) {
                System.out.println(" Gender: " + genderCount.getKey() + ", Count: " + genderCount.getValue());
            }
        }

        // Find Nth Highest Salary Dynamically
        int n = 3;
        employeeList.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .map(Employee::getSalary)
            .distinct()
            .skip(n - 1)
            .findFirst()
            .ifPresent(System.out::println);

        List<String> employeeNames = employeeList.stream()
                .filter(e -> e.getSalary()>50000)
                .map(Employee::getName)
                .toList();


	}

}
