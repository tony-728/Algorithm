package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1946 {

    /*
     * 
     * 압축된 문서의 알파벳과 숫자 쌍의 개수 1<= N <= 10
     * 알파벳은 A-Z
     * 알파벳 연속된 개수
     * 
     * 원본 문서의 너비는 10으로 고정
     * 
     * 1. 테스트 케이스를 입력받는다.
     * 2. 문서의 개수 N을 입력받는다.
     * 3. 각 문서의 알파벳과 알파벳 등장 횟수를 N번 입력받는다.
     * 4. 압축이 해제된 문자을 10만큼씩 출력한다. 10이 넘어가면 개행
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int testCase;
    public static int docCount;
    public static String[] alphabetList;
    public static int[] loopList;

    public static int loop;
    public static int enterCount;

    public static String alphabet;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sb = new StringBuilder();
            enterCount = 0;
            sb.append(String.format("#%d\n", tc));

            docCount = Integer.parseInt(br.readLine().trim());

            alphabetList = new String[docCount];
            loopList = new int[docCount];

            // 알파벳과 반복 순서 입력
            for (int docIdx = 0; docIdx < docCount; docIdx++) {
                st = new StringTokenizer(br.readLine().trim());

                alphabetList[docIdx] = st.nextToken();
                loopList[docIdx] = Integer.parseInt(st.nextToken());
            }

            // 알파벳 별로 반복된 숫자를 입력한다.
            // 10개가 되면 개행한다.
            for (int docIdx = 0; docIdx < docCount; docIdx++) {
                loop = loopList[docIdx]; // 몇번 반복할 것인지
                alphabet = alphabetList[docIdx];

                for (int count = 0; count < loop; count++) {
                    sb.append(alphabet);
                    enterCount++;

                    if (enterCount % 10 == 0) {
                        sb.append("\n");
                    }
                }
            }

            System.out.println(sb);
        }
    }
}