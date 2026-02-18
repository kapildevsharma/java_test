package javaTest;

import java.util.ArrayList;
import java.util.List;

public class MovingTotal
{
	
    /**
     * Adds/appends list of integers at the end of internal list.
     */
	public List<Integer> elements = new ArrayList<Integer>();
	
    public void append(int[] list) {
       for (int i : list) {
    	   elements.add(i);
       }
    }

    /**
     * Returns boolean representing if any three consecutive integers in the
     * internal list have given total.
     */
    public boolean contains(int total) {
        for(int i = 0; i < elements.size()-2; i ++) {
			if(elements.get(i) + elements.get(i+1) + elements.get(i+2) == total) {
				return true;
			}
        }
        return false;
    }

    public static void main(String[] args) {
        MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[] { 1, 2, 3 });

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));

        movingTotal.append(new int[] { 4 });

        System.out.println(movingTotal.contains(9));
    }
}
