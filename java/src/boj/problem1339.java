package boj;

import java.io.*;
import java.util.*;

public class problem1339 {
    /*
     * 
     * 단어가 주어질 때 알파벳을 숫자로 변환한다.
     * 숫자로 바뀐 단어들의 합이 최대로 될 때 값을 구하라
     * 
     * 그리디
     * - 주어진 알파벳의 위치와 등장 횟수를 모두 고려한 값이 큰 순서대로 숫자를 배정
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int numOfWord;
    static int[] arrOfAlphaToNumber = new int[26];
    static String[] arrOfWord;
    static int maxSize;
    static int answer;

    static final char A = 'A';

    static void inputData() throws IOException {
        answer = 0;

        numOfWord = Integer.parseInt(br.readLine().trim());

        arrOfWord = new String[numOfWord];

        for (int idx = 0; idx < numOfWord; idx++) {
            String word = br.readLine().trim();

            maxSize = Math.max(maxSize, word.length());

            arrOfWord[idx] = word;
        }

        // 단어 길이 순서로 정렬
        Arrays.sort(arrOfWord, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
    }

    static void solution() {
        // 단어 최대 10개, 단어 길이 최대 8

        int currentLength = arrOfWord[0].length();

        while (currentLength > 0) {
            for (int wordIdx = 0; wordIdx < numOfWord; wordIdx++) {
                String word = arrOfWord[wordIdx];

                if (word.length() - currentLength < 0) {
                    continue;
                }

                char alpha = word.charAt(word.length() - currentLength);

                // 현재 알파벳 위치에 해당하는 10의 제곱수를 더한다.
                arrOfAlphaToNumber[alpha - A] += Math.pow(10, (currentLength - 1));
            }
            currentLength--;
        }

        // System.out.println(Arrays.toString(arrOfAlphaToNumber));

        // 숫자 크기로 정렬(오름차순)
        Arrays.sort(arrOfAlphaToNumber);

        int number = 9;
        for (int idx = 25; idx > -1; idx--) {
            if (arrOfAlphaToNumber[idx] == 0) {
                break;
            }
            // 가장 큰 값에 number를 곱한 값을 정답에 누적합
            answer += (number-- * arrOfAlphaToNumber[idx]);
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
