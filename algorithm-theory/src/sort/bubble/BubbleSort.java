package sort.bubble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 버블 정렬
 * <p>데이터 갯수에 상관없이 버블 정렬 알고리즘 방식으로 정렬하기</p>
 */
public class BubbleSort {

  public List<Integer> sort(List<Integer> dataList) {

    for (int index = 0; index < dataList.size() - 1; index++) {

      boolean isSwap = false;

      for (int index2 = 0; index2 < dataList.size() - index - 1; index2++) {
        if (dataList.get(index2) > dataList.get(index2 + 1)) {
          Collections.swap(dataList, index2, index2 + 1);
          isSwap = true;
        }
      }

      if (isSwap == false) {
        break;
      }
    }

    return dataList;
  }

  public static void main(String[] args) {

    List<Integer> testData = new ArrayList<>();

    for (int a = 0; a < 100; a++) {
      testData.add((int) (Math.random() * 100));
    }

    BubbleSort bubbleSort = new BubbleSort();

    System.out.println(bubbleSort.sort(testData));
  }
}