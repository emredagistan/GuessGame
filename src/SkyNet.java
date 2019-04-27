import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SkyNet {

	private int[] skyNetsNumber; // computer's number
	private Human human;

	private List<int[]> possibleNumbers; // all possible numbers (initially 5040)

	private Random random;
	private Scanner s;

	public SkyNet(Scanner s) {
		skyNetsNumber = new int[4];
		possibleNumbers = new ArrayList<>();
		random = new Random();
		this.s = s;
		generatePossibleNumbers();
		skyNetsNumber = pickANumber(); // computer picks a number
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	private int[] pickANumber() {
		int n = random.nextInt(possibleNumbers.size());
		return possibleNumbers.get(n);
	}

	private void generatePossibleNumbers() {	// generate numbers set

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					for (int l = 0; l < 10; l++) {
						if (i != j) {
							if (i != k) {
								if (i != l) {
									if (j != k) {
										if (j != l) {
											if (k != l) {
												int[] number = { i, j, k, l };
												possibleNumbers.add(number);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	public void play() {
		System.out.println("----------------------------------------------");
		System.out.println("SkyNet's turn to guess your number.");
		System.out.println("There are " + possibleNumbers.size() + " possible numbers.");

		// skynet plays
		System.out.println("SkyNet guessed: ");
		int[] guess = pickANumber();
		for (int i : guess) {
			System.out.print(i);
		}
		System.out.println();

		String score;
		do {
			score = s.nextLine();
		} while (!isValidScoring(score));

		if (score.equals("+4-0")) {
			System.out.println("SkyNet guessed correct and beat you!");
		} else {
			removeMismatches(score, guess); // remove mismatches from pool
			if (possibleNumbers.size() == 0) {
				System.out.println("I couldn't find the number. Did you cheat?");
			} else {
				human.play(); // human's turn
			}

		}

	}

	private void removeMismatches(String score, int[] guess) {

		Iterator<int[]> iterator = possibleNumbers.iterator();

		while (iterator.hasNext()) {
			int[] possibleNumber = iterator.next();
			if (!score.equals(calculateScore(possibleNumber, guess))) {
				iterator.remove();
			}
		}

	}

	private boolean isValidScoring(String score) {		//check user scoring format

		if (score.length() != 4) {
			System.out.println("Scoring format is wrong. Example: \"+1-2\"");
			return false;
		}

		int x = score.charAt(1) - '0';
		int y = score.charAt(3) - '0';

		if (score.charAt(0) != '+' || score.charAt(2) != '-' || x < 0 || x > 4 || y < 0 || y > 4 || x + y > 4) {
			System.out.println("Wrong scoring. Example: \"+1-2\"");
			return false;
		}

		return true;
	}
	
	// little overloading to decrease code repetition 
	public String calculateScore(int[] number) {
		return calculateScore(number, skyNetsNumber);
	}

	private String calculateScore(int[] number, int[] actual) {

		int inPlaceCounter = 0;
		int includeCounter = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				if (number[i] == actual[j]) {
					if (i == j) {
						inPlaceCounter++;
					} else {
						includeCounter++;
					}
				}

			}
		}

		return "+" + inPlaceCounter + "-" + includeCounter;
	}

}
