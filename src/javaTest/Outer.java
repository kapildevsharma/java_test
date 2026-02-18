package javaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java Program to show Internal Working
//of TreeMap in Java

//Importing Map and TreeMap classes
//from java.util package
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.stream.Stream;

//Standard Comparable
class Key implements Comparable<Key> {

	// Custom input
	private String key;

	// Constructor of this class
	public Key(String key) {
		// Super keyword refers immediate parent class object
		super();

		// This keyword is a reference variable referring to current object
		this.key = key;
	}

	// Print Key method
	public String printKey() {
		return this.key;
	}

	// Override compareTo method
	@Override
	public int compareTo(Key obj) {
		return key.compareTo(obj.key);
	}
}

//Main Class
class Outer {

	// Main driver method
	public static void main(String[] args) {
		// Initialize TreeMap
		// Declaring object of String type
		Map<Key, String> treemap = new TreeMap<>();

		// Adding the elements in object of TreeMap
		// Custom inputs
		treemap.put(new Key("Key1"), "Alice");
		treemap.put(new Key("Key4"), "Zeya");
		treemap.put(new Key("Key3"), "Geek");
		treemap.put(new Key("Key2"), "Bob");

		// Iterate over object elements using for-each loop
		for (Map.Entry<Key, String> entry : treemap.entrySet())

			// Print elements in TreeMap object
			System.out.println("[" + entry.getKey().printKey() + " = " + entry.getValue() + "]");

		getSplit();

	}

	public static void getSplit() {
		// Create an object of array list
		ArrayList<Integer> list = new ArrayList<>();
		// Add values to the array list.
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		Stream<Integer> str = list.stream();
		// Get Spliterator object on list
		Spliterator<Integer> splitr = str.spliterator();
		// Get estimateSize method
		System.out.println("Estimate size: " + splitr.estimateSize());
		// Print getExactSizeIfKnown method
		System.out.println("Exact size: " + splitr.getExactSizeIfKnown());
		// Get characteristics method
		System.out.println("characteristics Result: " + splitr.characteristics());
		// Check for hasCharacteristics and characteristics method
		System.out.println("Boolean Result: " + splitr.hasCharacteristics(splitr.characteristics()));
		System.out.println("Elements of ArrayList :");
		// Obtain result forEachRemaining method
		splitr.forEachRemaining((n) -> System.out.print(n + " "));
		System.out.println();
		// Obtaining another Stream to the array list.
		Stream<Integer> str1 = list.stream();
		splitr = str1.spliterator();
		// Obtain result from trySplit() method
		Spliterator<Integer> splitr2 = splitr.trySplit();
		// If splitr could be split, use splitr2 first.
		if (splitr2 != null) {
			System.out.println("Output from splitr2: ");
			splitr2.forEachRemaining((n) -> System.out.print(n + " "));
		}
		System.out.println();
		// Now, use the splitr
		System.out.println("Output from splitr1: ");
		splitr.forEachRemaining((n) -> System.out.println(n));
	}

}
