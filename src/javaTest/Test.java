package javaTest;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		
		// Fibonacci Number
		test.viewFibonacciNumber();
		// Toggle String
		test.displayToggleString("KaPiL");
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(()-> System.out.println("Asynchronous task"));
		executorService.shutdown();
		
		
	}
	
	public static String foo(){
		System.out.println("Test foo called");
		return "";
	}
	
	

	public void displayToggleString(String str) {
        StringBuilder res = new StringBuilder();
		for(int i=0;i<str.length();i++) {
            char ch = str.charAt(i); //current character
            if(ch >='A' && ch <= 'Z') {
                res.append((char)(ch + 32));
            } else if(ch >='a' && ch<='z'){
                res.append((char)(ch - 32));
            } else { 
                res.append(ch);
            }
        }

        String ans = res.toString();
        System.out.println("The string after toggling becomes: " + ans);
	}
	public void viewFibonacciNumber() {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		scn.close();
		int a = 0; //0th fibonacci number
		int b = 1; //1st fibonacci number
	
		if(n < 0) {
			System.out.println("N cannot be negative");
			return;
		}
	
		if(n == 0) System.out.println(a);
		else if(n == 1) System.out.println(b);
		else {
			int c ;
			System.out.print(a+" ");
			System.out.print(b+" ");
			for(int i=2;i<=n;i++) {
				c = a + b;
				a = b;
				b = c;
				System.out.print(c+" ");
			}
		}
	}
}
