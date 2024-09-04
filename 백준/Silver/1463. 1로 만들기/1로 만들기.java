import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] D = new int[n+1];
        D[1] = 0;
        for(int i = 2; i <= n; i++){
            if (i % 6 == 0){
                D[i] = Math.min(D[i-1]+1, Math.min(D[i/2]+1, D[i/3]+1));
            } else if (i % 2 == 0){
                D[i] = Math.min(D[i-1]+1, D[i/2]+1);
                //System.out.println(i +": "+D[i]);
            } else if (i % 3 == 0){
                D[i] = Math.min(D[i-1]+1, D[i/3]+1);
                //System.out.println(i +": "+D[i]);
            } else {
                D[i] = D[i-1]+1;
                //System.out.println(i +": "+D[i]);
            }
        }
        System.out.println(D[n]);
    }
}
