package com.Test;

public abstract class A implements CI {

	public A (String s) {
		System.out.println(s);
	}
	@Override
	public void display() {
		System.out.println("A class");
	}

}
