package javaTest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java17 {
	
	public void duplciateElementInList() {
		// Creating a list of Even Numbers
		List<Integer> evenNumbers = Arrays.asList(2, 4, 6, 8, 4, 2);
		// distinct (unique) example
		List<Integer> uniqueList = evenNumbers.stream().distinct().toList();
        uniqueList.forEach(i -> System.out.println("unique element: "+ i));
        // get unique value from list
        Set<Integer> uniqueGas = new HashSet<Integer>(evenNumbers);
        //	uniqueGas.stream().forEach(System.out::println);
        System.out.println("By Using set, unique element count: " + uniqueGas.size());

		LinkedHashSet<Integer> linkedHashset = new LinkedHashSet<Integer>(evenNumbers);
		List<Integer> myArrayList = new ArrayList<Integer>(linkedHashset);
		myArrayList.forEach(e -> System.out.println("In linkedHashset, array list unique element: " + e));
		
		//Start Example of CopyOnWriteArrayList
		List<String> list = new CopyOnWriteArrayList<String>();
		list.add("2");list.add("4");list.add("6");list.add("8");list.add("4");
    /*  Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String value = iter.next();*/
        for (String value : list) {
			if(value.equals("4")) {
				System.out.println("Remove value from Arraylist"+ value);
				list.remove(value);
				System.out.println("Add value from Arraylist");
				list.add("1");
			}
		}
		// End Example of CopyOnWriteArrayList  
		
		// find duplicate value in list
		Set<Integer> set = new HashSet<Integer>();
		evenNumbers.stream().filter(e -> !set.add(e))
            .collect(Collectors.toSet())
            .forEach(duplicate -> System.out.println("Duplicate element: " + duplicate));

	}

	public void flatMapMethod() {
		
		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));

		List<String> flatMapPhones = people.values().stream()
		  .flatMap(Collection::stream).toList();
		
		for(String phone: flatMapPhones) {
			System.out.println("element of flatmap : "+phone);
		}
		
		// Creating a list of Prime Numbers
		List<Integer> primeNumbers = Arrays.asList(5, 7, 11, 13);
		// Creating a list of Odd Numbers
		List<Integer> oddNumbers = Arrays.asList(1, 3, 5);
		// Creating a list of Even Numbers
		List<Integer> evenNumbers = Arrays.asList(2, 4, 6, 8, 4, 2);
		evenNumbers = evenNumbers.stream()
				.peek(s -> System.out.println("list value : "+ s)).distinct()
				.peek(s -> System.out.println("unique list value : "+ s))
				.collect(Collectors.toList());
		List<List<Integer>> listOfListofInts = Arrays.asList(primeNumbers, oddNumbers, evenNumbers);
		System.out.println("The Structure before flattening is : " + listOfListofInts);

		// Using flatMap for transferFormating and flattening.  
		List<Integer> listofInts = listOfListofInts.stream().flatMap(Collection::stream)
				.peek(data -> System.out.println("peek "+data))
				.toList();
		System.out.println("The Structure after flattening is : " + listofInts);

		List<String> citylist = extractedMapUpperCase();
		System.out.println("Flat Map");
		citylist.stream().flatMap(num -> Stream.of(num)).forEach(System.out::println);

		System.out.println();
		List<List<String>> subList = Arrays.asList(List.of("a"), List.of("b"));
		System.out.println("subList : "+ subList);
		System.out.println(subList.stream().flatMap(Collection::stream).collect(Collectors.toList()));

		List<String> list1 = Arrays.asList("5.6", "7.4", "4", "1", "2.3");
		// Using Stream flatMap(Function mapper)
		list1.forEach(System.out::println);

		List<String> country = Stream
				.of(Arrays.asList("Colombia", "Finland", "Greece", "Iceland", "Liberia", "Mali", "Mauritius"),
						Arrays.asList("Peru", "Serbia", "Singapore", "Turkey", "Uzbekistan", "Yemen", "Zimbabwe",
								"Greece", "Iceland"))
				.flatMap(List::stream).toList();

		System.out.println("country " + country);

	}

	private List<String> extractedMapUpperCase() {
		List<String> citylist = Stream.of("delhi", "mumbai", "hyderabad", "ahmedabad", "indore", "patna")
				.map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("upperCase list : " + citylist);
		return citylist;
	}
	
	public void displayAvgIntArr() {
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Before time :" +LocalDateTime.now());
        Optional<Double> doubleOptional = Optional.of(Arrays.stream(arr).map(i -> (i % 2 != 0) ? i++ : i).average().orElse(0.0));
		double avg = doubleOptional.orElse(0.0);
		System.out.println("Avg :" +avg);
		System.out.println("After time :" +LocalDateTime.now());
		System.out.println("Before time :" +LocalDateTime.now());
		Arrays.stream(arr).boxed().toList().forEach(e -> System.out.print("listOfNumbers : " + e + " "));

		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9)) ;

        OptionalDouble doubleOpt = OptionalDouble.of(list.parallelStream().map(i ->(i%2!=0)? i++:i).mapToInt(Integer::intValue).average().getAsDouble());
        double avgD = doubleOpt.getAsDouble();
        System.out.println("Avg :" +avgD);
		System.out.println("After time :" +LocalDateTime.now());
		
	}

	public static void main(String[] args) {
		Java17 obj = new Java17();
		obj.duplciateElementInList();
		obj.flatMapMethod();
		obj.displayAvgIntArr();
	
		String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

		List<String> collect = Stream.of(array)     // Stream<String[]>
				.flatMap(Stream::of)                // Stream<String>
		        .filter(x -> !"a".equals(x))        // filter out the a
		        .toList();
		System.out.println("collect to list : " + collect);
		String[] result  = Stream.of(array)     // Stream<String[]>
		        .flatMap(Stream::of)                // Stream<String>
		          .filter(x -> !"a".equals(x))        // filter out the a
		          .toArray(String[]::new);  
		System.out.println("result in array : " + Arrays.toString(result));
		  
		List<String> citylist = Stream.of("delhi", "mumbai", "hyderabad", "ahmedabad", "indore", "patna")
				.map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("upperCase MAP list : " + citylist);
		citylist.stream().filter(s -> s.length() > 5)
                .forEach(name -> System.out.print(name + " "));

		System.out.println();
		
		optionalValue(citylist);
		
		System.out.println("use map");
		citylist.stream()
		.peek(data -> System.out.println("log in stream by peek() method " + data))
		.map(s -> {
			return s.substring(2);
		}).toList().forEach(e -> System.out.print("substring after 2nd index, name : " + e));
		System.out.println();
		
		System.out.println("\nSorted method");
		citylist.stream().filter(s -> s.length() > 5).sorted().forEach(name -> System.out.print(name + " "));;
		
		System.out.println("\nReverse Sorted method: ");
		citylist.stream().filter(s -> s.length() > 5).sorted(Comparator.reverseOrder()).forEach(name -> System.out.print(name + " "));;
				
		citylist = citylist.stream().filter(s -> s.length() < 6).collect(Collectors.toList());
		System.out.println("length list: " + citylist);

		boolean flag = citylist.stream().peek(data -> System.out.println("check peek "+data)).anyMatch(s -> s.contains("P"));
		System.out.println("match value in list: " + flag);

		List<Integer> intStream = Arrays.asList(12, 45, 67, 19, 87, 2, 9);
		System.out.println("Number of elements in stream=" + intStream.size());
		System.out.println("Stream contains all elements less than 50 :  "+ intStream.stream().allMatch(i -> i<50)
				+ " :: Stream contains any elements great than 50 : " + intStream.stream().anyMatch(i -> i>50) );
		
		Integer[] intArray = intStream.toArray(Integer[]::new);
		System.out.println("list to Array object : " + Arrays.toString(intArray));
		Stream.of(intArray).forEach(a -> System.out.print("intValue : " + a));
		System.out.println();
		
		Map<Integer, Integer> intMap = intStream.stream().collect(Collectors.toMap(i -> i, i -> i * 5));
		System.out.println("intMap " + intMap + "\n");

		intStream.stream().map(number -> number * 2).forEach(i -> System.out.print(i + " "));

		System.out.println(" ");
		intStream.stream().filter(i -> i % 2 == 0).map(number -> number * 2).forEach(System.out::println);

		consumeMain();
		try {
            getCompletableFutureOperation();
		} catch (InterruptedException | ExecutionException e1) {
			System.err.println(e1.getMessage());
		}
	}

	public static void getCompletableFutureOperation() throws InterruptedException, ExecutionException {
		System.out.println();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<String> completableFuture
		  = CompletableFuture.supplyAsync(() -> "Hello", executor);
		System.out.println("completableFuture--> " + completableFuture.get());

		CompletableFuture<String> future = completableFuture
				.thenApply(s -> s + " Kapil");
		System.out.println("completableFuture thenApply --> " + future.get());
		
		CompletableFuture<Void> future1 = future
				  .thenAccept(s -> System.out.println("Computation returned: " + s))
				  .thenRun(() -> System.out.println("Computation finished"));

		System.out.println("completableFuture accept then run--> " + future1.get());
		
		CompletableFuture<String> future2 = completableFuture.thenApply(s -> s + " Good Morning");
		CompletableFuture<String> combinedFuture = future.thenCombine(
      		future2, (m1, m2) -> m1 + " " + m2);
		System.out.println("completableFuture thenCombine  "+ combinedFuture.join());
		
		CompletableFuture<Integer> resultFuture 
        = CompletableFuture.supplyAsync(() -> 10 / 0)   
                  .exceptionally(ex -> 0);
      // 0 - returned by exceptionally block 
		System.out.println(resultFuture.get());
        resultFuture = CompletableFuture.supplyAsync(() -> 10/0)
            .handle((result, ex) -> {
                if (ex != null) {
                    System.out.println("Exception occurred: " + ex.getMessage());
                    return 0; // Return a default value in case of exception
                }
                return result;
            });
        System.out.println(resultFuture.join());

        CompletableFuture.supplyAsync(() -> "Hello")
            .whenComplete((result, error) -> {
                if (error == null) {
                    System.out.println(result);
                } else {
                    error.printStackTrace();
                }
            });
    }
	
	
	private static void optionalValue(List<String> citylist) {
		Optional<String> firstNameWithD = citylist.stream().filter(i -> i.contains("D")).findFirst();
        firstNameWithD.ifPresent(s -> System.out.println("find first Name contains with D =" + s));

        Optional<String> firstNameWith = citylist.stream().filter(i -> i.contains("D")).findAny();
        firstNameWith.ifPresent(s -> System.out.println("find any Name contains with D = " + s));
	}

	public static  void consumeMain() {
		// Consumer to display a number
		Consumer<Integer> display = a -> System.out.println("Consume 1 value : "+a);
		// Implement display using accept()
		display.accept(10);
		// Consumer to multiply 2 to every integer of a list
		Consumer<List<Integer>> modify = list -> {
            list.replaceAll(integer -> 2 * integer);
		};

		// Consumer to display a list of numbers
		Consumer<List<Integer>> dispList = list -> list.forEach(a -> System.out.print(a + " "));

		List<Integer> list = new ArrayList<Integer>();
		list.add(2); list.add(1); list.add(3); list.add(5);
        modify.accept(list); // Implement modify using accept()
		dispList.accept(list); // Implement dispList using accept()
		System.out.println("\n modify , display and accept list");
		modify.andThen(dispList).accept(list);
	}
}
