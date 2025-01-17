package boj;

import java.io.*;
import java.util.*;

public class problem22251 {
    /*
     * 1부터 N층까지 있다.
     * K자리의 디스플레이가 있다.
     * 0으로 시작할 수 있고 0부터 9까지 보여준다.
     * - 각 숫자는 7개의 표시등 중 일부에 불이 들어오면서 표현된다.
     * 
     * 실제 x층에 멈춰있을 때 반전시킬 LED를 고를 수 있는 경우의 수를 구하라
     * 
     * 앞에 디스플레이부터 바꿀 수 있는 경우의 수를 체크하면서 경우의 수를 확인한다.
     * 
     *  0부터 9까지 디스플레이를 불리언 배열로 만들어서 관리
     *  1부터 최대 층까지 디스플레이 화를 진행한다.
     *  현재 층과 디스플레이 반전 차이를 구한다.
     *  반전 차이가 최대 반전차이보다 작거나 같으면 카운트
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int maxFloor;
    static int displayCount;
    static int reverseCount;
    static int currentFloor;

    static int answer = 0;

    //                              0       1     2     3     4     5     6
    static final boolean[] ZERO = {true, true, false, true, true, true, true,};
    static final boolean[] ONE = {false, true, false, true, false, false, false,};
    static final boolean[] TWO = {true, true, true, false, true, true, false,};
    static final boolean[] THREE = {true, true, true, true, true, false, false,};
    static final boolean[] FOUR = {false, true, true, true, false, false, true,};
    static final boolean[] FIVE = {true, false, true, true, true, false, true,};
    static final boolean[] SIX = {true, false, true, true, true, true, true,};
    static final boolean[] SEVEN = {true, true, false, true, false, false, false,};
    static final boolean[] EIGHT = {true, true, true, true, true, true, true,};
    static final boolean[] NINE = {true, true, true, true, true, false, true,};

    static final boolean[][] DISPLAY = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};

    static boolean[][] currentDisplay;

    static boolean checkReverse(boolean[][] display) {

        int count = 0;

        for (int idx = 0; idx < displayCount; idx++) {

            for (int numberIdx = 0; numberIdx < 7; numberIdx++) {
                if (display[idx][numberIdx] != currentDisplay[idx][numberIdx]) {
                    count++;
                }

                if (count > reverseCount) {
                    return false;
                }
            }
        }

        return true;
    }

    static void solution() {

        boolean[][] display = new boolean[displayCount][10];

        for (int number = 1; number <= maxFloor; number++) {
            // 현재 위치를 디스플레이로 변환

            if (number == currentFloor) {
                continue;
            }

            int temp = number;
            for (int idx = displayCount - 1; idx >= 0; idx--) {
                int x = (int) Math.pow(10, idx);

                display[idx] = DISPLAY[temp / x];

                temp = temp - (x * (temp / x));
            }

            // 현재 위치와 실제 위치와의 반전 카운트 체크
            if (checkReverse(display)) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        maxFloor = Integer.parseInt(st.nextToken()); // 최대 층
        displayCount = Integer.parseInt(st.nextToken()); // 디스플레이 수
        reverseCount = Integer.parseInt(st.nextToken()); // 반전 개수
        currentFloor = Integer.parseInt(st.nextToken()); // 현재 층

        currentDisplay = new boolean[displayCount][10];

        int temp = currentFloor;
        for (int idx = displayCount - 1; idx >= 0; idx--) {
            int x = (int) Math.pow(10, idx);

            currentDisplay[idx] = DISPLAY[temp / x];

            temp = temp - (x * (temp / x));

        }

        solution();

        System.out.println(answer);
    }
}
