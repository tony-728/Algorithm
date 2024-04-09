package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem2178 {
    /*
     * 
     * NxM 크기의 배열로 표현되는 미로가 있다.
     * 1은 이동할 수 있는 칸을 나타내고 0은 이동할 수 없는 칸을 나타낸다.
     * 이러한 미로가 주어졌을 때 (1,1)에서 출발하여 (N,M)의 위치로 이동할 떄 지나야 하는
     * 최소의 칸 수를 구하는 프로그램
     * 
     * 다른 칸으로 이동할 때 서로 인접한 칸으로만 이동할 수 있다.
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;

        }
    }

    static final int BLANK = 1;
    static final int WALL = 0;
    static final int START = 0;

    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, -1, 0, 1 };

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int height;
    static int width;
    static int[][] map;
    static int[][] visited;

    static int answer;

    public static void inputTestCase() throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        visited = new int[height][width];

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < width; colIdx++) {
                visited[rowIdx][colIdx] = Integer.MAX_VALUE;
                map[rowIdx][colIdx] = row.charAt(colIdx) - '0'; // 문자이므로 정수로 변환
            }
        }

    }

    public static void go() {

        Deque<Location> queue = new ArrayDeque<>();
        visited[START][START] = 1;
        queue.offer(new Location(START, START));

        while (!queue.isEmpty()) {

            Location currentLocation = queue.poll();

            int currentRowIdx = currentLocation.rowIdx;
            int currentColIdx = currentLocation.colIdx;

            if (currentRowIdx == height - 1 && currentColIdx == width - 1) {
                break;
            }

            for (int dir = 0; dir < deltaX.length; dir++) {
                int nextRowIdx = currentRowIdx + deltaX[dir];
                int nextColIdx = currentColIdx + deltaY[dir];

                // 범위 확인
                if (nextRowIdx < 0 || nextRowIdx >= height || nextColIdx < 0 || nextColIdx >= width) {
                    continue;
                }

                // 벽확인
                if (map[nextRowIdx][nextColIdx] == WALL) {
                    continue;
                }

                // 방문처리
                if (visited[nextRowIdx][nextColIdx] > visited[currentRowIdx][currentColIdx] + 1) {
                    visited[nextRowIdx][nextColIdx] = visited[currentRowIdx][currentColIdx] + 1;
                    queue.offer(new Location(nextRowIdx, nextColIdx));
                }
            }

        }
    }

    public static void main(String args[]) throws IOException {
        inputTestCase();

        go();

        System.out.println(visited[height - 1][width - 1]);
    }
}