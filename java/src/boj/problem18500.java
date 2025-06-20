package boj;

import java.io.*;
import java.util.*;

public class problem18500 {
    /**
     * 
     * RxC로 구선된 동굴
     * - 바닥부터 1이 시작
     * - 동굴에 미네랄이 있다. 미네랄은 상하좌우 중 하나로 인접한 미네랄은 클러스터가 된다.
     * 
     * 창영과 상근이 있다. 둘이 번갈아가면서 높이로 설정한 후 던진다.
     * - 창영은 왼쪽, 상근은 오른쪽에서 던진다.
     * - 창은 미네랄을 만나면 미네랄은 파괴된다.
     * - 미네랄이 파괴된 이후에 남은 클러스터가 분리될 수 있다.
     * - 새롭게 생성된 클러스터가 떠 있는 경우에는 중력에 의해 떨어진다.
     * 
     * 클러스터는 다른 클러스터나 땅을 만나기 전까지 계속 떨어진다. 
     * 클러스터는 다른 클러스터 위에 떨어질 수 있고 이후 합쳐진다. 
     * 
     * bfs, 구현
     * - 바닥에 있는 미네랄 체크를 먼저 수행하는 것이 중요한 포인트
     * - 공중에 떠있는 클러스터는 하나이므로 공중에 있는 클러스터 처리를 비교적 쉽게 할 수 있었다.
     */

    static final char EMPTY = '.';
    static final char MINERAL = 'x';
    static final int MAX_VALUE = 987654321;

    static int rowSize, colSize;
    static int numOfArrow;
    static int[] arrOfArrow;
    static char[][] map;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static StringBuilder sb = new StringBuilder();

    static void printMap() {


        for (int rowIdx = 1; rowIdx < rowSize + 1; rowIdx++) {
            for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                System.out.print(map[rowIdx][colIdx]);
            }
            System.out.println();
        }
    }

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        // 미네랄 입력
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize + 1][colSize + 1];

        for (int rowIdx = 1; rowIdx < rowSize + 1; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx - 1);

            }
        }

        // 화살 입력
        numOfArrow = Integer.parseInt(br.readLine().trim());
        arrOfArrow = new int[numOfArrow];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfArrow; idx++) {
            arrOfArrow[idx] = Integer.parseInt(st.nextToken());
        }

    }

    static void solution() {
        // throw arrow
        for (int idx = 0; idx < numOfArrow; idx++) {
            // 화살을 던지는 높이 재설정이 필요함
            int arrow = rowSize - arrOfArrow[idx] + 1;

            // left
            if (idx % 2 == 0) {
                for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                    if (map[arrow][colIdx] == MINERAL) {
                        map[arrow][colIdx] = EMPTY;
                        // checkCluster
                        checkCluster();
                        break;
                    }
                }
                // right
            } else {
                for (int colIdx = colSize; colIdx > 0; colIdx--) {
                    if (map[arrow][colIdx] == MINERAL) {
                        map[arrow][colIdx] = EMPTY;
                        // checkCluster
                        checkCluster();
                        break;
                    }
                }
            }
        }

        for (int rowIdx = 1; rowIdx < rowSize + 1; rowIdx++) {
            for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                sb.append(map[rowIdx][colIdx]);
            }
            sb.append("\n");
        }
    }

    static void checkCluster() {

        // check cluster
        boolean[][] visited = new boolean[rowSize + 1][colSize + 1];

        // 바닥에 붙어있는 클러스터 먼저 확인
        for (int colIdx = 1; colIdx <= colSize; colIdx++) {
            if (map[rowSize][colIdx] == MINERAL && !visited[rowSize][colIdx]) {
                Deque<int[]> q = new ArrayDeque<>();
                q.addLast(new int[] {rowSize, colIdx});
                visited[rowSize][colIdx] = true;

                while (!q.isEmpty()) {
                    int[] now = q.pollFirst();
                    for (int dir = 0; dir < 4; dir++) {
                        int newRow = now[0] + dx[dir];
                        int newCol = now[1] + dy[dir];

                        // 범위 체크
                        if (1 > newRow || newRow >= rowSize + 1 || 1 > newCol
                                || newCol >= colSize + 1) {
                            continue;
                        }

                        // 방문 체크
                        if (visited[newRow][newCol]) {
                            continue;
                        }

                        // 빈곳 체크
                        if (map[newRow][newCol] == EMPTY) {
                            continue;
                        }

                        visited[newRow][newCol] = true;
                        q.addLast(new int[] {newRow, newCol});
                    }
                }
            }
        }

        // 공중에 떠있는 미네랄에 대해서만 확인하게 된다.
        outLoop: for (int rowIdx = 1; rowIdx < rowSize + 1; rowIdx++) {
            for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                // 미네랄이고 처음 방문한 곳일 때
                if (map[rowIdx][colIdx] == MINERAL && !visited[rowIdx][colIdx]) {
                    Deque<int[]> clusterQ = new ArrayDeque<>();
                    Deque<int[]> q = new ArrayDeque<>();

                    q.addLast(new int[] {rowIdx, colIdx});
                    visited[rowIdx][colIdx] = true;

                    while (!q.isEmpty()) {
                        int[] loc = q.pollFirst();

                        // 공중에 있는 클러스터는 하나 밖에 없으므로 가능
                        map[loc[0]][loc[1]] = EMPTY;

                        clusterQ.addLast(new int[] {loc[0], loc[1]});

                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc[0] + dx[dir];
                            int newCol = loc[1] + dy[dir];

                            // 범위 체크
                            if (1 > newRow || newRow >= rowSize + 1 || 1 > newCol
                                    || newCol >= colSize + 1) {
                                continue;
                            }

                            // 방문 체크
                            if (visited[newRow][newCol]) {
                                continue;
                            }

                            // 빈곳 체크
                            if (map[newRow][newCol] == EMPTY) {
                                continue;
                            }

                            q.addLast(new int[] {newRow, newCol});
                            visited[newRow][newCol] = true;
                        }
                    }

                    if (!clusterQ.isEmpty()) {
                        dropCluster(clusterQ);
                        break outLoop;
                    }
                }
            }
        }
    }


    static void dropCluster(Deque<int[]> clusterQ) {
        // drop cluster
        int minDistance = MAX_VALUE;
        for (int[] loc : clusterQ) {
            int locRow = loc[0];
            int locCol = loc[1];
            int distance = 1;

            if (locRow == rowSize) {
                return;
            }

            while (distance < rowSize) {
                int newRow = locRow + distance;

                // 범위 체크
                if (newRow >= rowSize + 1) {
                    minDistance = Math.min(minDistance, distance - 1);
                    break;
                }

                if (map[newRow][locCol] == EMPTY) {
                    distance++;

                    // 미네랄이 있다면 다른 클러스터임
                } else if (map[newRow][locCol] == MINERAL) {
                    minDistance = Math.min(minDistance, distance - 1);
                    break;
                }
            }
        }

        // System.out.println("minDistance " + minDistance);

        if (minDistance != MAX_VALUE) {
            while (!clusterQ.isEmpty()) {
                int[] loc = clusterQ.pollLast();

                map[loc[0] + minDistance][loc[1]] = MINERAL;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
