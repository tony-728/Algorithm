package boj;

import java.io.*;
import java.util.*;

public class problem7562 {
    /*
     * 체스판 위에 나이트가 있다.
     * 나이트가 이동하려고 하는 칸이 주어질 때 나이트는 몇 번 움직이면 해당 칸으로 움직일 수 있는가?
     * 
     * 
     * bfs
     * - 최대 변의 길이가 300이므로 가능하다
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int[] start;
    static int[] end;


    static void inputData() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        start = new int[3];
        end = new int[2];

        st = new StringTokenizer(br.readLine().trim());

        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        start[2] = 0;

        st = new StringTokenizer(br.readLine().trim());

        end[0] = Integer.parseInt(st.nextToken());
        end[1] = Integer.parseInt(st.nextToken());

    }

    static void solution() {

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[mapSize][mapSize];

        q.addLast(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] loc = q.pollFirst();

            if (loc[0] == end[0] && loc[1] == end[1]) {
                sb.append(loc[2]).append("\n");
                break;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = loc[0] + dx[dir];
                int newCol = loc[1] + dy[dir];

                // 범위체크
                if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                    continue;
                }

                // 방문체크
                if (visited[newRow][newCol]) {
                    continue;
                }

                visited[newRow][newCol] = true;
                q.addLast(new int[] {newRow, newCol, loc[2] + 1});

            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            inputData();

            solution();
        }

        System.out.println(sb);

    }
}
