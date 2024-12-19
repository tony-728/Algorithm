package programmers;

import java.io.*;
import java.util.*;

public class 등굣길 {

    /*
    
    2차원 공간에 집과 학교가 위치해있다
    - 집(1,1), 학교(n,m)
    
    물웅덩이는 지나가지 못한다.
    
    오른쪽과 아래쪽으로만 움직일 수 있다.
    
    집에서 학교까지 갈 수 있는 최단경로의 개수를 1_000_000_007로 나눈 나머지를 구하라
    */

    static final int STANDARD = 1_000_000_007;

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int rowSize = n + 1;
        int colSize = m + 1;

        boolean[][] map = new boolean[rowSize][colSize];
        int[][] answerMap = new int[rowSize][colSize];

        for (int idx = 0; idx < puddles.length; idx++) {
            // m, n의 위치를 파악해야함
            // n이 x, m이 y임
            if (puddles[idx].length != 0) {
                int x = puddles[idx][1];
                int y = puddles[idx][0];
                map[x][y] = true;
            }
        }

        answerMap[1][1] = 1;

        for (int rowIdx = 1; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 1; colIdx < colSize; colIdx++) {

                // 집 위치는 패스
                if (rowIdx == 1 && colIdx == 1) {
                    continue;
                }

                // 물웅덩이는 패스
                if (map[rowIdx][colIdx] == true) {
                    continue;
                }

                // 현재 위치가 물웅덩이가 아닐 때
                answerMap[rowIdx][colIdx] =
                        (answerMap[rowIdx - 1][colIdx] + answerMap[rowIdx][colIdx - 1]) % STANDARD;
            }
        }

        answer = answerMap[n][m];

        return answer;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());

        int[][] paddle = {{1, 1}};

        int answer = solution(m, n, paddle);

        System.out.println(answer);
    }

}
