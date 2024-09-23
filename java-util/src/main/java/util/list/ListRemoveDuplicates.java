package util.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ListRemoveDuplicates {

  /**
   * 리스트에서 중복 요소를 제거 (List 사용)
   *
   * @param list 중복 요소를 제거할 리스트
   * @param <T> 리스트 요소의 타입
   * @return 중복이 제거된 리스트
   */
  public <T> List<T> removeDuplicatesUsingList(List<T> list) {
    List<T> result = new ArrayList<>();
    for (T element : list) {
      if (!result.contains(element)) {
        result.add(element);
      }
    }
    return result;
  }

  /**
   * 리스트에서 중복 요소를 제거 (HashSet 사용)
   *
   * @param list 중복 요소를 제거할 리스트
   * @param <T> 리스트 요소의 타입
   * @return 중복이 제거된 리스트
   */
  public <T> List<T> removeDuplicatesUsingSet(List<T> list) {
    return new ArrayList<>(new HashSet<>(list));
  }

  /**
   * Stream API를 사용하여 리스트에서 중복 요소를 제거
   *
   * @param list 중복 요소를 제거할 리스트
   * @param <T> 리스트 요소의 타입
   * @return 중복이 제거된 리스트
   */
  public <T> List<T> removeDuplicatesUsingStream(List<T> list) {
    return list.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * 리스트에서 중복 요소를 제거 (TreeSet 사용, 요소 정렬)
   *
   * @param list 중복 요소를 제거할 리스트
   * @param <T> 리스트 요소의 타입
   * @return 중복이 제거되고 정렬된 리스트
   */
  public <T> List<T> removeDuplicatesUsingTreeSet(List<T> list) {
    return new ArrayList<>(new TreeSet<>(list));
  }

  /**
   * 리스트에서 중복 요소를 제거 (LinkedHashSet 사용, 삽입 순서 유지)
   *
   * @param list 중복 요소를 제거할 리스트
   * @param <T> 리스트 요소의 타입
   * @return 중복이 제거되고 삽입 순서가 유지된 리스트
   */
  public <T> List<T> removeDuplicatesUsingLinkedHashSet(List<T> list) {
    return new ArrayList<>(new LinkedHashSet<>(list));
  }
}
