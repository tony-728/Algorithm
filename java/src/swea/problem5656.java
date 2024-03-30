package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem5656 {

    /*
     * 구슬은 N번만 쏠 수 있다. 벽돌들의 정보는 아래와 같이 WxH배열로 주어진다. 0은 빈공간 그외 숫자는 벽돌의 의미한다.
     * 
     * 규칙 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다. 2. 벽돌은 숫자 1-9로 표현되며 구슬이
     * 명중한 벽돌은 상하좌우로 벽돌에 적힌 숫자 -1 칸 만큼 같이 제거된다. 3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다. 4. 빈
     * 공간이 있을 경우 벽돌은 밑으로 떨어지게 된다.
     * 
     * N개의 구슬로 최대한 많은 벽돌을 제거하려고 한다. 제거하고 남은 벽돌의 갯수를 구하라
     * 
     * 구슬의 개수는 최대 4 2<= 가로 <= 12 2<= 세로 <= 15
     * 
     * 15^4 = 50625 이므로 완탐이 가능
     * 
     * 가로 위치에서 구슬을 날릴 수 있는 경우를 모두 확인하여 가장 적게 남은 벽돌의 갯수를 저장한다.
     * 
     * 1. 현재 구슬이 던지는 맵 정보 저장
     * 2. 가로 인덱스만큼 반복문으로 구슬 날리기
     * 3. 빈 벽돌이 없이 벽돌 내리기
     * 4. 다음 구슬이 던지도록 재귀호출
     * 4-1. 모든 구슬을 던졌으면 지금까지 부순 벽돌 갯수와 최대로 부순 벽도 갯수와 비교해서 최대값 저장
     * 5. 재귀호출이 반환되어 돌아오면 1.에서 저장한 정보로 다시 현재 구슬이 던지는 맵 정보로 초기화
     * 
     */

    static class Block {
        int rowIdx;
        int colIdx;
        int power;

        public Block(int rowIdx, int colIdx, int power) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.power = power;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfBall;
    static int width;
    static int height;
    static int[][] map;
    static int[][][] mapHistory;

    static int totalBlock;
    static int answer;

    static final int BLANK = 0;
    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, -1, 0, 1 };

    // 입력받기
    public static void inputTestCase() throws IOException {

        totalBlock = 0;
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfBall = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        mapHistory = new int[numOfBall][height][width];

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {

            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < width; colIdx++) {

                int element = Integer.parseInt(st.nextToken());

                // 벽돌의 개수 세기
                if (element != BLANK) {
                    totalBlock += 1;
                }

                map[rowIdx][colIdx] = element;

            }
        }
    }

    public static void play(int depth, int result) {

        // 모든 벽돌을 던졌다
        if (depth == numOfBall) {
            answer = Math.max(answer, result);
            return;
        }

        // 현재 상태 벽돌정보 저장하기
        saveMap(depth);

        // 위치에서 벽돌 던지기
        for (int idx = 0; idx < width; idx++) {

            // 현재 위치에 열에 벽돌을 향해 구슬을 던짐
            int count = hit(idx);

            // 벽돌 밑으로 내리기
            downBlock();

            // 다음 게임으로 재귀 호출
            play(depth + 1, result + count);

            // 함수가 반환되어 돌아 왔을 때 현재 위치에 블록을 다시 초기화해주어야 함
            copyMap(depth);
        }

    }

    // 이전 상태 벽돌 복사하기
    public static void saveMap(int depth) {

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            for (int colIdx = 0; colIdx < width; colIdx++) {

                mapHistory[depth][rowIdx][colIdx] = map[rowIdx][colIdx];
            }
        }
    }

    // 벽돌을 향해 구슬을 던짐
    public static int hit(int checkColIdx) {

        int count = 0;

        Deque<Block> queue = new ArrayDeque<>();

        // 해당 열에 벽돌이 있는지 미리 확인했으므로 벽돌을 만날때까지 돌을 던지면 된다.
        // 처음 벽돌을 만나면 위치를 큐에 저장후 반복문 탈출
        for (int rowIdx = 0; rowIdx < height; rowIdx++) {

            if (map[rowIdx][checkColIdx] != BLANK) {
                queue.offer(new Block(rowIdx, checkColIdx, map[rowIdx][checkColIdx]));
                map[rowIdx][checkColIdx] = BLANK;
                count++;
                break;
            }
        }

        // 벽돌 부시기
        while (!queue.isEmpty()) {

            Block currentLoc = queue.poll();

            int newRowIdx = currentLoc.rowIdx;
            int newColIdx = currentLoc.colIdx;
            int currentPower = currentLoc.power;

            // 영향을 줄 수 있는 만큼 진행한다.
            for (int dir = 0; dir < deltaX.length; dir++) {
                newRowIdx = currentLoc.rowIdx;
                newColIdx = currentLoc.colIdx;

                for (int power = 1; power < currentPower; power++) {

                    newRowIdx = newRowIdx + deltaX[dir];
                    newColIdx = newColIdx + deltaY[dir];

                    // 범위확인
                    if (newRowIdx < 0 || newRowIdx >= height || newColIdx < 0 || newColIdx >= width) {
                        break;
                    }

                    if (map[newRowIdx][newColIdx] != BLANK) {
                        // 영향을 받은 블록 큐에 넣기
                        queue.offer(new Block(newRowIdx, newColIdx, map[newRowIdx][newColIdx]));

                        map[newRowIdx][newColIdx] = BLANK;
                        count++;
                    }

                }
            }

        }

        return count;
    }

    // 벽돌 밑으로 내리기
    public static void downBlock() {

        for (int colIdx = 0; colIdx < width; colIdx++) {
            Deque<Block> queue = new ArrayDeque<>();

            for (int rowIdx = height - 1; rowIdx > -1; rowIdx--) {

                // 빈칸을 찾았으면 위치를 저장한다.
                if (map[rowIdx][colIdx] == BLANK) {
                    queue.offer(new Block(rowIdx, colIdx, map[rowIdx][colIdx]));
                } else {

                    // 미리 찾은 빈칸이 있는 경우
                    if (!queue.isEmpty()) {
                        Block blanckLoc = queue.poll();

                        // 빈칸과 자리 스위칭
                        map[blanckLoc.rowIdx][blanckLoc.colIdx] = map[rowIdx][colIdx];
                        map[rowIdx][colIdx] = blanckLoc.power;

                        // 자리를 스위칭한 곳에 다시 0이 생겼으므로 그 자리를 큐에 넣는다.
                        queue.offer(new Block(rowIdx, colIdx, map[rowIdx][colIdx]));
                    }
                }
            }
        }
    }

    // 벽돌 정보 초기화하기
    public static void copyMap(int depth) {

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            for (int colIdx = 0; colIdx < width; colIdx++) {

                map[rowIdx][colIdx] = mapHistory[depth][rowIdx][colIdx];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc < testCase + 1; tc++) {

            inputTestCase();

            play(0, 0);

            sb.append(String.format("#%d %d\n", tc, totalBlock - answer));

        }
        System.out.println(sb);
    }

}
