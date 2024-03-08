package swea;

import java.util.Scanner;

public class problem2058 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String number = sc.nextLine();
		int answer = 0;

		for (int idx = 0; idx < number.length(); idx++) {
			answer += number.charAt(idx) - '0';
		}

		System.out.println(answer);
		sc.close();
	}
}
