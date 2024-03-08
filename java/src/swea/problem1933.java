package swea;

import java.util.Scanner;

public class problem1933 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int idx = 1; idx <= n; idx++) {
			if (n % idx == 0)
				System.out.print(idx + " ");
		}

		sc.close();
	}

}
