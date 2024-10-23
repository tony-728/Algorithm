package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem20125 {

    /*
     * 머리는 심장 바로 윗칸
     * 왼쪽 팔은 심장 바로 왼쪽에 붙어있다. 왼쪽으로 뻗어 있다
     * 오른쪽 팔은 심장 바로 오른쪽에 붙어 있다. 오른쪽으로 뻗어 있다.
     * 허리는 심장 아래 있다. 아래쪽으로 뻗어 있다.
     * 왼쪽다리는 허리의 왼쪽 아래
     * 오른쪽 다리는 허리의 오른쪽 아래
     * 
     * 심장의 위치
     * 팔, 다리, 허리의 길이를 구하라
     *
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int mapSize;
    static char[][] map;

    static final char COOKIE = '*';


    // 좌, 우, 하
    static final int[] dx = {0, 0, 1};
    static final int[] dy = {-1, 1, 0};

    static int heartRowIdx;
    static int heartColIdx;

    static int endWaistRowIdx;
    static int endWaistColIdx;

    static int leftArm = 0;
    static int rightArm = 0;
    static int leftLeg = 0;
    static int rightLeg = 0;
    static int waist = 0;

    static boolean checkRange(int rowIdx, int colIdx) {
        if (-1 < rowIdx && rowIdx < mapSize && -1 < colIdx && colIdx < mapSize) {
            return true;
        }

        return false;
    }

    static int cal(int rowIdx, int colIdx, int dir) {

        int count = 0;

        while (checkRange(rowIdx, colIdx) && map[rowIdx][colIdx] == COOKIE) {
            count++;

            rowIdx += dx[dir];
            colIdx += dy[dir];
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        mapSize = Integer.parseInt(br.readLine().trim());

        map = new char[mapSize][mapSize];

        boolean headCheck = false;

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                char val = row.charAt(colIdx);

                // 심장의 위치 찾기
                if (!headCheck && val == COOKIE) {
                    heartRowIdx = rowIdx + 1;
                    heartColIdx = colIdx;

                    sb.append(heartRowIdx + 1).append(" ").append(heartColIdx + 1).append("\n");
                    headCheck = true;
                }

                map[rowIdx][colIdx] = val;
            }
        }

        
        
        // 왼쪽 팔길이
        leftArm = cal(heartRowIdx, heartColIdx - 1, 0);
        // 오른쪽 팔길이
        rightArm = cal(heartRowIdx, heartColIdx + 1, 1);
        
        // 허리 길이
        endWaistRowIdx = heartRowIdx + 1;
        endWaistColIdx = heartColIdx;
        while (map[endWaistRowIdx][endWaistColIdx] == COOKIE && checkRange(endWaistRowIdx, endWaistColIdx)) {
            waist++;
            
            endWaistRowIdx += dx[2];
            endWaistColIdx += dy[2];
        }

        // 마지막에 한칸 더 갔으므로 빼주어야함
        endWaistRowIdx -= dx[2];
        endWaistColIdx -= dy[2];

        // 왼쪽 다리길이
        leftLeg = cal(endWaistRowIdx+1, endWaistColIdx-1, 2);
           
        // 오른쪽 다리길이
        rightLeg = cal(endWaistRowIdx+1, endWaistColIdx+1, 2);
        
        
        sb.append(leftArm).append(" ").append(rightArm).append(" ").append(waist).append(" ").append(leftLeg).append(" ").append(rightLeg);
        System.out.println(sb);

    }
}
