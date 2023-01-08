package sort.bubble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 01. 버블 정렬 연습
 * <p>데이터가 두 개일 때 버블 정렬 알고리즘 방식으로 정렬하기</p>
 */
public class BubbleSortPractice01 {

  public List<Integer> sort(List<Integer> dataList) {

    // 6 3 -> 3 6
    if (dataList.get(0) > dataList.get(1)) {
      Collections.swap(dataList, 0, 1);
    }

    return dataList;
  }

  public static void main(String[] arsg) {

    List<Integer> originalList = Arrays.asList(6, 3);
    System.out.println("originalList : " + originalList);

    BubbleSortPractice01 bubbleSort = new BubbleSortPractice01();
    List<Integer> sortedList = bubbleSort.sort(originalList);
    System.out.println("sortedList : " + sortedList);
  }
}
