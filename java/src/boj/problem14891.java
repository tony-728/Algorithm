package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14891 {
    /*
     * 
     * 4개의 톱니바퀴 자석이 있다. 하나의 자석이 1칸 회전할 때 서로 붙어있는 날의 자성과 다를 경우 반대방향으로 1칸 회전한다. 무작위로
     * k번 회전을 했을 때 - 1번자석에 0번 위치의 자성이 N이면 0, S면 1점 - 2번자석에 0번 위치의 자성이 N이면 0, S면 2점 -
     * 3번자석에 0번 위치의 자성이 N이면 0, S면 4점 - 4번자석에 0번 위치의 자성이 N이면 0, S면 8점
     * 
     * k번 회전이 끝난 후 점수의 합을 구하라
     * 
     * 입력 - 4개의 자석이 - 0번 위치부터 시계방향으로 자성이 주어진다. - N극은 0, S극은 1로 주어진다. - 시계방향은 1,
     * 반시계방향은 -1
     * 
     * 
     * 자석을 deque로 하여 사용한다. 1 -> 2번 자석은 2번과 6번이 매칭 2 -> 3번 자석은 2번과 6번이 매칭 3 -> 4번 자석은
     * 2번과 6번이 매칭 이 된다.
     * 
     * 자석 번호 2, 3번 은 양쪽으로 자석이 있다는 것을 고려해야한다.
     * 
     */

    static final int LEFT = -1;
    static final int RIGHT = 1;

    static final int N_POLE = 0;
    static final int S_POLE = 1;

    static final int BASE_SCORE = 2;

    static final int NUM_OF_MAGNETIC = 4;
    static final int NUM_OF_POINT = 8;

    static final int LEFT_POINT = 6;
    static final int RIGHT_POINT = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int testCase;
    static int numOfCommand;
    static int[][] magneticList = new int[NUM_OF_MAGNETIC][NUM_OF_POINT];
    static boolean[] visited;

    public static void inputTestCase() throws IOException {

        answer = 0;

        for (int magIdx = 0; magIdx < NUM_OF_MAGNETIC; magIdx++) {
            int[] magnetic = new int[NUM_OF_POINT];
            String row = br.readLine().trim();

            for (int idx = 0; idx < NUM_OF_POINT; idx++) {
                magnetic[idx] = row.charAt(idx) - '0';
            }
            magneticList[magIdx] = magnetic;
        }

        numOfCommand = Integer.parseInt(br.readLine().trim());

    }

    // 자석 배열 회전시키기
    public static void spin(int magIdx, int direction) {

        int[] magnetic = magneticList[magIdx];

        // 왼쪽으로 회전
        if (direction == LEFT) {
            // 첫번째 값
            int first = magnetic[0];

            for (int idx = 0; idx < NUM_OF_POINT - 1; idx++) {

                magnetic[idx] = magnetic[idx + 1];
            }

            magnetic[NUM_OF_POINT - 1] = first;

            // 오른쪽으로 회전
        } else if (direction == RIGHT) {
            // 마지막 값
            int last = magnetic[NUM_OF_POINT - 1];

            for (int idx = NUM_OF_POINT - 1; idx > 0; idx--) {

                magnetic[idx] = magnetic[idx - 1];
            }

            magnetic[0] = last;

        }
    }

    public static void checkSpin(int magIdx, int direction) {

        int[] magnetic = magneticList[magIdx];

        // 기준 자석에 3번째 7번째 자석이 무엇인지 확인
        int leftPoint = magnetic[LEFT_POINT];
        int rightPoint = magnetic[RIGHT_POINT];

        // 양쪽에 자석이 있으면 영향을 주는지 확인
        int leftMag = magIdx - 1;
        int rightMag = magIdx + 1;

        // 범위안에 존재하는지 확인
        if (leftMag > -1 && visited[leftMag] == false) {
            // 서로 다른 극이면 반대바향으로 회전
            // 현재의 왼쪽 포인트는 왼쪽 자석의 오른쪽 포인트와 비교
            if (leftPoint != magneticList[leftMag][RIGHT_POINT]) {
                visited[leftMag] = true;
                checkSpin(leftMag, direction * -1);
            }

        }

        if (rightMag < NUM_OF_MAGNETIC && visited[rightMag] == false) {
            // 서로 다른 극이면 반대바향으로 회전
            // 현재의 오른쪽 포인트는 오른쪽 자석의 왼쪽 포인트와 비교
            if (rightPoint != magneticList[rightMag][LEFT_POINT]) {

                visited[rightMag] = true;
                checkSpin(rightMag, direction * -1);
            }
        }

        // 현재 자석은 direction방향으로 회전한다.
        // 배열을 회전시키는 함수 호출
        spin(magIdx, direction);
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        // 자석 회전시키기
        for (int idx = 0; idx < numOfCommand; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            int magIdx = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            visited = new boolean[NUM_OF_MAGNETIC];

            visited[magIdx - 1] = true;
            checkSpin(magIdx - 1, direction);
        }

        // 자석 화살표 위치에 극 확인하여 점수 계산하기
        for (int idx = 0; idx < NUM_OF_MAGNETIC; idx++) {
            int mag = magneticList[idx][0];

            if (mag == S_POLE) {
                answer += Math.pow(BASE_SCORE, idx);
            }
        }

        System.out.println(answer);
    }
}
