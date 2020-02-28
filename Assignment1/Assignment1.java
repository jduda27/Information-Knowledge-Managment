
/** Assignment 1 - Inverted Index - Assignment1 Class
 * @Class: COSC 4315
 * @Professor: Dr. Leonard Brown
 * @Author:Joshua Duda
 * @Date: 2/14/2020
 * In this class we ask the user to input the name of the directory they would like to create an inverted index of and then we call readFile and execute
 *
 * How we handle stop words, punctuation, upper case, and stemming.
 * Stop Words:  We use ranks.nl's common English stop words list to eliminate the most common stop words from our inverted index.
 * Punctuation: We remove the punctuation characters "?.,!:;<>@#%^&*" from all words in our index.
 * Upper Case:  We convert every word to be completely in lower case.
 * Stemming: We remove simple stems such as "'s, 're, 'll, 'nt" 
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment1 extends ReadDirectory {

	public static void main(String[] args) {
		// prompt the user for the name of the directory
		System.out.println("Please Enter The Name of The Directory: ");
		// create a scanner object to read user input
		Scanner scan = new Scanner(System.in);
		// input in the next line the user types
		String dir = scan.nextLine();

		try { // if there is no error we call the readFile method from ReadDirectoy.java
			readFile(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
