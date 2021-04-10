import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_2_B();
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




class ALDS1_2_B implements OnlineJudge{
    public void execute(Scanner sc){
    	int max = next();
    	String[] array1 = new String[max];
    	for(int i = 0; i < max; i++) {
    		array1[i] = nexts();
    	}
    	String[] array2 = array1.clone();
    	bubbleSort(array1, 1);
    	selectionSort(array2, 1);
    	outary(array1);
    	System.out.println();
    	System.out.println("Stable");
    	outary(array2);
    	System.out.println();
    	System.out.println(equal(array1, array2)?"Stable":"Not stable");
    }

    public void bubbleSort(String[] array, int charat) {
    	int max = array.length;
    	int c, n;

    	for(int i = max - 1; 0 <= i; i--) {
    		for(int j = max - 1, limit = j - i; limit < j; j--) {
    			c = array[j].charAt(charat);
        		n = array[j - 1].charAt(charat);
        		if(c < n) {
        			String tmp = array[j];
        			array[j] = array[j-1];
        			array[j-1] = tmp;
        		}
    		}
    	}
    }

    public void selectionSort(String[] array, int charat) {
    	int max = array.length;
    	int minidx;

    	for(int i = 0; i < max; i++) {
    		minidx = i;
    		for(int j = i; j < max; j++) {
    			if(array[minidx].charAt(charat) > array[j].charAt(charat)) {
    				minidx = j;
    			}
    		}
    		if(minidx == i) {
    			continue;
    		}
    		String t = array[minidx];
    		array[minidx] = array[i];
    		array[i] = t;
    	}
    }

    public void outary(Object[] array) {
       	if(array.length == 0) {
    		System.out.println();
    	}
    	System.out.print(array[0]);
    	for(int i = 1,max=array.length; i < max; i++) {
    		System.out.print(" ");
    		System.out.print(array[i]);
		}
    }

    public boolean equal(Object[] bubble, Object[] selection) {
    	for(int i = 0, max = bubble.length; i < max; i++) {
    		if(!bubble[i].equals(selection[i])) {
    			return false;
    		}
    	}
    	return true;
    }
}

