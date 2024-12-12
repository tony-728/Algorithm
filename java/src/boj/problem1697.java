package boj;

import java.io.*;
import java.util.*;

public class problem1697 {

    /*
     * 수빈이는 n 위치에 있다. 동생은 k 위치에 있다.
     * 
     * 수빈이는 걷거나 순간이동을 할 수 있다.
     * - 걸을 때: 1초 후 x-1 or x+1 위치로 이동하게 된다.
     * - 순간이동: 1초 후 2*x 위치로 이동한다.
     * 
     * 수빈이와 동생 위치가 주어질 때 수빈이가 동생을 찾을 수 있는 가장 빠른 시간을 구하라
     * 
     * 수빈이의 위치가 0일 때 처리를 따로 해야함
     * 
     * 복잡한 접근방식
     * 수빈 >= 동생
     * - 수빈 - 동생
     * 
     * 수빈 < 동생
     * 수빈 * 2 <= 동생
     * - 순간이동
     * 수빈 * 2 > 동생 
     * - (수빈 * 2 - 동생) % 2 만큼 뒤로 간다. 이때 계산 결과와 (수빈*2 - 동생)의 값이 같으면 앞으로 이동한다.
     * 
     * bfs
     * - 수빈이의 위치부터 bfs를 시작
     * - bfs로 풀 수 있는 이유 3가지 이동 방법(+1, -1, *2)에 대해서 최단 거리를 구하는 문제로 해석할 수 있다.
     * - 처음 새로운 위치에 도달할 때가 최단 거리이기 때문에 방문처리를 통해서 목표지점까지의 최단거리를 구할 수 있다
     * 
     * 4 10
     * 답: 2, (4 5 10)
     */

    static class Node{
        int value;
        int depth;

        public Node(int value, int depth){
            this.value = value;
            this.depth = depth;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int current, goal;

    static int answer = 0;
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        current = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        if (goal <= current) {
            answer = current - goal;
        } else {

            Deque<Node> q = new ArrayDeque<>();

            q.addLast(new Node(current, 0));
            visited[current] = true;

            while(!q.isEmpty()){
                Node node = q.pollFirst();

                int value = node.value;
                int depth = node.depth;

                int newValue;

                if(value == goal){
                    answer = depth;
                    break;
                }
                
                for(int idx=0; idx<3; idx++){
                    if(idx == 0){
                        newValue = value - 1;
                    } else if(idx == 1){
                        newValue = value + 1;
                    } else{
                        newValue = value*2;
                    }
                    
                    // 범위 확인, 방문 확인
                    if(newValue > -1 && newValue < 100_001 && visited[newValue] == false){
                        q.addLast(new Node(newValue, depth+1));
                        visited[newValue] = true;
                    }

                }
            }
        }

        System.out.println(answer);


    }
}
