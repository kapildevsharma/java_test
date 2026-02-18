package com.Test;

abstract class AbstractClassTest {
	AbstractClassTest(int a) { // Parameterized Constructor
		System.out.println("Parameterized Constructor of an abstract class a=" + a);
	}
}

public class C extends AbstractClassTest {

	static String name;
	static int age;

	C() {
		super(20);
		System.out.println("Test Class Constructor");
		name = "Krishna";
		age = 25;
	}

	public static void main(String[] args) {
		C c = new C();
		c.show();
	}

	public void show() {
		System.out.println(C.name);
		System.out.println(C.age);
	}
}
