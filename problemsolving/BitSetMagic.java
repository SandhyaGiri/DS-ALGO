package problemsolving;

import java.util.*;

public class BitSetMagic {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        BitSet[] bitsets = new BitSet[2];
        bitsets[0] = new BitSet(N);
        bitsets[1] = new BitSet(N);

        for(int j =0;j<M;j++) {
            String op = scan.next();
            int i1 = scan.nextInt();
            int i2 = scan.nextInt();
            if("AND".equals(op)) {
                bitsets[i1-1].and(bitsets[i2-1]);
            } else if("OR".equals(op)) {
                bitsets[i1-1].or(bitsets[i2-1]);
            } else if("XOR".equals(op)) {
                bitsets[i1-1].xor(bitsets[i2-1]);
            } else if("FLIP".equals(op)) {
                bitsets[i1-1].flip(i2);
            } else if("SET".equals(op)) {
                bitsets[i1-1].set(i2);
            }
            System.out.printf("%d %d\n", bitsets[0].cardinality(),bitsets[1].cardinality());
        }
        scan.close();
    }
}

