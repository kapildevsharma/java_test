package javaTest;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8 {
	
	public void duplicateElementInList() {
		// Creating a list of Even Numbers
		List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8, 4, 2);
		// distinct (unique) example
        // Using Stream distinct() method to get unique elements from list
		List<Integer> deduped = EvenNumbers.stream().distinct().collect(Collectors.toList());
		deduped.stream().forEach(i -> System.out.println("unique element: "+ i));
		// normal way to find unique element in list by using set
		LinkedHashSet<Integer> linkedHashset = new LinkedHashSet<Integer>(EvenNumbers);
		List<Integer> myArrayList = new ArrayList<Integer>(linkedHashset);
		myArrayList.stream().forEach(e -> System.out.println("In linkedHashset, arralylist unique element: " + e));
		
		//Start Example of CopyOnWriteArrayList
		List<String> list = new CopyOnWriteArrayList<String>();
		list.add("2");list.add("4");list.add("6");list.add("8");list.add("4");
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String value = iter.next();
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
		EvenNumbers.stream().filter(e -> !set.add(e)).collect(Collectors.toSet()).
		forEach(duplicate -> System.out.println("Duplicate element: " + duplicate));

        // find duplicate value in list by using haspmap
        Map<Integer, Long> freq =
                EvenNumbers.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));

        freq.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

		// get unique value from list 
		Set<Integer> uniqueGas = new HashSet<Integer>(EvenNumbers);
	//	uniqueGas.forEach(System.out::println);
		System.out.println("By Using set, unique element count: " + uniqueGas.size());
	}

	public void flatMapMethod() {
		
		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));

		List<String> flatMapPhones = people.values().stream()
		  .flatMap(Collection::stream)
		    .collect(Collectors.toList());
		
		for(String phone: flatMapPhones) {
			System.out.println("element of flatmap : "+phone);
		}
		
		// Creating a list of Prime Numbers
		List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11, 13);
		// Creating a list of Odd Numbers
		List<Integer> OddNumbers = Arrays.asList(1, 3, 5);
		// Creating a list of Even Numbers
		List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8, 4, 2);
		EvenNumbers = EvenNumbers.stream()
				.peek(s -> System.out.println("list value : "+ s)).distinct()
				.peek(s -> System.out.println("unique list value : "+ s))
				.collect(Collectors.toList());
		List<List<Integer>> listOfListofInts = Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);
		System.out.println("The Structure before flattening is : " + listOfListofInts);

		// Using flatMap for transferFormating and flattening.  
		List<Integer> listofInts = listOfListofInts.stream().flatMap(list -> list.stream())
				.peek(data -> System.out.println("peek "+data))
				.collect(Collectors.toList());

		System.out.println("The Structure after flattening is : " + listofInts);

		List<List<String>> subList = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
		System.out.println("subList : "+ subList);
		System.out.println(subList.stream().flatMap(Collection::stream).collect(Collectors.toList()));

		List<String> country = Stream
				.of(Arrays.asList("Colombia", "Finland", "Greece", "Iceland", "Liberia", "Mali", "Mauritius"),
						Arrays.asList("Peru", "Serbia", "Singapore", "Turkey", "Uzbekistan", "Yemen", "Zimbabwe",
								"Greece", "Iceland"))
				.flatMap(List::stream).collect(Collectors.toList());

		System.out.println("country " + country);

        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        List<String> collect = Stream.of(array)     // Stream<String[]>
                .flatMap(Stream::of)                // Stream<String>
                .filter(x -> !"a".equals(x))        // remove "a"
                .collect(Collectors.toList());
        System.out.println("collect to list : " + collect);
        String[] result  = Stream.of(array)     // Stream<String[]>
                .flatMap(Stream::of)                // Stream<String>
                .filter(x -> !"a".equals(x))        // remove "a"
                .toArray(String[]::new);
        System.out.println("result in array[] object : " + Arrays.toString(result));

	}

	private List<String> extractedMapUpperCase(List<String> cityList) {
		cityList = cityList.stream()
				.map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("upperCase list : " + cityList);
        return cityList;

	}
	
	public void displayAvgIntArr() {
		int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10};
        int sum = Arrays.stream(arr).sum();
        System.out.println("sum of arr:" + sum);
		System.out.println("Before time :" +LocalDateTime.now());
		double avg = Arrays.stream(arr).map(i -> (i%2!=0) ? i+1 : i)
                .average().orElse(0.0);
		System.out.println("Avg of arr after convert odd to even :" +avg);
		System.out.println("After time :" +LocalDateTime.now());

        System.out.println("Before time :" +LocalDateTime.now());
		List<Integer> listOfNumbers = Arrays.stream(arr).boxed().collect(Collectors.toList());
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9)) ;

		double avgD = list.stream().mapToInt(i ->(i%2!=0)? i+1:i).average().getAsDouble();
		System.out.println("Avg :" +avgD);

		System.out.println("After avg time :" +LocalDateTime.now());



    }
    public void topKFrequent() {
        // Find Top K Frequent Words  Input: ["java","python","java","go","java","go"], K=2 → ["java
        //","go"]
        int k=2;
        List<String> words = List.of("java","python","java","go","java","go");
        Map<String, Long> freqMap = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        // print the frequency map
        freqMap.forEach((w, count) -> System.out.println("Word: " + w + ", Count: " + count));

        freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0L) + 1);
        }
    // directly filter the frequency map to get top K frequent words
        freqMap.entrySet().stream()
                .filter(entity -> entity.getValue()==k)
                .map(Map.Entry::getKey)
                .forEach(w -> System.out.println("Top " + k + " frequent word: " + w));

        // sort the frequency map by value and get top K frequent words
        freqMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .forEach(w -> System.out.println("Top " + k + " frequent word: " + w));

    }

	public static void main(String[] args) {
		Java8  obj = new Java8();
		obj.duplicateElementInList();
		obj.flatMapMethod();
		obj.displayAvgIntArr();
        obj.topKFrequent();

        // covert string value of list to upper case
       	List<String> citylist = obj.extractedMapUpperCase(Arrays.asList("delhi", "mumbai", "hyderabad", "ahmedabad", "indore", "patna"));
		System.out.println("upperCase MAP list : " + citylist);
        // filter list by length and print
		citylist.stream().filter(s -> s.length() > 5).forEach(name -> System.out.print(name + " "));
		System.out.println();
        // find first and any value in list by using optional
		optionalValue(citylist);
		
		System.out.println("use map");
        // use map to get substring after 2nd index and print
		citylist.stream()
            .peek(data -> System.out.println("log in stream by peek() method "+data))
            .map(s -> {
                return s.substring(2);
            }).collect(Collectors.toList()).forEach(e -> System.out.println("substring after 2nd index, name : " + e));
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
		System.out.println("Number of elements in stream=" + intStream.stream().count());
		System.out.println("Stream contains all elements less than 50 :  "+
                intStream.stream().allMatch(i -> i<50));
        System.out.println(" :: Stream contains any elements great than 50 : " +
                intStream.stream().anyMatch(i -> i>50) + " :: ");
		
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
			getComeltableFutureOperatrion();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        int[] arr = {1, 2, 3, -2, 5};
        arr = new int[]{1, 1, 1, 3, 2, 2, 3};
        // Occurrence of elements
        Map<Integer, Long> mapOccurrence = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Find duplicate elements
        mapOccurrence.entrySet().stream().filter(e ->e.getValue()>1)
            .map(Map.Entry::getKey)
            .forEach(e -> System.out.println("Duplicate element in arr : " + e)) ;

        // find  First Non Repeating Char
        String testStr = "aabbccdeff";
        Optional<Character> nonRepeatingChar = testStr.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(e -> e, LinkedHashMap :: new, Collectors.counting()))
        .entrySet().stream().filter(e -> e.getValue()==1).map(Map.Entry:: getKey).findFirst();
        System.out.println("first non repeating character in string : "
                + (nonRepeatingChar.isPresent()?nonRepeatingChar.get():"No non repeating character"));

        HashMap<Character, Integer> linkedMap = new LinkedHashMap<Character, Integer>  ();
        for(char c : testStr.toCharArray()){
            linkedMap.put(c, linkedMap.getOrDefault(c, 0)+1);
        }
        for(char c: linkedMap.keySet()){
            if(linkedMap.get(c)==1){
                System.out.println("first non repeating character in string by using linkedHashMap : " + c);
                break;
            }
        }

        testStr = "the sky is blue";
        String[] wordArr = testStr.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = wordArr.length-1 ; i>=0 ; i--){
            sb.append(wordArr[i]).append(" ");
        }
        System.out.println("reverse word in sentence : " + sb.toString().trim());
        Collections.reverse(Arrays.asList(wordArr));
        System.out.println("reverse word in sentence by using collection : " + String.join(" ",
                    wordArr));
        testStr = Arrays.stream(testStr.split(" ")).map(word -> new StringBuffer(word).reverse()).collect(Collectors.joining(" "));
        System.out.println("reverse word in sentence by using collection : " + testStr);

        // duplicate element in array
        Set<Integer> uniqueValSet = new HashSet<>();
        Set<Integer> duplicateValSet = new HashSet<>();
        for(int n : arr){
            if(!uniqueValSet.add(n)){
                duplicateValSet.add(n);
            }
        }
        duplicateValSet.forEach(e -> System.out.println("Duplicate element in arr by using set : " + e)) ;
        // Replace duplicates with -1
        uniqueValSet = new HashSet<>();
        for(int n =0; n<arr.length ; n++){
            if(!uniqueValSet.add(arr[n])){
                arr[n] = -1; // mark duplicate element with -1
            }
        }

        // Find largest and second-largest element in array
        arr = new int[]{5,3,2,8,2,5};
        int largest = Arrays.stream(arr).boxed().max(Integer::compareTo).orElseThrow();
        int secondLargest = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder()).skip(2-1)
                .findFirst().get();

        System.out.println("largest : "+ largest + " secondLargest : "+ secondLargest);
        largest = Integer.MIN_VALUE;
        secondLargest = Integer.MIN_VALUE;
        for(int n : arr){
            if(n>largest){
                secondLargest = largest;
                largest = n;
            }else if(n>secondLargest && n!=largest){
                secondLargest = n;
            }
        }
        System.out.println("largest : "+ largest + " secondLargest : "+ secondLargest);

        // First missing positive integer
        arr = new int[]{3,4,-1,1};
        uniqueValSet = new HashSet<>();
        for(int n : arr){
            if(n>0)
                uniqueValSet.add(n);
        }
        int missing = 1;
        while(true){
            if(!uniqueValSet.contains(missing)){
                System.out.println("first missing positive integer : "+ missing);
                break;
            }
            missing++;
        }
        System.out.println("first missing positive integer : "+ missing);

        // Move zeros to beginning
        arr = new int[]{0,5,1,0,7,0,2,0};

        arr = IntStream.concat(Arrays.stream(arr).filter(i -> i==0),
                Arrays.stream(arr).filter(i -> i!=0)
                ).toArray();
        System.out.println("after move zero to end : "+ Arrays.toString(arr));

        arr = new int[]{0,5,1,0,7,0,2,0};
        int  j = arr.length-1;
        for(int i = 0 ; i< arr.length ; i++){
            if (arr[i] != 0) {
                arr[j--] = arr[i];
            }
        }
        for(int i = j ; i>=0 ; i--){
            arr[i] = 0;
        }
        System.out.println("after move zero to end : "+ Arrays.toString(arr));


        String rearrangementStr = "era2 uoy3 woh1";
        String[] rearrangeArr =rearrangementStr.split(" ");
        String[] rearrangeArrRes = new String[rearrangeArr.length];
        for(String s : rearrangeArr){
            int pos = Integer.parseInt(String.valueOf(s.charAt(s.length()-1)));
            rearrangeArrRes[pos-1] = s.substring(0, s.length()-1);
        }

        System.out.println("rearrangementStr : "+ Arrays.toString(rearrangeArr) +" rearrangeArrRes : "+ Arrays.toString(rearrangeArrRes));

        rearrangementStr =  Arrays.stream(rearrangementStr.split(" ")).sorted(Comparator.comparingInt( str -> str.charAt(str.length()-1)))
                .map(s -> s.substring(0, s.length()-1)).collect(Collectors.joining(" "));

        System.out.println("rearrangementStr : "+ rearrangementStr);

        int X = 3;
        int res = Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted((a,b) -> b-a)
                .skip(1)
                .findFirst()
                .orElseThrow();
        System.out.println("res : "+ res);
        BigInteger res1 = Arrays.stream(arr)
                .mapToObj(BigInteger::valueOf)
                .distinct()
                .peek(e -> System.out.println("Filtered value: " + e))
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();
        System.out.println("res1 : "+ res1);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.computeIfAbsent(1, k -> 2);
        int sum = 0;
        int count = 0;

        for (int num : arr) {
            System.out.println("1st count : "+ count+ " Sum: "+ sum + " : map value: "+ map.getOrDefault(sum - X, 0) + " (sum - X) : "+ (sum - X)); // number of subarrays

            sum += num;
            count += map.getOrDefault(sum - X, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            System.out.println("2nd count : "+ count+ " Sum: "+ sum + " : map value : "+ map.getOrDefault(sum - X, 0)); // number of subarrays
        }

        System.out.println("count : "+ count); // number of subarrays

        // Find the first non-repeating character in a string.
        String st = "swisas";
        String string = st.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('0').toString();
        System.out.println("first non-repeating character : " + string);

        // Reverse each word in a sentence
        String sentence = "I love programming in Java";
        Map<String, Long> wordCount = Arrays.stream(sentence.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word Count: " + wordCount);

        String uniqueWord = wordCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("No unique word");
        System.out.println("Without LinkedHashMap First unique word: " + uniqueWord);
        uniqueWord = Arrays.stream(sentence.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("No unique word");
        System.out.println("First unique word: " + uniqueWord);


        String reversedWordSentence = Arrays.stream(sentence.split("\\s+"))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println("reversedWordInSentence: " + reversedWordSentence);
        List<String> words = Arrays.asList(sentence.split("\\s+"));

        // Reverse the order of words in the sentence
        String reversedSentence = words.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
            Collections.reverse(list); // reverse word order
            return String.join(" ", list);
        }));
        System.out.println("reversedOrderWordInSentence: " + reversedSentence);

    }

	public static void getComeltableFutureOperatrion() throws InterruptedException, ExecutionException {
		System.out.println();
        // Run a task asynchronously (no return)
        CompletableFuture<Void> futureNoReturn = CompletableFuture.runAsync(() -> {
            System.out.println("Task running in thread: " + Thread.currentThread().getName());
        });
        futureNoReturn.join(); // Wait for completion

        // Async task with a return value
        CompletableFuture<String> completableFuture
		  = CompletableFuture.supplyAsync(() -> "Hello");
		System.out.println("completableFuture--> " + completableFuture.get());

        // Transform result – thenApply()
		CompletableFuture<String> future = completableFuture
				.thenApply(s -> s + " World");
		System.out.println("completableFuture thenApply --> " + future.get());

        //  Consume result – thenAccept()
        //  Run after completion – thenRun()
		CompletableFuture<Void> future1 = future
				  .thenAccept(s -> System.out.println("Computation returned: " + s))
				  .thenRun(() -> System.out.println("Computation finished"));

		System.out.println("completableFuture accept then run--> " + future1.get());
		
		CompletableFuture<String> future2 = completableFuture
				.thenApply(s -> s + " Good Morning");

        // Combine two results – thenCombine()
		CompletableFuture<String> combinedFuture = future.thenCombine(
      		future2, (m1, m2) -> m1 + " " + m2);
        System.out.println(combinedFuture.join()); // 5
		System.out.println("completableFuture thenCombine  "+ combinedFuture.get());

        // Wait for all futures – allOf()
        CompletableFuture<Void> all = CompletableFuture.allOf(future, future2);
        all.join(); // Wait for both f1 & f2
        System.out.println("completableFuture allOf 'all': "+ all.get());

		CompletableFuture<Integer> resultFuture 
        = CompletableFuture.supplyAsync(() -> 10 / 0)
            .orTimeout(1000, TimeUnit.MILLISECONDS)
            .exceptionally(ex -> 0);
      //  handle() → handles both success & failure
        CompletableFuture<Integer> futurehandle = CompletableFuture.supplyAsync(() -> 1/0)
                .handle((res, ex) -> ex != null ? 0 : res);
        System.out.println(futurehandle.join()); // 0

       // use ExecutorService for IO-bound tasks
        ExecutorService customPool = Executors.newFixedThreadPool(10);
        CompletableFuture.runAsync(Java8::consumeMain, customPool);

      // 0 - returned by exceptionally block 
		System.out.println(resultFuture.get());

        customPool.shutdown();
		
	}
	
	
	private static void optionalValue(List<String> citylist) {
		Optional<String> firstNameWithD = citylist.stream().filter(i -> i.contains("D")).findFirst();
		if(firstNameWithD.isPresent()){
			System.out.println("find first Name contains with D ="+firstNameWithD.get());
		}
		Optional<String> firstAnyNameWith = citylist.stream().filter(i -> i.contains("D")).findAny();
        firstAnyNameWith.ifPresent(s -> System.out.println("find any Name contains with D = " + s));

	}

	public static void consumeMain() {
		// Consumer to display a number
		Consumer<Integer> display = a -> System.out.println("Consume 1 value : "+a);
		// Implement display using accept()
		display.accept(10);
		// Consumer to multiply 2 to every integer of a list
		Consumer<List<Integer>> modify = list -> {
			for (int i = 0; i < list.size(); i++)
				list.set(i, 2 * list.get(i));
		};

		// Consumer to display a list of numbers
		Consumer<List<Integer>> dispList = list -> list.stream().forEach(a -> System.out.print(a + " "));

		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		list.add(3);
		// Implement modify using accept()
		modify.accept(list);
		// Implement dispList using accept()
		dispList.accept(list);
		System.out.println("\n modify , display and accept list");
		modify.andThen(dispList).accept(list);
	}
}
