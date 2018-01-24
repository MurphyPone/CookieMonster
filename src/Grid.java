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
	
	//--------Recursive Optimal Path
	/* optimalPath(row, col)
	 * 		if(stack isEmpty) {
	 * 			base case --> return total num 
	 * 		if(current point.x = col && currentpoint.y = row) then 
	 * 			compare the sum of cookies to the optimal pathnum
	 * 			if(currentSum > highscore) then high = current, 
	 * 			else 
	 * 			optimalPath(stack.pop(pathNotTaken) )
	 * 		If grid(right) and grid[down] are valid, then 
	 * 			move down
	 * 			push right to the stack
	 * 		else if right is valid
	 * 			move right 
	 * 		else if down valid 
	 * 			move down
	 * 		else no valid moves
	 * 			optimalPath(stack.pop()) 
	 * 		}x
	 * 		
	 * 
	 */
	
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
