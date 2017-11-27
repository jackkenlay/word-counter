import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterTest {

	@Test
	public void firstTestExample() {
		WordCounter wordCounter = new WordCounter();
		String testString = "A mate material may maybe right maybe";
		
		String[][] expectedResponse = {{
			"material","1"
		},{
			"maybe","2"
		},{
			"right","1"
		}};
		
		String[][] response = wordCounter.countWords(testString);
		
		for(int i = 0; i < response.length; i++) {
			for(int p = 0; p < response[i].length; p++) {
				assertEquals(response[i][p],expectedResponse[i][p]);
			}
		}
	}
}
