import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class WordCounter {
	public static void main(String[] args) {
		if(args.length>0) {
			WordCounter wordCounter = new WordCounter();
			System.out.println("Input file name: " + args[0]);
			String[] wordCount = wordCounter.getWordCountFromFile(args[0]);
			System.out.println("Output:");
			for(String s : wordCount) {
				System.out.println(s);
			}
			System.out.println("Finished.");
		}else {
			System.out.println("Please give filename");
		}
	}
	
	public String[] getWordCountFromFile(String fileName) {
		String[] wordCount = {"Nothing Found"};
		try {
			String fileText = this.getTextFromFile(fileName);
			wordCount = this.getWordCount(fileText);
		}catch (Exception e) {
			System.out.println("Would handle exceptions here:");
			e.printStackTrace();
			System.out.println("Returning nothing found");
		}
		return wordCount;	
	}
	
	public String getTextFromFile(String fileName) {
        String line = null;
        String allText = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                allText +=line;
            }
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return allText;
	}
	
	String[] getWordCount(String inputSentance) {
		HashMap<String,Integer> countedWords = countWords(inputSentance);
		ArrayList<String> allWords = new ArrayList<String>(countedWords.keySet());
        sortListByWordLength(allWords);
        for(int i = 0; i < allWords.size(); i++) {
        	int count = countedWords.get(allWords.get(i));        	
        	allWords.set(i,allWords.get(i) + ": " + count);
        	allWords.set(i,capitalizeFirstLetter(allWords.get(i)));
        }
        return allWords.toArray(new String[allWords.size()]);
	}
	
	String capitalizeFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	private void sortListByWordLength(List<String> inputList){
		Collections.sort(inputList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()<o2.length()){
                    return 1;
                }else{
                    return o1.compareTo(o2);
                }
            }
        });
	}
	
	HashMap<String,Integer> countWords(String inputSentance){
		inputSentance = inputSentance.toLowerCase();
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
	
	boolean isContainedInOtherWords(String[] words, String inputWord){
		boolean includedInOtherWord = false;
		for(int i = 0; i < words.length; i++) {
			if(words[i].contains(inputWord)&& words[i].length()!=inputWord.length()) {
				includedInOtherWord = true;
				break;
			}
		}
		return includedInOtherWord;
	}
	
	int getWordCount(String[] words, String inputWord) {
		int counter = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(inputWord)) {
				counter++;
			}
		}
		return counter;
	}
}