package MasterMind;

import java.util.ArrayList;
import java.util.Random;

public class Model {
	private int LENGTH = 0;
	private ArrayList<Integer> randomPattern = new ArrayList<Integer>();
	private ArrayList<Integer> guessedPattern = new ArrayList<Integer>();
	private int countWhite = 0;
	private int countBlack = 0;
	int turn;
	int initialTurns=10;
	private ArrayList<Integer>[] guessedPatterns = new ArrayList[initialTurns];
	private ArrayList<String>[] hitsPattern = new ArrayList[initialTurns];
	
	public Model(int LENGTH, int turn){
		this.LENGTH = LENGTH;
		countWhite = 0;
		countBlack = 0;
		this.turn = turn;
		this.initialTurns = turn;
		guessedPattern.clear();
		randomPattern.clear();		
		//initializeLists
		this.initializeLists();
		//generate the winner pattern
		this.generateRandomPattern();
	}
	
	public void initializeLists() {
		for (int i=0; i<this.initialTurns; i++) {
			guessedPatterns[i] = new ArrayList<Integer>();
			hitsPattern[i] = new ArrayList<String>();
		}
	}
	
	 //Getters
	int getLENGTH() {return LENGTH;}
	int getTurn() {return turn;}
	int getInitialTurns() {return initialTurns;}
	ArrayList<Integer> getRandomPattern(){return randomPattern;}
	ArrayList<Integer> getGuessedPattern(){return guessedPattern;}
	ArrayList<Integer>[] getGuessedPatterns(){return guessedPatterns;}
	ArrayList<String>[] getHitsPattern(){return hitsPattern;}
	int getCountW() {return countWhite;}
	int getCountB() {return countBlack;}
	public void setRandomPattern(ArrayList<Integer> randomPattern) {
		this.randomPattern = randomPattern;
	}
	public void setGuessedPattern(ArrayList<Integer> guessedPattern) {
		this.guessedPattern = guessedPattern;
	}
	
	public void generateRandomPattern() {
		for (int i=0; i<LENGTH; i++) {
			boolean repeatedNumber = true;
			Random randomGenerator = new Random();
			if(randomPattern.size() < LENGTH) {
				while(repeatedNumber) {
					int randomNumber = randomGenerator.nextInt(10);
					if(!randomPattern.contains(randomNumber)) {
						randomPattern.add(randomNumber);
						repeatedNumber = false;
					}
				}	
			}
		}
	}
	
	public void checkResult() {
		for(int i=0; i<LENGTH; i++) {
			if(randomPattern.contains(guessedPattern.get(i))) {
				if(randomPattern.get(i)==guessedPattern.get(i)) {
					countWhite++;
					//Correct number and position = W
					hitsPattern[this.initialTurns - this.turn].add("W");
				}else {
					countBlack++;
					//Correct number but wrong position = B
					hitsPattern[this.initialTurns - this.turn].add("B");
				}
			} else {
				//Wrong number = X
				hitsPattern[this.initialTurns - this.turn].add("X");
			}
		}
	}
	
	boolean isFilled() {
		if(guessedPattern.size() == LENGTH) {
			return true;
		}else {
			return false;
		}	
	}
	
	boolean hasWon(){
		if(countWhite == LENGTH) {
			return true;
		}else {
			return false;
		}
	}
	
	public void updateTurn() {
		this.turn--;
	}
	
	public void resetGuessedPattern(){
		countWhite = 0;
		countBlack = 0;
		guessedPattern.clear();
		randomPattern.clear();
	}
	
}
