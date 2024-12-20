package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class 보물지도 {
    /*
    
    가로가 n, 세로가 m인 직사각형
    
    맨 왼쪽 아래 칸의 좌표를 (1,1)
    맨 오른쪽 위 칸의 좌표를 (n,m) = 보물에 위치
    함정이 있는 칸으로는 움직일 수 없다.
    
    (1,1)에서 출발해 보물이 있는 칸으로 이동
    - 상하좌우로 이동할 수 있다.
    - 한 칸을 이동하는데 걸리는 시간은 1
    
    신발을 한번 이용하면 한번에 두칸을 이동할 수 있고 함정이 있는 칸도 넘을 수 있다.
    
    보물이 위치한 칸으로 이동하는데 필요한 최소 시간
    - 만약 보물이 있는 칸으로 이동할 수 없다면 -1
    
    bfs
    - 신발에 사용을 고려해서 방문배열을 관리해야함
    
    */

    static int rowSize;
    static int colSize;

    static class Location implements Comparable<Location> {
        int x;
        int y;
        int spell;
        int cost;

        public Location(int x, int y, int spell, int cost) {
            this.x = x;
            this.y = y;
            this.spell = spell;
            this.cost = cost;
        }

        public int compareTo(Location o) {
            return this.cost - o.cost;
        }
    }

    public static boolean checkBoundary(int x, int y) {
        if (x > 0 && x < rowSize && y > 0 && y < colSize) {
            return true;
        } else {
            return false;
        }
    }

    public static int solution(int n, int m, int[][] hole) {
        int answer = Integer.MAX_VALUE;

        rowSize = m + 1;
        colSize = n + 1;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        boolean[][] map = new boolean[rowSize][colSize];
        boolean[][][] visited = new boolean[rowSize][colSize][2];

        // 홀 위치 초기화
        for (int holeIdx = 0; holeIdx < hole.length; holeIdx++) {
            int x = hole[holeIdx][1];
            int y = hole[holeIdx][0];

            map[x][y] = true;
        }


        // PriorityQueue<Location> q = new PriorityQueue<>();

        Deque<Location> q = new ArrayDeque<>();

        q.add(new Location(1, 1, 0, 0));
        visited[1][1][0] = true;


        while (!q.isEmpty()) {

            Location loc = q.poll();

            int x = loc.x;
            int y = loc.y;
            int spell = loc.spell;
            int cost = loc.cost;

            // 목적지에 도달
            if (x == m && y == n) {

                // answer = cost;
                // break;

                if (answer > cost) {
                    answer = cost;
                }
            }

            for (int idx = 0; idx < dx.length; idx++) {
                int newX = x + dx[idx];
                int newY = y + dy[idx];

                // 범위 안에 있고 함정이 아니고 스펠 상태에 따른 방문이 처음일 때
                if (checkBoundary(newX, newY) && map[newX][newY] == false
                        && visited[newX][newY][spell] == false) {

                    q.add(new Location(newX, newY, spell, cost + 1));
                    visited[newX][newY][spell] = true;
                }

                if (spell == 0) {
                    int spellX = x + (dx[idx] * 2);
                    int spellY = y + (dy[idx] * 2);

                    // 범위 안에 있고 함정이 아니고 스펠 상태에 따른 방문이 처음일 때
                    if (checkBoundary(spellX, spellY) && map[spellX][spellY] == false
                            && visited[spellX][spellY][spell + 1] == false) {

                        q.add(new Location(spellX, spellY, spell + 1, cost + 1));
                        visited[spellX][spellY][spell + 1] = true;
                    }
                }
            }
        }

        return answer != Integer.MAX_VALUE ? answer : -1;
    }

    public static void main(String[] args) {

        int[][] hole = {{1, 4}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {3, 3}, {4, 1}, {4, 3}, {5, 3}};

        int answer = solution(5, 4, hole);

        System.out.println(answer);
    }
}

