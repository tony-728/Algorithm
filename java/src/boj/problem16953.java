package boj;

import java.io.*;
import java.util.*;

public class problem16953 {
    /*
     * 정수 A를 B로 바꾸려고 한다.
     * 
     * 연산
     * - 2를 곱한다.
     * - 1을 수의 가장 오른쪽에 추가한다.
     * 
     * bfs, 자료형을 int말고 long으로 해야 된다. 오버플로우때문
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int start;
    static int target;
    static int answer;
    static boolean flag;

    static void solution() {

        Deque<long[]> q = new ArrayDeque<>();

        q.addLast(new long[] {start, 0});

        while (!q.isEmpty()) {

            long[] val = q.pollFirst();

            if (val[0] == target) {
                answer = (int) val[1] + 1;
                break;
            }

            long newVal = val[0] << 1;

            if (newVal <= 1_000_000_000 && newVal > 0) {
                q.addLast(new long[] {newVal, val[1] + 1});
            }

            newVal = (val[0] * 10) + 1;

            if (newVal <= 1_000_000_000 && newVal > 0) {
                q.addLast(new long[] {newVal, val[1] + 1});
            }


        }

    }

    public static void main(String[] args) throws IOException {
        flag = false;
        answer = -1;
        st = new StringTokenizer(br.readLine().trim());

        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        solution();

        System.out.println(answer);
    }
}
