import java.util.Scanner;
import java.util.Stack;
import java.io.FileReader;
import java.io.IOException;

public class Grid {
	private int[][] cookies;	//Contents of the grid
	private Stack<Integer> pathX;	//Stores the monsters x coords
	private Stack<Integer> pathY;	//Stores the monsters Y coords

	//Constructor with supplied file
	public Grid(String fileName, int cols, int rows) {
		cookies = new int[cols][rows];	//Initialize structures
		pathX = new Stack<Integer>();
		pathY = new Stack<Integer>();

		Scanner inputFile = null;

		try	{
			inputFile = new Scanner(new FileReader(fileName));
		} catch (IOException ex) {
			System.out.println("*** Cannot open " + fileName + " ***");
			System.exit(1);
		}

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				cookies[c][r] = inputFile.nextInt();
			}
			inputFile.nextLine();
		}
		inputFile.close();
	}	
	
	//Constructor for random mazes of a set size 
	public Grid(int size) {
		cookies = new int[size][size];
		pathX = new Stack<Integer>();
		pathY = new Stack<Integer>();
		
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				cookies[c][r] = (int) (Math.random() * 10) - 1;	//-1 to add the occasional barrel
			}
		}
	}	
	
	//Pathfinding methods
	public int optimalPath(int col, int row) {
		if(cookies[col][row] == -1) 	//Invalid maze
			return -9999;
		
		if(canGoUp(col, row) && canGoLeft(col, row)) {	//If two paths
			int left = optimalPath(col-1, row);	//Run through left
			int up = optimalPath(col, row-1);	//Run through up
			
			if(left > up) {	//Compare which had most cookies
				savePath(col, row);
				return cookies[col][row] + left;
			} else {  
				savePath(col, row);
				return cookies[col][row] + up;
			}
		} else if (canGoUp(col, row)) {	//Can only go up
			savePath(col, row);
			return cookies[col][row] + optimalPath(col, row-1) ;
		} else if (canGoLeft(col, row)) { 	//Can only go left
			savePath(col, row);
			return cookies[col][row] + optimalPath(col-1, row);
		} else { 	//Can't go anywhere
			savePath(col, row);
			return cookies[col][row];
		}
	}	
		
	//Takes in current pos, and checks an adjacent position
	private boolean canGoLeft(int c, int r) {
		return ((r >= 0 && c > 0) && (cookies[c-1][r]) >= 0 );	 
	}
	
	private boolean canGoUp(int c, int r) {
		return ((r > 0 && c >= 0) && (cookies[c][r-1]) >= 0 );
	}
	
	//Stack helper method
	private void savePath(int col, int row) {
		pathX.push(col);
		pathY.push(row);
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < cookies.length; i++) { 
			for(int j = 0; j < cookies[0].length; j++) {
				if(cookies[j][i] == -1)
					result += "X  ";
				else 
					result += cookies[j][i] + "  ";
			}
			result += "\n";
		}
		
		return result;
	}
	
	public String printCoords() {
		String result = "\nFormatted for X,Y coordinate pairs";
		while(!pathX.isEmpty())
			result += Integer.toString(pathX.pop()+1) + ", " + Integer.toString(pathY.pop()+1) + "\n";
		return result;
	}
	
	public String printPath() {
		String result = "";
		for(int i = 0; i < cookies.length; i++) { 	//for each r
			for(int j = 0; j < cookies[0].length; j++) {	//for each c
			
				if(i == pathY.peek() && j == pathX.peek()) { //If current point in traversal equals the next set of points in the stack
					result += "P  ";
					pathY.pop();
					pathX.pop();
				} else 
					result += "X  ";
			}
			result += "\n";
		}
		return result;
	}
	
}

