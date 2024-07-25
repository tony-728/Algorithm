package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2206 {
    /*
     * nxm 맵이 있다.
     * 0은 이동할 수 있는 곳
     * 1은 이동할 수 없는 벽이 있는 곳
     * 
     * (1,1)에서 (n, m) 위치까지 최단 경로로 이동하려 한다.
     * 최단 경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말한다.
     * 시작하는 칸과 끝나는 칸도 포함해서 샌다.
     * 
     * 만약 이동 도중 한 개의 벽을 부수고 이동하는 것이 더 경로가 짧아진다면 벽을 한 개까지 부수고 이동해도 된다.
     * 
     * 한칸에서 이동할 수 있는 칸은 4방(상하좌우)
     * 
     * 
     * 시작한 위치에서 최단 거리 = bfs
     * 고려해야하는 것 벽을 한번 부술 수 있다.
     * -> 3차원 배열로 생각 3차원에는 2비트(벽을 부쉈다. 부수지 않았다.)
     * 
     */

    static class Location implements Comparable<Location> {
        int rowIdx;
        int colIdx;
        int distance;
        int power;

        public Location(int rowIdx, int colIdx, int distance, int power) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.distance = distance;
            this.power = power;
        }

        @Override
        public int compareTo(Location location) {
            return this.distance - location.distance;
        }


    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int answer;

    static final int ROAD = 0;
    static final int WALL = 1;
    static final int GET_POWER = 0;
    static final int LOSE_POWER = 1;


    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int rowSize;
    static int colSize;
    static int[][] map;
    static boolean[][][] visited;

    static final Location START = new Location(0, 0, 1, GET_POWER);

    static void inputTestCase() throws IOException {

        answer = -1;

        st = new StringTokenizer(br.readLine().trim());

        // 맵 크기 입력
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵, 방문처리 맵 생성
        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize][2];

        // 맵정보 입력
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx) - '0';
            }
        }
    }


    static void goEnd() {
        // 가장 짧은 거리를 이동한 것을 보장하기 위해 priorityqueue
        Queue<Location> locationQ = new PriorityQueue<>();

        locationQ.add(START);

        while (!locationQ.isEmpty()) {
            Location currLocation = locationQ.poll();

            int currRowIdx = currLocation.rowIdx;
            int currColIdx = currLocation.colIdx;
            int currDistance = currLocation.distance;
            int currPower = currLocation.power;

            if (currLocation.rowIdx == rowSize - 1 && currLocation.colIdx == colSize - 1) {
                answer = currLocation.distance;
                break;
            }

            for (int dir = 0; dir < dx.length; dir++) {

                int newRowIdx = currRowIdx + dx[dir];
                int newColIdx = currColIdx + dy[dir];

                // 범위 확인
                if (0 > newRowIdx || newRowIdx >= rowSize || 0 > newColIdx
                        || newColIdx >= colSize) {
                    continue;
                }

                // 벽을 부순적이 있는 경우 방문처리
                if (currPower == LOSE_POWER && visited[newRowIdx][newColIdx][LOSE_POWER] == true) {
                    continue;
                }

                // 벽을 부순적이 없는 경우 방문처리
                if (currPower == GET_POWER && visited[newRowIdx][newColIdx][GET_POWER] == true) {
                    continue;
                }

                // 벽 확인
                if (map[newRowIdx][newColIdx] == WALL) {
                    // 벽을 부순적이 없는지 확인
                    if (currPower == GET_POWER) {
                        visited[newRowIdx][newColIdx][GET_POWER] = true;
                        locationQ.add(
                                new Location(newRowIdx, newColIdx, currDistance + 1, LOSE_POWER));
                    }
                    // 벽 아님
                } else {
                    visited[newRowIdx][newColIdx][currPower] = true;
                    locationQ.add(new Location(newRowIdx, newColIdx, currDistance + 1, currPower));

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        goEnd();

        System.out.println(answer);
    }
}
