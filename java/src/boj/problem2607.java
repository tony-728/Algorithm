package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2607 {
    /*
     * 영문 알파벳 대문자로 이루어진 두 단어가 다음 두 가지 조건을 만족하면 같은 구성을 갖는다고 한다.
     * 1. 두 개의 단어가 같은 종류의 문자로 이루어져 있다.
     * 2. 같은 문자는 같은 개수 만큼 있다.
     * 
     * 두 단어가 같은 구성을 갖는 경우,
     * 한 단어에서 한 문자를 더하거나, 빼거나
     * 하나의 문자를 다른 문자로 바꾸어 나머지 한 단어와 같은 구성을 갖게 되는 경우에 두 단어는 서로 비슷한 단어라고 한다.
     * 
     * 같은 구성이면 비슷한 단어이다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int answer;
    static int numOfWord;
    static String[] wordList;

    static String standard;
    static int[] alphabetList = new int[26];
    static char A = 'A';

    static void checkWord(String word) {
        // 같은 구성인지 확인
        int[] targetAlphabetList = new int[26];

        boolean checkSame = true;

        int sumOfDiff = 0;

        // target word 알파벳 구성 확인
        for (int idx = 0; idx < word.length(); idx++) {
            targetAlphabetList[(word.charAt(idx) - A)]++;
        }


        for (int idx = 0; idx < 26; idx++) {
            // 여기 조건을 더 확실하게
            //DGLLO 이 단어는 안되는데 된다고 함
            if (alphabetList[idx] != targetAlphabetList[idx]) {
                int diff = Math.abs(alphabetList[idx] - targetAlphabetList[idx]);
                sumOfDiff += diff;

                if (diff > 1) {
                    checkSame = false;
                }
            }
        }

        if (!checkSame) {
            return;
        }

        // 같은 구성은 아니지만 알파벳 하나만 수정하면 같아지는지 확인
        // 한글자만 변경할 수 있으므로 두 단어의 길이 차이가 1보다 크면 절대로 불가능하다
        if (sumOfDiff <= 2
                && ((word.length() == standard.length() || word.length() + 1 == standard.length()
                        || word.length() - 1 == standard.length()))) {
            answer++;
            return;
        }

        return;

    }

    public static void main(String[] args) throws IOException {

        numOfWord = Integer.parseInt(br.readLine().trim());

        wordList = new String[numOfWord];

        for (int idx = 0; idx < numOfWord; idx++) {
            String word = br.readLine().trim();

            if (idx == 0) {
                standard = word;

                // 기준 단어 알파벳 구성 확인
                for (int standardIdx = 0; standardIdx < word.length(); standardIdx++) {
                    alphabetList[(standard.charAt(standardIdx) - A)]++;
                }
            }

            wordList[idx] = word;
        }

        for (int idx = 1; idx < numOfWord; idx++) {
            checkWord(wordList[idx]);
        }

        System.out.print(answer);

    }
}
