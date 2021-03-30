import java.util.Scanner;
import java.lang.Math;

public class Main{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int count = 0;
        for(int i = 0; i < total; i++){
            if(isPrimeNum(sc.nextInt())){
                count++;
            }
        }
        sc.close();
        System.out.println(count);
    }
    
    private static boolean isPrimeNum(int x){
        
        if(x == 2)return true;
        if(x < 2)return false;
        if(x % 2 == 0) return false;
        
        double sqrtnum = Math.sqrt(x);
        for(int i = 3; i <= sqrtnum; i+=2){
            if(x % i == 0){
                return false;
            }
        }
        return true;
    }
    
}