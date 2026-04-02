package javaTest;

import com.Test.EmployeeSal;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java17QuesAns {

	public static void main(String[] args) {
		//Write a program to find and print the maximum value from a list.
        System.out.println("program to find and print the maximum value from a list");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int max = numbers.stream().max(Integer::compare).get();
        System.out.println("max value from list "+ max);
        max = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println("max value from list "+ max);
        
        //Write a program using a lambda expression to add two integers.
        System.out.println("program using a lambda expression to add two integers");
        BiFunction<Integer, Integer, Integer> biFun = Integer::sum;
        System.out.println("Add 2 integer : " + biFun.apply(5, 6));

        // Write a program to filter and print even numbers from a list.
        System.out.println("program to filter and print even numbers from a list");
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        boolean haseven = numbers.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("Check any even number in list : "+haseven); // Output: true

        numbers.stream().filter(n -> n%2 ==0).forEach(System.out::println);
        Map <Boolean, List <Integer>> map = numbers.stream().collect(Collectors.partitioningBy( n-> n%2==0));
        System.out.println("print even numbers by partitioningBy: "+ map.get(true));
        System.out.println("print old numbers by partitioningBy: "+ map.get(false));

        // Write a program to map integers to their squares and print results.
        System.out.println("program to map integers to their squares and print results");
        numbers.stream().map( n -> n*n).forEach(System.out::println);
        
        // Write a program to convert old to even integers then find average and print results.
        System.out.println("program to convert old to even integers then find average and print results");
        AtomicReference<Double> avgVal = new AtomicReference<>(0.0);

        numbers.stream().map( n -> n%2!=0 ? n+1 : n).mapToInt(Integer::intValue).average().ifPresent(avgVal::set);
        System.out.println("average of even list : "+avgVal);

        OptionalDouble t = numbers.stream().filter(n -> n % 2 == 0).mapToInt(n -> n).average();

        double avgValue = t.orElse(0.0);
        System.out.println("average of even list : "+avgValue);
        
        // Write a program to count and print the number of elements in a list.
        System.out.println("program to count and print the number of elements in a list");
        List<String> words = Arrays.asList("apple", "banana", "pear");
        long count = Stream.of("apple", "banana", "pear",1,true).count();
        System.out.println("Count of list element : "+count);

        words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k,v) -> System.out.println("Element: "+k + ", count: "+v));

        // Write a program to reduce a list of integers to their sum.
        System.out.println("program to reduce a list of integers to their sum");
        numbers = Arrays.asList(1, 2, 3, 4);
        int sumList = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> sumOptional = numbers.stream().reduce(Integer::sum);
        sumList = sumOptional.orElse(0);
        System.out.println("reduce method of get sum : "+sumList);
        sumList = numbers.stream().mapToInt(i -> i).sum();
        System.out.println("Sum method with maptoInt : "+sumList);
        sumList = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum method with maptoInt : "+sumList);
        sumList = (int) numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum();
        System.out.println("Sum of list element : "+sumList);

        DoubleSummaryStatistics doubleSummaryStatistics = numbers.stream().collect(Collectors.summarizingDouble(Integer::intValue ));
        System.out.println("Result of doubleSummaryStatistics for list , sum: "+doubleSummaryStatistics.getSum() + ", average::"+ doubleSummaryStatistics.getAverage()
        +" ,max:" + doubleSummaryStatistics.getMax()+", count:"+doubleSummaryStatistics.getCount());

       //Write a program to print the lengths of strings in a list.
        System.out.println("program to reduce a list of integers to their sum");
        words = Arrays.asList("Java", "Python", "JavaScript");
        words.forEach(word -> System.out.println(word+ " length : "+word.length()));

        // CompletableFuture async pipeline
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<CompletableFuture<Void>> futures = words.stream()
                .map(word -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("Calculating length for: " + word);
                    return "Processed: " + word;
                }, executor).thenAccept(System.out::println))
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();

        //Write a program to print distinct elements from a list of integers.
        System.out.println("program to print distinct elements from a list of integers");
        numbers = Arrays.asList(1, 2, 2, 3, 4, 4);
        numbers.stream().distinct().forEach(System.out::println);
        
        //Write a program to print names sorted in alphabetical order from a list.
        System.out.println("program to print names sorted in alphabetical order from a list");
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Kapil");
        names.stream().sorted().forEach(System.out::println);
        names.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("program to print names sorted in alphabetical reverse order from a list");
        names.stream().sorted(Comparator.comparing(String::length)).forEach(e-> System.out.println("Element for sorted length :"+e));

        //Write a program using Optional to check if a value is present.
        System.out.println("program using Optional to check if a value is present");
        Optional<String> name = Optional.of("Java");
        name.ifPresent(System.out::println);
        
        //Write a program to group strings by their lengths and print the groups.
        System.out.println("program to group strings by their lengths and print the groups");
        words = Arrays.asList("a", "bb", "ccc", "dd","fff");
        Map<Integer, List<String>> groupByLenghtMap= words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("groupByLenghtMap : "+ groupByLenghtMap);
        Iterator<Entry<Integer, List<String>>> iter = groupByLenghtMap.entrySet().iterator();
        while(iter.hasNext()) {
        	Entry<Integer, List<String>> entry = iter.next();
        	System.out.print("Group Count :"+entry.getKey() + ", List of String: " + entry.getValue()+ " ");
        }

        for (Entry<Integer, List<String>> entry : groupByLenghtMap.entrySet()) {
            System.out.print("Group Count :" + entry.getKey() + ", List of String: " + entry.getValue() + " ");
        }
 
        // Write a program to collect squares of numbers into a new list.
        System.out.println("program to collect squares of numbers into a new list with peek for debugging purpose.");
        numbers = Arrays.asList(1, 2, 3);
        numbers = numbers.stream().peek(n -> System.out.println("n"+ n)).map(n -> n*n).collect(Collectors.toList());
        System.out.println("Number : "+ numbers);
        
        //Write a program to limit and skip elements in a list, then print.
        System.out.println("program to limit and skip elements in a list, then print");
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.stream().limit(4).skip(2).forEach(System.out::println);

        // Write a program to find and print the first element in sorted order.
        System.out.println("program to find and print the first element in sorted order");
        numbers = Arrays.asList(5, 3, 1, 4, 2);
        int firstElement = numbers.stream().sorted().findFirst().get();
        System.out.println("first Element: "+ firstElement);
       
        // Write a program to find min and max value in list.
        System.out.println("program to max and min elements in a list, then print");
        max = numbers.stream().max(Comparator.naturalOrder()).get();
        int min = numbers.stream().min(Comparator.naturalOrder()).get();
        System.out.println("Min: "+min + ", Max: "+max);
        max = numbers.stream().min(Comparator.reverseOrder()).get();
        min = numbers.stream().min(Integer::compare).get();
        System.out.println("Min: "+min + ", Max: "+max);
        		
        // Write a program to create a custom functional interface for addition.
        System.out.println("program to create a custom functional interface for addition.");
        MyFunction add = Integer::sum;
        System.out.println("Addtion : "+ add.apply(5,7));
        
        //Write a program using flatMap to print characters from lists of strings.
        System.out.println("program using flatMap to print characters from lists of strings");
        List<List<String>> list = List.of(
                List.of("A", "B"),
                Arrays.asList("C", "D"));
        list.stream().flatMap(List::stream).forEach(System.out::println);
        
        // Write a program to create and print result from a CompletableFuture asynchronously.
        System.out.println("program to create and print result from a CompletableFuture asynchronously.");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5*5);
        future.thenAccept(System.out::println);
       
        // Write a program to find duplicate element in list.
        System.out.println("program to find duplicate element in list.");
        Map<Integer, Long> duplicateMap = IntStream.of( 1, 2, 3, 2, 1, 2, 3, 4, 2, 5, 2 ).boxed().
        		collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
         List<Integer> duplicateList= duplicateMap.entrySet().stream()
                 .filter(entry -> entry.getValue()>1).map(Entry::getKey).toList();
         duplicateList.forEach(System.out::println);
         
         // How to filter an array of strings by a given prefix using Stream
         System.out.println("program to filter an array of strings by a given prefix using Stream.");
         String[] strings = {"java", "scala", "javascript", "ruby","spring","angular"};
         String prefix = "j";
         strings = Arrays.stream(strings).filter(s -> s.startsWith(prefix)).toArray(String[]::new);
         System.out.println(Arrays.toString(strings));

         // reverse string words
         System.out.println("program to find reverse string in list.");
         String str = " avaj si a gnal os";
         str = Arrays.stream(str.split(" "))
                 .map(word -> new StringBuffer(word).reverse().toString())
                 .collect(Collectors.joining(" "));

         System.out.println("Reverse str "+ str);
 
         //program to check if two strings are anagrams or not
         System.out.println("program to check if two strings are anagrams or not");
         String s1 = "RaceCar";
         String s2 = "CarRace";
         s1 = Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
         s2 = Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
         System.out.println(s1.equals(s2)? "Two String are anagrams":"Two String are not anagrams");
         s1= Arrays.stream("RaceCar".split("")).map(String::toLowerCase).sorted()
                 .reduce("", (s,c)->s+c,(
                         acc, ch) -> ch + acc // use in parallel stream to reverse the string while reducing
                 );
         System.out.println(s1);
        
         //Palindrome program using reams
         System.out.println("program to check if string is Palindrome or not");
         String palindromStr = "ROTATOR";
         boolean isItPalindrome = IntStream.range(0, palindromStr.length()/2).
	                noneMatch(i -> palindromStr.charAt(i) != palindromStr.charAt(palindromStr.length() - i -1));
         System.out.println(palindromStr + " is Palindrome,:" + isItPalindrome);
         System.out.println("program to extract duplicate elements from an array");

         List<Integer> listOfIntegers = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
         Set<Integer> uniqueElements = new HashSet<Integer>();
         Set<Integer> duplicateElements = listOfIntegers.stream()
                 .filter(i -> ! uniqueElements.add(i)).collect(Collectors.toSet());
         System.out.println("duplicateElements: "+duplicateElements);

        duplicateElements = listOfIntegers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()
                )).entrySet().stream().filter(entry -> entry.getValue()>1)
                .map(Entry::getKey).collect(Collectors.toSet());
        System.out.println("duplicateElements: "+duplicateElements);

         Optional.ofNullable(listOfIntegers).orElse(Collections.emptyList()).stream().toArray();

         //program to find the factorial of a given number 
         System.out.println("program to factorial of given number");
         int number = 5;
         int factorial = IntStream.range(1, number+1).reduce(1, (a,b)->a*b);
         System.out.println("factorial of "+ number + "::" + factorial);
                  
         numbers = Arrays.asList(1, 2, 6, 5, 5);
         int product = numbers.stream().reduce(1, (a,b)->a*b);
         System.out.println(" product of all elements in a list: " + product);

        // Find top 2 highest paid employees from each department
        //Only include employees who are active and age > 30
        //Sort salaries descending
        List<EmployeeSal> employeeSals = Arrays.asList(
                new EmployeeSal(1, "John", 32, true, "IT", 2015, 60000),
                new EmployeeSal(2, "Jane", 28, true, "HR", 2018, 50000),
                new EmployeeSal(3, "Mike", 45, false, "IT", 2010, 80000),
                new EmployeeSal(4, "Emily", 38, true, "Finance", 2012, 75000),
                new EmployeeSal(5, "David", 29, true, "IT", 2019, 55000),
                new EmployeeSal(6, "Sophia", 41, true, "HR", 2008, 70000),
                new EmployeeSal(7, "Robert", 35, true, "Finance", 2011, 72000),
                new EmployeeSal(8, "Linda", 42, true, "IT", 2009, 90000)
        );

        Map<String, List<EmployeeSal>> result = employeeSals.stream()
                .filter(e -> e.isActive() && e.getAge() > 30)
                .collect(Collectors.groupingBy(
                        EmployeeSal::getDepartment, Collectors.collectingAndThen(
                                Collectors.toList(), empList -> empList.stream()
                                        .sorted(Comparator.comparingDouble(EmployeeSal::getSalary).reversed())
                                        .limit(2)
                                        .collect(Collectors.toList())
                        )
                ));

        result.forEach(
            (dept, employeeSalList) ->
                {
                    System.out.println("dept: "+ dept);
                    employeeSalList.forEach(e -> System.out.println(e.getName()+" "+ e.getSalary()));
                }
        );


    }

	@FunctionalInterface
	interface MyFunction {
		int apply(int a , int b );
	}
	
}
