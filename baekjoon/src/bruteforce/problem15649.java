package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 온라인 저지(Baekjoon Online Judge) 15649번 문제
 *  - https://www.acmicpc.net/problem/15649
 */
public class problem15649 {

  // 첫째 줄에 자연수 N과 M을 저장하기 위한 변수
  static int N, M;
  // 조건을 만족하는 수열을 저장하기 위한 배열
  static int[] selected;
  // 결과를 출력하기 위한 변수
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {

    try {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      selected = new int[M + 1];

      findResult(1);
      System.out.println(sb.toString());

      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 조건을 만족하는 값을 찾는 함수(재귀함수)
  static void findResult(int currentNumber) {
    if (currentNumber == M + 1) { // 1 ~ M 번째까지 반복한 경우
      for (int a = 1; a <= M; a++) {
        sb.append(selected[a]).append(' ');
      }
      sb.append('\n');
    } else {
      for (int candidateNumber = 1; candidateNumber <= N; candidateNumber++) {
        boolean isCandidate = false;
        for (int a = 1; a < currentNumber; a++) {
          if (candidateNumber == selected[a]) {
            isCandidate = true;
          }
        }
        if (!isCandidate) {
          selected[currentNumber] = candidateNumber;
          findResult(currentNumber + 1);
          selected[currentNumber] = 0;
        }
      }
    }
  }
}
