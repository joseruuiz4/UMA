package primos;

public class Primos {
	private int pr1;
	private int pr2;
	private int pos;
	

	public Primos(int pr1, int pr2, int pos) {
		this.pr1 = pr1;
		this.pr2 = pr2;
		this.pos = pos;
	}


	public int getPr1() {
		return pr1;
	}


	public int getPr2() {
		return pr2;
	}


	public int getPos() {
		return pos;
	}


	public String toString() {
		return pos + ":(" + pr1 + "," + pr2 + ")";
	}
	
	
	
}
