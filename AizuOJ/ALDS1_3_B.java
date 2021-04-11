import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ALDS1_3_B();
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




class ALDS1_3_B implements OnlineJudge{

	class Prc {
		String name;
		int q;
		public Prc(String name, int q) {
			this.name  = name;
			this.q = q;
		}
	}

	class MyQue<T> {
		int h;
		int t;
		int size;
		Object[] array;
		public MyQue(int size) {
			this.h = 1;
			this.t = 1;
			this.size = size+1;
			this.array = new Object[size+1];
		}

		public  void enque(T elm) {
			array[t] = elm;
			t++;
			if(t == size) {
				t = 0;
			}
		}
		@SuppressWarnings("unchecked")
		public T deque() {
			Object elm = array[h];
			h++;
			if(h == size) {
				h = 0;
			}
			return (T)elm;
		}
		public boolean hasAnyElm() {
			return h != t;
		}
	}

    public void execute(Scanner sc){
    	int max = nexti();
    	MyQue<Prc> que = new MyQue<Prc>(max);
    	int q = nexti();
    	int sumq = 0;
    	for(int i = 0; i < max; i++) {
    		Prc p = new Prc(nexts(), nexti());
    		if(p.q <= q) {
    			sumq += p.q;
    			System.out.println(p.name + " " + sumq);
    		}else {
    			p.q -= q;
    			sumq += q;
    			que.enque(p);
    		}
    	}

    	while(que.hasAnyElm()) {
    		Prc p = que.deque();
    		if(p.q <= q) {
    			sumq += p.q;
    			System.out.println(p.name + " " + sumq);
    		}else {
    			p.q -= q;
    			sumq += q;
    			que.enque(p);
    		}
    	}

    }

}

