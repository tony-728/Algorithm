package boj;

import java.io.*;
import java.util.*;

public class problem1012 {

    /*
    배추와 인접한 4 배추가 보호를 받는다.
    
    서로 인접한 배추들이 몇군데에 퍼져있는지 파악해서
    지렁이가 총 몇마리 필요한지 
    
    0은 배추 없음, 1은 배추있음
    
    bfs
    
    입력
    - 테스트케이스 갯수
    - 배추밭의 가로, 세로 길이
    - 배추가 심어져 있는 위치의 개수
    - 배추의 위치
    
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

    static int testCase;
    static int rowSize, colSize;
    static boolean[][] map;
    static int numOfCoin;

    static int answer;

    static Location[] coinList;
    static boolean[][] visited;


    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        numOfCoin = Integer.parseInt(st.nextToken());

        map = new boolean[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        coinList = new Location[numOfCoin];

        for (int idx = 0; idx < numOfCoin; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            map[row][col] = true;

            coinList[idx] = new Location(row, col);
        }
    }

    static boolean checkBoundary(int row, int col) {

        if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
            return true;
        }

        return false;
    }

    static void bfs(int r, int c) {
        Deque<Location> q = new ArrayDeque<>();

        q.addLast(new Location(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {

            Location loc = q.pollFirst();

            int row = loc.rowIdx;
            int col = loc.colIdx;

            for (int idx = 0; idx < dx.length; idx++) {
                int newRow = row + dx[idx];
                int newCol = col + dy[idx];

                // 범위 안에 있고 방문하지 않았고 배추가 있다.
                if (checkBoundary(newRow, newCol) && visited[newRow][newCol] == false
                        && map[newRow][newCol] == true) {

                    visited[newRow][newCol] = true;
                    q.addLast(new Location(newRow, newCol));
                }
            }

        }


    }

    static void solution() {
        for (int idx = 0; idx < numOfCoin; idx++) {
            Location loc = coinList[idx];

            int rowIdx = loc.rowIdx;
            int colIdx = loc.colIdx;

            if (visited[rowIdx][colIdx] == false) {
                answer++;
                bfs(rowIdx, colIdx);
            }
        }

        sb.append(answer).append("\n");
    }

    public static void main(String args[]) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < testCase; idx++) {
            inputData();

            solution();
        }

        System.out.println(sb);
    }
}
