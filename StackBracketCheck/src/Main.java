import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/* Create a tool that use a Stack to check if brackets given in an input are correct
If you have extra time. 
You can use a stack to calculate math statements using Reverse Polish Notation
https://mathworld.wolfram.com/ReversePolishNotation.html
Try and implement a simple calculator
*/

public class Main {
	
	// we need to have a hashmap I think of opening and matching closing brackets
	private HashMap<Character, Character> matchingBrackets = new HashMap<>();
	
	// sets containing all the opening and closing brackets we'll check for
	private Set<Character> openingBrackets = new HashSet<>();
	private Set<Character> closingBrackets = new HashSet<>();
	
	public Main() {
		// populate matching brackets map
		matchingBrackets.put(')', '(');
		matchingBrackets.put(']', '[');
		matchingBrackets.put('}', '{');
		
		// populate brackets set
		openingBrackets.add('(');
		closingBrackets.add(')');
		openingBrackets.add('[');
		closingBrackets.add(']');
		openingBrackets.add('{');
		closingBrackets.add('}');
		
		checkStrings();
	}
	
	public void checkStrings() {
//		String toCheck = "((8 - 4)^[83])/7";
		String toCheck = "{(((8 - 4)^[83])/7)}";
		System.out.println(checkBrackets(toCheck));
	}
	
	public boolean checkBrackets(String input) {
		boolean bracketsMatch = true;
		
		Stack<Character> stack = new Stack<Character>();
		
		for (int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			
			if (openingBrackets.contains(c)) {
				stack.add(c);
				printStack(stack);
				
			} else if (closingBrackets.contains(c)) {
				// if character is closing bracket, want to check if the top character in the stack is the matching opening bracket.
				
				if (stack.peek().equals(matchingBrackets.get(c))) {
					// if so, remove top character from stack and continue
					stack.pop();
					printStack(stack);
					
				} else {
					// if not the case, return false
					return false;
				}
			}
		}
		
		return bracketsMatch;
	}
	
	// print a stack to console, removing opening and closing [] from toString output
	public void printStack(Stack stack) {
		System.out.println("Stack: " + stack.toString().substring(1, stack.toString().length() - 1));
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
