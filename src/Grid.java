import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Grid {
	private int[][] cookies;
	
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
		if(!(col == 0 && row == 0) ) {
			if(canGoUp(col, row) && canGoLeft(col, row)) {	//If two paths
				int left = optimalPath(col-1, row);	//Run through left
				int up = optimalPath(col, row-1);	//Run through up
				
				if(left > up)	//Compare which had most cookies
					return cookies[col][row] + left;
				else 
					return cookies[col][row] + up;
			} else if (canGoUp(col, row))	//Can only go up
				return cookies[col][row] + optimalPath(col, row-1) ;
			
			else if (canGoLeft(col, row)) 	//Can only go left
				return cookies[col][row] + optimalPath(col-1, row);
			
			else { 	//Can't go anywhere
				//System.out.println(col + " " + row); 
				return cookies[col][row];
			}
		} else { System.out.println("Finished!"); return cookies[col][row];  }
	}	
		
	//Takes in current pos, and checks an adjacent position
	private boolean canGoLeft(int c, int r) {
		return ((r > 0 && c > 0) && (cookies[c-1][r]) >= 0 );	 
	}
	
	private boolean canGoUp(int c, int r) {
		return ((r > 0 && c > 0) && (cookies[c][r-1]) >= 0 );
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < cookies.length; i ++) { 
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
}

