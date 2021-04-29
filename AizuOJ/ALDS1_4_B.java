import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_4_B();
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

class ALDS1_4_B implements OnlineJudge{

    public void execute(Scanner sc){
    	int n = nexti();
    	int[] s = new int[n];
    	for(int i = 0; i < n; i++) {
    		s[i] =  nexti();
    	}
    	int q = nexti();
    	int p = n/2;
    	int dup = 0;
    	for(int i = 0; i < q; i++) {

    		int t = nexti();
    		if(s[p] < t) {

    		}else {

    		}


    		for(int chkn : s) {
    			if(chkn == t) {
    				dup++;
    				break;
    			}
    		}
    	}
    	System.out.println(dup);
    }

    public boolean binarySrch(int[] ary, int target) {
    	short p = (short)Math.floor(ary.length/2);
    	if(p == 1 && ary[0] != target) {
    		return false;
    	}
    	if(ary[p] == target) {
    		// 中央と一致
    		return true;
    	}else if(ary[p] < target){
    		// 中央より右にある
    		if(binarySrch(Arrays.copyOfRange(ary, p, ary.length), target)) {
    			return true;
    		}
    	}else {
    		// 中央より左にある
    		if(binarySrch(Arrays.copyOfRange(ary, 0, p-1), target)) {
    			return true;
    		}
    	}
    	return false;
    }

}