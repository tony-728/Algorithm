package boj;

import java.io.*;
import java.util.*;

public class problem28446 {

    /*
     * 사물함 안에 들어있는 볼링공들의 무게가 적혀있다.
     * 하나의 사물함에 여러 개의 볼링공이 들어갈 수 있다.
     * 
     * 1 x y: x번 사물홤에 w 무게의 볼링공을 넣는다.
     * 2 w : w 무게를 가진 볼링공이 들어 있는 사물함의 번호를 출력한다.
     * 
     * 볼링공은 무게가 모두 다르기 때문에 2번 요청의 답은 항상 하나만 존재한다.
     * 2번 요청이 들어올 때 w 무게를 가진 볼링공은 무조건 어딘가에 존재한다.
     * 
     * hashMap 자료구조를 이용
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int INSERT = 1;
    static final int SELECT = 2;

    static int numOfQuery;
    static int cabinetIdx;
    static int weightOfBall;

    static Map<Integer, Integer> cabinet = new HashMap<>();
    
    public static void main(String[] args) throws IOException{

        numOfQuery = Integer.parseInt(br.readLine().trim());

        for(int idx=0; idx<numOfQuery; idx++){

            st = new StringTokenizer(br.readLine().trim());

            int command = Integer.parseInt(st.nextToken());

            if(command == INSERT){
                cabinetIdx = Integer.parseInt(st.nextToken());

                weightOfBall = Integer.parseInt(st.nextToken());

                cabinet.put(weightOfBall, cabinetIdx);

            } else if(command == SELECT){
                weightOfBall = Integer.parseInt(st.nextToken());

                sb.append(cabinet.get(weightOfBall)).append("\n");
            }

        }

        System.out.println(sb);

    }
}
