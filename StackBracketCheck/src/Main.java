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
	
	// closing bracket key, opening bracket value
	private HashMap<Character, Character> matchingBrackets = new HashMap<>();
	
	// sets containing all the opening and closing brackets we'll check for
	private Set<Character> openingBrackets = new HashSet<>();
	private Set<Character> closingBrackets = new HashSet<>();
	private Set<Character> digitChars = new HashSet<>();
	private Set<Character> operators = new HashSet<>();
	
	private char[] digitsArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public Main() {
		// populate matching brackets map
		matchingBrackets.put(')', '(');
		matchingBrackets.put(']', '[');
		matchingBrackets.put('}', '{');
		matchingBrackets.put('>', '<');
		
		// populate brackets set
		openingBrackets.add('(');
		closingBrackets.add(')');
		openingBrackets.add('[');
		closingBrackets.add(']');
		openingBrackets.add('{');
		closingBrackets.add('}');
		openingBrackets.add('<');
		closingBrackets.add('>');
		
		// populate digits set
		for (char c : digitsArr) {
			digitChars.add(c);
		}
		
		// populate operators set
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');

		// run bracket check
//		checkStrings();
		
		// evaluate Reverse Polish Notation expression
		System.out.println(evaluateReversePolishExpression("8 3 * 4 - 7 + 9 / 2 +"));
		
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
				if (stack.peek().equals(matchingBrackets.get(c))) {
					stack.pop();
					printStack(stack);
				} else {
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
	
	/* Reverse Polish notation (RPN) is a method for representing expressions in which the operator symbol is placed after the arguments being operated on. Polish notation, in which the operator comes before the operands, was invented in the 1920s by the Polish mathematician Jan Lucasiewicz. In the late 1950s, Australian philosopher and computer scientist Charles L. Hamblin suggested placing the operator after the operands and hence created reverse polish notation. 
	 * 
	 * n practice RPN can be conveniently evaluated using a stack structure. Reading the expression from left to right, the following operations are performed:

1. If a value appears next in the expression, push this value on to the stack.

2. If an operator appears next, pop two items from the top of the stack and push the result of the operation on to the stack.

A standard infix arithmetic expression can be converted to an RPN expression using a parsing algorithm as a recursive descent parse.

RPN is used in Hewlett Packard and some Texas Instruments calculators and internally in some computer languages. */
	// for now, this method will only work for single-digit numbers...
	public int evaluateReversePolishExpression(String input) {
		
		// store integers in a stack
		Stack<Integer> stack = new Stack<>();
		
		// iterate over input string. assume the only numeric values present will be single-digit integers
		for (int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			
			// if a digit value appears next in the expression, push this value on to the stack
			if (digitChars.contains(c)) {
				stack.add(Character.getNumericValue(c));
				
			} else if (operators.contains(c)) {
				// otherwise if the next character is an operator, pop the top two items from the stack 
				int num1 = stack.pop();
				int num2 = stack.pop();
				
				// apply the relevant operator and add the result to the stack
				switch (c) {
				case '+':
					stack.add(num2 + num1);
					break;
				case '-':
					stack.add(num2 - num1);
					break;
				case '*':
					stack.add(num2 * num1);
					break;
				case '/':
					stack.add(num2 / num1);
					break;

				}
			}
			
		}
		// return top integer in the stack? if expression is well formed, should be result of the operations.
		return stack.pop();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
