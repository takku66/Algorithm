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
        
        sc.close();
        
        
    }
}