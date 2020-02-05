import java.util.*;
import java.io.*;

class ReadDirectory
{
  public static void main(String[]  args)
  {
    try
    {
      System.out.print("Enter name of a directory> ");
      Scanner scan = new Scanner(System.in);
      File dir = new File(scan.nextLine());
      File[] fileList = dir.listFiles();
      for (File f : fileList)
      {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine())
        {
          StringTokenizer st = new StringTokenizer(sc.nextLine());
          while (st.hasMoreTokens())
          {
            String word = st.nextToken();
            // Process word
            // Case Folding, punctuation, stemming, etc.
            System.out.println(word);
          }
        }
      }
    }
    catch(Exception e)
    {
      System.out.println("Error:  " + e.toString());
    }
  }
}