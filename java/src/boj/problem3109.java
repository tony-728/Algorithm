package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem3109 {
    /*
     * R x C 격자로 빵집을 표현한다.
     * 
     * 첫번째 열은 빵집 가스관이고 마지막열은 나의 빵집
     * 
     * 두 빵집을 연결하는 파이프를 설계한다.
     * 두 빵집 사이에 다른 건물이 있을 수 있다.
     * 파이프는 건물을 통과할 수 없다.
     * 
     * 각칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 갈 수 있다.
     * 파이프의 경로는 겹칠 수 없다.
     * 서로 접할 수 없다.
     * 각 칸을 지나는 파이프는 하나여야 한다.
     * 
     * 가스관과 빵집을 연결하는 파이프라인의 최대 개수
     * 
     * 
     * 가스관에서 시작한 파이프는 빵집에 가장 위부터 도착하는 경로를 설정한다.
     * - 빵집에 가장 위로 도착해야 다른 파이프가 갈 수 있는 선택지가 많게 된다.
     * - 빵집으로 가기 위해 dfs로 탐색한다. dfs로 가는 길을 선택하는 것은 우상, 우, 우하 순서로 가능한 경로를 선택하여
     * - dfs를 진행한다.
     * 목적지까지로 이동할 수 없는 경로는 어떠한 경우에도 불가능하게 된다.
     * - 시작이 다르더라도 실패하는 경로로 가게 된다면 결과는 같다. -> 다른 경로가 미리 검사한 것임
     * - 확인하며 지나간 길을 건물로 처리한다.
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int height;
    static int width;

    static char[][] map;
    static boolean[][] visited;
    static int answer;

    // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int[] deltaX = { -1, 0, 1 };
    static int[] deltaY = { 1, 1, 1 };

    static final char BUILDING = 'x';

    static Deque<int[]> pathList;

    static boolean flag;

    static void goTarget(int rowIdx, int colIdx) {
        // 현재 위치를 건물로 처리
        // 지나온 길을 건물로 처리하기 위함
        map[rowIdx][colIdx] = BUILDING;

        // 기저 조건
        // 마지막 열에 도착한 경우 연결 카운트 증가
        if (colIdx == width - 1) {
            // 마지막 위치에 왔으므로 처음 시작한 위치에서는
            // 이 경로가 최선이므로 더이상 탐색하지 않도록 조건 처리
            flag = false;
            answer++;
            return;
        }

        // 우상, 우, 우하 순서로 갈 수 있는지 확인
        for (int dir = 0; dir < deltaX.length; dir++) {
            int newRowIdx = rowIdx + deltaX[dir];
            int newColIdx = colIdx + deltaY[dir];

            // 맵 영역을 나가는지 확인
            if (0 > newRowIdx || newRowIdx >= height || 0 > newColIdx || newColIdx >= width) {
                continue;
            }

            // 건물로 막혀있는지 체크
            if (map[newRowIdx][newColIdx] == BUILDING) {
                continue;
            }

            // 지나갈 수 있음
            if (flag == true) {
                goTarget(newRowIdx, newColIdx);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new char[height][width];
        visited = new boolean[height][width];

        // map 입력받기
        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            map[rowIdx] = br.readLine().trim().toCharArray();
        }

        // 행 순서대로 첫번째 열인덱스로 파이프 연결이 가능한지 확인
        for (int rowIdx = 0; rowIdx < height; rowIdx++) {

            flag = true;
            // 경로 확인
            goTarget(rowIdx, 0);

        }

        System.out.println(answer);

    }
}