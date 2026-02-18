package com.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitListFromList {

	public static void main(String[] args) {
		int[] l = new int[] { 1, 2, 3, 4 };
		boolean[] flags = new boolean[l.length];
		
		for (int i = 0; i != l.length;) {
			ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>();
			for (int j = 0; j < l.length; j++)
				if (flags[j])
					a.add(l[j]);
				else
					b.add(l[j]);
			System.out.println("" + a + ", " + b);
			for (i = 0; i < l.length && !(flags[i] = !flags[i]); i++);
		}

		List<String> list = Arrays.asList("A", "B", "C", "D", "E");

		// split list into two
		List<List<String>>[] lists = splitListIntoTwoList(list);
		System.out.println("List: "+list+" split 2 list:: List1 : " + lists[0] + ", list2 :"+ lists[1]);

		
		ArrayList<Employee> test = new ArrayList<Employee>();
		ArrayList obj = test; 
		obj.add(new Object());
		System.out.println(test.get(0));
	}

	// Generic method to split a list into two sublists in Java 8 and above
	public static <T> List[] splitListIntoTwoList(List<T> list) {
		int midIndex = (list.size() - 1) / 2;
		
		List<T> first = new ArrayList<>(list.subList(0, midIndex));
		first.stream().forEach(System.out::println);
		
		List<List<T>> lists = new ArrayList<>(
				list.stream().collect(Collectors.partitioningBy(s -> list.indexOf(s) > midIndex)).values());

		/*
		 * lists = new ArrayList<>( list.stream() .collect(Collectors.groupingBy(s ->
		 * list.indexOf(s) > midIndex)) .values() );
		 */
		// return an array containing both lists
		return new List[] { lists.get(0), lists.get(1) };
	}
}
