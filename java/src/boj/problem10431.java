package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10431 {
    /*
     * 키 순서대로 번호를 부여
     * 반 아이들은 항상 20명
     * 같은 키를 가진 학생은 없다.
     * 
     * 아무나 한 명을 줄의 맨 앞에 세운다.
     * 자기 앞에 자기보다 키가 큰 학생이 없다면 그냥 그 자리에서 서로 차례가 끝난다.
     * 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면 그 중 가장 앞에 있는 학생의 바로 앞에 선다. -> 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다.
     * 
     * 총 몃번 뒤로 물러서게 될까?
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testCase;
    static final int MEMBER_SIZE = 20;

    static int[][] memberList;
    static int count;

    static void inputTestCase() throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        memberList = new int[testCase][MEMBER_SIZE];

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int idx = 0; idx < MEMBER_SIZE + 1; idx++) {
                // 첫번째 값은 번호라서 제외
                if (idx == 0) {
                    st.nextToken();
                    continue;
                }

                int value = Integer.parseInt(st.nextToken());

                memberList[tc][idx - 1] = value;
            }
        }
    }

    static void solution(int tc) {
        count = 0;

        for (int idx = 0; idx < MEMBER_SIZE; idx++) {
            // 현재 값이 정렬된 값들보다 작으면 개수만큼 이동해야한다.
            // 계속 반복하는 이유는 무조건 맨앞으로 가기 때문에
            for (int sortedIdx = 0; sortedIdx < idx; sortedIdx++) {
                if (memberList[tc][idx] < memberList[tc][sortedIdx]) {
                    count++;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        for (int tc = 0; tc < testCase; tc++) {
            solution(tc);

            sb.append(tc + 1).append(" ").append(count).append("\n");
        }

        System.out.println(sb);
    }
}
