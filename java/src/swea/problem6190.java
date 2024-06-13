package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem6190 {
    /*
     * 단조 증가하는 수, 각 숫자의 자릿수가 단순하게 증가하는 수를 말한다.
     * 각 자리수가 크거나 같아야 한다.
     * 
     * N개에 정수에서 두 수를 곱한 값이 단조 증가인 수를 구하고 그 중 최대값을 구하라
     * 
     * 
     * 테스트 케이스가 주어진다.
     * 첫번째 줄에는 하나의 정수 N이 주어진다.
     * 두번째 줄에는 N개의 정수가 주어진다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfNumber;
    static int[] numberList;
    static int answer;

    static void inputTestCase() throws IOException {

        answer = -1;
        numOfNumber = Integer.parseInt(br.readLine().trim());

        numberList = new int[numOfNumber];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfNumber; idx++) {
            numberList[idx] = Integer.parseInt(st.nextToken());
        }
    }

    static void checkIncreasingNumber(int number) {
        String stringNumber = String.valueOf(number);

        int digit = stringNumber.charAt(0) - '0';

        for (int idx = 1; idx < stringNumber.length(); idx++) {
            int temp = stringNumber.charAt(idx) - '0';

            // 현재 자리수의 값이 이전 자리수보다 작으면 안됨
            if (digit > temp) {
                return;
            }

            // 비교할 자리수 갱신
            digit = temp;
        }

        answer = Math.max(answer, number);

    }

    static void makeNumber() {
        for (int idx = 0; idx < numOfNumber - 1; idx++) {
            for (int innerIdx = idx + 1; innerIdx < numOfNumber; innerIdx++) {

                int value = numberList[idx] * numberList[innerIdx];
                checkIncreasingNumber(value);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            inputTestCase();

            makeNumber();

            sb.append(String.format("#%d %d\n", tc, answer));

        }

        System.out.println(sb);

    }
}
