package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2567 {
    /*
    색종이를 모두 마킹한다.
    마킹된 색종이를 사방 탐색
    - 사방 탐색할 때 빈칸의 개수를 카운트 -> 빈칸의 개수가 가장 밖에 위치함을 의미미
    */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    final static int[][] map = new int[100][100];
    static final int LENGTH = 10;

    static int numOfPaper;

    static int answer;

    // 상우하좌
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static void check() {
        for (int rowIdx = 0; rowIdx < 100; rowIdx++) {
            for (int colIdx = 0; colIdx < 100; colIdx++) {
                if (map[rowIdx][colIdx] == 1) {
                    for (int dir = 0; dir < dx.length; dir++) {
                        int newRow = rowIdx + dx[dir];
                        int newCol = colIdx + dy[dir];
                        
                        // 범위를 넘어갔을 때는 경계이므로 둘레에 포함시킨다.
                        if (newRow < 0 || newRow >= 100 || newCol < 0 || newCol >= 100) {
                            answer++;
                            continue;
                        }
    
                        if (map[newRow][newCol] == 0) {
                            answer++;
                        }
                    }
                }
                
            }
        }
    }


    public static void main(String[] args) throws IOException {

        answer = 0;

        numOfPaper = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfPaper; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int rowStart = Integer.parseInt(st.nextToken());
            int colStart = Integer.parseInt(st.nextToken());

            for (int row = rowStart; row < rowStart + LENGTH; row++) {
                for (int col = colStart; col < colStart + LENGTH; col++) {
                    map[row][col] = 1;
                }
            }
        }

        check();

        System.out.println(answer);
    }
}
