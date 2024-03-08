package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem5215 {

    /*
     * 
     * 맛을 최대로 칼로리는 최소로 만드는 햄버거 조합을 찾자
     * - 칼로리가 정해져 있음
     * 
     * 만든 햄버거는 맛의 합으로 이루어지고 같은 재료를 여러 번 사용할 수 없다.
     * 
     * 
     * 테스트 케이스를 입력받는다.
     * 재료들 개수와 제한 칼로리를 입력받는다.
     * 각 재료들의 맛과 칼로리를 입력받는다.
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int testCase;
    public static int elementCount;
    public static int limitKal;
    public static int[] scoreList;
    public static int[] kalList;

    // 부분집합
    public static boolean[] usedElementList;

    // 조합
    public static int selectCount;
    public static int[] selectElementList;

    public static int answer;
    public static int totalScore;
    public static int totalKal;

    public static void powerSet(int selectIdx) {
        // 1. 기저 조건
        if (selectIdx == elementCount) {
            totalScore = 0;
            totalKal = 0;

            // 햄버거 칼로리와 점수를 계산
            for (int idx = 0; idx < elementCount; idx++) {
                if (usedElementList[idx]) {
                    totalScore += scoreList[idx];
                    totalKal += kalList[idx];
                }

                if (totalKal > limitKal) {
                    break;
                }
            }

            if (totalKal <= limitKal) {
                answer = Math.max(answer, totalScore);
            }

            return;
        }

        usedElementList[selectIdx] = true;
        powerSet(selectIdx + 1);

        usedElementList[selectIdx] = false;
        powerSet(selectIdx + 1);
    }

    public static void combination(int selectIdx, int elementIdx) {
        if (selectIdx == selectCount) {
            totalScore = 0;
            totalKal = 0;

            // 햄버거 칼로리와 점수를 계산
            for (int idx : selectElementList) {

                totalScore += scoreList[idx];
                totalKal += kalList[idx];

                if (totalKal > limitKal) {
                    break;
                }
            }

            if (totalKal <= limitKal) {
                answer = Math.max(answer, totalScore);
            }

            return;
        }

        if (elementIdx == elementCount) {
            return;
        }

        selectElementList[selectIdx] = elementIdx;
        combination(selectIdx + 1, elementIdx + 1);

        selectElementList[selectIdx] = 0;
        combination(selectIdx, elementIdx + 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            answer = 0;
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine().trim());

            elementCount = Integer.parseInt(st.nextToken());
            limitKal = Integer.parseInt(st.nextToken());

            scoreList = new int[elementCount];
            kalList = new int[elementCount];

            usedElementList = new boolean[elementCount];

            // 재료들 점수와 칼로리 입력받기
            for (int idx = 0; idx < elementCount; idx++) {
                st = new StringTokenizer(br.readLine().trim());

                scoreList[idx] = Integer.parseInt(st.nextToken());
                kalList[idx] = Integer.parseInt(st.nextToken());
            }

            // 부분집합
            // powerSet(0);

            // 조합
            // 조합을 만드는 개수를 1~재료의 개수만큼 만들어야함
            for (int idx = 1; idx <= elementCount; idx++) {
                selectCount = idx;
                selectElementList = new int[idx];
                combination(0, 0);
            }

            sb.append(String.format("#%d %d", tc, answer));

            System.out.println(sb);
        }

    }

}