package boj;

import java.io.*;

public class problem17615 {
    /*
     * 볼을 옮기는 규칙
     * 
     * 1. 바로 옆에 다른 색깔의 볼이 있으면 그 볼을 모두 뛰어 넘어 옮길 수 있다.
     * 2. 옮길 수 있는 볼의 색깔은 한 가지이다. 
     * - 빨간볼을 처음에 옮겼으면 다음에도 빨간색 볼만 옮길 수 있다.
     * 
     * 규칙에 따라 볼을 옮길 때 최소 이동횟수를 찾는 프로그램을 작성하시오
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int numOfBall;
    static char[] ballList;

    static final char RED = 'R';
    static final char BLUE = 'B';

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        numOfBall = Integer.parseInt(br.readLine().trim());

        ballList = new char[numOfBall];

        String input = br.readLine().trim();

        // 빨간색 파란색 공의 위치를 저장
        for (int idx = 0; idx < numOfBall; idx++) {
            ballList[idx] = input.charAt(idx);
        }
    }

    static void solution() {

        boolean redSwitch = false;
        boolean blueSwitch = false;

        int redCount = 0;
        int blueCount = 0;

        // 정방향
        for (int idx = 0; idx < numOfBall; idx++) {
            char ball = ballList[idx];

            // 레드가 기준
            if (!redSwitch && ball == BLUE) {
                redSwitch = true;
            }

            if (redSwitch && ball == RED) {
                redCount++;
            }

            // 블루가 기준
            if (!blueSwitch && ball == RED) {
                blueSwitch = true;
            }

            if (blueSwitch && ball == BLUE) {
                blueCount++;
            }

        }


        answer = Math.min(redCount, blueCount);

        redSwitch = false;
        blueSwitch = false;

        redCount = 0;
        blueCount = 0;


        // 역방향
        for (int idx = numOfBall - 1; idx > -1; idx--) {
            char ball = ballList[idx];

            // 레드가 기준
            if (!redSwitch && ball == BLUE) {
                redSwitch = true;
            }

            if (redSwitch && ball == RED) {
                redCount++;
            }

            // 블루가 기준
            if (!blueSwitch && ball == RED) {
                blueSwitch = true;
            }

            if (blueSwitch && ball == BLUE) {
                blueCount++;
            }

        }

        answer = Math.min(answer, redCount);
        answer = Math.min(answer, blueCount);

    }

    public static void main(String[] args) throws IOException {

        inputData();

        solution();

        System.out.println(answer);

    }
}
