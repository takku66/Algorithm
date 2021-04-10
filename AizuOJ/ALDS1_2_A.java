import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_2_A();
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
}




class ALDS1_2_A implements OnlineJudge{
    public void execute(Scanner sc){
    	int max = next();
    	int[] array = new int[max];
    	for(int i = 0; i < max; i++) {
    		array[i] = next();
    	}

    	int c, n;
    	int cnt = 0;

    	for(int i = max - 1; 0 <= i; i--) {
    		for(int j = max - 1, limit = j - i; limit < j; j--) {
    			c = array[j];
        		n = array[j - 1];
        		if(c < n) {
        			array[j] = n;
        			array[j-1] = c;
        			cnt++;
        		}
    		}
    	}
    	if(array.length == 0) {
    		System.out.println();
    		System.out.println(cnt);
    	}
    	System.out.print(array[0]);
    	for(int i = 1; i < max; i++) {
    		System.out.print(" ");
    		System.out.print(array[i]);
		}
    	System.out.println();
    	System.out.println(cnt);
    }
}
