package sort.bubble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 01. 연습
 * <p>데이터가 두 개일 때 버블 정렬 알고리즘 방식으로 정렬하기</p>
 */
public class BubbleSortPractice1 {

  public List<Integer> sort(List<Integer> dataList) {

    if (dataList.get(0) > dataList.get(1)) {
      Collections.swap(dataList, 0, 1);
    }

    return dataList;
  }

  public static void main(String[] arsg) {

    List<Integer> originalList = Arrays.asList(6, 3);

    System.out.println("originalList : ");
    System.out.println(originalList);

    BubbleSortPractice1 bubbleSort = new BubbleSortPractice1();
    List<Integer> sortedList = bubbleSort.sort(originalList);

    System.out.println("sortedList : ");
    System.out.println(sortedList);
  }
}
