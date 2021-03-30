import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();
        
        if(x == y){
            System.out.println(x);
            return;
        }
        int result = (x > y) ? gcd(x, y) : gcd(y, x);
        System.out.println(result);
    }
    
    private static int gcd(int x, int y){
        int r = x % y;
        return y % r == 0 ? r : gcd(y, r);
    }
}