package boj;

import java.io.*;
import java.util.*;

public class problem16234 {
    /*
     * nxn 크기 땅이 있고 1x1로 나눠져 있다.
     * 각 땅에는 인구수가 있다.
     * 
     * 하루 동안 다음과 같이 진행된다.
     * - 국경을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면 두 나라가 공유하는 국경선을 연다
     * - 위 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
     * - 국경선이 열러있어 인접한 칸만을 이용해 이동할 수 있으면 그 나라를 연합이라고 한다.
     * - 연합을 이루고 있는 각 칸의 인구수는 연합의 인구수 / 연합을 이루고 있는 칸의 개수로 구한다. (소수점은 버림)
     * - 종료
     * 
     * 
     * 인구 이동이 며칠동안 발생하는지 구하는 프로그램을 구하라
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int size;
    static int minimum;
    static int maximum;

    static int[][] map;
    static boolean[][] visited;
    static boolean flag;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int answer;

    static class Location {
        int rowIdx;
        int colIdx;


        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

    }

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        size = Integer.parseInt(st.nextToken());
        minimum = Integer.parseInt(st.nextToken());
        maximum = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        for (int rowIdx = 0; rowIdx < size; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < size; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return false;
        }

        return true;
    }

    static void check(int rowIdx, int colIdx) {

        int count = 0;
        int sum = 0;

        Deque<Location> q = new ArrayDeque<>();
        Deque<Location> group = new ArrayDeque<>();

        q.addLast(new Location(rowIdx, colIdx)); // bfs용 큐
        group.addLast(new Location(rowIdx, colIdx)); // bfs로 확인한 위치를 저장 큐
        visited[rowIdx][colIdx] = true;

        while (!q.isEmpty()) {

            Location loc = q.pollFirst();

            int currentRowIdx = loc.rowIdx;
            int currentColIdx = loc.colIdx;

            int cost = map[currentRowIdx][currentColIdx];

            sum += cost;
            count++;

            for (int idx = 0; idx < dx.length; idx++) {
                int newRowIdx = currentRowIdx + dx[idx];
                int newColIdx = currentColIdx + dy[idx];


                // 범위 밖이면 통과
                if (checkBoundary(newRowIdx, newColIdx) == false) {
                    continue;
                }

                int diff = Math.abs(cost - map[newRowIdx][newColIdx]);

                //  처음 방문 인구 수 차이가 범위 이내일 때
                if (visited[newRowIdx][newColIdx] == false
                        && (diff >= minimum && diff <= maximum)) {

                    q.addLast(new Location(newRowIdx, newColIdx));
                    group.addLast(new Location(newRowIdx, newColIdx));
                    visited[newRowIdx][newColIdx] = true;

                    flag = true;
                }
            }
        }

        // 인구 수 갱신
        for (Location loc : group) {
            int r = loc.rowIdx;
            int c = loc.colIdx;

            map[r][c] = sum / count;
        }

    }

    static void solution() {

        while (true) {

            visited = new boolean[size][size];
            flag = false;

            for (int rowIdx = 0; rowIdx < size; rowIdx++) {
                for (int colIdx = 0; colIdx < size; colIdx++) {
                    if (visited[rowIdx][colIdx] == false) {
                        check(rowIdx, colIdx);
                    }
                }
            }

            if (flag == true) {
                answer++;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        solution();

        System.out.println(answer);
    }
}
