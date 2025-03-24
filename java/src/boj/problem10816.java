package boj;

import java.io.*;
import java.util.*;

public class problem10816 {
    /*
     * 정수가 적혀있는 카드 N개가 있다.
     *  
     * 정수 m개가 주어졌을 때 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하라
     * 
     * hashMap
     * 
     */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int numOfCard;
        HashMap<Integer, Integer> hmap = new HashMap<>();
    
        numOfCard = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
    
        for (int idx = 0; idx < numOfCard; idx++) {
            int card = Integer.parseInt(st.nextToken());
    
            if (hmap.containsKey(card)) {
                hmap.replace(card, hmap.get(card) + 1);
            } else {
                hmap.put(card, 1);
            }
        }
    
        numOfCard = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        for(int idx=0; idx<numOfCard; idx++){
            int card = Integer.parseInt(st.nextToken());

            sb.append(hmap.getOrDefault(card, 0)).append(" ");
        }

        System.out.println(sb);
    }
}
