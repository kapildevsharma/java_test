package com.Test;

class Test1 {
	public static void show() {
		System.out.println("parent");
	}

	static int i = 1111;

	static {
		i = i-- - --i; // L1
		System.out.println("L1 test1 i "+i);
	}

	{
		i = i++ + ++i; // L2
		System.out.println("L2 test1 i "+i);
	}
}

class Test2 extends Test1 {

	static {
		i = --i - i--; // L3
		System.out.println("L3 Test2 i "+i);
	}
	{
		i = ++i + i++; // L4
		System.out.println("L4 test2 i "+i);
	}

	public static void show() {
		System.out.println("child");
		System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
	}

}

public class Qwerty {

	public static void main(String[] args) {
		Test1 t = new Test1();
		t.show();

		System.out.println(10+20+ "done");
		Test2 t2 = new Test2();
		t2.show();
		
		System.out.println("test1 i: "+ t.i); // L5

		System.out.println("test2 i: "+ t2.i); // L5

	}

}
