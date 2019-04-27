import java.util.Scanner;

public class Human {

	private SkyNet skyNet;
	private Scanner s; 
	
	public Human(Scanner s) {
		this.s = s;
	}
	

	public void setSkyNet(SkyNet skyNet) {
		this.skyNet = skyNet;
	}

	public boolean isValidGuess(String number) {

		try {
			Integer.parseInt(number); // checking if entry is a number
		} catch (Exception e) {
			System.out.println(number + " is not a number.");
			return false;
		}

		if (number.length() != 4) { // checking if entry is a 4 digit number
			System.out.println("Number must be 4 digits.");
			return false;
		}

		return true;
	}

	public void play() {
		System.out.println("----------------------------------------------");
		System.out.println("Your turn to guess SkyNet's number.");
		// human plays
		String guess;
		do {
			guess = s.nextLine();
		} while (!isValidGuess(guess));

		int[] number = new int[4];
		for (int i = 0; i < 4; i++) { // convert guess to integer array
			number[i] = guess.charAt(i) - '0';
		}

		String skyNetsResponse = skyNet.calculateScore(number); // send guess to skynet to get a response
		System.out.println(skyNetsResponse);

		if (skyNetsResponse.equals("+4-0")) { // if guessed correct end the game
			System.out.println("Congratulations! You guessed correct and beat SkyNet.");
		} else {
			skyNet.play(); // computer's turn
		}

	}
	

}
