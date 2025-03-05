package boj;

import java.io.*;
import java.util.*;

public class problem11478 {
    /*
     * 부분문자열을 구하는 문제
     * 
     * 집합 사용하기
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] arrOfInput;
    static int inputSize;

    static TreeSet<String> selectSet = new TreeSet<>();
    static int answer;
    
    static void solution() {
        
        for (int idx = 0; idx < inputSize; idx++) {
            String value = "";

            value += arrOfInput[idx];
            selectSet.add(value);

            for (int innerIdx = idx + 1; innerIdx < inputSize; innerIdx++) {
                value += arrOfInput[innerIdx];
                selectSet.add(value);
            }
        }

        answer = selectSet.size();

    }


    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        answer = 0;

        inputSize = input.length();
        

        arrOfInput = new char[inputSize];

        for (int idx = 0; idx < inputSize; idx++) {
            arrOfInput[idx] = input.charAt(idx);
        }

        solution();

        System.out.println(answer);


    }
}
