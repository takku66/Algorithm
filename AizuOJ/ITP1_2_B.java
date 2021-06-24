import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ITP1_2_B();
        pgm.execute();
    }
}

interface OnlineJudge {
    public void execute();
}

class ITP1_2_B implements OnlineJudge{
    public void execute(){
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        int c = Integer.parseInt(sc.next());
        sc.close();
        
        if(a < b){
            if(b < c){
                System.out.println("Yes");
                return;
            }
        }
        
        System.out.println("No");
        return;
    }
}