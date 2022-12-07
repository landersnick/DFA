import java.io.*;
import java.util.Scanner;
import java.util.Scanner;

/* 
 * 
 * Authors: Tierra Anthony,
 			Phil Ware,
 			Nick Landers 
 * Created on 15 October 2022 
 * 
 * 
 */
/* class DFA*/
class DFA { /* transition function */

	/*
	 * 
	 * 
	 * Must input at the CMD as Java DFA.java "TextFile.txt"
	 * 
	 */

	public static void main(String args[]) {
		//First Check if user passes args 
		if (args.length != 1) 

			System.out.println("Please enter the data file");
		else {
			try (

					FileReader file = new FileReader(args[0]);
					BufferedReader reader = new BufferedReader(file); // BufferedReader applied on FileReader object
					Scanner input = new Scanner(System.in);

			) {

				// Should Follow format as discussed in Project Instructions
				String alphabet = reader.readLine();
				String states = reader.readLine();
				String startstate = reader.readLine();
				String acceptstates = reader.readLine();

				// Display current Strings to for error checking
				System.out.println("Alphabet: " + alphabet);
				System.out.println("States: " + states);
				System.out.println("Start state: " + startstate);
				System.out.println("Accept States: " + acceptstates);

				
				String str = "", s;

				 /* reads rules from file and concatenates them with a # symbol */
				while ((s = reader.readLine()) != null)
					str = str + s + "#";
				String rules[] = str.split("#");/* splits the rules at # symbol to form an array of rules */
				System.out.println("Transition rules are:");// displays rules
				for (int i = 0; i < rules.length; i++)
					System.out.println(rules[i]);

				System.out.println("Enter the string for testing:");
				String string = input.next();// reads a string from console for testing
				char state = startstate.charAt(0);

				/* removes {,} from acceptstates to form only string of states without {,} */
				acceptstates = acceptstates.replace("{", "");
				acceptstates = acceptstates.replace("}", "");
				acceptstates = acceptstates.replace(",", "");

				/* loops till input string not finished */
				for (int i = 0; i < string.length(); i++) {
					char alpha = string.charAt(i);// reads a char from input string at position i
					state = trans(rules, state, alpha);// calls to trans() to make state change according to rule
				}

				// Do the final check
				if (acceptstates.contains(state + "")) // if state is in acceptstates list
					System.out.println("String accepted"); // string is accepted
				else
					System.out.println("String not accepted"); // otherwise string not accepted

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// Delta Function
	public static char trans(String rules[], char state, char alpha) { /* loops for all the rules */
		for (int i = 0; i < rules.length; i++) {
			if (rules[i].charAt(1) == state && rules[i].charAt(3) == alpha)
				return rules[i].charAt(7);// returns next state
		}

		return state;
	}

}
