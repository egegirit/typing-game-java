import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int wordCountInGame = 10;
	static String directory = ".\\textFiles";
	static String filePath = ".\\textFiles\\DeutschUTF8.txt";
	
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<String> filteredWords = new ArrayList<String>();
	
	static boolean caseSensitive = true;
	static boolean filterOption = false;
	static String charactersToFilter = "";
	static int roundCount = 1; 
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		Utility.mainMenu();

	}

}
