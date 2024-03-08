package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1204 {

    /*
     * 
     * 최빈수 구하기
     * 
     * 빈도수가 같은 수라면 더 큰 수를 출력하라
     * 
     * 테스트케이스의 수를 입력
     * 테스트 케이스 번호
     * 
     * 숫자열이 입력된다.
     * 
     * 학생 수는 1000명
     * 점수는 0<=score<=100
     * 
     * 101개의 배열을 만들고 인덱스에 맞는 숫자에 카운트 증가
     * 
     * 정렬한 다음에 값이 맞는 인덱스 값 출력
     * 
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;

    public static int testCase;
    public static int[] scoreList;

    public static final int NumOfStudent = 1000;

    public static int maxFreq;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            maxFreq = 0;

            scoreList = new int[101];
            br.readLine().trim();

            st = new StringTokenizer(br.readLine().trim());

            // 학생들 점수 입력
            for (int idx = 0; idx < NumOfStudent; idx++) {
                scoreList[Integer.parseInt(st.nextToken())]++;
            }

            for (int idx = 1; idx <= 100; idx++) {
                maxFreq = Math.max(maxFreq, scoreList[idx]);
            }

            for (int idx = 100; idx > 0; idx--) {
                if (maxFreq == scoreList[idx]) {
                    System.out.println("#" + tc + " " + idx);
                    break;
                }
            }

        }

    }
}
