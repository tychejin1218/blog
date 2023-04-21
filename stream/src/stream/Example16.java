package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example16 {

  public static void main(String[] args) {

    System.out.println("1. Array(int array)를 List로 변환");
    convertListToArray01();

    System.out.println("2. Array(Integer array)를 List로 변환");
    convertListToArray02();
  }

  public static void convertListToArray01() {
    int[] numberArr = new int[] {1, 2, 3, 4, 5};
    List<Integer> numberList = Arrays.stream(numberArr)
        .boxed()
        .collect(Collectors.toList());
    System.out.println(numberList);
  }

  public static void convertListToArray02() {
    Integer[] numberArr = new Integer[] {1, 2, 3, 4, 5};

    List<Integer> numberList01 = Arrays.asList(numberArr);
    System.out.println(numberList01);

    List<Integer> numberList02 = new ArrayList<>(Arrays.asList(numberArr));
    System.out.println(numberList02);

    List<Integer> numberList03 = Arrays.stream(numberArr)
        .collect(Collectors.toList());
    System.out.println(numberList03);
  }
}
