package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class problem14502 {
    /*
     * 
     * 연구소의 크기 N x M
     * 연구소는 빈칸, 벽, 바이러스가 각 칸에 있다.
     * 빈칸: 0, 벽: 1, 바이러스: 2
     * 
     * 바이러스는 상하좌우로 인접한 빈칸으로 모두 퍼져나갈 수 있다.
     * 새로 벽을 3개 세울 수 있다. 벽 3개는 무조건 세워야 한다.
     * 
     * 연구소에 새로운 벽 3개를 세워 바이러스가 퍼저나가는 것을 최대한 막아
     * 안전한 빈칸의 개수 최대를 구하라
     * 
     * 3 <= N, M <= 8
     * 2 <= 바이러스 개수 <= 10
     * 
     * 바이러스가 퍼저나가는 것을 최대한 막아야한다.
     * 
     * 
     * 1. 연구소를 입력받는다.
     * 2. 바이러스의 위치를 찾아 저장한다.
     * 3. 빈칸 들 중 3개를 선택하여 벽을 세운다.
     * 4. 바이러스를 모두 퍼트린 후에 빈칸을 계산한다.
     * 5. 남은 빈칸 중 가장 많이 남은 것을 출력한다.
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        @Override
        public String toString() {
            return "Location [rowIdx=" + rowIdx + ", colIdx=" + colIdx;
        }

    }

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int WALL_COUNT = 3;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int height;
    static int width;
    static int[][] map;
    static int answer;
    static int totalBlank;

    static int[] selectList;

    static int[] deltaX = { 0, -1, 0, 1 };
    static int[] deltaY = { -1, 0, 1, 0 };

    static List<Location> virusLocationList = new ArrayList<>(); // 가장자리의 바이러스의 위치를 저장
    static List<Location> blankLocationList = new ArrayList<>(); // 가장자리의 바이러스에 인접한 빈칸의 위치 저장

    public static void inputTestCase() throws IOException {
        answer = 0;
        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < width; colIdx++) {
                int type = Integer.parseInt(st.nextToken());

                map[rowIdx][colIdx] = type;

                // 바이러스 위치
                if (type == VIRUS) {
                    virusLocationList.add(new Location(rowIdx, colIdx));
                    // 빈칸 위치
                } else if (type == BLANK) {
                    totalBlank++;
                    blankLocationList.add(new Location(rowIdx, colIdx));
                }

            }
        }
    }

    public static void spreadVirus() {

        boolean[][] visited = new boolean[height][width];

        // 선택된 빈칸을 벽으로 처리하기
        for (int idx = 0; idx < WALL_COUNT; idx++) {
            Location blankLoc = blankLocationList.get(selectList[idx]);

            map[blankLoc.rowIdx][blankLoc.colIdx] = WALL;
        }

        // 시작 바이러스 위치 큐에 넣기
        Deque<Location> virLocations = new ArrayDeque<>();
        for (Location loc : virusLocationList) {
            visited[loc.rowIdx][loc.colIdx] = true;
            virLocations.offer(loc);
        }

        int virusCount = 0;

        // 바이러스 퍼트리기
        while (!virLocations.isEmpty()) {
            Location virus = virLocations.poll();

            // 4방확인
            for (int dir = 0; dir < deltaX.length; dir++) {
                int newRowIdx = virus.rowIdx + deltaX[dir];
                int newColIdx = virus.colIdx + deltaY[dir];

                // 범위체크
                if (0 > newRowIdx || newRowIdx >= height || 0 > newColIdx || newColIdx >= width) {
                    continue;
                }

                // 방문확인
                if (visited[newRowIdx][newColIdx]) {
                    continue;
                }

                // 벽이면 진행 안됨
                if (map[newRowIdx][newColIdx] == WALL) {
                    continue;
                }

                visited[newRowIdx][newColIdx] = true;
                virLocations.add(new Location(newRowIdx, newColIdx));
                virusCount++;
            }
        }

        answer = Math.max(answer, totalBlank - virusCount - WALL_COUNT);

        // 선택되어 벽을 세운 빈칸 다시 빈칸으로 처리하기
        for (int idx = 0; idx < WALL_COUNT; idx++) {
            Location blankLoc = blankLocationList.get(selectList[idx]);

            map[blankLoc.rowIdx][blankLoc.colIdx] = BLANK;
        }

        return;

    }

    public static void buildWall(int selectIdx, int elementIdx) {

        if (selectIdx == WALL_COUNT) {
            // 바이러스 퍼트리기
            spreadVirus();
            return;
        }

        if (elementIdx == blankLocationList.size()) {
            return;
        }

        selectList[selectIdx] = elementIdx;
        buildWall(selectIdx + 1, elementIdx + 1);

        selectList[selectIdx] = 0;
        buildWall(selectIdx, elementIdx + 1);
    }

    public static void main(String[] args) throws IOException {

        // 입력데이터 받기
        inputTestCase();

        // 바이러스에 인접한 빈칸 중 3개를 선택해서 바이러스가 퍼지는 것을 막을 수 있는지 확인
        selectList = new int[WALL_COUNT];
        buildWall(0, 0);

        System.out.println(answer);
    }
}
