package boj;

import java.io.*;
import java.util.*;

public class problem2217 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfRope = Integer.parseInt(br.readLine().trim());

        int[] arrOfRope = new int[numOfRope];

        for(int idx=0;idx<numOfRope; idx++){
            arrOfRope[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(arrOfRope);

        int answer = 0;

        for(int idx=0; idx<numOfRope; idx++){
            int weight = arrOfRope[idx];

            int maxWeight = weight * (numOfRope - idx);

            answer = Math.max(answer, maxWeight);
        }

        System.out.println(answer);
    }
}
