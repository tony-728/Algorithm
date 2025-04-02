package boj;

import java.io.*;
import java.util.*;

public class problem11656 {
    /*
     * 부분문자열을 구하고 사전순으로 정렬하기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine().trim();

        ArrayList<String> sList = new ArrayList<>();

        for (int idx = 0; idx < s.length(); idx++) {
            sList.add(s.substring(idx, s.length()));
        }

        Collections.sort(sList);

        StringBuilder sb = new StringBuilder();
        for(String str: sList){
            sb.append(str).append("\n");
        }

        System.out.println(sb);

    }
}
