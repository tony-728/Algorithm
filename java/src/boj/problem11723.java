package boj;

import java.util.*;
import java.io.*;

public class problem11723 {
    /*
     * 비어있는 공집합이 주어질 때 연산을 수행하는 프로그램
     * 
     * add
     * remove
     * check
     * toggle
     * all
     * empty
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int numOfOperator;


    public static void main(String[] args) throws IOException {
        numOfOperator = Integer.parseInt(br.readLine().trim());
        TreeSet<Integer> mySet = new TreeSet<>();

        for (int idx = 0; idx < numOfOperator; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            String operator = st.nextToken();
            int value;
            
            if (operator.equals("add")) {
                value = Integer.parseInt(st.nextToken());
                mySet.add(value);
            } else if (operator.equals("remove")) {
                value = Integer.parseInt(st.nextToken());
                mySet.remove(value);
            } else if (operator.equals("check")) {
                value = Integer.parseInt(st.nextToken());
                if (mySet.contains(value)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (operator.equals("toggle")) {
                value = Integer.parseInt(st.nextToken());
                if (mySet.contains(value)) {
                    mySet.remove(value);
                } else {
                    mySet.add(value);
                }
            } else if (operator.equals("all")) {
                for (int setValue = 1; setValue <= 20; setValue++) {
                    mySet.add(setValue);
                }

            } else if (operator.equals("empty")) {
                mySet.clear();
            }
        }

        System.out.println(sb);
    }
}
