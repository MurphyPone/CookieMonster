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
		if(col == 0 && row == 0) 
			return cookies[col][row];	//arrived at origin
		
		if(cookies[col][row-1] > -1 && cookies[col-1][row] < 0 ) 	//if top is valid, Left is not
			return optimalPath(col, row-1);	//go up
		
		if(cookies[col-1][row] > -1 && cookies[col][row-1] < 0) 	//if left is valid, Top is not
			return optimalPath(col-1, row);	//go left
		 
		if(cookies[col-1][row] > -1 && cookies[col][row-1] > -1)  //if both valid
			if( optimalPath(col-1, row) > optimalPath(row-1,col) )	//see which is better (suuuuper recursive)
				return optimalPath(col-1, row);
			else 
				return optimalPath(col, row-1);
		
		//if(cookies[col-1][row] < 0 && cookies[col][row-1] < 0)  //no valid moves
		return cookies[col][row];		//return cookies in current cell
	}	
}
