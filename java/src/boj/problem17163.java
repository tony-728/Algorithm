package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem17163 {
    /*
     * 
     * 5종류의 색종이가 있다.
     * - 각 크기는 1x1, 2x2, 3x3, 4x4, 5x5
     * - 각 색종이는 5개씩 있다.
     * 
     * 색종이를 크기가 10x10인 종이 위에 붙이려고 한다.
     * 각 칸에 0 또는 1이 적혀있다.
     * 
     * 1이 적힌 칸은 모두 색종이로 덮여져야 한다.
     * 0이 적힌 칸에는 색종이가 있으면 안된다.
     * => 1이 적힌 칸을 딱 맞추어 색종이로 덮어야 한다.
     * 
     * 색종이를 붙일 때는 종이의 경계 밖으로 나가면 안되고, 겹쳐서는 안된다.
     * 
     * 종이가 주어졌을 때 모든 칸을 붙이는 데 필요한 색조이의 최소 개수
     * - 1을 모두 덮어야 한다.
     * 
     * 
     * 10개 줄에 종이의 칸에 적힌 수가 주어진다.
     * 
     * 10 x 10 종이를
     * 5x5, 4x4, 3x3, 2x2, 1x1
     *
     * DFS + 백트래킹
     * 위에서 아래로, 왼쪽에서 오른쪽으로 가며 탐색할 경우, 1이 적힌 칸을 만났을 시 항상 해당 점을 왼쪽 위 꼭짓점으로 하는 색종이를 붙여야
     * 함
     * 5*5부터 1*1까지 붙일 수 있는 색종이를 붙여가며 DFS 진행
     * 붙인 후 다시 떼 주어야 한다
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAP_SIZE = 10;
    static final int NUM_OF_COLOR = 5;
    static final int[][] MAP = new int[MAP_SIZE][MAP_SIZE];
    static int[][] copiedMap = new int[MAP_SIZE][MAP_SIZE];

    static final int BLANK = 0;
    static final int WALL = 1;

    static int answer = Integer.MAX_VALUE;
    static int result = 0;

    static int totalWallCount = 0;
    static int tempWallCount = 0;

    static int[] colorPaperList = { 5, 4, 3, 2, 1 };
    static int[] colorPaperCountList = { 5, 5, 5, 5, 5 };

    static int[] selectList;
    static boolean[] usedElementList;

    public static void inputTestCase() throws IOException {

        selectList = new int[NUM_OF_COLOR];
        usedElementList = new boolean[NUM_OF_COLOR];

        // 영역을 입력 받는다.
        for (int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
                MAP[rowIdx][colIdx] = Integer.parseInt(st.nextToken());

                // 벽의 개수 카운팅
                if (MAP[rowIdx][colIdx] == WALL) {
                    totalWallCount++;
                }
            }
        }

    }

    public static void copyMap() {

        for (int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
            for (int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
                copiedMap[rowIdx][colIdx] = MAP[rowIdx][colIdx];
            }

        }

    }

    public static boolean isAvailable(int startRowIdx, int startColIdx, int colorMapSize) {

        for (int rowIdx = startRowIdx; rowIdx < startRowIdx + colorMapSize; rowIdx++) {
            for (int colIdx = startColIdx; colIdx < startColIdx + colorMapSize; colIdx++) {

                // 영역 확인
                if (0 > rowIdx || rowIdx >= MAP_SIZE || 0 > colIdx || colIdx >= MAP_SIZE) {
                    return false;
                }

                // 빈칸이 있으면 안된다.
                if (copiedMap[rowIdx][colIdx] == BLANK) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void setColorPaper(int startRowIdx, int startColIdx, int colorMapSize) {

        for (int rowIdx = startRowIdx; rowIdx < startRowIdx + colorMapSize; rowIdx++) {
            for (int colIdx = startColIdx; colIdx < startColIdx + colorMapSize; colIdx++) {

                copiedMap[rowIdx][colIdx] = BLANK;
            }
        }
    }

    public static void checkSection(int index) {

        // 영역을 돌면서 색종이 영역에 해당하는 곳이 모두 1로 되어 있는지 확인한다.
        for (int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
            for (int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {

                // 빈칸이면 넘어간다.
                if (copiedMap[rowIdx][colIdx] == BLANK) {
                    continue;
                }

                // 선택된 색종이 영역만큼 1로 되어 있는지 확인한다. && 선택된 색종이의 남은 개수가 0개 이상이어야 한다.
                // 영역이 모두 1로 되어 있으면 0으로 방문처리
                // 색종이 영역 카운트 -1
                // 전체 사용 색종이 카운트 +1
                if (isAvailable(rowIdx, colIdx, colorPaperList[index]) && colorPaperCountList[index] > 0) {
                    setColorPaper(rowIdx, colIdx, colorPaperList[index]);

                    // 해당 영역 색종이 개수 줄여주기
                    colorPaperCountList[index]--;

                    // 전체 벽 갯수 덮은 영역만큼 줄여주기
                    tempWallCount -= (colorPaperList[index] * colorPaperList[index]);

                    // 사용한 색종이 카운트 증가
                    result++;
                }
            }
        }
    }

    public static void selectColorPaper(int selectIdx) {

        if (selectIdx == NUM_OF_COLOR) {
            result = 0;
            tempWallCount = totalWallCount;

            colorPaperCountList = new int[] { 5, 5, 5, 5, 5 };
            copyMap();

            for (int idx = 0; idx < NUM_OF_COLOR; idx++) {
                checkSection(selectList[idx]);
            }

            // 모든 블록을 처리했을 때 정답을 갱신한다.
            if (tempWallCount == 0) {
                answer = Math.min(answer, result);
            }

            return;
        }

        for (int elementIdx = 0; elementIdx < NUM_OF_COLOR; elementIdx++) {

            if (usedElementList[elementIdx]) {
                continue;
            }
            usedElementList[elementIdx] = true;
            selectList[selectIdx] = elementIdx;
            selectColorPaper(selectIdx + 1);

            usedElementList[elementIdx] = false;

        }

    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        selectColorPaper(0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
