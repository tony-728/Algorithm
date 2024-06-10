package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem13218 {
    /*
     * 3명의 이상의 학생으로 구성된 조의 수를 최대화해야한다.
     * 각 학생은 정확히 한 개의 조에만 속할 수 있다.
     * 
     * 학생들을 조로 적당히 나누었을 때 3명 이상의 학생으로 구성된 조의 수의 최댓값이 얼마인지 구하라
     * 
     * 테스트케이스 수를 입력받는다.
     * 
     * 테스트케이스 수 만큼 학생의 수를 입력받는다.
     * 3명 이상의 학생으로 구성된 조의 수의 최대값을 구한다.
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int totalStudent;
    static int answer;

    static final int STANDARD = 3;

    // 입력데이터를 받는 메서드
    // 총 학생의 수를 입력받는다.
    static void inputTestCase() throws IOException {
        totalStudent = Integer.parseInt(br.readLine().trim());
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            answer = totalStudent / STANDARD;

            sb.append(String.format("#%d %d\n", tc, answer));
        }
        
        System.out.println(sb);
    }
}
