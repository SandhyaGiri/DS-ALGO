package algorithms.dp;

class Pair {
    public int a;
    public int b;
    Pair(int a,int b) {
        this.a=a;
        this.b=b;
    }
    public String toString() {
        return a+ " " + b;
    }
}

public class LongestChainOfPairs {
    private static int lcp(Pair[] arr, int n) {
        int[] ls = new int[n];
        int[] pi = new int[n];
        int maxLengthIndex = -1;
        for(int i=0;i<n;i++) {
            int maxLengthSoFar = 0;
            int p = -1;
            for(int j=i;j>=0;j--) {
                if(arr[j].b < arr[i].a && ls[j] > maxLengthSoFar) {
                    maxLengthSoFar = ls[j];
                    p = j;
                }
            }
            ls[i] = 1 + maxLengthSoFar;
            if(maxLengthIndex == -1 || ls[i] > ls[maxLengthIndex]) {
                maxLengthIndex = i;
            }
            if(p != -1) {
                pi[i] = p;
            }
        }
        int index = maxLengthIndex;
        while(index >=0) {
            System.out.println(arr[index]);
            if(ls[index] == 1){ // starting pair
                break;
            }
            index = pi[index];
        }
        return ls[maxLengthIndex];
    }
    public static void main(String args[]) 
    { 
        Pair arr[] = new Pair[] {new Pair(5,24), new Pair(15, 25), 
            new Pair (27, 40), new Pair(50, 60)}; 
        int n = arr.length; 
        System.out.println("Length of lis is "
                           + lcp(arr, n) + "\n"); 
    } 
}