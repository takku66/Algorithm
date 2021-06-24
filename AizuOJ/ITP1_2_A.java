import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ITP1_2_A();
        pgm.execute();
    }
}

interface OnlineJudge {
    public void execute();
}

class ITP1_2_A implements OnlineJudge{
    public void execute(){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();
        
        if(x == y){
            System.out.println("a == b");
        }else if(x < y){
            System.out.println("a < b");
        }else if(x > y){
            System.out.println("a > b");
        }
    }
}