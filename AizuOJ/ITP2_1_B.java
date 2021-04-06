import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ITP2_1_B();
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

class MyDeque {
	private int[] array;
	private int head;
	private int last;

	public MyDeque(int max) {
		this.array = new int[max*2];
		this.head = max;
		this.last = max-1;
	}

	public void addFirst(int e) {
		--this.head;
		array[this.head] = e;
	}
	public void addLast(int e) {
		++this.last;
		array[this.last] = e;
	}
	public void removeFirst() {
		++this.head;
	}
	public void removeLast() {
		--this.last;
	}
	public int get(int idx) {
		return this.array[this.head + idx];
	}

}

class ITP2_1_B implements OnlineJudge{
    public void execute(Scanner sc){
    	int max = next();
    	MyDeque deque = new MyDeque(max);
    	for(int i = 0; i < max; i++) {
    		int code = next();
    		switch(code) {
    		case 0:
    			push(deque, next(), next());
    			break;
    		case 1:
    			System.out.println(deque.get(next()));
    			break;
    		case 2:
    			remove(deque, next());
    			break;
    		}
    	}
    }
    private void push(MyDeque dq,int code,  int num) {
    	switch(code) {
    	case 0:
    		dq.addFirst(num);
    		break;
    	case 1:
    		dq.addLast(num);
    		break;
		default:
			break;
    	}
    }
    private void remove(MyDeque dq,int code) {
    	switch(code) {
    	case 0:
    		dq.removeFirst();
    		break;
    	case 1:
    		dq.removeLast();
    		break;
		default:
			break;
    	}
    }
}
