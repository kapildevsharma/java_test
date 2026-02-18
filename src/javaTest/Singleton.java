package javaTest;

import java.io.Serial;
import java.io.Serializable;

public class Singleton implements Cloneable, Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

    // Lazy Initialization
	// Static variable single_instance of type Singleton
    private static volatile Singleton single_instance;
 
    // Declaring a variable of type String
    public String s;
 
    // Constructor of this class
    // Here private constructor is is used to restricted to this class itself
    private Singleton()
    {
        s = "Hello I am a string part of Singleton class";
    }
 
    // Method Always use volatile with double-checked locking.
    // Static method to create instance of Singleton class
    public static Singleton getSingleton()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
        	synchronized (Singleton.class) {
        		if(single_instance==null)
        			single_instance = new Singleton();
			}
        }
        return single_instance;
    }
    
 // implement readResolve method
    @Serial
    protected Object readResolve()
    {
        return single_instance;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException 
    {
    	return single_instance;
    }
    
}
