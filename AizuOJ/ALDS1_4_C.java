import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_4_C();
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

class ALDS1_4_C implements OnlineJudge{

    public void execute(Scanner sc){
    	int n = nexti();
    	Set<String> set = new HashSet<String>();
    	for(int i = 0; i < n; i++) {
    		String c = nexts();
    		if(c.charAt(0) == 'i') {
    			set.add(nexts());
    		}else {
    			if(set.contains(nexts())) {
    				System.out.println("yes");
    			}else {
    				System.out.println("no");
    			}
    		}
    	}

    }
}