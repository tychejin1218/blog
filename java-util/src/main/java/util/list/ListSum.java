package util.list;

import java.util.List;

public class ListSum {

  /**
   * 리스트의 합을 전통적인 for 루프를 사용해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingForLoop(List<Integer> list) {
    int sum = 0;
    for (int i = 0; i < list.size(); i++) {
      sum += list.get(i);
    }
    return sum;
  }

  /**
   * 리스트의 합을 향상된 for 루프를 사용해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingEnhancedForLoop(List<Integer> list) {
    int sum = 0;
    for (int num : list) {
      sum += num;
    }
    return sum;
  }

  /**
   * 리스트의 합을 Stream API를 사용해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingStreams(List<Integer> list) {
    return list.stream()
        .mapToInt(Integer::intValue)
        .sum();
  }

  /**
   * 리스트의 합을 Stream의 reduce 메서드를 사용해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingStreamReduce(List<Integer> list) {
    return list.stream()
        .reduce(0, Integer::sum);
  }

  /**
   * 리스트의 합을 재귀 호출을 사용해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingRecursion(List<Integer> list) {
    if (list.isEmpty()) {
      return 0;
    }
    return sumListRecursive(list, list.size() - 1);
  }

  /**
   * 재귀 호출을 통해 리스트의 인덱스까지의 합을 계산
   *
   * @param list 합을 구할 리스트
   * @param index 합을 계산할 마지막 인덱스
   * @return 지정된 인덱스까지의 리스트 요소들의 합
   */
  private int sumListRecursive(List<Integer> list, int index) {
    if (index == 0) {
      return list.get(index);
    }
    return list.get(index) + sumListRecursive(list, index - 1);
  }

  /**
   * 리스트의 합을 병렬 연산을 사용 (parallelStream)해 계산
   *
   * @param list 합을 구할 리스트
   * @return 리스트 요소들의 합
   */
  public int sumUsingParallelStream(List<Integer> list) {
    return list.parallelStream()
        .mapToInt(Integer::intValue)
        .sum();
  }
}
