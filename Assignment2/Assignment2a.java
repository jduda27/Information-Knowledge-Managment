
/**Assignment 2 - Permutation Index - Assignment2a Class
 * @Class: COSC 4315
 * @Professor: Dr. Leonard Brown
 * @Author:Joshua Duda
 * @Date: 2/29/2020
 * This class contains the permutermIndex Method that reads 
 * in a file input name and prints a permuterm index to the 
 * console. It also contains a main method that lets the user 
 * enter the file name they'd like to index.
 * 
 * Note: We ignore upper case, we remove all punctuation, and 
 * we remove common English stop words from our permuterm index.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assignment2a {

	// initialize our scanner object
	private static Scanner input;

	/**
	 * permutermIndex() Method - This method takes in a file and displays a
	 * permuterm index of all words in that file (excluding common English stop
	 * words) in sorted order.
	 * 
	 * @precondition - String fileName: A valid string object representing a file
	 *               name that can be read.
	 * @postcondition - The method prints out a sorted permuterm Index of the file
	 * @throws IOException
	 *             - for invalid fileName
	 */
	public static void permutermIndex(String fileName) throws IOException {

		// this reads the common_stopwords.txt file into a list of Strings
		List<String> stopwords = Files.readAllLines(Paths.get("common_stopwords.txt"));

		// we create a buffered reader that reads the file we are creating the index of
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Scanner file = new Scanner(br); // create a new scanner for that file

		// initializing two lists, list for our words, permInd for our permutations of
		// the words
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> permInd = new ArrayList<String>();

		// while there is still lines to read in
		while (file.hasNextLine()) {

			// create tokens of the next line in the file
			StringTokenizer st = new StringTokenizer(file.nextLine());

			// while there are still strings in the line to be read
			while (st.hasMoreTokens()) {

				// set the word to next token and then normalize
				String word = st.nextToken();

				// remove all punctuation marks
				word = word.replaceAll("[?.,!:;<>@#%^&*]", "");

				// convert the word to lower case
				word = word.toLowerCase();

				// if the word is not contained in our stop words list then add the word to our
				// initial list
				if (!stopwords.contains(word)) {
					list.add(word);
				}

			}
		}

		file.close(); // closing our scanner

		// Sorting the list of words
		Collections.sort(list);

		// removing duplicate words
		for (int i = 1; i < list.size(); i++) {

			// if the current word is equal to the word before
			if (list.get(i).equals(list.get(i - 1))) {

				// remove the word before and correct the current location in our for loop
				list.remove(i - 1);
				i = i - 1;
			}
		}

		// For our entire list of words
		for (int i = 0; i < list.size(); i++) {

			// set the base word to be the current word with $ appended to the end
			String base = list.get(i) + "$";

			// add this to our permutation index as well as our reference word separated
			// with a " : "
			permInd.add(base + " : " + list.get(i));

			// while the first character of the permutation is not equal to $
			while (!base.substring(0, 1).equals("$")) {

				// set our base equal to the current base with the first letter rotated to the
				// end
				base = base.substring(1, base.length()) + base.substring(0, 1);

				// add this new base to our permutation index with its word it references
				permInd.add(base + " : " + list.get(i));
			}
		}

		// sort our permutation Index
		Collections.sort(permInd);

		// print out our permutation Index
		for (int i = 0; i < permInd.size(); i++) {
			System.out.println(permInd.get(i));
		}
	}

	// main() method takes a user input for a file name and creates a permutermIndex
	// of the file.
	public static void main(String[] args) throws IOException {

		// initializing our variables
		String file = "";
		input = new Scanner(System.in);

		// prompting for user input
		System.out.println("Please enter the file you want a permuterm index of: ");

		// reading the next input by the user
		file = input.nextLine();

		// run the permutermIndex method
		permutermIndex(file);

	}

}