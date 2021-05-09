import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main{
	public static void main(String[] args){
		OnlineJudge pgm = new ALDS1_4_D();
		pgm.execute();
	}
}

interface OnlineJudge {
	final MyScanner sc = new MyScanner(System.in);
	public default void execute() {
		execute(sc);
		sc.close();
	}
	abstract void execute(MyScanner sc);
	/**
	 * nextIntよりもnextからのparseの方が早いらしいので
	 * @return
	 */
	public default int nexti() {
		return sc.nextInt();
	}
	public default String nexts() {
		return sc.next();
	}
}

class ALDS1_4_D implements OnlineJudge{

	public void execute(MyScanner sc){
    	int max = nexti();
    	MyList list = new MyList(max);
    	for(int i = 0; i < max; i++) {
    		switch(nexts()) {
    		case "insert":
    			list.insertFirst(nexti());
    			break;
    		case "delete":
    			list.delete(nexti());
    			break;
    		case "deleteFirst":
    			list.deleteFirst();
    			break;
    		case "deleteLast":
    			list.deleteLast();
    			break;
    		}
    	}
    	System.out.print(list.getNum());
    	while(list.hasNext()) {
    		System.out.print(" ");
    		System.out.print(list.getNum());
    	}
    	System.out.println();
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
	public void moveFirst() {
		this.cursor = this.fst;
	}
	public void moveLast() {
		this.cursor = array[this.end].pre;
	}
	public void deleteFirst() {
		moveFirst();
		erase();
	}
	public void deleteLast() {
		moveLast();
		erase();
	}
	public void delete(int key) {
		this.cursor = searchKey(key);
		if(this.cursor == -1) {
			return;
		}
		erase();
	}
	public void insertFirst(int e) {
		moveFirst();
		insert(e);
	}
	public int searchKey(int key) {
		for(int i = this.n, size = array.length; i < size; i++) {
			if(array[i] != null && array[i].num == key) {
				return array[i].idx;
			}
		}
		return -1;
	}
}



class MyScanner {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	public MyScanner(InputStream stream) {
		this.stream = stream;
	}
	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		}else{
			ptr = 0;
			try {
				buflen = stream.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}
	private int readByte() {
		if (hasNextByte()) return buf[ptr++]; else return -1;
	}
	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}
	public boolean hasNext() {
		while(hasNextByte() && !isPrintableChar(buf[ptr])) ptr++;
		return hasNextByte();
	}
	public String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while(isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	public long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while(true){
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			}else if(b == -1 || !isPrintableChar(b)){
				return minus ? -n : n;
			}else{
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}
	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
		return (int) nl;
	}
	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public void close() {
		try{
			stream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


