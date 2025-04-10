package boj;

import java.io.*;
import java.util.*;

public class problem11559 {
    /*
     * 필드에 뿌요를 놓는다. 중력에 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
     * 뿌요를 놓고 난 후 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.
     * - 1연쇄
     * 
     * 뿌요들이 없어지고 위에 다른 뿌요들이 있다면 중력에 영향을 받아 차례대로 아래로 떨어진다.
     * 뿌요가 또 터지면 1연쇄씩 늘어난다.
     * 
     * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
     * 
     * 필드가 주어졌을 때 연쇄가 몇 번 연속으로 일어날지 계산하라
     * 
     * 구현
     * bfs
     */

    static final int ROW_SIZE = 12;
    static final int COL_SIZE = 6;
    static char[][] map = new char[ROW_SIZE][COL_SIZE];
    static boolean[][] visited = new boolean[ROW_SIZE][COL_SIZE];

    static Deque<int[]> breakPuyoQ = new ArrayDeque<>();

    static final char EMPTY = '.';

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx);
            }
        }
    }

    static void checkPuyo(int rowIdx, int colIdx) {

        Deque<int[]> q = new ArrayDeque<>();
        Deque<int[]> visitedQ = new ArrayDeque<>();

        q.addLast(new int[] {rowIdx, colIdx});

        visitedQ.addLast(new int[] {rowIdx, colIdx});
        visited[rowIdx][colIdx] = true;

        // 색깔 확인용
        char standard = map[rowIdx][colIdx];

        // 연결된 뿌요갯수 확인
        int count = 1;

        while (!q.isEmpty()) {

            int[] loc = q.pollFirst();

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = loc[0] + dx[dir];
                int newCol = loc[1] + dy[dir];

                // 범위처리
                if (0 > newRow || newRow >= ROW_SIZE || 0 > newCol || newCol >= COL_SIZE) {
                    continue;
                }

                // 방문처리
                if (visited[newRow][newCol]) {
                    continue;
                }

                // 다른 색상은 확인하지 않는다.
                if (map[newRow][newCol] != standard) {
                    continue;
                }

                q.addLast(new int[] {newRow, newCol});

                visited[newRow][newCol] = true;
                visitedQ.addLast(new int[] {newRow, newCol});

                count++;
            }
        }

        if (count >= 4) {
            while (!visitedQ.isEmpty()) {
                breakPuyoQ.addLast(visitedQ.pollFirst());
            }
        }
    }

    static void breakPuyo() {

        // 1. 터트릴 수 있는 뿌요를 터트린다.
        while (!breakPuyoQ.isEmpty()) {
            int[] loc = breakPuyoQ.pollFirst();

            map[loc[0]][loc[1]] = EMPTY;
        }

        // 2. 뿌요를 중력에 의해 내린다.
        for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {

            PriorityQueue<int[]> emptyQ = new PriorityQueue<>(new Comparator<int[]>() {

                public int compare(int[] o1, int[] o2) {
                    // row가 큰 값이 먼저 오도록
                    return o2[0] - o1[0];
                }
            });

            // 바닥부터 검사한다.
            for (int rowIdx = ROW_SIZE - 1; rowIdx > -1; rowIdx--) {
                // 빈칸이면 큐에 추가
                if (map[rowIdx][colIdx] == EMPTY) {
                    emptyQ.add(new int[] {rowIdx, colIdx});
                } else {

                    if (emptyQ.isEmpty()) {
                        continue;
                    }

                    // 공중에 떠있는 뿌요인 경우 빈칸에 채워넣는다.
                    int[] emptyLoc = emptyQ.poll();

                    // 빈칸에 뿌요 넣기
                    map[emptyLoc[0]][emptyLoc[1]] = map[rowIdx][colIdx];
                    // 기존 뿌요칸을 빈칸 처리
                    map[rowIdx][colIdx] = EMPTY;

                    // 빈칸 큐에 기존 뿌요칸 넣기
                    emptyQ.add(new int[] {rowIdx, colIdx});

                }
            }
        }

    }

    static void solution() {

        // 터트릴 뿌요가 있는지 확인
        while (true) {

            visited = new boolean[ROW_SIZE][COL_SIZE];

            for (int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
                for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {

                    if (!visited[rowIdx][colIdx] && map[rowIdx][colIdx] != EMPTY) {
                        // 같은 뿌요가 4개이상 상하좌우로 있는지 확인한다.
                        checkPuyo(rowIdx, colIdx);
                    }
                }
            }

            // 터트릴 수 있는 뿌요가 없다.
            if (breakPuyoQ.isEmpty()) {
                break;
            }

            // 뿌요를 터트린다.
            breakPuyo();
            answer++;

        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
