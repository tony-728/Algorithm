package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class problem1230 {
    /*
     * 명령어
     * 1. I(삽입) x, y, s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다. s는 덧붙일 암호문들이다.
     * [ ex) I 3 2 123152 487651
     * 2. D(삭제) x, y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.[ ex) D 4 4 ]
     * 3. A(추가) y, s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문들이다. [ ex) A 2 421257
     * 796813 ]
     * 
     * 첫 번째 줄: 원본 암호문 뭉치 속 암호문의 개수
     * 두 번째 줄 : 원본 암호문 뭉치
     * 세 번째 줄 : 명령어의 개수 ( 250 ≤ M ≤ 500 의 정수)
     * 네 번째 줄 : 명령어
     * 
     * 
     * 총 10개의 testcase가 주어진다.
     * 명령어를 실행 한 결과 수정된 암호문 뭉치의 앞 10개를 출력한다.
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int testCase = 1;

    static int numOfCipher;
    static List<String> cipherStringList;
    static int numOfCommand;
    static String command;

    static final String INSERT = "I";
    static final String DELETE = "D";
    static final String ADD = "A";

    public static void runCommand(String commandString) {

        st = new StringTokenizer(commandString);

        // toknen이 없을 때까지 반복한다.
        while (st.hasMoreTokens()) {

            command = st.nextToken();

            if (command.equals(INSERT)) {
                int startIdx = Integer.parseInt(st.nextToken()); // 삽입시작위치
                int numOfString = Integer.parseInt(st.nextToken()); // 삽입 문자열 개수

                List<String> tempList = new LinkedList<>();

                for (int idx = 0; idx < numOfString; idx++) {
                    tempList.add(st.nextToken());
                }

                cipherStringList.addAll(startIdx, tempList);

            } else if (command.equals(DELETE)) {
                int startIdx = Integer.parseInt(st.nextToken()); // 삭제 시작위치
                int numOfString = Integer.parseInt(st.nextToken()); // 삭제 문자열 개수

                for (int idx = 0; idx < numOfString; idx++) {
                    cipherStringList.remove(startIdx);
                }

            } else if (command.equals(ADD)) {
                int numOfString = Integer.parseInt(st.nextToken()); // 삭제 문자열 개수

                for (int idx = 0; idx < numOfString; idx++) {
                    cipherStringList.add(st.nextToken());
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= testCase; tc++) {

            cipherStringList = new LinkedList<>();

            numOfCipher = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim());

            // 암호문 리스트에 추가하기
            for (int idx = 0; idx < numOfCipher; idx++) {
                cipherStringList.add(st.nextToken());
            }

            numOfCommand = Integer.parseInt(br.readLine().trim());

            runCommand(br.readLine().trim());

            sb.append(String.format("#%d ", tc));

            for (int idx = 0; idx < 10; idx++) {
                sb.append(cipherStringList.get(idx) + " ");
            }
            sb.append("\n");

        }

        System.out.println(sb);
    }
}
