package com.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmpMain {
    public static void main(String[] args) {
        List<EmployeeSal> empList = new ArrayList<>();
        EmployeeSal emp2 = new EmployeeSal(101, "John Doe", 30, true, "Engineering", 2015, 75000);
        EmployeeSal emp1 = new EmployeeSal(102, "John", 30, false,  "Software", 2015, 7500);
        empList.add(emp2);
        empList.add(emp1);

        Map<String, EmployeeSal> result = empList.stream().
                collect(Collectors.groupingBy(e -> e.department,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(e -> e.salary)),
                                Optional::get)));

        result = empList.stream().
                collect(Collectors.toMap(e -> e.department, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingDouble(e -> e.salary))));

        result.forEach((dept, emp) -> {
            System.out.println("Department: " + dept + ", Employee: " + emp.name + ", Salary: " + emp.salary);
        });
    }
}