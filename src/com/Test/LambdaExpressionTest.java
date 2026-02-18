package com.Test;

import java.util.ArrayList;
//Functional Interface : - Runnable, Callable, Comparable, ActionListner
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FunctionalInterface
interface Square {
    int calculate(int x);
    default void showMsg() {
    	System.out.println("Calling default method of functional interface");
    }
    static void displayMsg() {
    	System.out.println("Calling static method of functional interface");
    }
}

public class LambdaExpressionTest {

	// operation is implemented using lambda expressions
    interface FuncInter1 {
        int operation(int a, int b);
    }
	public static void main(String[] args) {
		String[] arr = new String[2];
		System.out.println(arr[0] + "::" + arr[1]);
		
		ArrayList<String> al = new ArrayList<String>();
        al.add("1430");
        al.add("80");
        al.add("5");
        al.add("20");
        al.add("3420");
        al.add("497890");

        Object[] objects = al.toArray();

        // Printing array of objects
        for (Object obj : objects)
            System.out.print(obj + " ");

        System.out.println(" ");
        al.forEach(x-> System.out.print(x+" "));

        //Functional Interface and Lambda expression
        Square s =  (int a) ->(a*a);
        System.out.println(s.calculate(5));

        s.showMsg();
        Square.displayMsg();
        new Thread(new Runnable () {
            public void run() {
                System.out.println("Runnable functional interface");
            }
        }).start();


        //Thread Example with lambda
        Runnable runnable = ()->{
            System.out.println("Runnable functional interface with lambda");
        };
        new Thread(runnable).start();

        Predicate<String> predicate = (value) -> value.equals("5");
        System.out.println("Predicate interface : " + predicate.test("5"));

        FuncInter1 rs = (int a , int b) -> a*b;
        System.out.println(rs.operation(10,6));

        //Stream API example
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        Map<String, Integer> filteredMap = map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 15)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Filtered Map: " + filteredMap);

        String st = "madam";

        boolean isPalindrome =
                IntStream.range(0, st.length() / 2)
                        .allMatch(i -> st.charAt(i) == st.charAt(st.length() - i - 1));
        System.out.println("String "+st+" is palindrome "+isPalindrome);  //
	}

	
}
