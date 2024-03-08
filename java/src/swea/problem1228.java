package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class problem1228 {
    /*
     * 암호문을 수정한 후 처음 10개 숫자를 출력하라
     * 
     * 수정하는 방법
     * 앞에서부터 x의 위치 바로 다음 y개의 숫자를 삽입
     * 
     * 총 10개의 테스트 케이스
     * 
     * 원본 암호문의 길이 10 <= N <= 20
     * 원본 암호문
     * 명령어의 개수 5 <= N <= 10
     * 명령어
     * 
     * 명령어를 모두 수행한 후 10개의 숫자를 출력하자
     * 
     * linkedlist로 관리하면서 입력하자
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringTokenizer stCommand;
    public static StringBuilder sb;

    public static final int testCase = 2;
    public static final int numOfPrint = 10;

    public static int passwordLength;
    public static int numOfCommand;
    public static List<Integer> passwordList;

    public static int startIdx;
    public static int numOfAddNumber;
    public static int addNumber;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= testCase; tc++) {
            sb = new StringBuilder();

            passwordList = new LinkedList<>();
            passwordLength = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            // 원본 암호문 입력
            for (int idx = 0; idx < passwordLength; idx++) {
                passwordList.add(Integer.parseInt(st.nextToken()));
            }

            // 총 명령어 개수
            numOfCommand = Integer.parseInt(br.readLine().trim());

            // 명령어 입력
            // 명령어 I를 기준으로 split
            st = new StringTokenizer(br.readLine().trim(), "I");

            // 명령어 실행
            while (st.hasMoreTokens()) {
                // I를 기준으로 나눈 명령어를 다시 공백 기준으로 나눔
                stCommand = new StringTokenizer(st.nextToken());

                startIdx = Integer.parseInt(stCommand.nextToken()); // 삽입위치
                numOfAddNumber = Integer.parseInt(stCommand.nextToken()); // 삽입할 숫자 개수

                while (stCommand.hasMoreTokens()) {
                    addNumber = Integer.parseInt(stCommand.nextToken());
                    // 삽입 위치에 숫자를 추가
                    // 추가 후 인덱스를 1씩 증가해주어야 추가한 숫자 뒤로 새로운 추가할 숫자가 삽입
                    passwordList.add(startIdx++, addNumber);
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int idx = 0; idx < numOfPrint; idx++) {
                sb.append(passwordList.get(idx)).append(" ");
            }

            System.out.println(sb);
        }
    }
}
