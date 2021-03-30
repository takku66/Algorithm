import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int bef = sc.nextInt();
        int now = 0;
        int profit = now - bef;
        for(int i = 1; i < total; i++){
            int tmp = sc.nextInt();
            now = tmp;
            if(now - bef > profit){
                profit = now - bef;
            }
            if(bef > now){
                bef = now;
            }
        }
        sc.close();
        System.out.println(profit);
    }
}