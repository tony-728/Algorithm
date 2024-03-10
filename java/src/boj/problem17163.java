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
     * -> 그리디한 방법으로는 예외상황이 발생한다. ex) 6x6
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

    static final int BLANK = 0;
    static final int WALL = 1;

    static int answer;

    static int[] colorPaperCountList = { 0, 5, 5, 5, 5, 5 };

    public static void inputTestCase() throws IOException {
        answer = Integer.MAX_VALUE;

        // 영역을 입력 받는다.
        for (int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
                MAP[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

    }

    // rowIdx, colIdx를 시작으로 length만큼의 영역이 WALL로 채워져 있는지 확인한다.
    public static boolean isAttach(int rowIdx, int colIdx, int length) {

        // 영역을 벗어나거나
        // 남은 색종이의 개수가 없을 때
        // 붙일 수 없다.
        if (rowIdx + length > MAP_SIZE || colIdx + length > MAP_SIZE || colorPaperCountList[length] <= 0) {
            return false;
        }

        for (int startRowIdx = rowIdx; startRowIdx < rowIdx + length; startRowIdx++) {
            for (int startColIdx = colIdx; startColIdx < colIdx + length; startColIdx++) {
                if (MAP[startRowIdx][startColIdx] == BLANK) {
                    return false;
                }
            }
        }

        return true;
    }

    // rowIdx, colIdx를 시작으로 length만큼 status로 채운다.
    public static void fill(int rowIdx, int colIdx, int length, int status) {
        for (int startRowIdx = rowIdx; startRowIdx < rowIdx + length; startRowIdx++) {
            for (int startColIdx = colIdx; startColIdx < colIdx + length; startColIdx++) {
                MAP[startRowIdx][startColIdx] = status;
            }
        }

        // status에 따라서 남은 색종이의 개수를 갱신
        if (status == BLANK) {
            colorPaperCountList[length]--;
        } else {
            colorPaperCountList[length]++;
        }
    }

    public static void checkPaper(int rowIdx, int colIdx, int count) {

        // 종료 조건
        if (rowIdx == MAP_SIZE - 1 && colIdx >= MAP_SIZE) {
            answer = Math.min(answer, count);
            return;
        }

        // 조기 조건
        if (count >= answer) {
            return;
        }

        // 현재 행을 모두 확인함
        // 다음 행을 확인할 수 있도록 재귀호출
        if (colIdx == MAP_SIZE) {
            checkPaper(rowIdx + 1, 0, count);
            return;
        }

        if (MAP[rowIdx][colIdx] == WALL) {
            for (int length = 5; length > 0; length--) {
                // 5칸짜리 색종이부터 1칸짜리 색종이까지 붙일 수 있는지 확인한다.
                if (isAttach(rowIdx, colIdx, length)) {
                    // 색종이를 붙이므로 영역을 BLANK 처리
                    fill(rowIdx, colIdx, length, 0);
                    checkPaper(rowIdx, colIdx + length, count + 1);
                    // 탐색 후 돌아왔으므로 영역을 WALL로 처리
                    fill(rowIdx, colIdx, length, 1);
                }
            }

        } else {
            checkPaper(rowIdx, colIdx + 1, count);
        }

    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        checkPaper(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
