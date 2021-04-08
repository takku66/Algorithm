import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        OnlineJudge pgm = new ITP2_1_C();
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

class MyList {
	private Node[] array;
	private int cursor;
	private int fst;
	private int end;
	private int n;
	private int read;

	public boolean hasNext() {
		return read == -1 || array[array[read].next].next != -1;
	}

	public int getNum() {
		if(read == -1) {
			read = fst;
			return array[fst].num;
		}else {
			read = array[read].next;
			return array[read].num;
		}
	}

	class Node{
		private int num;
		private int idx;
		private int next;
		private int pre;
		public Node() {
		}
		public String toString() {
			return Integer.toString(idx) +":"+
						Integer.toString(num) +":"+
						Integer.toString(next) +"/"+
						Integer.toString(pre);
		}
	}

	public MyList(int max) {
		this.array = new Node[max+1];
		this.end = max;
		Node endNode = new Node();
		endNode.num = 0;
		endNode.idx = max;
		endNode.next = -1;
		endNode.pre = -1;
		this.array[end] = endNode;
		this.cursor = end;
		this.fst = cursor;
		this.n = end - 1;
		this.read = -1;
	}

	public void insert(int e) {
		Node node = new Node();
		array[n] = node;
		Node curnode = array[this.cursor];
		// 追加対象のノードを設定
		node.num = e;
		node.idx = n;
		node.next = curnode.idx;

		// 割り込みでinsertしたかどうかチェック
		if(curnode.pre != -1) {
			// 割り込みしていた場合は、前のノード情報を書き換える
			Node prenode = array[curnode.pre];
			prenode.next = node.idx;
			node.pre = prenode.idx;
		}else {
			// 割り込みじゃなかった場合は、最初の要素をマークする
			node.pre = -1;
			this.fst = node.idx;
		}

		// カーソルにあったノード情報を書き換える
		curnode.pre = node.idx;
		// カーソルを移動
		this.cursor = node.idx;
		// 次に追加する場所を設定する
		this.n--;
	}
	public void move(int n) {
		int idx;
		if(n >= 0) {
			idx = array[this.cursor].next;
			for(int i = 0; i < n-1; i++) {
				idx = array[idx].next;
			}
		}else {
			idx = array[this.cursor].pre;
			for(int i = 0; i < n*-1-1; i++) {
				idx = array[idx].pre;
			}
		}
		this.cursor = idx == -1 ? this.end : idx;
	}
	public void erase() {
		Node curnode = array[this.cursor];
		// 消す前に、前後のインデックスを保持
		int nxtidx = curnode.next;
		int preidx = curnode.pre;

		Node nxtnode = array[nxtidx];
		nxtnode.pre = preidx;

		if(preidx != -1) {
			Node prenode = array[preidx];
			prenode.next = nxtidx;
		}
		if(this.cursor == this.fst) {
			this.fst = nxtidx;
		}
		array[this.cursor] = null;
		this.cursor = nxtidx;
	}
}

class ITP2_1_C implements OnlineJudge{
    public void execute(Scanner sc){
    	int max = next();
    	MyList list = new MyList(max);
    	for(int i = 0; i < max; i++) {
    		int type = next();
    		switch(type) {
    		case 0:
    			list.insert(next());
    			break;
    		case 1:
    			list.move(next());
    			break;
    		case 2:
    			list.erase();
    			break;
    		}
    	}
    	while(list.hasNext()) {
    		System.out.println(list.getNum());
    	}
    }
}
