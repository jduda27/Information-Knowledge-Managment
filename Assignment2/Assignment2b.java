
/**Assignment 2 - Edit Distance - Assignment2b Class
 * @Class: COSC 4315
 * @Professor: Dr. Leonard Brown
 * @Author:Joshua Duda
 * @Date: 2/29/2020
 * This class contains the editDistance method and a main method to read in
 * two user defined words and print out a grid of edit distances between each
 * character in the two words.
 */
import java.util.Scanner;

public class Assignment2b {

	/**
	 * editDistance() Method - This method reads in two words and creates a grid of
	 * the edit distance between between each character in the two words
	 * 
	 * @Preconditions - String word1: A valid word to be compared. String word2:
	 *                Another valid word to be compared.
	 * @Postcondition - The method prints out a grid showing the edit distance
	 *                between two words
	 */
	public static void editDistance(String word1, String word2) {

		// initialize 2 string arrays for the two words
		String[] w1 = word1.split("");
		String[] w2 = word2.split("");

		// create a grid array equal to the length of both words + 1
		int[][] grid = new int[w1.length + 1][w2.length + 1];

		// set our corner value to zero because it is a blank space
		grid[0][0] = 0;

		// set each position of the grid equal to the value representing the character
		// position of the first word
		for (int i = 1; i < w1.length + 1; i++) {
			grid[i][0] = i;
		}

		// set each position of the grid equal to the value representing the character
		// position of the second word
		for (int j = 1; j < w2.length + 1; j++) {
			grid[0][j] = j;
		}

		// find the edit distance between each character position of the two words
		for (int i = 1; i < w1.length + 1; i++) {
			for (int j = 1; j < w2.length + 1; j++) {

				// if the letter of the first word is equal to the letter of the second word in
				// the same position, set the edit distance to the character position
				if (w1[i - 1].equals(w2[j - 1])) {
					grid[i][j] = grid[i - 1][j - 1];
				} else {

					// if not set the current grid position to the minimum distance plus one
					grid[i][j] = Math.min(grid[i - 1][j], Math.min(grid[i - 1][j - 1], grid[i][j - 1])) + 1;
				}
			}
		}

		// position the start of word 2 to the corresponding grid location and print out
		// the header
		System.out.print("\t");
		for (int i = 0; i < w2.length; i++) {
			System.out.print("\t" + w2[i]);
		}

		// move down a line
		System.out.println();

		// print out the grid to the console
		for (int i = 0; i < w1.length + 1; i++) {
			for (int j = 0; j < w2.length + 1; j++) {

				// if we are not at position 0 and not longer than w1 and it's before our grid
				// is filled out
				if (i > 0 && i < w1.length + 1 && j < 1) {

					// print the letter for word 1 that corresponds to the row
					System.out.print(w1[i - 1] + "\t");

					// if it is only the start of a row space it properly
				} else if (j < 1) {
					System.out.print("\t");
				}

				// print the current value at the correct grid position
				System.out.print(grid[i][j] + "\t");
			}

			// move to the next line for the next row
			System.out.println();
		}

	}

	// main method reads in user input and returns the edit distance grid
	public static void main(String[] args) {

		// initialize our variables and scanner object for user input
		String word1, word2;
		Scanner input = new Scanner(System.in);

		// prompt the user for the first word
		System.out.println("Please enter the first word you'd like to comapre: ");

		// read the next word entered
		word1 = input.nextLine();

		// prompt the user for the second word
		System.out.println("Please enter the second word you'd like to comapre: ");

		// read the next word entered
		word2 = input.nextLine();

		// use the edit distance method to display a grid showing the edit distances
		// between the two words.
		editDistance(word1, word2);
	}

}