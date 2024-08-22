package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2527 {
    /*
     * 8개의 정수가 주어진다. 4개씩 사각형의 좌표를 나타낸다.
     * 
     * 공통부분의 특성
     * - 직사각형: a
     * - 선분: b
     * - 점: c
     * - 공통부분이 없다.: d
     * 
     * 주어진 사각형를 모두 마킹 -> 50000이하의 정수이므로 비효율적
     * 
     * 직사각형
     * - 사각형 중 하나의 꼭짓점이 다른 사각형 범위 안에 들어있다.
     * - 사각형의 변이 다른 사각형의 변을 가로지른다.
     * 
     * 선분
     * - 사각형의 변이 만난다.
     * 
     * 꼭짓점
     * - 사각형의 꼭짓점이 동일하다
     * 
     * 입력은 총 4개
     * 2개의 사각형을 나타낼 수 있도록 8개의 점이 주어짐
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int INPUT_COUNT = 4;

    static final char SURFACE = 'a';
    static final char LINE = 'b';
    static final char POINT = 'c';
    static final char NO_COMMON = 'd';

    static int x1, x2, y1, y2;
    static int p1, p2, q1, q2;

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        p1 = Integer.parseInt(st.nextToken());
        q1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        q2 = Integer.parseInt(st.nextToken());
    }

    static void check() {
        // 앞에서 조건을 하나씩 확인했다는 것이 중요함
        // 조건을 확인했으니까 다시 확인하지 않아도 됨

        // 만나지 않을 때
        if ((x1 > p2) || (p1 < x2) || (y1 > q2) || (q1 < y2)) {
            sb.append(NO_COMMON).append("\n");
        }
        else if (x1 == p2 || x2 == p1) {
            if (y1 == q2 || q1 == y2) {
                // 점일 때
                sb.append(POINT).append("\n");
                // 선일 때
            } else {
                sb.append(LINE).append("\n");
            }
            // 선일 때
        } else if (q1 == y2 || q2 == y1) {
            sb.append(LINE).append("\n");
        }
        // 면일 때
        else {
            sb.append(SURFACE).append("\n");
        }

    }

    public static void main(String[] args) throws IOException {
        for (int inputCase = 0; inputCase < INPUT_COUNT; inputCase++) {

            inputTestCase();
            check();

        }

        System.out.println(sb);

    }
}
