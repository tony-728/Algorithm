package swea;

import java.util.Scanner;

public class problem1938 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a, b;

		a = sc.nextInt();
		b = sc.nextInt();

		for (int idx = 0; idx < 4; idx++) {
			if (idx == 0)
				System.out.println(a + b);
			else if (idx == 1)
				System.out.println(a - b);
			else if (idx == 2)
				System.out.println(a * b);
			else if (idx == 3)
				System.out.println((int) (a / b));
		}
		sc.close();
	}

}
