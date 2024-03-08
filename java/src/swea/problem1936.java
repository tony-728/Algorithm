package swea;

import java.util.Scanner;

public class problem1936 {

	/*
	 * 가위: 1
	 * 바위: 2
	 * 보: 3
	 */

	static int A;
	static int B;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();

		switch (A) {
			case 1: {
				if (A - B == -2)
					System.out.println("A");
				else
					System.out.println("B");
				break;
			}
			case 2: {
				if (A - B == 1)
					System.out.println("A");
				else
					System.out.println("B");
				break;
			}
			case 3: {
				if (A - B == 1)
					System.out.println("A");
				else
					System.out.println("B");
				break;
			}
		}
		sc.close();
	}
}
