import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ITP2_1_A();
        pgm.execute();
    }
}

interface OnlineJudge {
	final Scanner sc = new Scanner(System.in);
    public default void execute() {
    	execute(sc);
    	sc.close();
    }
    abstract void execute(Scanner sc);
    public default int nexti() {
    	return Integer.parseInt(sc.next());
    }
    public default String nexts() {
    	return sc.next();
    }
}

class ITP2_1_A implements OnlineJudge{
    public void execute(Scanner sc){
        int max = next();
        int[] array = new int[max];
        int arycount = 0;
        for(int i = 0; i < max; i++) {
        	int code = next();

        	switch(code) {
        	case 0:
        		array[arycount] = next();
        		++arycount;
        		break;
        	case 1:
        		int idx = next();
        		if(idx > arycount) {
        			break;
        		}
        		System.out.println(array[idx]);
        		break;
        	case 2:
        		array[--arycount] = 0;
        		break;
        	default:
        		break;
        	}
        }
        sc.close();
    }
}
