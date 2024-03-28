package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem1194 {
    /*
     * 
     * 빈칸 . : 언제나 이동할 수 있다. 벽 # : 이동할 수 없다. 열쇠(a,b,..): 이동할 수 있다. 처음들어가면 열쇠를 집는다.
     * 문(A,B,..): 대응하는 열쇠가 있으면 이동할 수 있다. 현재 위치 0 도착점 1
     * 
     * 미로를 탈출하자 한번 움직임은 상하좌우
     * 
     * 미로를 탈출하는데 걸리는 이동 횟수의 최솟값
     * 
     * 미로를 탈출하기 위한 최소값을 구해야하기 때문에 BFS 알고리즘과 다익스트라 알고리즘을 생각할 수 있다. 미로에서는 열쇠와 문이라는 조건이
     * 추가로 등장하기 때문에 방문처리를 다르게 해주어야 한다. 이동경로를 갱신하는 배열이 가로+세로+(등장하는 열쇠개수)만큼의 차원이 필요하다.
     * -> 열쇠개수가 많아지게 되면 차원이 굉장히 커지기 때문에 다른 방법이 필요하다 3차원 배열로 생성하고 마지막 차원을 비트마스킹을 이용하여
     * 열쇠의 종류를 모두 처리할 수 있다.
     * 
     * 방문을 처리할 배열에 대한 고민이후에 가장 빠르게 도착점에 도착하는 경우를 고민해야한다. 단순히 큐를 사용하여 들어오는 순서대로 처리하게
     * 되면 도착점에 도착할 때마다 최소비용인지 확인하며 큐가 빌 때까지 반복해야한다. -> 우선순위큐를 사용하면 가장 작은 비용으로 이동하는
     * 경우만 고려할 수 있으므로 도착점에 최초에 도착할 때에 비용이 가장 적은 비용이 된다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int rowSize;
    static int colSize;
    static char[][] map;
    static int[][][] visited;
    static int answer;

    static final char BLANK = '.';
    static final char WALL = '#';
    static final char START = '0';
    static final char END = '1';

    static int startRowIdx;
    static int startColIdx;

    static final char KEY = 'a';
    static final char DOOR = 'A';
    static final int INF = Integer.MAX_VALUE;

    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, -1, 0, 1 };

    static class Minsick implements Comparable<Minsick> {
        int rowIdx;
        int colIdx;
        int cost;
        int keys;

        public Minsick(int rowIdx, int colIdx, int cost, int keys) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.cost = cost;
            this.keys = keys;
        }

        public int compareTo(Minsick node) {
            return this.cost - node.cost;
        }
    }

    public static void inputTestCase() throws IOException {

        answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        // 열쇠의 개수가 6개이고 열쇠를 갖지 않는 경우도 있으므로 6+1
        visited = new int[rowSize][colSize][1 << 7];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {

            String row = br.readLine().trim();

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                Arrays.fill(visited[rowIdx][colIdx], INF);

                map[rowIdx][colIdx] = row.charAt(colIdx);

                // 민식이의 시작위치 저장
                if (map[rowIdx][colIdx] == START) {
                    map[rowIdx][colIdx] = '.';
                    startRowIdx = rowIdx;
                    startColIdx = colIdx;
                }
            }
        }

    }

    public static void findEnd() {

        PriorityQueue<Minsick> queue = new PriorityQueue<>();

        // 시작위치에서 민식이 만들기
        Minsick node = new Minsick(startRowIdx, startColIdx, 0, 0);

        // 시작위치에 민식이 큐에 넣기
        queue.offer(node);
        visited[startRowIdx][startColIdx][0] = 0;

        while (!queue.isEmpty()) {

            Minsick currentNode = queue.poll();

            int currentRow = currentNode.rowIdx;
            int currentCol = currentNode.colIdx;
            int currentKeys = currentNode.keys;
            int currentCost = currentNode.cost;

            if (map[currentRow][currentCol] == END) {
                answer = currentNode.cost;
                break;
            }

            for (int dir = 0; dir < deltaX.length; dir++) {

                int newRowIdx = currentRow + deltaX[dir];
                int newColIdx = currentCol + deltaY[dir];

                // 범위 확인
                if (newRowIdx < 0 || newRowIdx >= rowSize || newColIdx < 0 || newColIdx >= colSize) {
                    continue;
                }

                // 벽 확인
                if (map[newRowIdx][newColIdx] == WALL) {
                    continue;
                }

                // 열쇠확인
                // 열쇠인 경우 열쇠를 얻었음을 비트마스킹해주어야 한다.
                if ('a' <= map[newRowIdx][newColIdx] && map[newRowIdx][newColIdx] <= 'f') {
                    int newkey = (1 << (map[newRowIdx][newColIdx] - KEY)); // 해당 키를 얻었음을 비트마스킹
                    int newKeys = currentKeys | newkey; // 현재 노드의 키와 새로 얻은 키와 | 연산을 통해 세팅

                    // 최소 이동 횟수가 갱신이 가능한지 확인한다.
                    // 갱신이 가능하면
                    if (visited[newRowIdx][newColIdx][newKeys] > currentCost + 1) {
                        visited[newRowIdx][newColIdx][newKeys] = currentCost + 1;

                        Minsick newNode = new Minsick(newRowIdx, newColIdx, currentCost + 1, newKeys);
                        queue.offer(newNode);

                    }
                }

                // 문확인
                // 문에 맞는 열쇠가 있는지 확인후 있다면 큐에 추가한다.
                else if ('A' <= map[newRowIdx][newColIdx] && map[newRowIdx][newColIdx] <= 'F') {
                    int door = (1 << (map[newRowIdx][newColIdx] - DOOR));
                    if ((currentKeys & door) == door) {

                        // 최소 이동 횟수가 갱신이 가능한지 확인한다.
                        // 갱신이 가능하면
                        if (visited[newRowIdx][newColIdx][currentKeys] > currentCost + 1) {
                            visited[newRowIdx][newColIdx][currentKeys] = currentCost + 1;

                            Minsick newNode = new Minsick(newRowIdx, newColIdx, currentCost + 1, currentKeys);
                            queue.offer(newNode);

                        }
                    }
                }

                // 빈칸이거나 도착점
                else {

                    // 최소 이동 횟수가 갱신이 가능한지 확인한다.
                    // 갱신이 가능하면
                    if (visited[newRowIdx][newColIdx][currentKeys] > currentCost + 1) {
                        visited[newRowIdx][newColIdx][currentKeys] = currentCost + 1;

                        Minsick newNode = new Minsick(newRowIdx, newColIdx, currentCost + 1, currentKeys);
                        queue.offer(newNode);
                    }

                }

            }

        }

    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        findEnd();

        // 도착점에 도착하지 않는 경우 처리
        if (answer == INF) {
            answer = -1;
        }

        System.out.println(answer);
    }

}
