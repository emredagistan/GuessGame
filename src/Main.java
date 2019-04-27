import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the turn based number guessing game. You are against SkyNet. Good luck.");
		System.out.println("Pick a 4 digit number. This number can have leading zero and digits must be unique.");
		System.out.println("Please score after SkyNet's prediction.");
		
		
		String command = "";
		Scanner s = new Scanner(System.in);
		do {
			SkyNet skyNet = new SkyNet(s);
			Human human = new Human(s);
			
			skyNet.setHuman(human);
			human.setSkyNet(skyNet);
			
			human.play();		// human starts first
			System.out.println("----------------------------------------------");
			System.out.println("Press any key to play again.");
			System.out.println("Press \"E\" to exit.");	

			command = s.nextLine();
			
		}
		while(!command.equalsIgnoreCase("e"));
		
		
		
		s.close();
		
	}
	
}
