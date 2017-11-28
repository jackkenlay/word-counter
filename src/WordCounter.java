import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public HashMap<String,Integer> countWords(String inputSentance){
		String[] allWords = inputSentance.split(" ");
		HashMap <String,Integer> toReturn = new HashMap <String,Integer>();

		for(int i = 0; i < allWords.length; i++) {
			String currentWord = allWords[i];
			if(!isContainedInOtherWords(allWords, currentWord)) {
				int count = getWordCount(allWords, currentWord);
				toReturn.put(currentWord, count);
			}
		}

		return toReturn;
	}
	
	public boolean isContainedInOtherWords(String[] words, String inputWord){
		boolean includedInOtherWord = false;

		for(int i = 0; i < words.length; i++) {
			if(words[i].contains(inputWord)&& words[i].length()!=inputWord.length()) {
				includedInOtherWord = true;
				break;
			}
		}

		return includedInOtherWord;
	}
	
	public int getWordCount(String[] words, String inputWord) {
		int counter = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(inputWord)) {
				counter++;
			}
		}
		return counter;
	}
}
