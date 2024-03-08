package swea;

import java.util.Scanner;

public class problem2043 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int p = sc.nextInt();
		int k = sc.nextInt();
		int answer = 0;

		for (int idx = k; idx <= p; idx++) {
			answer++;
		}

		System.out.println(answer);

		sc.close();
	}
}
