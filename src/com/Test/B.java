package com.Test;

public class B extends A{

	public B() {
		super("Kapil");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getData() {
		
		return "B class data " ;
	}
	
	public static void main(String[] args) {
		CI a = new B();
		String str = a.getData();
		System.out.println(str);
		a.display();
		CI.callInterface();

	}
	 public void display() {
	      System.out.println("Hello welcome to Tutorialspoint");
	   }

}
