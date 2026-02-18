package com.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
	private String s ;
	public Test(String s) {
		this.s = s;
	}

	public void abc(String st) {
		System.out.println(s + " string : " + st);
	}
	
	public void abc(Object st) {
		System.out.println(s + "  object : " + st);
	}
	
	public static void main(String[] args) {
		String st = "2022/09/03".replace("/", "-");
		System.out.println(st);
        // calls abc(String) because String is more specific
        new Test(st).abc(null); // prints: 2022-09-03 string : null

        // force the Object overload
        new Test(st).abc((Object) null); // prints: 2022-09-03  object : null

        // Find Top K Frequent Words  Input: ["java","python","java","go","java","go"], K=2 → ["java
        //","go"]
        List<String> words = List.of("java","python","java","go","java","go");
        Map<String, Long> freqMap = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        freqMap.forEach((word,count) -> System.out.println(word + " : " + count));
	}
	
}
