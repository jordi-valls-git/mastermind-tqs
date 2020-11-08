package MasterMind;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ModelTest {

	@Test
	public void testIsFilled() {
		//Test with shorter pattern
		Model m = new Model(4);
		m.generateRandomPattern();
		ArrayList<Integer> guessedPattern = new ArrayList<Integer>();
		guessedPattern.add(1);
		guessedPattern.add(2);
		guessedPattern.add(3);
		m.setGuessedPattern(guessedPattern);
		boolean result;
		result = m.isFilled();
		//It should be false because the length is 3
		assertEquals(result, false);
		
		//Test with the right length
		Model m2 = new Model(4);
		m2.generateRandomPattern();
		ArrayList<Integer> guessedPattern2 = new ArrayList<Integer>();
		guessedPattern2.add(1);
		guessedPattern2.add(2);
		guessedPattern2.add(3);
		guessedPattern2.add(4);
		m2.setGuessedPattern(guessedPattern2);
		boolean result2;
		result2 = m2.isFilled();
		//It should be true because the length is 4
		assertEquals(result2, true);
		
	}
	@Test
	public void testHasWon() {
		MockModel m = new MockModel(4);
		ArrayList<Integer> randomPattern = new ArrayList<Integer>();
		randomPattern.add(1);
		randomPattern.add(2);
		randomPattern.add(3);
		randomPattern.add(4);
		m.setRandomPattern(randomPattern);
		ArrayList<Integer> guessedPattern = new ArrayList<Integer>();
		guessedPattern.add(1);
		guessedPattern.add(2);
		guessedPattern.add(3);
		guessedPattern.add(4);
		m.setGuessedPattern(guessedPattern);
		m.checkResult();
		boolean result = false;
		if(m.getCountW()==m.getLENGTH()) {
			result = true;
		}
		//Test if it is the same result
		assertEquals(result,m.hasWon());
		
		//Test if is in different position
		MockModel m2 = new MockModel(4);
		m2.setRandomPattern(randomPattern);
		ArrayList<Integer> guessedPattern2 = new ArrayList<Integer>();
		guessedPattern2.add(2);
		guessedPattern2.add(1);
		guessedPattern2.add(4);
		guessedPattern2.add(3);
		m2.setGuessedPattern(guessedPattern2);
		m2.checkResult();
		boolean result2 = false;
		if(m2.getCountW()==m2.getLENGTH()) {
			result2 = true;
		}
		assertEquals(result2,m2.hasWon());
		
	}
	
	@Test
	public void testGenerateRandomPattern() {
		Model m = new Model(4);
		m.generateRandomPattern();
		//Test if size of array is equal to LENGTH
		assertEquals(m.getRandomPattern().size(), m.getLENGTH());
		//Test if there is repeated numbers
		boolean repeatedNumbers = false;
		Set<Integer> set = new HashSet<Integer>(m.getRandomPattern());
		if(set.size() < m.getRandomPattern().size()) {
			repeatedNumbers = true;
		}	
		assertEquals(repeatedNumbers, false);
		//Test if there is all numbers are between 0 and 9
		boolean wrongNumber = false;
		for(int i=0; i<m.getRandomPattern().size(); i++) {
			if (m.getRandomPattern().get(i) < 0 && m.getRandomPattern().get(i) > 9) {
				wrongNumber = true;
			}
		}
		assertEquals(wrongNumber, false);
	}
	
	@Test
	public void testCheckResult() {
		MockModel m = new MockModel(4);
		ArrayList<Integer> randomPattern = new ArrayList<Integer>();
		randomPattern.add(1);
		randomPattern.add(2);
		randomPattern.add(3);
		randomPattern.add(4);
		m.setRandomPattern(randomPattern);
		ArrayList<Integer> guessedPattern = new ArrayList<Integer>();
		guessedPattern.add(1);
		guessedPattern.add(2);
		guessedPattern.add(3);
		guessedPattern.add(4);
		m.setGuessedPattern(guessedPattern);
		m.checkResult();
		//Test if it is the same result
		assertEquals(m.getCountW(),4);
		assertEquals(m.getCountB(),0);
		//Test if is in different position
		MockModel m2 = new MockModel(4);
		m2.setRandomPattern(randomPattern);
		ArrayList<Integer> guessedPattern2 = new ArrayList<Integer>();
		guessedPattern2.add(2);
		guessedPattern2.add(1);
		guessedPattern2.add(4);
		guessedPattern2.add(3);
		m2.setGuessedPattern(guessedPattern2);
		m2.checkResult();
		assertEquals(m2.getCountW(),0);
		assertEquals(m2.getCountB(),4);
	}
	
	@Test
	public void testResetGuessedPattern() {
		MockModel m = new MockModel(4);
		ArrayList<Integer> randomPattern = new ArrayList<Integer>();
		randomPattern.add(1);
		randomPattern.add(2);
		randomPattern.add(3);
		randomPattern.add(4);
		m.setRandomPattern(randomPattern);
		ArrayList<Integer> guessedPattern = new ArrayList<Integer>();
		guessedPattern.add(1);
		guessedPattern.add(2);
		guessedPattern.add(3);
		guessedPattern.add(4);
		m.setGuessedPattern(guessedPattern);
		m.checkResult();
		m.resetGuessedPattern();
		//Test if countWhite is 0
		assertEquals(m.getCountW(),0);
		//Test if countBlack is 0
		assertEquals(m.getCountB(),0);
	}
}
