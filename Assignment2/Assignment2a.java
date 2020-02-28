import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assignment2a {

	public static void permutermIndex(String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		Scanner sc = new Scanner(f);

		ArrayList<String> list = new ArrayList<String>();
		
		while (sc.hasNextLine()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());

			while (st.hasMoreTokens()) {

				String word = st.nextToken();

				word = word.replaceAll("[?.,!:;<>@#%^&*]", "");

				word = word.toLowerCase();

				list.add(word);
				
			}
		}
		sc.close(); // closing our scanner
		
		Collections.sort(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	
	public static void main(String [] args) throws FileNotFoundException {
		String file = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the file you want a permuterm index of: ");
		file = input.next();
		
		permutermIndex(file);
		
	}

}