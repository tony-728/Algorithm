package boj;

import java.io.*;
import java.util.*;

public class problem1525 {
    /*
     * 주어진 3x3 배열을 
     * 123
     * 456
     * 780 로 만들기 위한 최소 이동횟수를 구하라
     * 
     * 
     * 시작 아이디어를 도저히 떠올리지 못해서 참고
     * 
     * bfs
     * 1. 무엇을 기준으로 bfs를 할 것인가
     * 2. 방문처리는 어떻게 할 것인가
     * 방법
     * 1. 어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어있으면(0이면) 수를 그 칸으로 움직일 수 있다.
     * 0을 기준으로 숫자가 움직인다. 0을 인접한 칸으로 움직인다.
     * 2. 단순히 0을 인접한 칸으로 움직였을 때 방문처리를 하게 되면 안된다. 
     * 왜냐하면 
     * 102  103
     * 345  245
     * 678  678
     * 
     * 모두 0이 왼쪽으로 움직일 때 전체 배열 구성은 다르다
     * - 2차원배열을 1차원으로 생각해보자 102345678, 103245678이 되고 
     * - 왼쪽으로 움직였을 때는 012345678, 013245678로 다름을 구분할 수 있다.
     * - 0이 어떤 칸으로 움직였을 때 방문을 확인하는 것이 아니라 전체 구성에 대해서 방문처리를 해야한다.
     * 이것을 관리하는 자료구조는 hashmap으로 관리한다.
     * 
     */

    static String initString = "";
    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = -1;

        for (int rowIdx = 0; rowIdx < 3; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < 3; colIdx++) {
                initString += st.nextToken();
            }
        }
    }

    static void solution() {

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        String targetString = "123456780";

        // (1차원으로 바꾼)게임판 상태, 이동횟수
        HashMap<String, Integer> visited = new HashMap<>();

        Deque<String> q = new ArrayDeque<>();
        q.addLast(initString);
        visited.put(initString, 0);

        while (!q.isEmpty()) {
            String puzzle = q.pollFirst();

            // 정리된 상태가 된다.
            if (targetString.equals(puzzle)) {
                answer = visited.get(targetString);
                return;
            }

            // 1차원에서 0위치 찾기
            int zeroIdx = puzzle.indexOf('0');
            
            // 1차원 0위치를 2차원 위치로 변환
            int zeroRowIdx = zeroIdx / 3;
            int zeroColIdx = zeroIdx % 3;
            
            // 0을 인접한 칸으로 이동한다.
            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = zeroRowIdx + dx[dir];
                int newCol = zeroColIdx + dy[dir];

                // 범위 체크
                if (0 > newRow || newRow >= 3 || 0 > newCol || newCol >= 3) {
                    continue;
                }

                // 1차원 위치로 변환
                int newZeroIdx = newRow * 3 + newCol;
                char newZero = puzzle.charAt(newZeroIdx);

                // 1차원에서 0위치를 갱신
                String newPuzzle = puzzle.replace(newZero, 'x');
                newPuzzle = newPuzzle.replace('0', newZero);
                newPuzzle = newPuzzle.replace('x', '0');

                // hashmap에서 현재 퍼즐 모양이 있는지 확인
                if (!visited.containsKey(newPuzzle)) {
                    q.addLast(newPuzzle);
                    visited.put(newPuzzle, visited.get(puzzle) + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
