import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {
	private int[][] cookies;
	Scanner in;
	
	public Grid(String arg) {
		try {
			File file = new File(arg);
			in = new Scanner(file);
			helper();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Constructor Helper
	private void helper() {
		int row = 0;
		int col = 0;
		
		while(in.hasNextLine() ) { 
			while(in.hasNext() ) {
				cookies[col][row] = Integer.parseInt(in.next());
				col++;
			}
			row++;
		}
	}
	
	public int optimalPath(int col, int row) {
		if(atOrigin(col, row)) 
			return cookies[col][row];	//arrived at origin
		
		if(!canGoLeft(col, row) && canGoUp(col, row)) 	//if top is valid, Left is not
			return optimalPath(col, row-1);	//go up
		
		if(canGoLeft(col, row) && !canGoUp(col, row)) 	//if left is valid, Top is not
			return optimalPath(col-1, row);	//go left
		 
		if(canGoLeft(col, row) && canGoUp(col,row))  //if both valid
			if( optimalPath(col-1, row) > optimalPath(row-1,col) )	//see which is better (suuuuper recursive)
				return optimalPath(col-1, row);
			else 
				return optimalPath(col, row-1);
		
		return cookies[col][row];		//return cookies in current cell
	}	
	
	
	//Directional Helper Methods
	private boolean canGoLeft(int c, int r) {
		return ((r >= 0 && c >= 0) && (cookies[c-1][r]) >= 0 );
	}
	
	private boolean canGoUp(int c, int r) {
		return ((r >= 0 && c >= 0) && (cookies[c][r-1]) >= 0 );
	}
	
	private boolean atOrigin(int c, int r) {
		return (c == 0 && r == 0);
	}
}

