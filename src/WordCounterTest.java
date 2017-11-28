import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class WordCounterTest {

	@Test
	public void firstTestExample() {
		WordCounter wordCounter = new WordCounter();
		//change to upper case later on
		String testString = "a mate material may maybe right maybe";
		
		HashMap<String, Integer> expectedResponse = new HashMap<String,Integer>();
		
		expectedResponse.put("material", 1);
		expectedResponse.put("maybe", 2);
		expectedResponse.put("right", 1);
		
		HashMap<String, Integer> response = wordCounter.countWords(testString);

		assertEquals(response.get("material"),(Integer)1);
		assertEquals(response.get("maybe"),(Integer)2);
		assertEquals(response.get("right"),(Integer)1);
		assertEquals(response.get("a"),null);
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
	public void getWordCountTest() {
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
}
