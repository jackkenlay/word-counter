import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

public class WordCounterTest {
	@Test
	public void countWordsTest() {
		WordCounter wordCounter = new WordCounter();
		String testString = "A mate material may maybe right maybe";
		
		HashMap<String, Integer> expectedResponse = new HashMap<String,Integer>();
		
		expectedResponse.put("material", 1);
		expectedResponse.put("maybe", 2);
		expectedResponse.put("right", 1);
		
		HashMap<String, Integer> response = wordCounter.countWords(testString);

		assertEquals(response.get("material"),(Integer)1);
		assertEquals(response.get("maybe"),(Integer)2);
		assertEquals(response.get("right"),(Integer)1);
		assertEquals(response.get("A"),null);
		assertEquals(response.get("a"),null);
		assertEquals(response.get("may"),null);
	}
	
	@Test
	public void isContainedInOtherWordsTest(){
		String[] testStringArr = "a bag".split(" ");
		String[] testStringArr2 = "a not".split(" ");
		String[] testStringArr3 = "bag baggage baggled".split(" ");
		
		WordCounter wordCounter = new WordCounter();
		
		assertTrue(wordCounter.isContainedInOtherWords(testStringArr, "a"));
		assertFalse(wordCounter.isContainedInOtherWords(testStringArr2, "a"));
		assertTrue(wordCounter.isContainedInOtherWords(testStringArr3, "bag"));
		assertFalse(wordCounter.isContainedInOtherWords(testStringArr2, "not"));
	}
	
	@Test
	public void wordCountTest() {
		String[] testStringArr = "a bag".split(" ");
		String[] testStringArr2 = "a not".split(" ");
		String[] testStringArr3 = "bag baggage baggled bag".split(" ");
		
		WordCounter wordCounter = new WordCounter();
		
		assertEquals(wordCounter.getWordCount(testStringArr, "a"),1);
		assertEquals(wordCounter.getWordCount(testStringArr, "bag"),1);
		assertEquals(wordCounter.getWordCount(testStringArr2, "a"),1);
		assertEquals(wordCounter.getWordCount(testStringArr2, "not"),1);
		assertEquals(wordCounter.getWordCount(testStringArr3, "bag"),2);
		assertEquals(wordCounter.getWordCount(testStringArr3, "baggage"),1);
		assertEquals(wordCounter.getWordCount(testStringArr3, "baggled"),1);
		assertEquals(wordCounter.getWordCount(testStringArr3, "a"),0);
	}
	
	@Test
	public void capitalizeFirstLetterTest() {
		String testString = "jack";
		String testString1 = "bob";
		String testString2 = "Dave";
		
		WordCounter wordCounter = new WordCounter();
		
		assertEquals(wordCounter.capitalizeFirstLetter(testString),"Jack");
		assertEquals(wordCounter.capitalizeFirstLetter(testString1),"Bob");
		assertEquals(wordCounter.capitalizeFirstLetter(testString2),"Dave");
		assertEquals(wordCounter.capitalizeFirstLetter(""),"");
	}
	
	@Test
	public void getWordCountTest() {
		WordCounter wordCounter = new WordCounter();
		String testString = "A mate material may maybe right maybe";
		
		String[] response = wordCounter.getWordCount(testString);

		assertEquals(response[0],"Material: 1");
		assertEquals(response[1],"Maybe: 2");
		assertEquals(response[2],"Right: 1");
	}
}