import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Grid {
	private int[][] cookies;
	int r;
	int c;
	
	//Constructor
	public Grid(String fileName) {
		Scanner in = readFile(fileName);	//Creates a Scanner from the supplied String of the filename
		cookies = createGrid(in);	//Populates the grid with the info from the file
		c = cookies.length-1;
		r = cookies[0].length-1;
		//System.out.println("max cookies: " + optimalPath(cookies.length-1, cookies[0].length-1));
	}

	//Patrick's fileReader 
	public Scanner readFile(String s) {
		File f = new File(s);
		Scanner result = null;

		try {
			result = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(0);
		}

		return result;
	}
	
	//Patrick's grid constructor, bc mine doesn't work :'(
	/**
	 * This takes in a scanner and then converts into an int[][]
	 * 
	 * @param fileGrid
	 *            the scanner containing the grid
	 * @return a 2D array of ints that represents the maze
	 */
	public int[][] createGrid(Scanner fileGrid) {
		String line;

		ArrayList<int[]> temp = new ArrayList<int[]>();
		while (fileGrid.hasNextLine()) {

			line = fileGrid.nextLine();
			// String[] unparsed = line.split(" ");
			ArrayList<String> unparsed = new ArrayList<String>(Arrays.asList(line.split(" ")));
			for (int i = unparsed.size() - 1; i >= 0; i--) {
				if (unparsed.get(i).equals(""))
					unparsed.remove(i);
			}

			int[] row = new int[unparsed.size()];

			for (int i = 0; i < unparsed.size(); i++) {
				// System.out.println(unparsed.get(i));
				row[i] = Integer.parseInt(unparsed.get(i));
			}

			temp.add(row);
		}
		int[][] ret = new int[temp.size()][temp.get(0).length];
		for (int i = 0; i < temp.size(); i++)
			ret[i] = temp.get(i);
		return ret;
	}
	
	public int optimalPath(int col, int row) {
		if(col == 0 && row == 0) 
			return cookies[col][row];	//arrived at origin
		
		if(col > 0 && row > 0) {
			if(cookies[col][row-1] > -1 && cookies[col-1][row] < 0 ) 	//if top is valid, Left is not
				return optimalPath(col, row-1);	//go up
			
			if(cookies[col-1][row] > -1 && cookies[col][row-1] < 0) 	//if left is valid, Top is not
				return optimalPath(col-1, row);	//go left
			 
			if(cookies[col-1][row] > -1 && cookies[col][row-1] > -1)  //if both valid
				if( optimalPath(col-1, row) > optimalPath(row-1,col) )	//see which is better (suuuuper recursive)
					return optimalPath(col-1, row);
				else 
					return optimalPath(col, row-1);
		} else if (col > 0 && row < 0 ) {	//current pos at top of screen
			return optimalPath(col-1, row);	//go left
		} else if (row > 0 && col <0 ) {	//current pos at left of screen
			return optimalPath(col, row-1);	//go up
		}
		
		//if(cookies[col-1][row] < 0 && cookies[col][row-1] < 0)  //no valid moves
		return cookies[col][row];		//return cookies in current cell
	}	
	
	
	//toString to confirm the Grid was created properly
	public String toString() {
		String result = "";
		for (int i = 0; i < cookies.length; i++) {
		    for (int j = 0; j < cookies[i].length; j++) {
		        result += cookies[i][j] + " ";
		    }
		    result += "\n";
		}
		return result;
	}
}
