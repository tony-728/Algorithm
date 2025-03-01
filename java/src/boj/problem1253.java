package boj;

import java.io.*;
import java.util.*;

public class problem1253 {
    /*
     * N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있으면 그 수를 좋다라고 한다.
     * 좋은 수의 개수를 구하라
     * 
     * 수의 위치가 다르면 값이 같아도 다른 수 이다.
     * 
     * 조합
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfNumber;
    static int[] arrOfNumber;
    static boolean[] visited;

    static int answer;

    static void inputData() throws IOException {
        answer = 0;
        numOfNumber = Integer.parseInt(br.readLine().trim());

        arrOfNumber = new int[numOfNumber];
        visited = new boolean[numOfNumber];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfNumber; idx++) {
            arrOfNumber[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrOfNumber);

    }

    static void solution() {
        for (int idx = numOfNumber - 1; idx > -1; idx--) {
            int target = arrOfNumber[idx];

            out: for (int innerIdx = 0; innerIdx < numOfNumber; innerIdx++) {
                if (idx == innerIdx) {
                    continue;
                }

                int diff = target - arrOfNumber[innerIdx];

                int left = 0;
                int right = numOfNumber - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (diff < arrOfNumber[mid]) {
                        right = mid - 1;
                    } else if (diff > arrOfNumber[mid]) {
                        left = mid + 1;
                    } else {
                        if (mid != idx && mid != innerIdx) {
                            answer++;
                            break out;
                        }
                        right = mid - 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
