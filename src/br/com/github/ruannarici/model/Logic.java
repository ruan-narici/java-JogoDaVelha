package br.com.github.ruannarici.model;
import java.util.Scanner;

public class Logic {

	private String[][] slots = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
			
	private void showSlots() {
		System.out.println("> JOGO DA VELHA");
		System.out.println("> Developed by: Ruan Narici");
		System.out.println();
		for (int line = 0; line < 3; line++) {
			for(int column = 0; column < 3; column++) {
				System.out.print("   " + slots[line][column]);
				if (column < 2) {
						System.out.print("   |");
				}
			}
			System.out.println();
			if (line < 2) {
				System.out.println  ("-------------------------");
			}
		}
		System.out.println();
	}
	
	private boolean verifySlot(String position) {
		for (int line = 0; line < 3; line++) {
			for (int column = 0; column < 3; column++) {
				if (slots[line][column].contains(position)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void selectSlot(String position, String player) {
		switch (position) {
		case "1": slots[0][0] = slots[0][0].replace(position, player);
		case "2": slots[0][1] = slots[0][1].replace(position, player);
		case "3": slots[0][2] = slots[0][2].replace(position, player);
		case "4": slots[1][0] = slots[1][0].replace(position, player);
		case "5": slots[1][1] = slots[1][1].replace(position, player);
		case "6": slots[1][2] = slots[1][2].replace(position, player);
		case "7": slots[2][0] = slots[2][0].replace(position, player);
		case "8": slots[2][1] = slots[2][1].replace(position, player);
		case "9": slots[2][2] = slots[2][2].replace(position, player);
		}
	}
	
	private boolean verifyWinner(int moves) {
		String[] winner = new String[8];
		winner[0] = slots[0][0] + slots[0][1] + slots[0][2];
		winner[1] = slots[1][0] + slots[1][1] + slots[1][2];
		winner[2] = slots[2][0] + slots[2][1] + slots[2][2];
		winner[3] = slots[0][0] + slots[1][0] + slots[2][0];
		winner[4] = slots[0][1] + slots[1][1] + slots[2][1];
		winner[5] = slots[0][2] + slots[1][2] + slots[2][2];
		winner[6] = slots[0][0] + slots[1][1] + slots[2][2];
		winner[7] = slots[0][2] + slots[1][1] + slots[2][0];
		
		if(moves == 9) {
			System.out.println("Nobody wins.");
			return true;
		} else {
		for (int count = 0; count < winner.length; count++) {
			if (winner[count].contains("XXX")) {
				System.out.println("> Player X Win!");
				return true;
			} else if (winner[count].contains("OOO")) {
				System.out.println("> Player O Win!");
				return true;
			}
		}
		return false;
		}
	}
	
	public void playGame() {
		int moves = 0;
		int status = 0;
		String position = "null";
		Scanner write = new Scanner(System.in);
		
		showSlots();
		while(true) {
			do {//Player 1
				System.out.println("Player 1, what slot you want play?");
				position = write.next();
				while (verifySlot(position)) {
					System.out.println("Invalid position. Please, try again.");
					System.out.println("Player 1, what slot you want play?");
					position = write.next();
					status = 0;
				}
				selectSlot(position, "X");
				status = 1;
			} while(status == 0);
			moves++;
			showSlots();
			if (verifyWinner(moves)) {
				break;
			}
			do {//Player 2
				System.out.println("Player 2, what slot you want play?");
				position = write.next();
				while (verifySlot(position)) {
					System.out.println("Invalid position. Please, try again.");
					System.out.println("Player 2, what slot you want play?");
					position = write.next();
					status = 1;
				}
				selectSlot(position, "O");
				status = 0;
			} while(status == 1);
			moves++;
			showSlots();
			if (verifyWinner(moves)) {
				break;
			}
		}
	}
}
