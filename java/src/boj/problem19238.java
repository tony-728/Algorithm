package boj;

import java.io.*;
import java.util.*;

public class problem19238 {
    /*
     * 손님을 도착지로 데려다줄 때마다 연료가 충전
     * 연료가 바닥나면 그 날의 업무가 끝난다.
     * 
     * 오늘 M명의 승객을 태우는 것이 목표이다.
     * NxN 배열
     * - 칸이 비어있거나 벽이 놓여 있다.
     * - 택시가 빈칸에 있을 때 상하좌우로 빈칸인 곳으로 이동할 수 있다.
     * - 항상 최단 경로로 이동한다.
     * 
     * M명의 승객은 빈칸에 위치한다.
     * - 여러 승객이 같이 탑승하는 경우는 없다.
     * - 한 승객을 태워 목적지로 이동해야 한다. 
     * - 최단거리가 가장 짧은 승객, 행 번호가 가장 작은 승객, 열 번호가 가장 작은 승객 우선
     * - 택시와 승객이 같은 위치에 있으면 최단 거리 0
     * - 한 칸 이동할 때마다 1만큼 연료 소모
     * - 목적지로 이동하면 소모한 연료 양의 두 배가 충전
     * - 승객을 목적지로 이동시킨 동시에 연료가 바닥나는 것은 실패한 것이 아님
     * 
     * 맵의 크기 최대 20
     * 승객의 최대 400
     * 연료 최대 50만
     * 
     * bfs
     * 
     * 기준에 따라 승객을 찾는 순서
     * 상좌우하
     * 
     */
    static class Passenger {
        int rowIdx;
        int colIdx;
        int targetRow;
        int targetCol;

        public Passenger(int rowIdx, int colIdx, int targetRow, int targetCol) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.targetRow = targetRow;
            this.targetCol = targetCol;
        }
    }

    static class Taxi {
        int rowIdx;
        int colIdx;
        int currentFuel;
        int usedFuel;
        int passenger;

        public Taxi(int rowIdx, int colIdx, int currentFuel, int usedFuel, int passenger) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.currentFuel = currentFuel;
            this.usedFuel = usedFuel;
            this.passenger = passenger;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;
    static int numOfPassenger;

    static int passengerCount;
    static Passenger[] arrOfPassenger;
    static int globalFuel;

    static int taxiRow, taxiCol;

    static int answer;

    // 상좌우하
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int NO_PASSENGER = 0;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        numOfPassenger = Integer.parseInt(st.nextToken());
        globalFuel = Integer.parseInt(st.nextToken());

        passengerCount = 0;

        map = new int[mapSize + 1][mapSize + 1];
        arrOfPassenger = new Passenger[numOfPassenger + 2];

        for (int rowIdx = 1; rowIdx <= mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 1; colIdx <= mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());

        // 최초 택시의 위치
        taxiRow = Integer.parseInt(st.nextToken());
        taxiCol = Integer.parseInt(st.nextToken());

        // 승객의 위치를 배열에 저장한다.
        for (int idx = 0; idx < numOfPassenger; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            // 승객의 위치
            int passengerRow = Integer.parseInt(st.nextToken());
            int passengerCol = Integer.parseInt(st.nextToken());

            // 목적지의 위치
            int targetRow = Integer.parseInt(st.nextToken());
            int targetCol = Integer.parseInt(st.nextToken());

            // 승객의 위치만 맵의 표시한다.
            map[passengerRow][passengerCol] = idx + 2;

            // 승객과 목적지를 저장하는 배열
            arrOfPassenger[idx + 2] =
                    new Passenger(passengerRow, passengerCol, targetRow, targetCol);
        }
    }

    static boolean checkBoundary(int row, int col) {
        return 1 <= row && row <= mapSize && 1 <= col && col <= mapSize;
    }

    static boolean checkTarget(Taxi taxi) {
        // 현재 택시 위치에 승객이 있는지 체크
        return taxi.rowIdx == arrOfPassenger[taxi.passenger].targetRow
                && taxi.colIdx == arrOfPassenger[taxi.passenger].targetCol;
    }

    static boolean goTarget(Taxi taxi) {

        boolean isArrive = false;

        Deque<Taxi> taxiQ = new ArrayDeque<>();

        taxiQ.addLast(taxi);

        boolean[][] visited = new boolean[mapSize + 1][mapSize + 1];

        while (!taxiQ.isEmpty()) {

            Taxi passengerTaxi = taxiQ.pollFirst();

            // 승객의 목적지로 이동했다.
            if (checkTarget(passengerTaxi)) {

                // System.out.println("usedFuel " + passengerTaxi.usedFuel + " row "
                //         + passengerTaxi.rowIdx + " col " + passengerTaxi.colIdx + " passenger "
                //         + passengerTaxi.passenger + " taxi " + taxi.passenger);

                // 연료 처리
                globalFuel = passengerTaxi.currentFuel + (passengerTaxi.usedFuel * 2);

                // 승객을 데려다 주었으므로 카운트 증가
                passengerCount++;

                // 모든 승객을 운송했다.
                if (passengerCount == numOfPassenger) {
                    answer = globalFuel;
                }

                isArrive = true;

                break;
            }


            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = passengerTaxi.rowIdx + dx[dir];
                int newCol = passengerTaxi.colIdx + dy[dir];
                int totalFuel = passengerTaxi.currentFuel - 1;
                int newUsedFuel = passengerTaxi.usedFuel + 1;

                // 연료가 없으면 못간다.
                if (totalFuel < 0) {
                    break;
                }

                // 범위 체크
                if (!checkBoundary(newRow, newCol)) {
                    continue;
                }

                // 방문처리
                if (visited[newRow][newCol]) {
                    continue;
                }

                // 벽은 이동 불가
                if (map[newRow][newCol] == WALL) {
                    continue;
                }

                taxiQ.add(
                        new Taxi(newRow, newCol, totalFuel, newUsedFuel, passengerTaxi.passenger));
                visited[newRow][newCol] = true;

            }
        }

        return isArrive;

    }

    static void solution() {

        Deque<Taxi> q = new ArrayDeque<>();
        boolean isPassenger = false;
        boolean[][] visited = new boolean[mapSize + 1][mapSize + 1];
        visited[taxiRow][taxiCol] = true;

        // 최초 택시의 위치를 큐에 저장
        q.add(new Taxi(taxiRow, taxiCol, globalFuel, 0, map[taxiRow][taxiCol]));

        // 사이클
        // 사이클을 추가하는 이유는 택시에 위치에서 최단 거리에 있는 승객의 우선순위가 있기 때문
        q.add(new Taxi(-1, 0, 0, 0, 0));

        out: while (!q.isEmpty()) {

            // 모든 승객을 운송함
            if (passengerCount == numOfPassenger) {
                break;
            }

            Taxi taxi = q.poll();

            // 현재 위치에 승객이 있는지 확인해야한다.
            if (taxi.rowIdx > 0 && (map[taxi.rowIdx][taxi.colIdx] > 1)) {
                // 승객을 태운 택시를 목적지로 이동한다.
                if (goTarget(taxi)) {
                    q.clear();

                    int targetRow = arrOfPassenger[map[taxi.rowIdx][taxi.colIdx]].targetRow;
                    int targetCol = arrOfPassenger[map[taxi.rowIdx][taxi.colIdx]].targetCol;

                    q.addLast(new Taxi(targetRow, targetCol, globalFuel, 0,
                            map[targetRow][targetCol]));
                    q.addLast(new Taxi(-1, 0, 0, 0, 0));

                    // 현재 위치에 승객을 태웠으므로 빈칸처리
                    map[taxi.rowIdx][taxi.colIdx] = EMPTY;

                    // 방문 배열 초기화
                    visited = new boolean[mapSize + 1][mapSize + 1];
                    visited[targetRow][targetCol] = true;

                    // 승객여부 초기화
                    isPassenger = false;

                    continue;

                } else {
                    break;
                }

            }

            // 사이클에 들어옴
            if (taxi.rowIdx == -1) {

                // 승객을 태우지 않은 사이클일 때 다시 사이클 추가
                if (!isPassenger) {
                    if (q.isEmpty()) {
                        break;
                    }

                    q.addLast(new Taxi(-1, 0, 0, 0, 0));
                    continue;
                }

                // 승객을 태운 사이클
                // 현재 큐에서 우선순위가 높은 승객을 찾아야 한다.
                int passengerRow = 987654321;
                int passengerCol = 987654321;
                int fuel = 0;

                while (!q.isEmpty()) {

                    Taxi passengerInTaxi = q.poll();

                    // 택시 위치에 승객이 있다.
                    if (passengerInTaxi.passenger > 1) {

                        if (passengerRow > passengerInTaxi.rowIdx) {
                            passengerRow = passengerInTaxi.rowIdx;
                            passengerCol = passengerInTaxi.colIdx;

                        } else if (passengerRow == passengerInTaxi.rowIdx) {
                            passengerCol = Math.min(passengerCol, passengerInTaxi.colIdx);
                        }

                        fuel = passengerInTaxi.currentFuel;
                    }
                }

                // System.out.println("start row " + passengerRow + " col " + passengerCol
                //         + " currentFuel " + fuel + " passenger " + map[passengerRow][passengerCol]);

                // 승객을 태운 택시를 목적지로 이동한다.
                if (goTarget(new Taxi(passengerRow, passengerCol, fuel, 0,
                        map[passengerRow][passengerCol]))) {
                    q.clear();

                    int targetRow = arrOfPassenger[map[passengerRow][passengerCol]].targetRow;
                    int targetCol = arrOfPassenger[map[passengerRow][passengerCol]].targetCol;

                    q.addLast(new Taxi(targetRow, targetCol, globalFuel, 0,
                            map[targetRow][targetCol]));
                    q.addLast(new Taxi(-1, 0, 0, 0, 0));

                    // 현재 위치에 승객을 태웠으므로 빈칸처리
                    map[passengerRow][passengerCol] = EMPTY;

                    // 방문 배열 초기화
                    visited = new boolean[mapSize + 1][mapSize + 1];
                    visited[targetRow][targetCol] = true;

                    // 승객여부 초기화
                    isPassenger = false;

                    continue;

                } else {
                    break;
                }

            }

            // 인접한 위치로 이동
            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = taxi.rowIdx + dx[dir];
                int newCol = taxi.colIdx + dy[dir];
                int totalFuel = taxi.currentFuel - 1;

                // 연료가 없으면 갈 수 없다.
                if (totalFuel < 0) {
                    break out;
                }

                // 범위 체크
                if (!checkBoundary(newRow, newCol)) {
                    continue;
                }

                // 방문처리
                if (visited[newRow][newCol]) {
                    continue;
                }

                // 벽은 이동 불가
                if (map[newRow][newCol] == WALL) {
                    continue;
                }

                // 승객이 없는 택시이고
                // 승객이 있는 곳을 갔을 떄
                if (map[newRow][newCol] > 1) {
                    isPassenger = true;
                }

                q.add(new Taxi(newRow, newCol, totalFuel, taxi.usedFuel + 1, map[newRow][newCol]));
                visited[newRow][newCol] = true;
            }
        }

        // 모든 승객을 운송하지 못함
        if (passengerCount != numOfPassenger) {
            answer = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
