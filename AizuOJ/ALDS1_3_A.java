import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_3_A();
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
    /**
     * nextIntよりもnextからのparseの方が早いらしいので
     * @return
     */
    public default int next() {
    	return Integer.parseInt(sc.next());
    }
    public default String nexts() {
    	return sc.next();
    }
}




class ALDS1_3_A implements OnlineJudge{
    public void execute(Scanner sc){
    	int[] stack = new int[100];
    	int last = 0;
    	while(sc.hasNext()) {
    		String s = nexts();
    		switch(s) {
    		case "+":
    			stack[last-2] = stack[last-2] + stack[last - 1];
    			last--;
    			break;
    		case "-":
    			stack[last-2] = stack[last-2] - stack[last - 1];
    			last--;
    			break;
    		case "*":
    			stack[last-2] = stack[last-2] * stack[last - 1];
    			last--;
    			break;
    		default:
    			stack[last++] = Integer.parseInt(s);
    			break;
    		}
    	}
    	System.out.println(stack[0]);

    }

}

