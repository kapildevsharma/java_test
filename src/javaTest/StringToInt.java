package javaTest;

public class StringToInt {
    public static void main(String[] args) {
        String str = "1234";
        int result = 0;

        // Iterate through each character of the string
        for (int i = 0; i < str.length(); i++) {
            // Get the digit character at index i
            char digitChar = str.charAt(i);
            
         // Cast char to int to get ASCII value
		/*
		 * int asciiValue = (int) digitChar; int digit = asciiValue -(int)'0';
		 * System.out.println("Converted integer: " + asciiValue + " '0'" + (int)'0' +
		 * " digit : "+ digit );
		 */
            // Convert the char to its integer value
            int digit = digitChar - '0'; // '0' corresponds to 48 in ASCII
          
            // Shift the result and add the digit
            result = result * 10 + digit;
        }

        System.out.println("Converted integer: " + result);
    }
}

