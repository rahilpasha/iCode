import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parser {
	
	private final ArrayList<ArrayList<String>> lines;
	
	public Parser(String filename) throws FileNotFoundException {
		
		// Update the instance variable
		this.lines = new ArrayList<>();
		
		// Create a scanner getting input from the text file
		File code = new File(filename);
		Scanner s = new Scanner(code);
		
		// Add the input from the file into the 2D ArrayList
		while (s.hasNextLine()) {
			ArrayList<String> line = new ArrayList<>();
			Collections.addAll(line, s.nextLine().split(" "));
			lines.add(line);
		}
		
	}
	
	public ArrayList<ArrayList<String>> getLines() {
		return lines;
	}
	
}
