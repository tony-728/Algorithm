package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class problem20920 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfWords;
    static int standardLength;

    static List<String> wordList = new ArrayList<>(); // 단어장
    static HashMap<String, Integer> wordCountMap = new HashMap<>(); // 단어 카운트

    static List<String> answer;

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfWords = Integer.parseInt(st.nextToken());
        standardLength = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < numOfWords; idx++) {
            String word = br.readLine().trim();

            if (word.length() >= standardLength) {

                wordList.add(word);
                wordCountMap.put(word,
                        wordCountMap.get(word) == null ? 1 : wordCountMap.get(word) + 1);
            }

        }

    }

    static void solution() {

        answer = new ArrayList<>(wordCountMap.keySet());


        Collections.sort(answer, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                // 빈도수
                if (Integer.compare(wordCountMap.get(o1), wordCountMap.get(o2)) != 0) {
                    return Integer.compare(wordCountMap.get(o2), wordCountMap.get(o1));
                }

                // 길이가 긴 단어 
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                }

                // 사전순
                return o1.compareTo(o2);

            }
        });
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();
        solution();

        for (String word : answer) {
            sb.append(word).append("\n");
        }

        System.out.println(sb);

    }
}
