import java.util.Arrays;
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
    	int[] s = new int[n+1];
    	for(int i = 0; i < n; i++) {
    		s[i] =  nexti();
    	}
    	int q = nexti();
    	int dup = 0;
    	while(q > 0) {
    		s[n] = nexti();
    		dup += linearSrch(s, s[n]) == -1 ? 0 : 1;
    		q--;
    	}
    	System.out.println(dup);

    }
    public int linearSrch(int[] ary, int target) {
    	int i = 0;
    	while(ary[i] != target) {
    		i++;
    	}
    	if(i >= ary.length-1) {
    		return -1;
    	}else {
    		return i;
    	}
    }

}

