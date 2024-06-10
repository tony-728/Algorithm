package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem5789 {
    /*
     * 1번부터 N번까지 N개의 상자를 가지고 있다.
     * 각 상자에는 숫자가 있고 0으로 시작한다.
     * 
     * Q회 동안 일정 범위의 연속한 상자를 동일한 숫자로 변경하려고 한다.
     * 변경하는 방법
     * - i번째 작업에 대해 L번 상자부터 R번 상자까지의 값을 i로 변경한다.
     * 
     * q회 동안 작업을 수행한 뒤 N개의 상자에 적혀있는 값들을 순서대로 출력하는 프로그램을 작성
     * 
     * 
     * T개의 테스트케이스가 주어진다.
     * N, Q가 공백으로 주어진다.
     * Q개의 줄의 i번째 줄에는 L, R이 주어진다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int totalBox;
    static int numOfAction;

    static int[] boxList;

    static int[] startList;
    static int[] endList;

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        // 상자의 개수와 행동 횟수를 입력
        totalBox = Integer.parseInt(st.nextToken());
        numOfAction = Integer.parseInt(st.nextToken());

        boxList = new int[totalBox + 1]; // 상자가 1번부터 시작함

        startList = new int[numOfAction];
        endList = new int[numOfAction];

        // 작업의 범위 입력
        for (int actionIdx = 0; actionIdx < numOfAction; actionIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            startList[actionIdx] = Integer.parseInt(st.nextToken());
            endList[actionIdx] = Integer.parseInt(st.nextToken());
        }

    }

    static void boxAction() {
        // 작업 횟수
        for (int actionIdx = 0; actionIdx < numOfAction; actionIdx++) {
            for (int idx = startList[actionIdx]; idx <= endList[actionIdx]; idx++) {
                boxList[idx] = actionIdx + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            boxAction();

            sb.append("#").append(tc).append(" ");
            for (int idx = 1; idx <= totalBox; idx++) {
                sb.append(boxList[idx]).append(" ");
            }
            sb.append("\n");

        }
        System.out.println(sb);

    }
}
