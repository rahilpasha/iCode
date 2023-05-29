import java.util.ArrayList;

public class Variable {
	
	private static final ArrayList<Variable> variables = new ArrayList<>();
	private final String name;
	private String svalue;
	private int ivalue;
	
	// Create constructors for String and int variables
	public Variable(String name, String svalue) {
		this.name = name;
		this.svalue = svalue;
		
		// Check if there is already a variable with the same name and delete that one
		try {
			variables.remove(find(name));
		} catch (Exception ignored) {
		
		}
	}
	
	public Variable(String name, int ivalue) {
		this.name = name;
		this.ivalue = ivalue;
		
		// Check if there is already a variable with the same name and delete that one
		try {
			variables.remove(find(name));
		} catch (Exception ignored) {
		
		}
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public String getSvalue() {
		return svalue;
	}
	
	public int getIvalue() {
		return ivalue;
	}
	
	public static void addVariable(Variable var) {
		variables.add(var);
	}
	
	public static Variable find(String name) {
		
		// Find the variable by its name
		for (Variable var : variables) {
			if (var.getName().equals(name)) {
				return var;
			}
		}
		throw new IllegalArgumentException("Variable could not be found");
		
	}
	
}
