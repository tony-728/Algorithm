package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem4012 {

    /*
     * N개의 식재료가 있다. N은 짝수
     * 식재료를 반씩 나누어 두 개의 요리를 한다.
     * 
     * 식재료끼리의 시너지효과가 있다.
     * 
     * 식재료로 A, B 음식을 만들때 두 음식의 시너지 점수가 최소가 되도독 만들자
     * 
     * 첫번째 값을 기준으로 1/N 순열을 만들면 식재료를 활용하여 만들 수 있는 음식 2가지가 만들어진다.
     * 순열을 만들고 남은 나머지 가 음식B가 된다.
     * 
     * 이 조합들 중에서 음식의 점수 차이가 최소인 값을 찾자
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int testCase;
    public static int type;
    public static int[][] scoreMap;

    public static int[] selectElementList;
    public static int[] unSelectElementList;
    public static int[] elementList;
    public static boolean[] usedElementList;

    public static int answer;

    public static void combination(int selectIdx, int elementIdx) {

        // 기저조건
        if (selectIdx == type >> 1) {
            // 점수 계산을 해야한다.

            int scoreA = 0;
            int scoreB = 0;

            // 선택되지 않은 요소 리스트를 만듬
            int unselectIdx = 0;
            for (int idx = 0; idx < type; idx++) {
                if (!usedElementList[idx]) {
                    unSelectElementList[unselectIdx++] = idx;
                }
            }

            // 음식 A, B를 만듬
            for (int rowIdx = 0; rowIdx < (type >> 1); rowIdx++) {
                for (int colIdx = 0; colIdx < (type >> 1); colIdx++) {
                    scoreA += scoreMap[selectElementList[rowIdx]][selectElementList[colIdx]];
                    scoreB += scoreMap[unSelectElementList[rowIdx]][unSelectElementList[colIdx]];
                }
            }

            answer = Math.min(answer, Math.abs(scoreA - scoreB));

            return;
        }

        if (elementIdx == type) {

            return;
        }

        // 2. 전처리
        selectElementList[selectIdx] = elementIdx;

        usedElementList[elementIdx] = true;
        combination(selectIdx + 1, elementIdx + 1);

        // 2. 전처리
        selectElementList[selectIdx] = 0;
        usedElementList[elementIdx] = false;

        // 첫번째 요소로 시작하는 조합만으로 모든 경우를 확인가능하므로 가지치기
        if (usedElementList[0] == false) {
            return;
        }

        combination(selectIdx, elementIdx + 1);

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sb = new StringBuilder();

            answer = 1_000_000;

            type = Integer.parseInt(br.readLine().trim());
            scoreMap = new int[type][type];

            // 재료 시너지 점수 입력
            for (int rowIdx = 0; rowIdx < type; rowIdx++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int colIdx = 0; colIdx < type; colIdx++) {
                    scoreMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
            }

            // 조합을 만들기 위한 준비
            elementList = new int[type];
            selectElementList = new int[type >> 1];
            unSelectElementList = new int[type >> 1];
            usedElementList = new boolean[type];

            for (int idx = 0; idx < type; idx++) {
                elementList[idx] = idx;
            }
            combination(0, 0);

            sb.append(String.format("#%d %d", tc, answer));

            System.out.println(sb);
        }
    }

}