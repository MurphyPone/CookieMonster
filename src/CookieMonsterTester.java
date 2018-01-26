public class CookieMonsterTester {

	public static void main(String[] args) {
		//Supplied maze 
		Grid maze = new Grid("cookies.txt", 12, 12);
		System.out.print(maze);
		System.out.println("\n The max # of cookies in this file is: " + maze.optimalPath(11, 11) );
		
		//Small maze
		maze = new Grid("cookiesSmall.txt", 4, 4);
		System.out.print(maze);
		System.out.println("\n The max # of cookies in this file is: " + maze.optimalPath(3, 3) );
		
		//random maze 
		int size = 3;
		maze = new Grid(size);
		System.out.print(maze);
		System.out.println("\n The max # of cookies in this file is: " + maze.optimalPath(size-1, size-1) );
		System.out.print(maze.printCoords());
	}
}
