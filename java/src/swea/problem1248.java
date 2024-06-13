package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem1248 {
    /*
     * 이진 트리에서 임의의 두 정점의 가장 가까운 공통 조상을 찾고
     * 그 정점을 루트로 하는 서브 트리의 크기를 알아내는 프로그램을 작성
     * 
     * 테스트케이스의 수가 주어진다.
     * 첫번째 줄에는 정점의 개수, 간선의 개수, 공통 조상을 찾는 두 개의 정점 번호
     * 두번째 줄에는 E개 간산이 나열, 간선은 부모 자식 순서로 표기
     */
    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            super();
            this.to = to;
            this.next = next;
        }

        public Node(int to) {
            super();
            this.to = to;
        }

        @Override
        public String toString() {
            return "Node [to=" + to + ", next=" + next + "]";
        }
    }

    static int testCase;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Node[] tree;
    static int[] levelTree;
    static int[] parentsTree;

    static int numOfNode;
    static int numOfEdge;
    static int leftNode;
    static int rightNode;

    static List<Integer> leftParentsList;
    static List<Integer> rightParentsList;

    static int answer;
    static int count;

    static void inputTestCase() throws IOException {
        count = 0; // 서브 트리의 크기

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken()); // 정점의 개수
        numOfEdge = Integer.parseInt(st.nextToken()); // 간선의 개수
        leftNode = Integer.parseInt(st.nextToken()); // 공통조상을 찾는 두 개의 정점번호
        rightNode = Integer.parseInt(st.nextToken()); // 공통조상을 찾는 두 개의 정점번호

        tree = new Node[numOfNode + 1];
        parentsTree = new int[numOfNode + 1];

        leftParentsList = new ArrayList<>();
        rightParentsList = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        // 자식노드의 부모를 넣어줌
        for (int edgeIdx = 0; edgeIdx < numOfEdge; edgeIdx++) {
            int parents = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            tree[parents] = new Node(child, tree[parents]); // 현재 노드의 자식 노드를 저장함
            parentsTree[child] = parents; // 현재 노드의 부모 노드를 저장함
        }
    }

    // 부모노드들 찾기
    static void findParentNode(List<Integer> parentsList, int node) {
        if (node == 1) {
            return;
        }

        parentsList.add(parentsTree[node]);
        findParentNode(parentsList, parentsTree[node]);
    }

    // 서브트리의 노드 갯수 구하기
    static void countSubTree(int node) {

        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(node);
        count++;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (Node temp = tree[currentNode]; temp != null; temp = temp.next) {
                queue.offer(temp.to);
                count++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            // 각 노드별로 부모노드를 모두 찾아 리스트에 저장한다.
            findParentNode(leftParentsList, leftNode);
            findParentNode(rightParentsList, rightNode);
            
            // 두 정점의 부모 노드가 같을 때까지 반복한다.
            loop1: for (int leftIdx = 0; leftIdx < leftParentsList.size(); leftIdx++) {
                for (int rightIdx = 0; rightIdx < rightParentsList.size(); rightIdx++) {

                    if (leftParentsList.get(leftIdx).equals(rightParentsList.get(rightIdx))) {
                        answer = leftParentsList.get(leftIdx);
                        break loop1;
                    }
                }
            }

            countSubTree(answer);

            sb.append(String.format("#%d %d %d\n", tc, answer, count));
        }
        System.out.println(sb);
    }
}
