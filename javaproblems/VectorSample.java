package javaproblems;

import java.util.*;

public class VectorSample {

    public static void main(String args[]) {
        // Without typesafety (i.e not using generics)
        // Vector v = new Vector();
        // v.add(1);
        // v.add(2);
        // v.add(3);
        // v.add("Sandhya");
        // v.add("Giri");

        Vector<Integer> v = new Vector<Integer>();
        v.add(1);
        v.add(2);
        v.add(3);
        v.add(1,10);
        System.out.println(v);

        Vector<Integer> v2 = new Vector<Integer>();
        v2.add(1);
        v2.add(2);
        v2.add(3);
        v2.add(1,10);

        // .equals does deep comparison of elements
        System.out.println(v.equals(v2));

        System.out.println(v2.size());
        
        // Clearing the vector
        v.clear();
        System.out.println(v);
    }
}