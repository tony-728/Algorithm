package boj;

import java.io.*;
import java.util.*;

public class problem7569 {
    /*
     * 
     * 토마토 상자가 3차원으로 존재
     * 
     * 익지 않은 토마토가 하루가 지나면 인접한 곳에
     * 익지 않은 토마토에 영향을 주어 익게 만든다.
     * - 대각선에 있는 토마토에는 영향을 주지 못한다.
     * 
     * bfs
     * 
     * 토마토들이 며칠이 지나면 다 익게 되는지 최소일수를 구하라
     * - 토마토가 모두 익지 못하는 상황이면 -1을 출력
     * 
     */

    static class Location {
        int x;
        int y;
        int z;
        int day;

        public Location(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int answer;
    static int width, height, depth;

    static int count;

    static int[][][] box;

    static final int WELL_TOMATO = 1;
    static final int NONE_TOMATO = 0;
    static final int EMPTY = -1;

    static final int[] dx = {-1, 0, 1, 0, 0, 0};
    static final int[] dy = {0, -1, 0, 1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, -1, 1};

    static Deque<Location> q = new ArrayDeque<>();
    static boolean[][][] visited;

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());


        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        depth = Integer.parseInt(st.nextToken());

        box = new int[width][height][depth];
        visited = new boolean[width][height][depth];

        for (int depthIdx = 0; depthIdx < depth; depthIdx++) {
            for (int heightIdx = 0; heightIdx < height; heightIdx++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int idx = 0; idx < width; idx++) {
                    int val = Integer.parseInt(st.nextToken());

                    box[idx][heightIdx][depthIdx] = val;

                    if (val == WELL_TOMATO) {
                        q.addLast(new Location(idx, heightIdx, depthIdx, 0));
                        visited[idx][heightIdx][depthIdx] = true;
                    }

                    // 익지 않은 토마토 개수 확인
                    if (val == NONE_TOMATO) {
                        count++;
                    }
                }
            }
        }
    }

    static boolean checkBoundry(int x, int y, int z) {
        if (x > -1 && x < width && y > -1 && y < height && z > -1 && z < depth) {
            return true;
        } else {
            return false;
        }
    }

    static void solution() {

        while (!q.isEmpty()) {

            Location loc = q.pollFirst();

            int x = loc.x;
            int y = loc.y;
            int z = loc.z;
            int day = loc.day;

            answer = day;

            for (int idx = 0; idx < dx.length; idx++) {
                int newX = x + dx[idx];
                int newY = y + dy[idx];
                int newZ = z + dz[idx];

                // 위 안에 위치, 방문하지 않은 곳, 익지 않은 토마토
                if (checkBoundry(newX, newY, newZ) && visited[newX][newY][newZ] == false
                        && box[newX][newY][newZ] == NONE_TOMATO) {

                    q.addLast(new Location(newX, newY, newZ, day + 1));

                    // 방문 처리
                    visited[newX][newY][newZ] = true;

                    // 토마토가 익었으므로 개수 감소
                    count--;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(count == 0 ? answer : -1);
    }
}
