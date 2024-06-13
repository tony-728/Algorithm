package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2930 {
    /*
     * 힙
     * 
     * 연산 1: 최대 힙에 추가
     * 연산 2: 최대 힙의 루트 노드의 키 값을 출력하고 해당 노드를 삭제
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfOperator;

    // static PriorityQueue<Integer> pQ;

    static class MyHeap<T extends Comparable<T>> {
        private static final int MAX_SIZE = 1000000;

        Object[] data;
        int lastIndex = 1;

        public MyHeap() {
            data = new Object[MAX_SIZE + 1];
        }

        public void add(T input) {
            if (lastIndex >= MAX_SIZE) {
                throw new RuntimeException("Heap is full.");
            }
            data[lastIndex] = input;

            int currentIdx = lastIndex;
            while (currentIdx > 1) {
                int parentIdx = currentIdx / 2;
                T parentNode = (T) data[parentIdx];
                if (input.compareTo(parentNode) < 0) {
                    data[parentIdx] = input;
                    data[currentIdx] = parentNode;
                    currentIdx /= 2;
                } else {
                    break;
                }
            }

            lastIndex++;
        }

        public T remove() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is empty.");
            }

            T output = (T) data[1];
            lastIndex--;

            int currentIdx = 1;
            T currentData = (T) data[lastIndex];

            data[lastIndex] = null;
            data[1] = currentData;

            while (currentIdx <= lastIndex) {
                int leftChildIdx = 2 * currentIdx;
                int rightChildIdx = 2 * currentIdx + 1;
                T leftChild = (T) data[leftChildIdx];
                T rightChild = (T) data[rightChildIdx];
                

                // 자식 노드가 모두 없는 경우
                if (leftChild == null && rightChild == null) {
                    break;
                    // 자식 노드가 하나는 있는 경우
                } else if (leftChild == null || rightChild == null) {

                    // 오른쪽 자식이 없는 경우
                    if (rightChild == null) {
                        // 왼쪽 자식이 부모보다 작은 경우 교체
                        if (leftChild.compareTo(currentData) < 0) {
                            data[leftChildIdx] = currentData;
                            data[currentIdx] = leftChild;
                            currentIdx = leftChildIdx;
                        } else {
                            break;
                        }
                        // 왼쪽 자식이 없는 경우
                    } else {
                        // 오른쪽 자식이 부모보다 작은 경우 교체
                        if (rightChild.compareTo(currentData) < 0) {
                            data[rightChildIdx] = currentData;
                            data[currentIdx] = rightChild;
                            currentIdx = rightChildIdx;
                        } else {
                            break;
                        }
                    }
                    // 왼, 오른쪽 자식이 모두 있는 경우
                } else {
                    // 왼쪽 자식이 오른쪽 자식보다 작은 경우
                    if (leftChild.compareTo(rightChild) < 0) {
                        // 왼쪽 자식이 부모보다 작은 경우
                        if (leftChild.compareTo(currentData) < 0) {
                            data[leftChildIdx] = currentData;
                            data[currentIdx] = leftChild;
                            currentIdx = leftChildIdx;
                        } else {
                            break;
                        }
                        // 오른쪽 자식이 왼쪽 자식보다 작은 경우
                    } else {
                        // 오른쪽 자식이 부모보다 작은 경우
                        if (rightChild.compareTo(currentData) < 0) {
                            data[rightChildIdx] = currentData;
                            data[currentIdx] = rightChild;
                            currentIdx = rightChildIdx;
                        } else {
                            break;
                        }
                    }
                }

            }

            return output;
        }

        public boolean isEmpty() {
            return lastIndex == 1;
        }
    }

    static void inputTestCase() throws IOException {
        numOfOperator = Integer.parseInt(br.readLine().trim());

        // java는 최소힙이기 때문에 -를 붙여서 최대힙으로 변경
        // pQ = new PriorityQueue<>();
        MyHeap<Integer> pQ = new MyHeap<>();

        for (int operatorIdx = 0; operatorIdx < numOfOperator; operatorIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            int operator = Integer.parseInt(st.nextToken());

            if (operator == 1) {
                int value = -Integer.parseInt(st.nextToken());
                pQ.add(value);
            } else {
                if (pQ.isEmpty()) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(-pQ.remove()).append(" ");
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            sb.append("#").append(tc).append(" ");

            inputTestCase();
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
