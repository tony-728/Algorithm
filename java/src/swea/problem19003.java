package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem19003 {
    /*
     * 문자 S를 뒤집어도 S와 동일하다면 S를 팰린드롬이라고 한다.
     * 
     * N개의 길이가 같은 문자열이 주어질 때
     * 몇 개의 문자열을 고른 후 문자열을 재배치하여 순서대로 합쳤을 때 팰린드롬이 되게 해야 한다.
     * 최종 팰린드롬의 길이를 최대화해라
     * 
     * 
     * 주어진 문자열을 모두 선택하는 순열부터 1개 선택하는 순열까지 만들어서
     * 만든 경우의 문자열이 팰린드롬인지 확인하고 확인이 완료되면 종료한다.
     * - 최대 길이를 구하는 것이므로 더 작은 경우는 구하지 않아도 된다.
     * -> 완탐은 시간초과 발생
     * -> n이 최대 100개 부분집합은 2^100 안됨
     * 
     * 
     * N개의 문자열을 재배치하여 펠린드롬을 만들기 위한 조건
     * 1. 단일 문자열 중 펠린드롬은 한개만 사용이 가능하다
     * 2. 단일 문자열 중 펠린드롬이 아닌 문자열 중 역순 관계를 갖는 문자열이 펠린드롬이 될 수 있다.
     * 3. 따라서 역순 관계를 갖는 문자열의 개수 * 문자열길이 + (펠린드롬 문자열이 있다면) 문자열 길이 => 정답
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfString;
    static int lengthOfString;

    static String[] stringList;
    static boolean[] isPalindromeList;

    static boolean isPalidrome;

    static int answer;

    public static void inputTestCase() throws IOException {

        answer = 0;

        isPalidrome = false;

        st = new StringTokenizer(br.readLine().trim());

        // 문자열 개수
        numOfString = Integer.parseInt(st.nextToken());
        // 문자열의 길이
        lengthOfString = Integer.parseInt(st.nextToken());

        stringList = new String[numOfString];
        isPalindromeList = new boolean[numOfString];

        for (int idx = 0; idx < numOfString; idx++) {
            String inputString = br.readLine().trim();

            stringList[idx] = inputString;
        }

        checkPalindrome();
    }

    // 단일 문자열 중 펠린드롬이 있는지 확인한다.
    public static void checkPalindrome() {

        for (int stringIdx = 0; stringIdx < numOfString; stringIdx++) {
            String currentString = stringList[stringIdx];
            boolean flag = true;
            for (int idx = 0; idx < lengthOfString; idx++) {
                if (currentString.charAt(idx) != currentString.charAt(lengthOfString - idx - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                isPalindromeList[stringIdx] = true;
                isPalidrome = true;
            }
        }

    }

    // 두 문자열이 역순관계인지 확인
    public static boolean checkPalindrome(String left, String right) {

        for (int idx = 0; idx < lengthOfString; idx++) {

            if (left.charAt(idx) != right.charAt(lengthOfString - idx - 1)) {
                return false;
            }

        }

        return true;
    }

    // 역순관계 문자열의 개수 체크
    public static void checkReverse() {

        int count = 0;

        for (int idx = 0; idx < numOfString - 1; idx++) {
            if (isPalindromeList[idx]) {
                continue;
            }
            String targetString = stringList[idx];

            for (int innerIdx = idx + 1; innerIdx < numOfString; innerIdx++) {
                if (checkPalindrome(targetString, stringList[innerIdx])) {
                    count += 2;
                    // 역순관계를 확인한 문자열은 중복확인하지 않도록 확인처리
                    isPalindromeList[idx] = true;
                    isPalindromeList[innerIdx] = true;
                }
            }
        }

        // 역순 관계에 있는 문자열 개수 * 문자열 길이
        answer = count * lengthOfString;

        // 단일 펠리드롬 문자열이 있는 경우 추가로 더해주어야 함
        if (isPalidrome) {
            answer += lengthOfString;
        }

        return;
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            checkReverse();

            sb.append(String.format("#%d %d\n", tc, answer));

        }

        System.out.println(sb);
    }
}
