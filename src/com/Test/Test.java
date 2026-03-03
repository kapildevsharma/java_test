package com.Test;

import java.io.File;
import java.util.ArrayList;
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

        List<String> arrList = new ArrayList<>();
        arrList.add("A");
        arrList.add("C");
        // Inserting "B" at index 1 requires shifting "C" to index 2
        arrList.add(1, "B"); // O(n) operation
        System.out.println(arrList); // Output: [A, B, C]


        String st = "2022/09/03".replace("/", "-");
		System.out.println(st);
        // calls abc(String) because String is more specific
        new Test(st).abc(null); // prints: 2022-09-03 string : null

        // force the Object overload
        new Test(st).abc((Object) null); // prints: 2022-09-03  object : null

        // Find Top K Frequent Words  Input: ["java","python","java","go","java","go"], K=2 → ["java","go"]
        List<String> words = List.of("java","python","java","go","java","go");
        Map<String, Long> freqMap = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        freqMap.forEach((word,count) -> System.out.println(word + " : " + count));

        searchFile(new File("C:\\Kapil\\Intellij Workspace\\java_test\\src"), "Test.java");

	}

    public static void searchFile(File dir, String fileName) {
        if(dir.isDirectory()) {
            File[] files   = dir.listFiles();
            for (File file : files) {
                searchFile(file, fileName);
            }
        } else if(dir.getName().equals(fileName)) {
            System.out.println("File found at: " + dir.getAbsolutePath());
        }
    }
	
}
