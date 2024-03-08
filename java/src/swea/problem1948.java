package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1948 {

    /*
     * 
     * 월 일로 이루어진 날짜 2개를 받아 두 번째 날짜가 첫번째 날짜의 며칠 째인지 구하라
     * 
     * 1. 입력받은 월와 일을 일로 바꾸어 두 날짜의 차이를 구한다.
     * 2. 각 달마다 마지막 날짜가 다르기 때문에 이것을 고려해야한다.
     * 
     */

    // 입력받은 월, 일까지의 일수를 계산
    public static int calTime(int month, int day) {
        int result = day;
        int[] monthlyDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // 1부터 시작하는 이유는 x월 y일일 때 x-1달 만큼의 일만 지나간것임 x월은 y일이 지나간 것이다.
        // y일을 앞에서 더하고 시작했으므로 x-1달 만큼의 일만 계산하면 된다.
        for (int idx = 1; idx < month; idx++) {
            result += monthlyDay[idx - 1];
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        int firstMonth, firstDay, secondMonth, secondDay;
        int first, second;

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int answer = 0;

            firstMonth = Integer.parseInt(st.nextToken());
            firstDay = Integer.parseInt(st.nextToken());
            secondMonth = Integer.parseInt(st.nextToken());
            secondDay = Integer.parseInt(st.nextToken());

            first = calTime(firstMonth, firstDay);
            second = calTime(secondMonth, secondDay);

            System.out.printf("first: %d, second: %d\n", first, second);

            answer = second - first + 1;

            System.out.println("#" + testCase + " " + answer);

        }
    }
}
