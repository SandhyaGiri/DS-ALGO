package problemsolving;

import java.util.*;

/**

Sample input:
5 // number of lines
5 41 77 74 22 44 // x <> ; x- number of elements in that line, followed by the elements
1 12 
4 37 34 36 52
0
3 20 22 33
5 // number of queries
1 3 // x y; x- line number, y- element index within the line starting at 1
3 4
3 1
4 3
5 5

Sample output:
74
52
37
ERROR!
ERROR!

 */
public class ArrayListDynamic {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = Integer.valueOf(scan.nextLine());
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Array to maintain the lengths of individual lines
        int arr[] = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = scan.nextInt();
            for(int j=0;j<arr[i];j++) {
                list.add(scan.nextInt());
            }
        }
        int q = scan.nextInt();
        for(int i=0;i<q;i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            // Line x should have atleast 1 element and index y in line x should not exceed the number of elements in that line
            if(arr[x-1] == 0 || y>arr[x-1]) {
                System.out.println("ERROR!");
            } else {
                int index = 0;
                for(int j=0;j<x-1;j++) {
                    index += arr[j];
                }
                System.out.println(list.get(index + y -1));
            }
        }
        scan.close();
    }
}