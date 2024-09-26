package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ListSearch {

  /**
   * 순차 검색을 사용하여 리스트에서 특정 값을 찾음
   *
   * @param list  검색할 리스트
   * @param value 검색할 값
   * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int linearSearch(List<Integer> list, int value) {

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 리스트에서 이진 검색을 사용하여 정렬된 리스트에서 특정 값을 찾음
   *
   * @param list  정렬된 리스트
   * @param value 검색할 값
   * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int binarySearch(List<Integer> list, int value) {

    int left = 0;
    int right = list.size() - 1;

    while (left <= right) {
      int middle = left + (right - left) / 2;

      if (list.get(middle) == value) {
        return middle;
      }

      if (list.get(middle) < value) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }

    return -1;
  }

  /**
   * Lists.binarySearch를 사용하여 정렬된 리스트에서 특정 값을 찾음
   *
   * @param list  정렬된 리스트
   * @param value 검색할 값
   * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
   */
  public int binarySearchUsingLibrary(List<Integer> list, int value) {
    int index = Collections.binarySearch(list, value);
    return index >= 0 ? index : -1;
  }

  /**
   * 리스트에서 특정 값의 모든 인덱스를 찾음
   *
   * @param list  검색할 리스트
   * @param value 검색할 값
   * @return 값이 나타나는 모든 인덱스의 리스트
   */
  public List<Integer> findAllIndices(List<Integer> list, int value) {

    List<Integer> indices = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == value) {
        indices.add(i);
      }
    }

    return indices;
  }

  /**
   * 리스트에서 최대값을 찾음
   *
   * @param list 검색할 리스트
   * @return 리스트에서 최대값
   * @throws NoSuchElementException 리스트가 비어있는 경우
   */
  public int findMaxUsingForLoop(List<Integer> list) {

    if (list == null || list.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }

    int max = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i) > max) {
        max = list.get(i);
      }
    }
    return max;
  }

  /**
   * 리스트에서 최대값을 찾음
   *
   * @param list 검색할 리스트
   * @return 리스트에서 최대값
   * @throws NoSuchElementException 리스트가 비어있는 경우
   */
  public int findMax(List<Integer> list) {
    return list.stream()
        .max(Integer::compareTo)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * 리스트에서 최소값을 찾음
   *
   * @param list 검색할 리스트
   * @return 리스트에서 최소값
   * @throws NoSuchElementException 리스트가 비어있는 경우
   */
  public int findMinUsingForLoop(List<Integer> list) {

    if (list == null || list.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }

    int min = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i) < min) {
        min = list.get(i);
      }
    }
    return min;
  }

  /**
   * 리스트에서 최소값을 찾음
   *
   * @param list 검색할 리스트
   * @return 리스트에서 최소값
   * @throws NoSuchElementException 리스트가 비어있는 경우
   */
  public int findMin(List<Integer> list) {
    return list.stream()
        .min(Integer::compareTo)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * 리스트에서 최빈값(가장 빈번하게 나타나는 값)을
   *
   * @param list 검색할 리스트
   * @return 가장 빈번하게 나타나는 값
   * @throws NoSuchElementException 리스트가 비어있는 경우
   */
  public int findMostFrequent(List<Integer> list) {

    if (list == null || list.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }

    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : list) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    return frequencyMap.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .get()
        .getKey();
  }

  /**
   * 리스트에서 중복값을 찾음
   *
   * @param list 검색할 리스트
   * @return 중복값의 집합
   */
  public Set<Integer> findDuplicates(List<Integer> list) {

    Set<Integer> duplicates = new HashSet<>();
    Set<Integer> seen = new HashSet<>();

    for (int num : list) {
      if (!seen.add(num)) {
        duplicates.add(num);
      }
    }

    return duplicates;
  }
}
