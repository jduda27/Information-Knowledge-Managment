
/** Assignment 1 - Inverted Index - readFile Class
 * @Class: COSC 4315
 * @Professor: Dr. Leonard Brown
 * @Author:Joshua Duda
 * @Date: 2/14/2020
 * In this class we read in files from a directory and create a inverted index with our words followed by the documents you can find them in.
 *
 * How we handle stop words, punctuation, upper case, and stemming.
 * Stop Words:  We use ranks.nl's common English stop words list to eliminate the most common stop words from our inverted index.
 * Punctuation: We remove the punctuation characters "?.,!:;<>@#%^&*" from all words in our index.
 * Upper Case:  We convert every word to be completely in lower case.
 * Stemming: We remove simple stems such as "'s, 're, 'll, 'nt" 
 * 
 */

import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class ReadDirectory {

	/**
	 * readFile() Method This method reads in files from a specified directory and
	 * outputs a inverted index to the console
	 * 
	 * @Precondition: String directory - A valid string that represents a directory
	 *                of files you want to read into the inverted Index
	 * @Postcondition: This method returns a inverted index of relevant words and
	 *                 the files they can be located in.
	 */
	public static void readFile(String directory) throws IOException {

		// This program uses the ranks.nl Common English stop words list to determine
		// stop words
		List<String> stopwords = Files.readAllLines(Paths.get("common_stopwords.txt"));

		try {
			// Initializing an ArrayList<String> to hold contain our index of words
			ArrayList<String> list = new ArrayList<String>();

			// Initializing our Directory
			File dir = new File(directory);

			// Creating an array of type File with all files within the directory.
			File[] fileList = dir.listFiles();

			// Printing out a start statement
			System.out.println("Inverted Index for directory: " + directory);

			// while there are files in our file list we go through the operations below.
			for (File f : fileList) {

				// We store the file ID (the path - the directory inputed) so we can insert it
				// to our index
				String fileID = f.toString().substring(f.toString().indexOf("\\") + 1, f.toString().length());
				// our scanner reads the next file
				Scanner sc = new Scanner(f);

				// while there are lines in the file we read those into our word tokenizer
				while (sc.hasNextLine()) {
					StringTokenizer st = new StringTokenizer(sc.nextLine());

					// while there are still words in the line we manipulate the words and add
					// them to our list
					while (st.hasMoreTokens()) {

						// looking at the next token and setting it as our new word
						String word = st.nextToken();

						// we remove the specified punctuation marks
						word = word.replaceAll("[?.,!:;<>@#%^&*]", "");

						// we make all words lower case
						word = word.toLowerCase();

						// stemming ' stems
						if (word.contains("'")) {
							word = word.substring(0, word.indexOf("'"));
						}

						// is it a stop word? if so we do not add it to the list
						if (!stopwords.contains(word)) {
							list.add(word + "\t\t" + fileID);
						}
					}
				}
				sc.close(); // closing our scanner
			}

			// This method sorts our list in alphabetical order
			Collections.sort(list);


			// Combining index results
			for (int i = 1; i < list.size(); i++) {

				// we find the index of the point at which the word ends and the first FileID is
				// referenced
				int brk = list.get(i).indexOf("\t\t");
				int prevBrk = list.get(i - 1).indexOf("\t\t");

				// if the current word is equal to the previous word then they should be
				// combined.
				if (list.get(i).substring(0, brk).equals(list.get(i - 1).substring(0, prevBrk))) {

					// if the word was not in the same document add the new document fileID to the
					// end of the original
					if (!list.get(i).substring(brk, list.get(i).length())
							.equals(list.get(i - 1).substring(brk, list.get(i - 1).length()))) {
						
						//add a new updated index at the current location adding the new document
						list.add(i, list.get(i) + "," + list.get(i - 1).substring(brk + 1, list.get(i - 1).length()));
						
						//remove the old index that was appended
						list.remove(i + 1);
					}
					
					//remove the repeated word from the index and set i to reflect this change
					list.remove(i - 1);
					i = i - 1;

				}
			}

			System.out.println(list.size() + " relavant words indexed"); //How many words are in the index
			
			//printing out the inverted index
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}

		} catch (Exception e) {
			//If there is an error this reports it.
			System.out.println("Error:  " + e.toString());
		}
	}
}