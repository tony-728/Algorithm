package boj;

import java.io.*;
import java.util.*;

public class problem1099 {

    /*
     * N개의 단어가 있다. 
     * 문장은 단어를 공백없이 붙여썼다. 
     * 문장에서 각 단어는 0번 또는 그 이상 나타날 수 있다.
     * 
     * 단어에 쓰여 있는 문자의 순서를 바꿔도 된다.
     * - 원래 단어의 위치와 다른 위치에 있는 문자의 개수 만큼이 그 순서를 바꾼 단어를 만드는 비용이다.
     * 
     * 예를 들어 abc란 단어가 있을 때
     * - abc는 비용 0
     * - acb, cba, bac 는 비용 2
     * - bca, cab는 비용 3
     * 
     * 한 문장을 여러 가지 방법으로 해석할 수 있다.
     * 비용의 최솟값을 구하라
     * 
     * 
     * dfs로 단어들을 하나씩 찾아가면서 검사
     * - 문장의 길이가 50이고 단어 갯수가 50보다 작은 자연수니까 충분
     * - 충분하다고 생각했지만 시간 초과
     * 
     * dp로 문제를 해결해야함
     * - 문장에 집중해서 모든 경우를 찾아야함
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String inputSentence;
    static int numOfWords;
    static String[] wordList;
    static String[] splitWords;

    static int[] wordAlphaList = new int[26];
    static int[] sentenceAlphaList = new int[26];

    static char wordAlpha;
    static char sentenceAlpha;
    static int[] dp;

    static final char STANDARD = 'a';

    static void inputData() throws IOException {

        inputSentence = br.readLine().trim();

        numOfWords = Integer.parseInt(br.readLine().trim());

        wordList = new String[numOfWords];

        dp = new int[inputSentence.length() + 1];

        Arrays.fill(dp, 100);
        dp[0] = 0;

        for (int idx = 0; idx < numOfWords; idx++) {
            wordList[idx] = br.readLine().trim();
        }
    }

    static int countWord(String s1, String s2) {
        int count = 0;

        for (int idx = 0; idx < s1.length(); idx++) {
            if (s1.charAt(idx) != s2.charAt(idx)) {
                count++;
            }
        }

        return count;
    }

    static boolean compareAlphabet(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Arrays.fill(wordAlphaList, 0);
        Arrays.fill(sentenceAlphaList, 0);

        for (int idx = 0; idx < s1.length(); idx++) {
            wordAlpha = s1.charAt(idx);
            sentenceAlpha = s2.charAt(idx);

            wordAlphaList[wordAlpha - STANDARD]++;
            sentenceAlphaList[sentenceAlpha - STANDARD]++;
        }

        for (int idx = 0; idx < 26; idx++) {
            if (wordAlphaList[idx] != sentenceAlphaList[idx]) {
                return false;
            }
        }

        return true;
    }

    static void solution() {

        // 입력 문자열을 하나씩 증가하면서 확인한다.
        for (int idx = 1; idx <= inputSentence.length(); idx++) {
            splitWords = new String[idx];

            // 길이 1부터 길이 idx까지 문장을 자른다.(앞부터 하나씩 줄여가면서)
            for (int splitIdx = 0; splitIdx < idx; splitIdx++) {
                splitWords[splitIdx] = inputSentence.substring(splitIdx, idx);
            }

            // 자른 단어들과 같은 알파벳으로 구성된 단어가 있는지 확인
            for (int splitIdx = 0; splitIdx < idx; splitIdx++) {
                for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {

                    // 단어의 알파벳이 같은지 확인
                    if (!compareAlphabet(splitWords[splitIdx], wordList[wordIdx])) {
                        continue;
                    }

                    // 알파벳 위치 차이 계산
                    int count = countWord(splitWords[splitIdx], wordList[wordIdx]);

                    // 자르기 시작한 인덱스(splitIdx)까지의 최솟값에 현재 비교한 문자의 다른 자릿수를 더한 것과 현재 문자까지의 최솟값을 갱신
                    dp[idx] = Math.min(dp[idx], count + dp[splitIdx]);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(dp[inputSentence.length()] == 100 ? -1 : dp[inputSentence.length()]);


    }
}
