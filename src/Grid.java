import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Grid {
	private int[][] cookies;
	//private int total;
	
	//Constructor
	public Grid(String fileName, int cols, int rows) {
		cookies = new int[cols][rows];
		//total = 0;
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
	
	//Pathfinding methods
	public int optimalPath(int col, int row) {
		//System.out.println(col + ", " + row);
		if(atOrigin(col, row) ) 
			return cookies[col][row];	//arrived at origin
		
		if(canGoLeft(col, row) && canGoUp(col,row)) { //if both valid
			int left = optimalPath(col-1, row);	//Save left in var
			int up = optimalPath(row-1,col);	//save right in var
			
			if( left > up)	//see which is better (suuuuper recursive)
				return cookies[col][row] + left;	//Choose the one that has the greater # cookies
			else 
				return cookies[col][row] + up;
		}
		if(!canGoLeft(col, row) && canGoUp(col, row)) 	//if top is valid, but Left is not
			return cookies[col][row] + optimalPath(col, row-1);	//go up
		
		if(canGoLeft(col, row) && !canGoUp(col, row)) 	//if left is valid, but Top is not
			return cookies[col][row] + optimalPath(col-1, row);	//go left
		 
		//if reaches end 
		return cookies[col][row];
		
		/* if(!canGoLeft(col, row) && !canGoUp(col, row))	//No valid moves
		/ 	return total + cookies[col][row];		//return cookies in current cell
		*/
	}	
	
	//Directional Helper Methods
	
	//Takes in current pos, and checks an adjacent position
	private boolean canGoLeft(int c, int r) {
		return ((r > 0 && c > 0) && (cookies[c-1][r]) >= 0 );	//This should work because of short circuiting....
	}
	
	private boolean canGoUp(int c, int r) {
		return ((r > 0 && c > 0) && (cookies[c][r-1]) >= 0 );
	}
	
	private boolean atOrigin(int c, int r) {
		return (c == 0 && r == 0);
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < cookies.length; i ++) { 
			for(int j = 0; j < cookies[0].length; j++) {
				result += cookies[j][i] + " ";
			}
			result += "\n";
		}
		
		return result;
	}
}

