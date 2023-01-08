package sort.bubble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 03. 버블 정렬 연습
 * <p>데이터가 네 개일 때 버블 정렬 알고리즘 방식으로 정렬하기</p>
 */
public class BubbleSortPractice03 {

  public List<Integer> sort(List<Integer> dataList) {

    Boolean isSwap = false;

    for (int a = 0; a < 3; a++) {

      // 12 9 6 3 -> 9 12 6 3 / 9 12 6 3 -> 9 6 12 3 / 9 6 12 3 -> 9 6 3 12
      // 9 6 3 12 -> 6 9 3 12 / 6 9 3 12 -> 6 3 9 12
      // 6 3 9 12 -> 3 6 9 12
      for (int b = 0; b < 3 - a; b++) {
        if (dataList.get(b) > dataList.get(b + 1)) {
          Collections.swap(dataList, b, b + 1);
          isSwap = true;
        }
      }

      if (!isSwap) {
        break;
      }
    }

    return dataList;
  }

  public static void main(String[] arsg) {

    List<Integer> originalList = Arrays.asList(12, 9, 6, 3);
    System.out.println("originalList : " + originalList);

    BubbleSortPractice03 bubbleSort = new BubbleSortPractice03();
    List<Integer> sortedList = bubbleSort.sort(originalList);
    System.out.println("sortedList : " + sortedList);
  }
}
