package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem9205 {

    /*
     * 맥주는 20개 50미터에 한 병씩 마신다. 50미터를 가려면 그 직전에 맥주 한병을 마셔야 한다. 중간에 편의점에서 빈병을 버리고 새
     * 맥주는 살 수 있다. 하지만 박스의 맥주 20병을 넘을 수 없다.
     * 
     * 편의점, 집, 목적지 좌표가 주어질 때 행복하게 목적지에 도착할 수 있는지 구하라 행복하게 갈 수 있으면 happy, 없으면 sad
     * 
     * 테스트케이스가 주어진다. 편의점의 개수 n이 주어진다. 집, 목적지 좌표가 주어진다. 2차원 배열에 각각이 위치하게 되며 좌표의 사이
     * 거리는 맨해튼 거리이다.
     * 
     * 
     * 출발지를 큐에 넣고 시작한다.
     * 
     * 0. 현재 위치에서 도착지까지 갈 수 있는지 확인한다.
     * 1. 갈 수 있으면 happy 리턴
     * 2. 갈 수 없으면 모든 경유지를 갈 수 있는지 확인한다.
     * 2-1. 방문하지 않는 경유지이고 갈 수 있는 경유지는 방문체크하고 큐에 추가한다.
     * 3. 0번부터 다시 반복한다.
     * 4. 큐에 있는 요소가 없다면 sad 리턴
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final String CAN_GO = "happy\n";
    static final String CANT_GO = "sad\n";
    static final int DIFF = 50 * 20;

    static int testCase;
    static int numOfStopover;

    static Location[] stopoverList;
    static boolean[] visited;

    static Location start;
    static Location end;

    public static void inputTestCase() throws IOException {

        numOfStopover = Integer.parseInt(br.readLine().trim());

        stopoverList = new Location[numOfStopover];

        st = new StringTokenizer(br.readLine().trim());

        int rowIdx = Integer.parseInt(st.nextToken());
        int colIdx = Integer.parseInt(st.nextToken());

        start = new Location(rowIdx, colIdx);

        for (int idx = 0; idx < numOfStopover; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            rowIdx = Integer.parseInt(st.nextToken());
            colIdx = Integer.parseInt(st.nextToken());

            stopoverList[idx] = new Location(rowIdx, colIdx);
        }

        st = new StringTokenizer(br.readLine().trim());

        rowIdx = Integer.parseInt(st.nextToken());
        colIdx = Integer.parseInt(st.nextToken());

        end = new Location(rowIdx, colIdx);

    }

    public static int distance(Location leftLoc, Location rightLoc) {

        return Math.abs(leftLoc.rowIdx - rightLoc.rowIdx) + Math.abs(leftLoc.colIdx - rightLoc.colIdx);

    }

    public static void checkBeer() {

        visited = new boolean[numOfStopover];
        Deque<Location> locationQueue = new ArrayDeque<>();

        // 처음 위치 저장
        locationQueue.offer(start);

        while (!locationQueue.isEmpty()) {

            Location current = locationQueue.poll();

            // 현재 위치가 도착지에 갈 수 있는 거리인지 확인
            if (distance(current, end) <= DIFF) {
                sb.append(CAN_GO);
                return;
            }

            // 모든 경유지를 확인하여 갈 수 있는지 확인
            // 갈 수 있는 경유지면 큐에 추가
            for (int idx = 0; idx < numOfStopover; idx++) {
                if (visited[idx] == false) {
                    if (distance(current, stopoverList[idx]) <= DIFF) {

                        visited[idx] = true;
                        locationQueue.offer(stopoverList[idx]);
                    }

                }
            }

        }

        sb.append(CANT_GO);
        return;

    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {

            inputTestCase();

            checkBeer();
        }

        System.out.println(sb);

    }

}
