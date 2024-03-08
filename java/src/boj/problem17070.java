package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem17070 {
    /*
     * N x N
     * 
     * 1,1 부터 시작한 파이프를 N,N까지 놓을 수 있는 총 방법 수를 찾아보자
     * 
     * 파이프는 총 3가지로 놓을 수 있다.
     * - 가로: 시작 위치를 제외하고 1칸이 필요하다
     * - 2가지로 다음 파이프를 놓을 수 있다. 가로, 대각선
     * - 세로: 시작 위치를 제외하고 1칸이 필요하다
     * - 2가지로 다음 파이프를 놓을 수 있다. 세로, 대각선
     * - 대각선: 시작 위치를 제외하고 3칸이 필요하다
     * - 2가지로 다음 파이프를 놓을 수 있다. 가로, 세로, 대각선
     * 
     * 벽이 있는 경우 파이프를 놓을 수 없다.
     * 
     * 시작 위치는 1,1 이고 파이는 가로로 놓여있다. 따라서 다음 파이프를
     * 연결하는 위치는 1,2 이다.
     * 
     */
    static final int BLANK = 0;
    static final int WALL = 1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static int mapSize;

    // 가로, 대각선, 세로 순서로 확인
    static int[] deltaX = { 0, 1, 1 };
    static int[] deltaY = { 1, 1, 0 };

    static int answer;

    /**
     * 현재 위치에서 파이프를 놓을 수 있는지 확인하여
     * 놓을 수 있는 파이프 위치로 이동
     * 
     * @param rowIdx:    현재 행 위치
     * @param colIdx:    현재 열 위치
     * @param direction: 현재 방향(0: 가로, 1: 대각선, 2: 세로)
     */
    static void setPipe(int rowIdx, int colIdx, int pipeDirection) {
        // 1. 기저 조건
        if (rowIdx == mapSize - 1 && colIdx == mapSize - 1) {
            answer++;
            return;
        }

        for (int dir = 0; dir < deltaX.length; dir++) {

            // 가로는 세로로 불가능
            // 세로는 가로로 불가능
            // 대각선은 제외
            if (Math.abs(dir - pipeDirection) > 1) {
                continue;
            }

            int newRowIdx = rowIdx + deltaX[dir];
            int newColIdx = colIdx + deltaY[dir];

            // 맵 영역 밖으로 나가는지 확인
            if (0 > newRowIdx || newRowIdx >= mapSize || 0 > newColIdx || newColIdx >= mapSize) {
                continue;
            }

            // 대각선에 경우 다음으로 갈 때 3칸이 비어있는지 확인
            if (dir == 1) {
                // 대각선은 가로, 대각선, 세로가 모두 비어있어야 한다.
                boolean flag = false;

                for (int check = 0; check < deltaX.length; check++) {
                    int checkRowIdx = rowIdx + deltaX[check];
                    int checkColIdx = colIdx + deltaY[check];

                    // 가로,세로,대각선에 벽이 있는 경우
                    if (map[checkRowIdx][checkColIdx] == WALL) {
                        flag = true;
                        break;
                    }
                }

                // 가로,세로,대각선에 벽이 있다.
                if (flag) {
                    continue;
                }

                // 가로에 경우 다음으로 가는 한 칸이 비어있는지 확인
                // 세로에 경우 다음으로 가는 한 칸이 비어있는지 확인
            } else {
                if (map[newRowIdx][newColIdx] == WALL) {
                    continue;
                }
            }

            setPipe(newRowIdx, newColIdx, dir);

        }

    }

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];

        // map 입력받기
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        setPipe(0, 1, 0);

        System.out.println(answer);
    }

}
