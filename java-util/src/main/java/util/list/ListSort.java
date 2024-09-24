package util.list;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListSort {

  /**
   * Collections.sort 메소드를 사용하여 리스트를 오름차순으로 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> listSortAscending(List<T> list) {
    Collections.sort(list);
    return list;
  }

  /**
   * Collections.sort 메소드를 사용하여 리스트를 내림차순으로 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> listSortDescending(List<T> list) {
    list.sort(Comparator.reverseOrder());
    return list;
  }

  /**
   * Stream을 사용하여 리스트를 오름차순으로 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> streamSortAscending(List<T> list) {
    return list.stream()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Stream을 사용하여 리스트를 내림차순으로 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> streamSortDescending(List<T> list) {
    return list.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
  }

  /**
   * Parallel Stream을 사용하여 리스트를 병렬로 오름차순 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> parallelStreamSortAscending(List<T> list) {
    return list.parallelStream()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Parallel Stream을 사용하여 리스트를 병렬로 내림차순 정렬
   *
   * @param list 정렬할 리스트
   * @param <T> 정렬할 리스트의 타입
   * @return 정렬된 리스트
   */
  public <T extends Comparable<? super T>> List<T> parallelStreamSortDescending(List<T> list) {
    return list.parallelStream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
  }
}
