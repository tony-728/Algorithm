package swea;

import java.util.Scanner;

public class problem2068 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int answer = 0;

			for (int idx = 0; idx < 10; idx++) {
				answer = Math.max(answer, sc.nextInt());
			}
			System.out.println("#%d %d".formatted(test_case, answer));

		}
		sc.close();
	}

}
