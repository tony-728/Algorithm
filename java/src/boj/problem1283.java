package boj;

import java.io.*;
import java.util.*;

public class problem1283 {

    /*
     * N개의 옵션이 있다.
     * 단축키를 지정하는 방법
     * 1. 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살핀다.
     * 단축키로 아직 지정이 안되어있다면 그 알파벳을 단축키로 지정한다.
     * 2. 모든 단어의 첫 글자가 이미 지정이 되었다면 
     * 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안된 것이 있다면 단축키로 지정한다.
     * 3. 어떤것도 단축키로 지정할 수 없다면 그냥 둔다. (대소문자를 구분하지 않는다.)
     * 4. 위 규칙을 첫번째부터 N개까지 적용한다.
     * 
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfOption;
    static String word;
    static String[] words;
    static boolean[] visited = new boolean[26];

    static final char LOWER = 'a';

    static void makeAnswer(int numOfWord, int wordIdx, int optionIdx) {

        for (int idx = 0; idx < numOfWord; idx++) {
            if (idx != wordIdx) {
                sb.append(words[idx]).append(" ");

            } else {
                for (int i = 0; i < words[wordIdx].length(); i++) {
                    if (i == optionIdx) {
                        sb.append("[");
                        sb.append(words[wordIdx].charAt(i));
                        sb.append("]");
                    } else {
                        sb.append(words[wordIdx].charAt(i));
                    }
                }

                sb.append(" ");
            }
        }

        sb.append("\n");

    }

    public static void main(String[] args) throws IOException {

        numOfOption = Integer.parseInt(br.readLine());

        for (int idx = 0; idx < numOfOption; idx++) {
            st = new StringTokenizer(br.readLine());

            int numOfWord = st.countTokens();

            words = new String[numOfWord];

            for (int i = 0; i < numOfWord; i++) {
                words[i] = st.nextToken();
            }

            // 모든 단어의 첫글자가 등록되었는지 확인한다.
            boolean firstCheck = false;

            for (int wordIdx = 0; wordIdx < numOfWord; wordIdx++) {
                word = words[wordIdx];

                int temp = word.charAt(0) - LOWER;

                // 대문자
                if (temp < 0) {
                    temp += 32;
                }

                // 이미 방문
                if (visited[temp]) {
                    continue;
                }

                visited[temp] = true;
                makeAnswer(numOfWord, wordIdx, 0);
                firstCheck = true;
                break;
            }

            if (firstCheck) {
                continue;
            }

            // 단어의 첫글자가 모두 등록되었다.
            // 앞에서부터 차례로 확인
            boolean flag = false;
            for (int wordIdx = 0; wordIdx < numOfWord; wordIdx++) {
                word = words[wordIdx];
                flag = false;

                for (int i = 0; i < word.length(); i++) {
                    int temp = word.charAt(i) - LOWER;

                    // 대문자
                    if (temp < 0) {
                        temp += 32;
                    }

                    // 이미 방문
                    if (visited[temp]) {
                        continue;
                    }

                    visited[temp] = true;
                    makeAnswer(numOfWord, wordIdx, i);
                    flag = true;
                    break;
                }

                if (flag) {
                    break;
                }
            }

            // 모두 확인했는데 등록이 안됬으면 넣어준다.
            if(!flag){
                for (int wordIdx = 0; wordIdx < numOfWord; wordIdx++) {
                    word = words[wordIdx];
    
                    sb.append(word).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }
}
