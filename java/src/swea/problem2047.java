package swea;

import java.util.Scanner;

public class problem2047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// String str = sc.nextLine().toUpperCase();
		// System.out.println(str);

		String str = sc.nextLine();

		for (int idx = 0; idx < str.length(); idx++) {
			System.out.print(Character.toUpperCase(str.charAt(idx)));
		}
		sc.close();
	}

}
