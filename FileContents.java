import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FileContents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = args[0];
		Scanner scnr;
		
		try {
			File file = new File(filename);
			scnr = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.print("Error: File name not found.");
			return;
		}
		
		int numLines = 0; // number of lines in the file
		int numChars = 0; // number of character in the file
		int numLetters = 0; // number of character which are letters
		int numFigures = 0; // number of characters which are figures
		int numOther = 0; // number of other characters
		int numWords = 0; // number of words
		

		// counts for each length of word present
		HashMap<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();
		
		
		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			numLines++;
			int wordStartIndex = 0;
			for (int i = 0; i < line.length(); i++) {
				numChars++;
				char cur = line.charAt(i);
				
				if (Character.isLetter(cur)) {
					numLetters++;
				}
				else if (Character.isDigit(cur)) {
					numFigures++;
				}
				else if (Character.isWhitespace(cur)) {
					numOther++;
					numWords++;
					// find length of the new word
					int wordLength = i - wordStartIndex;
					// increment count for this word, or add new key if not present
					if (wordLengths.containsKey(wordLength)) {
						int prevCount = wordLengths.get(wordLength);
						wordLengths.replace(wordLength, prevCount + 1);
					}
					else {
						wordLengths.put(wordLength, 1);
					}
					wordStartIndex = i + 1;
				}
				else {
					numOther++;
				}
			}
			numWords++; // increment once for the final word in the line
			int wordLength = line.length() - 1 - wordStartIndex;
			// increment count for this word, or add new key if not present
			if (wordLengths.containsKey(wordLength)) {
				int prevCount = wordLengths.get(wordLength);
				wordLengths.replace(wordLength, prevCount + 1);
			}
			else {
				wordLengths.put(wordLength, 1);
			}
			wordStartIndex = 0;
			
		}
		
		// display the results to console
		System.out.println("Number of lines: " + numLines);
		System.out.println("Number of chatacters: " + numChars);
		System.out.println("Number of letters: " + numLetters);
		System.out.println("Number of figures: " + numFigures);
		System.out.println("Number of other characters: " + numOther);
		System.out.println("Number of words: " + numWords);
		
		for (Map.Entry pair : wordLengths.entrySet()) {
			int length = (Integer) pair.getKey();
			int number = (Integer) pair.getValue();
			System.out.print("Number of " + length + " letter words: ");
			System.out.println(number);
		}
	}
}
