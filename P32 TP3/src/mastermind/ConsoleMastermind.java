package mastermind;
import java.util.Scanner;

public class ConsoleMastermind {
	
	public static void main(String[] args) {
		ModeleMastermind mastermind = new ModeleMastermind(4, 6);
		mastermind.genererCombinaison();

		System.out.println(mastermind.toString());
		Scanner scan = new Scanner(System.in);
		int[] prop = {-1, -1, -1, -1};
		int nbEssais = 0;
		while(mastermind.nbChiffresBienPlaces(prop) != 4) {
			System.out.println("nbEssais : " + nbEssais);
			System.out.println("Bien placés : " + mastermind.nbChiffresBienPlaces(prop));
			System.out.println("Mal placés : " + mastermind.nbChiffresMalPlaces(prop));
			prop[0] = scan.nextInt();
			prop[1] = scan.nextInt();
			prop[2] = scan.nextInt();
			prop[3] = scan.nextInt();
			nbEssais++;
		}
		scan.close();
		System.out.println(mastermind.toString());
	}
	
}
