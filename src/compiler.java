// Rahil Pasha
// Period 3

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class compiler {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Load in the code from the file using the Parser object
		Parser file;
		try {
			file = new Parser("/Users/rahilpasha/IdeaProjects/compiler/src/example.txt");
		} catch (Exception e) {
			throw new FileNotFoundException("File could not be found");
		}
		
		// Execute the code
		execute(file.getLines());
		
	}
	
	public static void execute(ArrayList<ArrayList<String>> file) {
		
		// Loop through each of the lines
		for (int line = 0; line < file.size(); line++) {
			
			// Check if it is a print statement
			if (file.get(line).get(0).equals("\uD83D\uDDA8")) {
				
				// Print out a string
				if (file.get(line).get(1).equals("\uD83D\uDD21")) {
					for (int i = 2; i < file.get(line).size(); i++) {
						System.out.print(file.get(line).get(i) + " ");
					}
					System.out.println();
					continue;
				}
				
				// Print out an integer
				if (file.get(line).get(1).equals("\uD83D\uDD22")) {
					
					// Get the three elements of the mathematical expression and evaluate it
					ArrayList<String> expression = new ArrayList<>();
					for (int i = 2; i < file.get(line).size(); i++) {
						expression.add(file.get(line).get(i));
					}
					System.out.println(evaluate(expression));
					continue;
				}
				
				// Print a variable
				try {
					if (file.get(line).size() == 2) {
						// Get the variable and print it out
						Variable var = Variable.find(file.get(line).get(1));
						if (var.getSvalue() != null) {
							System.out.println(var.getSvalue());
						} else {
							System.out.println(var.getIvalue());
						}
					} else {
						// Get both variables and the operator
						Variable var1 = Variable.find(file.get(line).get(1));
						Variable var2 = Variable.find(file.get(line).get(3));
						String operator = file.get(line).get(2);
						
						// If they are both ints then perform the operation
						if (var1.getSvalue() == null && var2.getSvalue() == null) {
							ArrayList<String> expression = new ArrayList<>();
							expression.add(String.valueOf(var1.getIvalue()));
							expression.add(operator);
							expression.add(String.valueOf(var2.getIvalue()));
							
							System.out.println(evaluate(expression));
						} else if (var1.getSvalue() != null && var2.getSvalue() != null) {
							
							if (!operator.equals("+")) {
								throw new IllegalArgumentException("Cannot perform" + operator + " operation on strings");
							}
							System.out.println(var1.getSvalue() + " " + var2.getSvalue());
							
						} else {
							throw new ArithmeticException("Cannot perform operations on strings and ints");
						}
					}
					
				} catch (Exception e) {
					throw new IllegalArgumentException("Invalid syntax");
				}
				
				
			} else if (file.get(line).get(0).equals("\uD83D\uDCE6")) { // Check if it is a variable assignment
				
				if (file.get(line).get(2).equals("\uD83D\uDD22")) {
					Variable var = new Variable(file.get(line).get(1), Integer.parseInt(file.get(line).get(3))); // create an integer
					Variable.addVariable(var);
				} else if (file.get(line).get(2).equals("\uD83D\uDD21")) {
					String value = "";
					for (int i = 3; i < file.get(line).size(); i++) {
						value += file.get(line).get(i) + " ";
					}
					Variable var = new Variable(file.get(line).get(1), value); // create a string
					Variable.addVariable(var);
				} else if (file.get(line).get(2).equals("⌨️")) {
					// Print the prompt
					for (int i = 3; i < file.get(line).size(); i++) {
						System.out.print(file.get(line).get(i) + " ");
					}
					// Store the input in a variable
					Scanner s = new Scanner(System.in);
					Variable var = new Variable(file.get(line).get(1), s.nextLine());
					Variable.addVariable(var);
				} else {
					throw new IllegalArgumentException("Variable type error");
				}
				
			} else if (file.get(line).get(0).equals("\uD83D\uDD01")) { // Check if it is a loop
				
				int repetitions = Integer.parseInt(file.get(line).get(1)); // Find the number of repetitions
				ArrayList<ArrayList<String>> subfile = new ArrayList<>(); // Initialize a subfile
				
				// Execute each of the lines in the loop until the end character is reached
				for (int i = line + 2; i < file.size(); i++) {
					if (file.get(i).get(0).equals("↩️")) {
						for (int j = 0; j < repetitions; j++) { // Execute the subfile
							execute(subfile);
						}
						line = i + 1; // advance to the next line
						break;
					} else {
						subfile.add(file.get(i)); // Add lines to the subfile
					}
				}
				
				
			} else if (file.get(line).get(0).equals("❓")) { // Check if it is a conditional
				
				ArrayList<String> equation = new ArrayList<>();
				for (int i = 1; i < file.get(line).size(); i++) {
					equation.add(file.get(line).get(i));
				}
				boolean condition = checkCondition(equation);
				
				ArrayList<ArrayList<String>> subfile = new ArrayList<>(); // Initialize a subfile
				// execute the subfile
				for (int i = line + 2; i < file.size(); i++) {
					if (file.get(i).get(0).equals("↩️")) {
						if (condition) {
							execute(subfile);
						}
						line = i + 1; // advance to the next line
						break;
					} else {
						subfile.add(file.get(i)); // Add lines to the subfile
					}
				}
				
			} else if (file.get(line).size() > 1 && !file.get(line).get(0).equals("\uD83D\uDCDD")) {
				throw new IllegalArgumentException("Syntax error"); // Throw an error if a line isn't commented out
			}
			
		}
		
	}
	
	public static int evaluate(ArrayList<String> expression) throws IllegalArgumentException {
		
		if (expression.size() == 1) {
			return Integer.parseInt(expression.get(0));
		}
		
		int a = Integer.parseInt(expression.get(0));
		int b = Integer.parseInt(expression.get(2));
		String operator = expression.get(1);
		
		if (operator.equals("+")) {
			return a + b;
		} else if (operator.equals("-")) {
			return a - b;
		} else if (operator.equals("*")) {
			return a * b;
		} else if (operator.equals("/")) {
			return a / b;
		} else if (operator.equals("%")) {
			return a % b;
		} else {
			throw new IllegalArgumentException("This operator does not exist");
		}
		
	}
	
	public static boolean checkCondition(ArrayList<String> equation) {
		
		// Check if there are any variables in the equation
		if (equation.size() == 5) {
			if (equation.get(0).equals("\uD83D\uDD22") && equation.get(3).equals("\uD83D\uDD22")) {
				return compareInt(Integer.parseInt(equation.get(1)), Integer.parseInt(equation.get(4)), equation.get(2));
			} else if (equation.get(0).equals("\uD83D\uDD21") && equation.get(3).equals("\uD83D\uDD21")) {
				if (!equation.get(2).equals("=")) {
					throw new ArithmeticException("Cannot use the " + equation.get(2) + " operator on strings");
				}
				return equation.get(1).equals(equation.get(3));
			} else {
				throw new ArithmeticException("Cannot compare ints and strings");
			}
		} else if (equation.size() == 4) {
			
			// Check if there is one variable in the equation
			if (equation.get(0).equals("\uD83D\uDD21") || equation.get(0).equals("\uD83D\uDD22")) {
				// The variable occurs on the right side
				String operator = equation.get(2);
				Variable var = Variable.find(equation.get(3));
				String str = null;
				int num = 0;
				
				if (equation.get(0).equals("\uD83D\uDD21")) {
					str = equation.get(1);
				} else {
					num = Integer.parseInt(equation.get(1));
				}
				
				// Now compare the two sides
				if (var.getSvalue() == null && str == null) {
					// Compare the two ints
					return compareInt(var.getIvalue(), num, operator);
				} else if (var.getSvalue() != null && str != null) {
					// Check if the strings are equal
					if (!operator.equals("+")) {
						throw new ArithmeticException("Cannot use the " + operator + " operator on strings");
					}
					return var.getSvalue().equals(str);
				} else {
					throw new ArithmeticException("Cannot compare ints and strings");
				}
				
				
			} else if (equation.get(2).equals("\uD83D\uDD21") || equation.get(2).equals("\uD83D\uDD22")) {
				// The variable occurs on the left side
				String operator = equation.get(1);
				Variable var = Variable.find(equation.get(0));
				String str = null;
				int num = 0;
				
				if (equation.get(2).equals("\uD83D\uDD21")) {
					str = equation.get(3);
				} else {
					num = Integer.parseInt(equation.get(3));
				}
				
				// Now compare the two sides
				if (var.getSvalue() == null && str == null) {
					// Compare the two ints
					return compareInt(num, var.getIvalue(), operator);
				} else if (var.getSvalue() != null && str != null) {
					// Check if the strings are equal
					if (!operator.equals("+")) {
						throw new ArithmeticException("Cannot use the " + operator + " operator on strings");
					}
					return var.getSvalue().equals(str);
				} else {
					throw new ArithmeticException("Cannot compare ints and strings");
				}
				
			} else {
				throw new IllegalArgumentException("Invalid conditional expression");
			}
			
			
		} else if (equation.size() == 3) {
			
			// Compare two variables
			Variable var1 = Variable.find(equation.get(0));
			Variable var2 = Variable.find(equation.get(2));
			String operator = equation.get(1);
			
			// Compare both variables if they are ints
			if (var1.getSvalue() == null && var2.getSvalue() == null) {
				return compareInt(var1.getIvalue(), var2.getIvalue(), operator);
			} else if (var1.getSvalue() != null && var2.getSvalue() != null) {
				// Check if both variables are strings
				if (!operator.equals("=")) {
					throw new ArithmeticException("Cannot use the " + operator + " operator on strings");
				}
				return var1.getSvalue().equals(var2.getSvalue());
			} else {
				throw new ArithmeticException("Cannot compare ints and strings");
			}
			
		} else {
			throw new IllegalArgumentException("Syntax Error");
		}
		
	}
	
	public static boolean compareInt(int a, int b, String operator) {
		
		if (operator.equals("=")) {
			return a == b;
		} else if (operator.equals(">")) {
			return a > b;
		} else if (operator.equals("<")) {
			return a < b;
		} else if (operator.equals(">=")) {
			return a >= b;
		} else if (operator.equals("<=")) {
			return a <= b;
		} else {
			throw new IllegalArgumentException("Comparator " + operator + " does not exist");
		}
		
	}
	
}
