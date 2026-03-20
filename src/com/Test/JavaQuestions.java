package com.Test;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class JavaQuestions {
    public static void main(String[] args) throws FileNotFoundException {
        JavaQuestions p  = new JavaQuestions();
        List<Integer> list  = new ArrayList<>();
        list.add(3);list.add(1); list.add(5);list.add(9);list.add(6);
        int[] arr = {3,1, 8,4,2};
        Arrays.sort(arr);
        Arrays.sort(arr);
        Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)).sort((a, b) -> b - a);
        Arrays.sort(Arrays.stream(arr).boxed().toArray(Integer[]::new), Integer::compareTo);

        System.out.println("'"+Arrays.toString(arr)+"' Sort  '"+ Arrays.toString(arr) +"'");
        list.sort(Integer::compareTo);
        list.sort((a, b) -> Integer.compare(b,a));
        list.sort(Comparator.reverseOrder());
        Collections.sort(list);
        System.out.println("'"+Arrays.toString(arr)+"' Sort  '"+ Arrays.toString(arr) +"'");
        System.out.println("List contains only odd no "+ p.onlyOddNumbers(list));
        p.checkPalindromeString("kapak");
        p.checkPalindromeString("kapik");
        String input = "   kapil dev sharma";
        p.removeWhiteSpaces(input);
        input = input.strip(); // remove tailing and leading whitespace
        System.out.println(input);


        int seqLength = 10;
        System.out.println("A Fibonacci Recursive of " + seqLength + " numbers: ");
        for (int i = 0; i < seqLength; i++) {
            System.out.print(p.printFibonacciRecursive(i) + " ");
        }
        System.out.println();
        System.out.println("A Fibonacci sequence of " + seqLength + " numbers: ");
        p.printFibonacciSequence(seqLength);

        // java 8 features
            System.out.println();
            List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");
            System.out.println("Names in uppercase using Java 8 features: ");
            names.stream().map(String::toUpperCase).forEach(System.out::println);
       // java 9 features
        // try-with-resources statement
       /* final Scanner scanner = new Scanner(new File("testRead.txt"));
        final PrintWriter writer = new PrintWriter(new File("testWrite.txt"));
        try (scanner;writer) {
            // ...
        }*/
        // Private Interface Methods
        interface MyInterface {
            private void privateMethod() {
                System.out.println("This is a private method in an interface.");
            }

            default void defaultMethod() {
                privateMethod();
                System.out.println("This is a default method in an interface.");
            }
        }
        // List.of() method in java 9
        List<String> fruits = List.of("Apple", "Banana", "Cherry");
        System.out.println("Fruits list using List.of() in java 9: " + fruits);
        // Optional.or() method in java 9
        Optional<String> optionalValue = Optional.empty();
        String result = optionalValue.or(() -> Optional.of("Default Value")).get();
        System.out.println("Result using Optional.or() in java 9: " + result);
        // java 10 features
        // Local variable type inference with var in java 10
        var message = "Hello, Java 10!";
        System.out.println("Message using var in java 10: " + message);

        // java 11 features
        // Local Variable Type in Lambda Expressions
        List<String> namesList = Arrays.asList("Alice", "Bob", "Charlie");
        namesList.forEach((var name) -> System.out.println("Hello, " + name + "!"));

        // String methods in java 11
        String multilineString = "This is a\nmultiline string.";
        System.out.println("Multiline string using String methods in java 11: " + multilineString);
         String blankString = "   ";
        System.out.println("Is blank string using String.isBlank() in java 11: " + blankString.isBlank());
        String repeatString = "Repeat! ".repeat(3);
        System.out.println("Repeated string using String.repeat() in java 11: " + repeatString);
        // HTTP Client
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.example.com/data"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
        // Removed the Java EE and CORBA modules

        // java 12 features
       //String Indent and Transform
        String indentedString = "    This string is indented.";
        System.out.println("Indented string using String.indent() in java 12: '" + indentedString.indent(4) + "'");
        String transformedString = "hello world".transform(String::toUpperCase);
        System.out.println("Transformed string using String.transform() in java 12: " + transformedString);


        //java 14 features
        // Switch expression with yield in java 14
        System.out.println("Switch expression with yield in java 14: ");
        DayOfWeek dayOfTheWeek = DayOfWeek.MONDAY;
        String messageOfTheDay = switch (dayOfTheWeek) {
            case MONDAY -> {
                System.out.println(dayOfTheWeek);
                yield "Two days till Wednesday!";
            }
            case TUESDAY -> {
                System.out.println(dayOfTheWeek);
                yield "One day till Wednesday!";
            }
            case WEDNESDAY -> "It is Wednesday my dudes!";
            default -> "It is not Monday, Tuesday or Wednesday!";
        };
        System.out.println(messageOfTheDay);

        // Helpful NullPointerExceptions in log in java 15
        String str = null;
        try {
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }

        // text blocks in java 15
        String detailsJsonTextBlock = """
           {
               "name": "John",
               "lastName": "Doe",
               "experience": "3 years",
               "description": "This is description"
           }
        """;
        System.out.println(detailsJsonTextBlock);

        // Records in java 16
        record Person(String name, String lastName, int experience, String description) {}
        Person person = new Person("John", "Doe", 3, "This is description ");
        System.out.println(person);
        // Pattern Matching for instanceof in java 16
        Object obj = "This is a string";
        if (obj instanceof String strobj) {
            System.out.println("The length of the string is: " + strobj.length());
        }
        // Sealed classes in java 17
        // Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them.
        // This provides a more declarative way of controlling the inheritance hierarchy and can help improve code maintainability and security.

        // Pattern Matching for instanceof in java 17
        Object obj2 = 123;
        if (obj2 instanceof Integer i) {
            System.out.println("The value of the integer is: " + i);
        }

        // java 21 features
        // Virtual Threads in java 21
        Runnable task = () -> {
            System.out.println("Running in a virtual thread: " + Thread.currentThread().getName());
        };
      //  Thread.startVirtualThread(task);
        /*try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 500_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }*/  // executor.close() is called implicitly, and waits
        // String Templates
           /* String name = "Alice";
            String greeting = STR."Hello, my name is {name}";
            System.out.println(greeting);*/


    }

    public int printFibonacciRecursive(int n){
        if (n<=1){
            return n;
        }
        return printFibonacciRecursive(n-1) + printFibonacciRecursive(n-2);
    }

    public void printFibonacciSequence(int n){
        int a =0, b=1, c=1;
        for(int i=0 ; i<n; i++){
            System.out.print(a+ ",");
            a = b;
            b = c;
            c = a + b;
        }
    }

    public boolean onlyOddNumbers(List<Integer> list) {
        /*for(int i : list){
            if(i%2==0){
                return false;
            }
        }
        return true;*/

        return list.stream().anyMatch( n -> n%2!=0);
    }

    public void checkPalindromeString(String str){
        int length = str.length();
        boolean flag = true;
        for(int i =0 ; i< length/2;i++){
            if(str.charAt(i) != str.charAt(length-i-1))
                flag=  false;
        }
        if(flag){
            System.out.println("string is panlindrom : " + flag);
        }else{
            System.out.println("string is not panlindrom : " + flag);
        }
    }

    public void removeWhiteSpaces(String input){
        StringBuilder builder = new StringBuilder();
        char[] charArray = input.toCharArray();

        for(int i =0 ; i< input.length() ;i++){
            if(Character.isWhitespace(input.charAt(i)))
               continue;
            builder.append(input.charAt(i));
        }

        System.out.println("'"+input + "' removeWhiteSpaces string : '" + builder.toString()+"'");

    }
}
