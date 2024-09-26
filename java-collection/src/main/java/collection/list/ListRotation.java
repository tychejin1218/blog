package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListRotation {

  /**
   * Collections.rotate() 메서드를 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithCollections(List<T> list, int k) {
    Collections.rotate(list, k);
    return list;
  }

  /**
   * 반복문을 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithLoop(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    k = k % n;
    List<T> rotated = new ArrayList<>(n);

    // 초기 크기 n의 리스트 생성
    for (int i = 0; i < n; i++) {
      rotated.add(null);
    }

    // 원본 리스트의 각 요소를 회전된 리스트의 적절한 위치에 배치
    for (int i = 0; i < n; i++) {
      rotated.set((i + k) % n, list.get(i));
    }

    return rotated;
  }

  /**
   * 스트림을 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithStreams(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    int shift = k % n;

    return IntStream.range(0, n)
        .mapToObj(i -> list.get((i + (n - shift)) % n))
        .collect(Collectors.toList());
  }

  /**
   * 헬퍼 메서드를 사용하여 리스트를 회전시킵니다. 리스트를 역순으로 뒤집는 방법을 사용
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithHelperMethods(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    k = k % n;

    List<T> result = list.stream().collect(Collectors.toList());
    reverse(result, 0, n - 1);
    reverse(result, 0, k - 1);
    reverse(result, k, n - 1);

    return result;
  }

  /**
   * 리스트의 일부를 뒤집는 헬퍼 메서드
   *
   * @param list  회전할 리스트
   * @param start 뒤집을 시작 인덱스
   * @param end   뒤집을 끝 인덱스
   */
  private <T> void reverse(List<T> list, int start, int end) {
    while (start < end) {
      T temp = list.get(start);
      list.set(start, list.get(end));
      list.set(end, temp);
      start++;
      end--;
    }
  }
}
