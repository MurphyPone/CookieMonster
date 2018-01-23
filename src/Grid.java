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
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	//Constructor Helper
	private void helper() {
		while(in.hasNextLine()) {
			
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
	 * 		}
	 * 		
	 * 
	 */
	
	
}
