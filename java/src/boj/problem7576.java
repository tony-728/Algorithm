package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem7576 {
    /*
     * 격자 모양 상자 칸에 하나씩 넣어서 보관한다.
     * 
     * 보관 후 하루가 지나면 
     * 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토는 익게 된다.(4방)
     * 토마토가 저절로 익는 경우는 없다.
     * 
     * 보관된 토모토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 구하고 싶다.
     * 
     * 1: 익은 토마토
     * 0: 익지 않은 토마토
     * -1: 토마토가 없음
     * 
     * 초기 익은 토마토를 기준으로 BFS
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize;
    static int colSize;
    static int[][] box;

    static Queue<Tomato> goodTomatoQ = new ArrayDeque<>();

    static int badTomatoCount;
    static int answer;

    static final int GOOD_TOMATO = 1;
    static final int BAD_TOMATO = 0;
    static final int NONE = -1;

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static class Tomato {
        int rowIdx;
        int colIdx;
        int day;

        public Tomato(int rowIdx, int colIdx, int day) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.day = day;
        }
    }

    static void inputTestCase() throws IOException {
        badTomatoCount = 0;
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        box = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                int tomato = Integer.parseInt(st.nextToken());

                // 초기 토마토 위치 저장
                if (tomato == GOOD_TOMATO) {
                    goodTomatoQ.add(new Tomato(rowIdx, colIdx, 0));
                } else if (tomato == BAD_TOMATO) {
                    // 익지 않은 토마토 개수 카운트
                    badTomatoCount++;
                }

                box[rowIdx][colIdx] = tomato;
            }
        }
    }

    static void makeGoodTomato() {

        // 좋은 토마토가 있는 동안 토마토박스를 익게 만든다.
        while (!goodTomatoQ.isEmpty()) {

            Tomato tomato = goodTomatoQ.poll();

            // 마지막 토마토가 익는데 걸린 일수를 저장
            answer = tomato.day;

            // 4방 탐색
            for (int dir = 0; dir < dx.length; dir++) {
                int newRowIdx = tomato.rowIdx + dx[dir];
                int newColIdx = tomato.colIdx + dy[dir];

                // 범위 체크
                if (0 > newRowIdx || newRowIdx >= rowSize || 0 > newColIdx || newColIdx >= colSize) {
                    continue;
                }

                // 토마토가 없는 위치 체크
                if (box[newRowIdx][newColIdx] == NONE) {
                    continue;
                }

                // 익지 않은 토마토인 경우
                if (box[newRowIdx][newColIdx] == BAD_TOMATO) {
                    // 익지 않은 토마토 개수 감소
                    // 박스에 토마토 갱신
                    // 익은 토마토 추가
                    badTomatoCount--;
                    box[newRowIdx][newColIdx] = GOOD_TOMATO;
                    goodTomatoQ.add(new Tomato(newRowIdx, newColIdx, tomato.day + 1));
                }
            }
        }

        // 상자에 익지 않은 토마토가 있는지 확인
        if(badTomatoCount > 0){
            answer = -1;
        }
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        makeGoodTomato();

        System.out.println(answer);
    }
}
