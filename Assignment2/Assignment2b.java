import java.util.Scanner;

public class Assignment2b {
	
	public static void editDistance(String word1,String word2) {
	
		String[] w1 = word1.split("");
		String[] w2 = word2.split("");
		int[][] grid = new int[w1.length+1][w2.length+1];
		
		grid[0][0] = 0;
		for(int i = 1; i < w1.length+1; i++) {
			grid[i][0] = i;
		}
		for(int j = 1; j < w2.length+1; j++) {
			grid[0][j] = j;
		}
		
		for(int i =1; i < w1.length+1; i++) {
			for(int j = 1; j < w2.length+1; j++) {
				if(w1[i-1].equals(w2[j-1])) {
					grid[i][j] = grid[i-1][j-1];
				}else {
					grid[i][j] = Math.min(grid[i-1][j],Math.min(grid[i-1][j-1],grid[i][j-1]))+1;
				}
			}
		}
		System.out.print("\t");
		for(int i =0; i <w2.length;i++) {
			System.out.print("\t"+w2[i]);
		}
		System.out.println();
		for(int i =0; i < w1.length+1; i++) {
			for(int j = 0; j < w2.length+1; j++) {
				if(i > 0 && i < w1.length+1 && j < 1) {
					System.out.print(w1[i-1]+"\t");
				}else if(j <1) {
					System.out.print("\t");
				}
				System.out.print(grid[i][j]+"\t");
			}
			System.out.println();
		}
	
		
	}
	
	public static void main(String [] args) {
		String word1, word2;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first word you'd like to comapre: ");
		word1 = input.next();
		System.out.println("Please enter the second word you'd like to comapre: ");
		word2 = input.next();
		
		editDistance(word1,word2);
	}
	
	
}