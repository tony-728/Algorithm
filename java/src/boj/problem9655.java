package boj;

import java.io.*;

public class problem9655 {
    /*
     * 탁자 위에 돌 n개가 있다
     * 두명이 턴을 번갈아가변서 돌을 가져간다.
     * 돌은 1개 또느 3개를 가져갈 수 있다.
     * 마지막 돌을 가져가는 사람이 게임을 이게 된다.
     * 두 사람이 완벽하게 게임을 했을 때 이기는 사람을 구하라
     * - 게임은 상근이가 먼저 시작한다.
     * 
     * 돌의 개수는 1부터 1000
     */

    static int numOfStone;
    static final int ONE = 1;
    static final int THREE = 3;

    static final String SANG = "SK";
    static final String CHANG = "CY";

    static String answer;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        numOfStone = Integer.parseInt(br.readLine().trim());

        // 1000번까지 하나씩 확인할 필요는 없다.
        // 3으로 나눈 나머지부터 확인하면 된다.
        // 3으로 나눈 나머지부터 확인할 때 시작하는 사람을 확인해야한다.
        // 시작하는 사람을 확인
        int turn = 0;
        turn = (numOfStone / THREE) % 2;
        
        numOfStone %= THREE;

        while(true){
            if(numOfStone == 0){
                break;
            }
            turn++;

            if(numOfStone >= THREE){
                numOfStone -= THREE;
            } else{
                numOfStone -= ONE;
            }
        }

        // 마지막에 선택하는 사람
        if(turn % 2 == 1){
            answer = SANG;
        } else{
            answer = CHANG;
        }

        System.out.println(answer);

    }
}
