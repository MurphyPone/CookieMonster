import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class CookieMonsterTester {

	public static void main(String[] args) {
		Grid maze = new Grid("cookies.txt", 12, 12);
		System.out.print(maze);
		
		System.out.println("\n The max # of cookies in this file is: " + maze.optimalPath(11, 11) );
	}
}
