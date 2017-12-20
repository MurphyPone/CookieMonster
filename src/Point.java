public class Point {
	private int x, y;	//coords on the cookie grid
	private int val;	//number of cookies so that 
	
	public Point(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getVal() {
		return val;
	}
}
