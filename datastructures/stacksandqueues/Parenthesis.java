package datastructures.stacksandqueues;

import java.util.*;

class Parenthesis{
	
	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			String input=sc.next();
            Stack<Character> parenthesis = new Stack<Character>();
            boolean invalid = false;
            for(int i=0;i<input.length();i++) {
                char p = input.charAt(i);
                if(p == '{' || p == '[' || p== '(') {
                    parenthesis.push(p);
                } else {
                    if (parenthesis.size() > 0) {
                        char ps = parenthesis.peek();
                        if((p == '}' && ps == '{') || (p == ']' && ps == '[') || (p == ')' && ps == '(')) {
                            parenthesis.pop();
                        } else {
                            break;
                        }
                    } else {
                        invalid = true;
                        break;
                    }
                } 
            }
            System.out.println(parenthesis.size() == 0 && !invalid);
		}
		
	}
}