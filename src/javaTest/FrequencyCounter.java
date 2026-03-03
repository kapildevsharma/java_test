package javaTest;

import com.Test.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class FrequencyCounter {

    public static <T>Map<T, Integer> count(T[] arr){
        Map<T, Integer> freqMap = new LinkedHashMap<>();
        for(T item : arr){
            freqMap.put(item, freqMap.getOrDefault(item,0) + 1);
        }
        return freqMap;
    }

    public static void main(String[] args) {
        Double[] doubleArr = {1.1, 2.2, 1.2, 1.1, 4.1, 5.1, 7.1};
        Integer[] intArr = {1, 2, 7, 3, 5, 2, 7};
        Person[] personArr = {new Person("A", "C"), new Person("B", "D"), new Person("A", "C")};

        Map<Double, Integer> doubleFreq = count(doubleArr);
        Map<Integer, Integer> intFreq = count(intArr);
        Map<Person, Integer> personFreq = count(personArr);

        doubleFreq.forEach((k, v) -> System.out.println("Item " + k + " is repeated for " + v + " time/s."));
        intFreq.forEach((k, v) -> System.out.println("Item " + k + " is repeated for " + v + " time/s."));
        personFreq.forEach((k, v) -> System.out.println("Item " + k + " is repeated for " + v + " time/s."));
    }
}
