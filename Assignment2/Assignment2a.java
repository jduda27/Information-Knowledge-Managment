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

	public static void permutermIndex(String fileName) throws IOException {

		List<String> stopwords = Files.readAllLines(Paths.get("common_stopwords.txt"));

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Scanner file = new Scanner(br);

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> permInd = new ArrayList<String>();

		while (file.hasNextLine()) {
			StringTokenizer st = new StringTokenizer(file.nextLine());

			while (st.hasMoreTokens()) {

				String word = st.nextToken();

				word = word.replaceAll("[?.,!:;<>@#%^&*]", "");

				word = word.toLowerCase();

				if (!stopwords.contains(word)) {
					list.add(word);
				}

			}
		}
		file.close(); // closing our scanner

		// initial sort
		Collections.sort(list);

		// removing duplicate words
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).equals(list.get(i - 1))) {
				list.remove(i - 1);
				i = i - 1;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			String base = list.get(i) + "$";
			permInd.add(base + " : " + list.get(i));
			while (!base.substring(0, 1).equals("$")) {
				base = base.substring(1, base.length()) + base.substring(0, 1);
				permInd.add(base + " : " + list.get(i));
			}
		}
		
		Collections.sort(permInd);

		for(int i = 0; i < permInd.size(); i++) {
			System.out.println(permInd.get(i));
		}
	}

	public static void main(String[] args) throws IOException {
		String file = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the file you want a permuterm index of: ");
		file = input.next();

		permutermIndex(file);

	}

}