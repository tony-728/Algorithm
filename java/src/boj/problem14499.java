package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem14499 {
    /*
     * 인덱스를 바꿔주는 방법으로 주사위의 회전을 구현함
     */
    public static void main(String[] args) throws IOException {
        rollDice();
    }

    public static StringBuilder sb = new StringBuilder();
    public static int N, M, x, y, K;
    public static final int ROW = 0, COL = 1;

    public static void rollDice() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        x = Integer.parseInt(st.nextToken()); // 초기 주사위 x 좌표
        y = Integer.parseInt(st.nextToken()); // 초기 주사위 y 좌표
        K = Integer.parseInt(st.nextToken()); // 명령 횟수
        int[][] map = new int[N][M];

        for (int r = 0; r < N; ++r) {
            st = new StringTokenizer(br.readLine().trim());
            for (int c = 0; c < M; ++c) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        } // 지도 입력 완료

        int[] order = new int[K];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < K; ++i) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        int[][] move = { // 동서북남
                {0, +0, +0, -1, +1}, // ROW
                {0, +1, -1, +0, +0} // COL
        };

        Dice dice = new Dice();
        for (int i = 0; i < K; ++i) {
            int dir = order[i];

            x = x + move[ROW][dir];
            y = y + move[COL][dir];

            if (x < 0 || x >= N || y < 0 || y >= M) {
                x = x - move[ROW][dir];
                y = y - move[COL][dir];
                continue;
            }

            int value = map[x][y];
            if (value == 0) {
                dice.roll(dir, 0); // 지도가 0이면
                map[x][y] = dice.info[3]; // 복사
            } else {
                dice.roll(dir, value); // 지도가 0이 아니면
                map[x][y] = 0; // 0 설정
            }
        }

        System.out.println(sb.toString());
    }

    /*
      1
      2
    3 4 5
      6
     */
    // org:     1 2 3 4 5 6
    // right:   3 *2 4 5 1 *6 동
    // left:    5 *2 1 3 4 *6 서
    // up:      6 1 *3 2 *5 4 북
    // down:    2 4 *3 6 *5 1 남
    public static class Dice {
        int[] info;

        public Dice() {
            info = new int[6];
        }

        public void roll(int dir, int value) {
            switch (dir) {
                case 1:
                    right();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    up();
                    break;
                case 4:
                    down();
                    break;
            }
            if (value != 0) { // 0이 아닌 경우 밑 변경
                info[3] = value;
            }
            sb.append(info[0]).append("\n");
        }

        public void right() {
            int temp = info[0];
            info[0] = info[2];
            info[2] = info[3];
            info[3] = info[4];
            info[4] = temp;
        }

        public void left() {
            int temp = info[0];
            info[0] = info[4];
            info[4] = info[3];
            info[3] = info[2];
            info[2] = temp;
        }

        public void up() {
            int temp = info[0];
            info[0] = info[5];
            info[5] = info[3];
            info[3] = info[1];
            info[1] = temp;
        }

        public void down() {
            int temp = info[0];
            info[0] = info[1];
            info[1] = info[3];
            info[3] = info[5];
            info[5] = temp;
        }


    }
}
