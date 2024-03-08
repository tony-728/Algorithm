package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2805 {

    /*
     * NxN 농작물
     * 
     * 조건
     * 1. 농장의 크기는 항상 홀수 있다. -> N 홀수
     * 2. 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능하다
     * 
     * 풀이
     * 1. 가운데를 기준으로 좌, 우로만 이동한다. 확인하는 값은 상,하로만 확인한다.
     * 1.1 좌(우)로 이동한 방향으로만 계속이동해야한다.
     * 2. 중심에서 멀어지는 칸만큼 상(하)로 이동할 수 있는 범위가 줄어든다. (한칸 멀어지면 한칸 줄어듬)
     * 3. 좌(우)로 이동한 곳을 호함하여 상,하로 이동하며 값을 더한다.
     * 3.1. 한번 더한 값은 중복해서 더하지 않는다.
     * 4. 경계에 있는 곳에서는 더 이상 진행할 수 없다.
     * 
     * 
     */

    public static BufferedReader br;
    public static int[][] farm;
    public static int width;
    public static int[] LRMoveX = { 0, 0 };
    public static int[] LRMoveY = { -1, 1 };
    public static int[] UDMoveX = { -1, 1 };
    public static int[] UDMoveY = { 0, 0 };
    public static int answer;
    public static boolean[][] visited;

    public static void getFarm(int x, int y, int move) {
        // 2.전처리
        // 방문처리
        visited[x][y] = true;
        // 현재 위치 더해주기
        answer += farm[x][y];

        // 1. 기저 조건
        // 경계에 도착하면 종료한다.
        if (x == 0 || y == 0 || x == width - 1 || y == width - 1) {
            return;
        }

        // 상하로 이동하며 값을 더해준다.
        // 더한 값은 0으로 처리해 중복해서 더하지 않도록 한다.
        // 중심에서 멀어지는 칸만큼 상(하)로 이동할 수 있는 범위가 줄어든다.
        // (한칸 멀어지면 한칸 줄어듬)
        for (int idx = 0; idx < 2; idx++) {
            int loop = 1;
            while (loop <= (width / 2) - move) { // 중심에서 멀어지는 만큼 이동범위를 줄임
                int newX = x + UDMoveX[idx] * loop;
                int newY = y + UDMoveY[idx] * loop;

                answer += farm[newX][newY];

                farm[newX][newY] = 0;

                loop++;
            }
        }

        // 3. 재귀 호출
        for (int idx = 0; idx < 2; idx++) {
            int newX = x + LRMoveX[idx];
            int newY = y + LRMoveY[idx];

            // 진행 방향으로만 재귀를 갈 수 있도록 해야함
            if (visited[newX][newY] == false) {
                getFarm(newX, newY, move + 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            answer = 0;

            // 농장 가로 길이
            width = Integer.parseInt(br.readLine().trim());

            farm = new int[width][width];
            visited = new boolean[width][width];

            // 농장 입력
            for (int row = 0; row < width; row++) {
                String rows = br.readLine().trim();
                for (int col = 0; col < width; col++) {
                    farm[row][col] = rows.charAt(col) - '0';
                }
            }

            answer += farm[width / 2][width / 2];
            farm[width / 2][width / 2] = 0;

            getFarm(width / 2, width / 2, 0);

            System.out.println("#" + testCase + " " + answer);
        }

    }

}
