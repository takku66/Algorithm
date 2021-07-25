import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main{
	public static void main(String[] args){
		OnlineJudge pgm = new ALDS1_5_C();
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

class ALDS1_5_C implements OnlineJudge{

	public void execute(MyScanner sc){
		int n = nexti();
		double[] p1 = new double[2],
				p2 = new double[2];
		p1[0] = 0.0;
		p1[1] = 0.0;
		p2[0] = 100.0;
		p2[1] = 0.0;

		System.out.println(p1[0] + " " + p1[1]);
		koch(n, p1, p2);
		System.out.println(p2[0] + " " + p2[1]);
	}

	private void koch(int n, double[] p1, double[] p2) {
		if(n == 0) {
			return;
		}
		double[] s = new double[2],
				u = new double[2],
				t = new double[2];
		double rg = (double)(Math.PI * 60 /180);

		s[0] = (2.0 * p1[0] + 1.0 * p2[0])/3.0;
		s[1] = (2.0 * p1[1] + 1.0 * p2[1])/3.0;
		t[0] = (1.0 * p1[0] + 2.0 * p2[0])/3.0;
		t[1] = (1.0 * p1[1] + 2.0 * p2[1])/3.0;
		u[0] = (t[0] - s[0]) * Math.cos(rg) - (t[1] - s[1]) * Math.sin(rg) + s[0];
		u[1] = (t[0] - s[0]) * Math.sin(rg) + (t[1] - s[1]) * Math.cos(rg) + s[1];

		koch(n-1, p1, s);
		System.out.println(s[0] + " " + s[1]);
		koch(n-1, s, u);
		System.out.println(u[0] + " " + u[1]);
		koch(n-1, u, t);
		System.out.println(t[0] + " " + t[1]);
		koch(n-1, t, p2);
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
