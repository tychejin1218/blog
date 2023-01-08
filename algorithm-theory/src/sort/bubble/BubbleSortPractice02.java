package sort.bubble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 02. 버블 정렬 연습
 * <p>데이터가 세 개일 때 버블 정렬 알고리즘 방식으로 정렬하기</p>
 */
public class BubbleSortPractice02 {

  public List<Integer> sort(List<Integer> dataList) {

    // 9 6 3 -> 6 9 3
    if (dataList.get(0) > dataList.get(1)) {
      Collections.swap(dataList, 0, 1);
    }

    // 6 9 3 -> 6 3 9
    if (dataList.get(1) > dataList.get(2)) {
      Collections.swap(dataList, 1, 2);
    }

    // 6 3 9 -> 3 6 9
    if (dataList.get(0) > dataList.get(1)) {
      Collections.swap(dataList, 0, 1);
    }

    return dataList;
  }

  public static void main(String[] arsg) {

    List<Integer> originalList = Arrays.asList(9, 6, 3);
    System.out.println("originalList : " + originalList);

    BubbleSortPractice02 bubbleSort = new BubbleSortPractice02();
    List<Integer> sortedList = bubbleSort.sort(originalList);
    System.out.println("sortedList : " + sortedList);
  }
}
