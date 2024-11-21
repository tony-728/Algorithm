package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class problem1863 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfBuilding;
    static int[] heightList;
    static int answer;

    static void solutionByStack() {
        Stack<Integer> visited = new Stack<>();

        for (int index = 0; index < numOfBuilding; index++) {
            int height = heightList[index];

            // 높이가 0일 때는 새롭게 다시 시작한다.
            if (height == 0) {
                visited.clear();
                continue;
            }

            if (visited.empty()) {
                visited.add(height);
                answer++;
            } else {

                // 최대 높이보다 현재 높이가 크면 방문처리 + 카운트 증가 
                if (visited.peek() < height) {
                    visited.add(height);
                    answer++;
                    continue;
                }

                // 방문한 높이 중 나보다 큰 높이는 제거 
                while (!visited.isEmpty()) {
                    if (visited.peek() > height) {
                        visited.pop();
                    } else {
                        break;
                    }
                }

                if (visited.isEmpty()) {
                    visited.add(height);
                    answer++;
                    continue;
                }

                if (visited.peek() == height) {
                    continue;
                }

                answer++;
                visited.push(height);
            }
        }
    }

    static void solutionByTreeSet() {
        TreeSet<Integer> visited = new TreeSet<>(Collections.reverseOrder());

        for (int index = 0; index < numOfBuilding; index++) {
            int height = heightList[index];


            // 높이가 0일 때는 새롭게 다시 시작한다.
            if (height == 0) {
                visited.clear();
                continue;
            }

            // 방문한 적이 없으면 방문처리 + 카운트 증가
            if (visited.isEmpty()) {
                visited.add(height);
                answer++;
                continue;
            }

            // 최대 높이보다 현재 높이가 크면 방문처리 + 카운트 증가 
            if (height > visited.first()) {
                answer++;

                // 최대 높이보다 현재 높이가 작다
            } else {

                // 방문한 높이 중 나보다 큰 높이는 제거 
                while (!visited.isEmpty()) {
                    if (visited.first() > height) {
                        visited.pollFirst();
                    } else {
                        break;
                    }
                }

                // 현재 높이가 방문한 높이 중 가장 크다.

                // 1. 방문 트리가 비어있는 경우
                // 2. 현재 높이와 같은 경우


                // 1.
                if (visited.isEmpty()) {
                    answer++;
                    visited.add(height);
                    continue;
                }

                // 2.
                if (visited.first() == height) {
                    continue;
                }

                answer++;
            }

            visited.add(height);
        }
    }

    public static void main(String args[]) throws IOException {

        numOfBuilding = Integer.parseInt(br.readLine());

        heightList = new int[numOfBuilding];

        for (int index = 0; index < numOfBuilding; index++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            int height = Integer.parseInt(st.nextToken());

            heightList[index] = height;
        }

        solutionByStack();


        System.out.println(answer);
    }
}
