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
	
	
	
	private void readGrid() {
		
	}
}
