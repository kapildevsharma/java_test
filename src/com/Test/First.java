package com.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javaTest.Singleton;

interface AI {
	default void display() {
		System.out.println("AI");
	}
}

interface BI extends AI {
	default void display() {
		System.out.println("BI");
	}
}

interface CI extends AI {
	default void display() {
		System.out.println("CI");
	}

	static void callInterface() {
		System.out.println("interface method");
	}

	public String getData();
}

class DI implements BI, CI {

	@Override
	public void display() {
		BI.super.display();
	}
	public String getData() {
		return "kapil";
	}
}

public class First {
	public static void main(String arg[]) {
		CI.callInterface();

		DI obj = new DI();
		System.out.println(obj.getData());
		obj.display();

		Singleton x = Singleton.getSingleton();
		// Instantiating Singleton class with variable y
		serialization();

		getCloning();
		// instantiating Singleton class with variable z
		Singleton z = Singleton.getSingleton();
		// Now changing variable of instance x
		// via toUpperCase() method
		x.s = (x.s).toUpperCase();
	}

	public static void getCloning() {

		try {
			Singleton instance1 = Singleton.getSingleton();
			Singleton instance2 = (Singleton) instance1.clone();
			System.out.println("instance1 hashCode:- " + instance1.hashCode());
			System.out.println("instance2 hashCode:- " + instance2.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void serialization() {
		try {
			Singleton instance1 = Singleton.getSingleton();
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.text"));
			out.writeObject(instance1);
			out.close();

			// deserialize from file to object
			ObjectInput in = new ObjectInputStream(new FileInputStream("file.text"));

			Singleton instance2 = (Singleton) in.readObject();
			in.close();

			System.out.println("serialization instance1 hashCode:- " + instance1.hashCode());
			System.out.println("deserialization instance2 hashCode:- " + instance2.hashCode());
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
