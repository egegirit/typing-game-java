import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

public class Utility {

	static Random random = new Random();
	static Scanner scan = new Scanner(System.in);

	public static void listAllFilesInDirectory(String path) {

		System.out.println("Contents of " + path + " folder:");

		String[] pathnames;

		// Creates a new File instance by converting the given pathname string
		// into an abstract pathname
		File f = new File(path);

		// Populates the array with names of files and directories
		pathnames = f.list();

		// For each pathname in the pathnames array
		for (String pathname : pathnames) {
			// Print the names of files and directories
			System.out.println("  " + pathname);
		}

	}

	public static String readLine(String path, int lineNumber) {

		if (lineNumber < 1) {
			System.out.print("Line number not allowed!");
			System.exit(1);
		}
		String line = "";
		BufferedReader reader;
		int lines = 1;

		try {
			reader = new BufferedReader(new FileReader(path));
			while (((line = reader.readLine()) != null) && (lines < lineNumber)) {
				// System.out.println(lines + ". line: " + line);
				if (line.trim().equals("")) {
					// System.out.println(" Line is empty");
					continue;
				}
				++lines;
			}
			line = line.trim();
			reader.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		}

		// System.out.println(" Read line: " + line);
		return line;
	}

	public static int countLines(String path) {

		BufferedReader reader;
		String line = null;
		int lines = 0;
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				// Dont count the characters like \n and \r
				if (line.trim().equals("")) {
					continue;
				}
				++lines;
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		}

		return lines;
	}

	public static void listArrayListElements(ArrayList<String> list) {

		int index = 1;

		for (String str : list) {
			System.out.println("  " + index + "- " + str);
			index++;
		}

	}

	public static ArrayList<String> convertWordsToArrayList(String path) {

		ArrayList<String> strings = new ArrayList<String>();

		String line = "";
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(path));
			while (((line = reader.readLine()) != null)) {
				// System.out.println(lines + ". line: " + line);
				if (line.trim().equals("")) {
					// System.out.println(" Line is empty");
					continue;
				}
				strings.add(line);
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
		}

		return strings;
	}

	public static void test(String path) {

		try {
			// Scanner scanner = new Scanner(new File(path));
			Scanner scanner = new Scanner(new FileInputStream(path), StandardCharsets.UTF_8.name());
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static int generateDigit() {

		return random.nextInt(10);

	}

	public static String generateNumber(int length) {

		String number = "";

		for (int i = 0; i < length; i++) {
			number += random.nextInt(10);
		}

		return number;

	}

	// Initialize the Integer List with random numbers
	public static void generateRandomIntegerList(int wordCount, int lineCount, ArrayList<Integer> chosenIndexes) {

		if (wordCount > lineCount) {
			System.out.println("Word count must be less than line count!");
			System.exit(1);
		}
		for (int i = 0; i < wordCount; i++) {
			chosenIndexes.add(Utility.generateNumberBetween(1, lineCount));
		}

	}

	// Convert all the letters of the elements of the arraylist to lowercase
	public static void convertListToLowercase(ArrayList<String> strings) {

		ListIterator<String> iterator = strings.listIterator();
		while (iterator.hasNext()) {
			iterator.set(iterator.next().toLowerCase());
		}

		System.out.println("Words are converted to lowercase!");

	}

	// Converts (some) international charachters to english characters
	public static String convertChars(String s) {

		String str = "";

		for (int i = 0; i < s.length(); i++) {

			switch (s.charAt(i)) {
			// Turkish characters
			case 'ı':
				str += 'i';
				break;
			case 'İ':
				str += 'I';
				break;
			case 'Ü':
				str += 'U';
				break;
			case 'ü':
				str += 'u';
				break;
			case 'ğ':
				str += 'g';
				break;
			case 'Ğ':
				str += 'G';
				break;
			case 'Ş':
				str += 'S';
				break;
			case 'ş':
				str += 's';
				break;
			case 'Ç':
				str += 'C';
				break;
			case 'ç':
				str += 'c';
				break;
			case 'Ö':
				str += 'O';
				break;
			case 'ö':
				str += 'o';
				break;
			// German
			case 'Ä':
				str += "Ae";
				break;
			case 'ä':
				str += "ae";
				break;
			case 'â':
				str += "a";
				break;
			case 'ß':
				str += "ss";
				break;
			// BUG: the character i is always typed wrong
			/*
			 * case 'i': str += "i"; break;
			 */
			default:
				str += s.charAt(i);

			}

		}

		return str;

	}

	public static int getUserInputInteger(String message) { // Gets an integer

		if (message != null) {
			System.out.print(message);
		}
		int input = 0;

		String inputStr = scan.nextLine();

		try {
			input = Integer.parseInt(inputStr); // Ob Parameter in Interger konverntierbar
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number!");
		}

		return input;

	}

	public static String getUserInputStr(String message) {

		if (message != null) {
			System.out.print(message);
		}
		String inputStr = scan.nextLine();
		return inputStr;

	}

	// Filter/Replace words from selected
	// words if they contain a character you choose
	public static void filterCertainCharacters(ArrayList<String> l, char c) {

		boolean lowercase;
		char ch;

		if (Character.isUpperCase(c)) {
			lowercase = false;
		} else {
			lowercase = true;
		}

		if (lowercase) {
			ch = Character.toUpperCase(c);
		} else {
			ch = Character.toLowerCase(c);
		}

		l.removeIf(word -> (word.indexOf(c) >= 0)); // easy syntax from Java 8 stream
		System.out.println("Words including " + c + " deleted!");

		l.removeIf(word -> (word.indexOf(ch) >= 0));
		System.out.println("Words including " + ch + " deleted!");
	}

	// Deletes the string elements from the list if they contain the character c,
	// the upper and lowercase versions of c is deleted
	public static ArrayList<String> filterCertainCharactersIntoNewList(ArrayList<String> l, char c) {

		boolean lowercase;
		char ch;

		if (Character.isUpperCase(c)) {
			lowercase = false;
		} else {
			lowercase = true;
		}

		ArrayList<String> filteredList = new ArrayList<String>(l);

		filteredList.removeIf(word -> (word.indexOf(c) >= 0)); // easy syntax from Java 8 stream
		System.out.println("Words including " + c + " deleted!");

		if (lowercase) {
			ch = Character.toUpperCase(c);
		} else {
			ch = Character.toLowerCase(c);
		}

		filteredList.removeIf(word -> (word.indexOf(ch) >= 0));
		System.out.println("Words including " + ch + " deleted!");

		return filteredList;
	}

	public static ArrayList<String> getFirstNElements(ArrayList<String> list, int n) {

		if (n <= 0) {
			System.out.println("The number n can't be less than 1!");
			System.exit(1);
		}
		if (list.isEmpty()) {
			System.out.println("List is Empty!");
			System.exit(1);
		}

		ArrayList<String> firstNElements = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			firstNElements.add(list.get(i));
		}

		return firstNElements;

	}

	// Generates an integer between [lower, upper]
	public static int generateNumberBetween(int lower, int upper) {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(upper) + lower;
	}

	public static String[] shuffleStrings(String[] strings) {

		List<String> strList = Arrays.asList(strings);
		Collections.shuffle(strList);
		return strList.toArray(new String[strList.size()]);

	}

	// Generates 1 of those: , : ; - " "
	// Todo: Add Tokens ? !
	// Todo: Specify the token than can be used in the parameter
	public static String generateRandomToken() {

		String selectedToken = ""; // Variable for the generated token

		// Only 5 tokens But 20 possible random numbers to adjust the frequencies

		int rand = Utility.generateNumberBetween(1, 20);

		switch (rand) { // if between 1-10 than space ( 50% chance to generate space)
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			selectedToken = " ";
			break;
		case 11:
		case 12:
		case 13:
			selectedToken = "?";
			break;
		case 14:
		case 15:
		case 16:
			selectedToken = "!";
			break;
		case 17:
			selectedToken = ",";
			break;
		case 18:
			selectedToken = "-";
			break;
		case 19:
			selectedToken = ";";
			break;
		case 20:
			selectedToken = ":";
			break;
		default:
			selectedToken = " ";
		}

		return selectedToken;

	}

	// No check if the strings are just 1 character to allow punctiations like ...
	// call: generateSpecifiedToken(".", ",", "...", "?", "!", " ")
	public static String generateSpecifiedToken(String[] strings) {

		String selectedToken = ""; // Variable for the generated token
		int length = strings.length;

		if (length <= 0) {
			System.out.println("Empty Parameter for generateSpecifiedToken()!");
			System.exit(1);
		}

		int rand = Utility.generateNumberBetween(1, length);
		selectedToken = strings[rand];

		// System.out.println( "Generated token: " + selectedToken);

		return selectedToken;

	}

	// Todo: if . selected, make the first letter of the next word uppercase
	// Todo: shuffle the words?
	public static String makeSentence(String[] strs) { // change to ArrayList

		String sentence = "";
		String selectedToken = ""; // , - ; : or monospace NO DOT -> ONLY AT THE END
		boolean wordEnd = false;

		// for n words there can be n-1 commas/etc. But at the end there must be a .
		for (int x = 0; x < strs.length; x++) {

			if (wordEnd) { // Word starts with upper case
				String cap = strs[x].substring(0, 1).toUpperCase() + strs[x].substring(1);
				sentence += cap;
				wordEnd = false;
			} else {
				sentence += strs[x]; // add the current word to the sentence
			}

			if (x != (strs.length - 1)) {

				selectedToken = Utility.generateRandomToken(); // or generateSpecifiedToken(".", ",", "?", "!", " ");
				sentence += selectedToken;
				if (selectedToken.equals(".") || selectedToken.equals("?") || selectedToken.equals("!")) {
					wordEnd = true;
				}

			} else { // add dot at the end
				sentence += ".";
			}

		}

		return sentence;

	}

	public static boolean checkDuplicates(ArrayList<Integer> list) {

		Set<Integer> set = new HashSet<Integer>(list);

		if (set.size() < list.size()) {
			System.out.println("Duplicate detected!"); // DELETE
			return true;
		}

		return false;
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		// Create a new ArrayList
		ArrayList<T> newList = new ArrayList<T>();

		// Traverse through the first list
		for (T element : list) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		System.out.println("Duplicates removed!"); // DELETE

		// return the new list
		return newList;
	}

	public static void mainMenu() {

		do {
			
			// Initialise the word arrays
			Main.words = Utility.convertWordsToArrayList(Main.filePath);
			Main.filteredWords = Main.words;
			
			System.out.println();
			System.out.println("*** Main Menu ***");
			System.out.println("1- Start game");
			System.out.println("2- Settings");
			System.out.println("3- Help");
			System.out.println("4- Exit");

			int input = getUserInputInteger("Answer: ");
			if (input == 1) {
				startGame();
			} else if (input == 2) {
				System.out.println("  *** Settings ***");
				System.out.println("  1- Set Custom Path");
				System.out.println("  2- Set Case Sensitivity");
				System.out.println("  3- Set Filter Options");
				System.out.println("  4- Convert umlauts to english");
				
				
				
				int inputSetting = Utility.getUserInputInteger("Answer: ");
				if (inputSetting == 1) {
					Utility.setCustomTxtPath();
				} else if (inputSetting == 2) {
					Utility.setCaseSensitivity();
				} else if (inputSetting == 3) {
					Utility.setFilterOptions();
				} 
				else if (inputSetting == 4) {
					Utility.setUmlautToEnglish();				
				}
				else {
					System.out.println("  Wrong input!");
				}

			} else if (input == 3) {
				helpMenu();
			} else if (input == 4) {
				System.out.println("  Exiting...");
				System.exit(1);
			}
		} while (true);

	}

	// TODO: Not working
	private static void setUmlautToEnglish() {
		
		String str = "";
		for(int i = 0; i < Main.filteredWords.size(); i++) {
			// System.out.println("Converting this: " + Main.filteredWords.get(i));
			str = Utility.convertChars(Main.filteredWords.get(i));
			// System.out.println("Converted to: " + str);
			Main.filteredWords.set(i, str );			
			
		}		
		System.out.println("Umlauts converted to english!");
		Utility.listArrayListElements(Main.filteredWords);
	}

	public static void helpMenu() {
		System.out.println();
		System.out.println("*** Help ***");
		System.out.println("Cheat codes:");
		System.out.println("_cheat, _menu, _end");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mainMenu();
	}

	// TODO: Start a new game with the applied settings
	@SuppressWarnings("unchecked")
	public static void startGame() {

		System.out.println("Round: " + Main.roundCount);
		
		Main.words = Utility.convertWordsToArrayList(Main.filePath);

		// Everytime a game is started, the settings are applied to the words again
		// and the words are shuffled again
		Main.filteredWords = Main.words;
		
		Main.wordCountInGame = Utility.getUserInputInteger("Enter word count: ");
		System.out.println("Word count set to " + Main.wordCountInGame);

		applyFiltering();

		applyCaseSensitivity();

		shuffleWords(Main.filteredWords);

		// pick n words
		Main.filteredWords = Utility.getFirstNElements(Main.filteredWords, Main.wordCountInGame);
		System.out.println("First " + Main.wordCountInGame + " Words: ");
		Utility.listArrayListElements(Main.filteredWords);
		
		ArrayList<String> remainingWords = new ArrayList<String>();
		remainingWords = (ArrayList<String>) Main.filteredWords.clone();		
		
		String input = "";
		int failCount = 0;  // How many times the user failed to type a certain word
		int failedWordCount = 0;  // How many words the user failed to type 3 times
		int correctCount = 0;		
		boolean exitGame = false;		
		// Measure the time
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < Main.filteredWords.size(); i++) {

			failCount = 0;
			String currentWord = Main.filteredWords.get(i);
			do {

				if (failCount == 3) {
					failedWordCount++;
					removeFirstElement(remainingWords);
					System.out.println("  Word Failed!");
					break;
				}

				// List the remaining words in one line
				for(int x=0; x < remainingWords.size(); x++) {
					System.out.print(remainingWords.get(x) + " ");			
				}
				
				System.out.println();
				
				// Get user input while showing the current word
				input = Utility.getUserInputStr(currentWord + " = ");

				if (input.equals(currentWord)) {
					
					// Points++
					correctCount++;					
					removeFirstElement(remainingWords);					
					System.out.println("  Correct!");
					
				} else if (input.equalsIgnoreCase("_cheat")) {		
					
					// skip the current word
					removeFirstElement(remainingWords);					
					System.out.println("  Skipped!");
					break;
					
				} else if (input.equalsIgnoreCase("_end")) {
					System.out.println("  Ending game...");
					System.exit(0);
					break;
				} else if (input.equalsIgnoreCase("_menu")) {
					
					exitGame = true;
					break;
				} else {
					failCount++;
					System.out.println("  Wrong! (" + failCount + ")");
					// wrong, repeat current word
				}
							

			} while (!currentWord.equals(input));
			if (exitGame) {
				break;
			}

		}
		Main.roundCount++;
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);  
		
		System.out.println();
		System.out.println("  Game over!");
		long miliseconds = duration % 1000;
		long seconds = duration /1000;
		long minutes = seconds /60;
		System.out.println("  Duration: " + minutes + " minutes " + seconds + " seconds " + miliseconds + " miliseconds.");
		System.out.println("  Wrong word count  : " + failedWordCount + " of " + Main.filteredWords.size() + " words");
		System.out.println("  Correct word count: " + correctCount + " of " + Main.filteredWords.size() + " words");		

	}

	public static void shuffleWords(ArrayList<String> strList) {

		System.out.println("Shuffling words...");
		Collections.shuffle(strList);

	}

	public static void setCustomTxtPath() {

		int customTextOption = 0;
		System.out.println("Set custom text file?");
		System.out.println("  1- Yes");
		System.out.println("  2- No");
		customTextOption = Utility.getUserInputInteger("Answer: ");
		if (customTextOption == 1) {
			Utility.listAllFilesInDirectory(Main.directory);
			String customTxtPath = Utility.getUserInputStr("Enter custom path: ");
			Main.filePath = customTxtPath;
			System.out.println("  Path set to " + Main.filePath);
		}

	}
	
	public static void removeFirstElement(ArrayList<String> strList) {
		// Avoid null pointer exception
		if(strList.size() > 0) {
			strList.remove(0);
		}	
	}

	public static void setFilterOptions() {
		int filterOption = 0;
		System.out.println("Do you want to filter certain characters?");
		System.out.println("  1- Yes");
		System.out.println("  2- No (Or press anything)");
		filterOption = Utility.getUserInputInteger("Answer: ");

		// TODO: do this in start game bcs we only want to set the setting and not yet
		// apply them
		if (filterOption == 1) {
			Main.filterOption = true;

			// Delete duplicate characters from string
			String input = Utility.getUserInputStr("Type all the characters to filter: ");
			Main.charactersToFilter = Arrays.asList(input.split("")).stream().distinct().collect(Collectors.joining());
			System.out.println("  These characters will be filtered:");
			System.out.println("  " + Main.charactersToFilter );
		}
		// else there is nothing to filter so Main.filteredWords = Main.words;
		// and we continue to work with Main.filteredWords
		else {
			Main.filterOption = false;
		}
	}

	public static void applyFiltering() {
		if (Main.filterOption) {
			for (int i = 0; i < Main.charactersToFilter.length(); i++) {
				Main.filteredWords = Utility.filterCertainCharactersIntoNewList(Main.words,
						Main.charactersToFilter.charAt(i));
			}
			// Utility.listArrayListElements(filteredWords);
			if (Main.filteredWords.isEmpty()) {
				System.out.println("No More Words Left! (You probably filtered all the words)");
				// TODO: Dont exit, reset settings and go to main menu
				System.out.println("Exiting...");
				System.exit(1);
			}
		}
	}

	public static void applyCaseSensitivity() {
		if (!Main.caseSensitive) {
			Utility.convertListToLowercase(Main.filteredWords);
		}
	}

	public static void setCaseSensitivity() {
		// Handle case sensitivity
		System.out.println("Enable Case Sensitivity?");
		System.out.println("  1- Yes");
		System.out.println("  2- No (or press anything)");
		String input = Utility.getUserInputStr("Answer: ");
		if ("1".equals(input)) {
			Main.caseSensitive = true;
			System.out.println("Case Sensitivity set to true!");
		} else {
			Main.caseSensitive = false;
			System.out.println("Case Sensitivity set to false!");
		}

		// System.out.println("List after Case sensitivity:");
		// Utility.listArrayListElements(filteredWords);
	}
		
}
