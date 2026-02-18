package javaTest;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NoOccurenceWord {

	public static void main(String[] args) {
		String str= "welcome to kapil decode and decodee kapil to welcome";
		List<String> list = Arrays.asList(str.split(" "));
		Map<String, Long> map =  list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		map.entrySet().forEach(e -> System.out.println(e.getKey() + ":: " + e.getValue() ));
		System.out.println(map);
		map =   list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		System.out.println(map);
		
		String st = "kapildevkapil";
		printCountOfDuplicateCharMapCompute(st);
		printCountOfDuplicateCharacterUsingHashMap(st);
	}

	// Using hashmap : print count of each character in a given string.
    private static void printCountOfDuplicateCharMapCompute(String input) {
        Map<Character, Integer> output = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            output.compute(ch, (key, value) -> (value == null) ? 1 : value + 1);
        }
        System.out.println(output);
    }
	
	// Using hashmap : print count of each character in a given string.
    private static void printCountOfDuplicateCharacterUsingHashMap(String input) {
        Map<Character, Integer> output = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (output.containsKey(ch)) {
                output.put(ch, output.get(ch) + 1);
            } else {
                output.put(ch, 1);
            }
        }
        System.out.println(output);
    }
}
