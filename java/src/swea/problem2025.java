package swea;

import java.util.Scanner;

public class problem2025 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		int answer = 0;

		for (int idx = 1; idx <= num; idx++) {
			answer += idx;
		}

		System.out.println(answer);
		sc.close();
	}
}
