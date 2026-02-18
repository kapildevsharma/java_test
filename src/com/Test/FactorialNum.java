package com.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FactorialNum {
    public static void main(String[] args) {
        int num = 5;
        System.out.println("Factorial of " + num + " is "
                + factorial(5));
        System.out.println("Fact of " + num + " is "
                + factorialRecursive(5));

        System.out.println("factorialUsingStreams of " + num + " is "
                + factorialUsingStreams(5));

    }

    // Formulae for Factorial
    //n! = n * (n-1) * (n-2) * (n-3) * ........ * 1
    // Factorial in Java Using Recursive Method
    static BigInteger factorialRecursive(int n)
    {
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0 || n == 1) return BigInteger.ONE;
        return BigInteger.valueOf(n)
                .multiply(factorialRecursive(n - 1));

        // single line to find factorial
      //  return (n == 1 || n == 0) ? BigInteger.ONE :
        //  BigInteger.valueOf(n).multiply(factorialRecursive(n - 1));
    }
    // Iterative Solution for Factorial in Java

    static BigInteger factorial(int n)
    {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "Factorial is not defined for negative numbers"
            );
        }
       /* if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }*/
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            res = res.multiply(BigInteger.valueOf(i));
        return res;
    }

    // Factorial Using Java 8 Streams
    static BigInteger factorialUsingStreams(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        return IntStream.rangeClosed(1, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);

       /* return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);*/


    }

}
