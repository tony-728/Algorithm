package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2817 {
    /*
     * N개의 자연수가 ㅈ어졌을 때 최소 1개 이상의 수를 선택하여 그 합이 K가 되는 경우의 수
     * 
     * 테스트케이스수가 주어진다.
     * 첫번째 줄에는 2개의 자연수, N, K가 주어진다.
     * 두번째 줄에는 N개의 자연수 수열 A가 주어진다. 수열의 원소인 N개의 자연수는 공백을 사이에 두고 주어지며
     * 1이상 100이하이다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfNumber;
    static int standard;
    static int[] numberList;

    static int[] selectedList;

    static int answer;

    static int globalCount;

    static void inputTestCase() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfNumber = Integer.parseInt(st.nextToken());
        standard = Integer.parseInt(st.nextToken());

        numberList = new int[numOfNumber];

        // 조합에 사용하는 배열
        selectedList = new int[numOfNumber];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfNumber; idx++) {
            numberList[idx] = Integer.parseInt(st.nextToken());
        }

    }

    // 조합을 이용
    static void makeCase(int selectedIdx, int elementIdx) {

        // 선택할 갯수를 모두 채움
        if (globalCount == selectedIdx) {
            // 선택한 수의 합이 k가 되는 경우를 찾음
            int result = 0;

            for (int idx = 0; idx < numOfNumber; idx++) {
                result += selectedList[idx];
            }

            System.out.println(result);

            if (result == standard) {
                answer++;
            }

            return;
        }

        // 1. 기저 조건
        // 모든 수를 확인하였다.
        if (elementIdx == numOfNumber) {
            return;
        }

        // 인덱스의 요소를 선택한다.
        selectedList[selectedIdx] = numberList[elementIdx];

        // 재귀 호출
        makeCase(selectedIdx + 1, elementIdx + 1);

        // 인덱스의 요소를 선택하지 않는다.
        selectedList[selectedIdx] = 0;
        makeCase(selectedIdx, elementIdx + 1);

    }

    // 부분집합을 이용
    static void makeCaseByPowerSet(int selectedIdx, int sum) {
        // 수열을 모두 확인함
        if (selectedIdx == numOfNumber) {
            // 수열을 모두 확인했을 때 합이 기준과 같은지 확인
            if (sum == standard) {
                answer++;
            }
            return;
        } else {
            // 부분집합에 포함시키지 않음
            makeCaseByPowerSet(selectedIdx + 1, sum);
            // 부분집합에 포함시킴
            makeCaseByPowerSet(selectedIdx + 1, sum + numberList[selectedIdx]);
        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            makeCaseByPowerSet(0, 0);

            // 조합을 만들어서 구한다.
            // for (int count = 1; count <= numOfNumber; count++) {
            // globalCount = count;

            // makeCase(0, 0);
            // }

            sb.append(String.format("#%d %d\n", tc, answer));
        }

        System.out.println(sb);
    }
}
