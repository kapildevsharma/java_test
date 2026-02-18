package javaTest;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class OpenCSVExample {

	public static void main(String[] args) {
		// Input file which needs to be parsed
		String fileToParse = "SampleCSVFile.csv";
		BufferedReader fileReader = null;

		// Delimiter used in CSV file
		final String DELIMITER = ",";
		try {
			String line = "";
			// Create the file reader
			// fileReader = new BufferedReader(new FileReader(fileToParse));

			StringBuffer str = new StringBuffer();
			// Read the file line by line

			// while ((line = fileReader.readLine()) != null)
			// {
			line = "Geeks,for,Geeks,GeeksQuiz,for,GeeksforGeeks";
			// Get all tokens available in line
			String[] tokens = line.split(DELIMITER);
			for (String token : tokens) {
				// Print all tokens
				// System.out.println(token);
				str.append(token.toString());
			}
			// }

			str.toString().chars().mapToObj(e -> Character.toString((char) e)).distinct().forEach(System.out::print);

			List<String> list = Arrays.asList(tokens);
			System.out.println("");
			System.out.println("The distinct elements are :");

			// Displaying the distinct elements in the list using Stream.distinct() method
			list.stream().distinct().forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * try { fileReader.close(); } catch (IOException e) { e.printStackTrace(); }
			 */
		}
	}

}
