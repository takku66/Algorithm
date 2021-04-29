import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_4_A();
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
    public default int nexti() {
    	return Integer.parseInt(sc.next());
    }
    public default String nexts() {
    	return sc.next();
    }
}

class ALDS1_4_A implements OnlineJudge{

    public void execute(Scanner sc){
    	int n = nexti();
    	int[] s = new int[n];
    	for(int i = 0; i < n; i++) {
    		s[i] =  nexti();
    	}
    	int q = nexti();
    	int dup = 0;
    	for(int i = 0; i < q; i++) {
    		int t = nexti();
    		for(int chkn : s) {
    			if(chkn == t) {
    				dup++;
    				break;
    			}
    		}
    	}
    	System.out.println(dup);
    }

}