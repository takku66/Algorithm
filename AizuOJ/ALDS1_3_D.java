import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_3_D();
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




class ALDS1_3_D implements OnlineJudge{

    public void execute(Scanner sc){
    	AreasCrossSection area = new AreasCrossSection();
    	String[] str = nexts().split("");
    	for(int i = 0, ilen=str.length; i < ilen; i++) {
    		if(AreasCrossSection.DOWN.equals(str[i])) {
    			area.store(i);
    		}else if(AreasCrossSection.UP.equals(str[i])) {
    			area.calc(i);
    		}else {
    			continue;
    		}
    	}
    	area.printResult();
    }

}

class AreasCrossSection {
	public final static String DOWN = "\\";
	public final static String FLAT = "_";
	public final static String UP = "/";
	MyStack idxStack;
	int[][] pdlStack;
	short pdlcnt = 0;
	int total = 0;
	public AreasCrossSection() {
		idxStack = new MyStack(20000);
		pdlStack = new int[5000][2];
	}
	public void store(int idx) {
		idxStack.push(idx);
	}
	public void calc(int ux) {
		int dx = idxStack.pop();
		if(dx == -1) {
			return;
		}
		int pdl = ux - dx;
		total += pdl;
		pdlStack[pdlcnt][0] = dx;
		pdlStack[pdlcnt][1] = ux - dx;
		pdlcnt++;
		for(int i = pdlcnt-2; 0 <= i; i--) {
			if(pdlStack[i][0] > dx) {
				pdlStack[i][0] = dx;
				pdlStack[i][1] += pdl;
				pdl = pdlStack[i][1];
				pdlcnt--;
			}else {
				break;
			}
		}
	}
	public void printResult() {
		System.out.println(total);
		System.out.print(pdlcnt);
		for(int i = 0; i < pdlcnt; i++) {
			System.out.print(" ");
			System.out.print(pdlStack[i][1]);
		}
		System.out.println();
	}
}


class MyStack {

	private int[] stack;
	private int tail = 0;

	public MyStack(int n) {
		stack = new int[n];
	}

	public void push(int e) {
		stack[tail++] = e;
	}

	@SuppressWarnings("unchecked")
	public int pop() {
		if(tail == 0) {
			return -1;
		}
		return stack[--tail];
	}
	public int size() {
		return tail;
	}
	public boolean isEmpty() {
		return tail == 0;
	}
}