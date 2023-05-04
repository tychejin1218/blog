package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example16 {

  public static void main(String[] args) {

    System.out.println("1. Array(int array)를 List로 변환");
    convertArrayToList01();

    System.out.println("2. Array(Integer array)를 List로 변환");
    convertArrayToList02();

    System.out.println("3. Array(String)를 List로 변환");
    convertArrayToList03();
  }

  public static void convertArrayToList01() {
    int[] numberArr = new int[] {1, 2, 3, 4, 5};
    List<Integer> numberList = Arrays.stream(numberArr)
        .boxed()
        .collect(Collectors.toList());
    System.out.println(numberList);
  }

  public static void convertArrayToList02() {
    Integer[] numberArr = new Integer[] {1, 2, 3, 4, 5};

    List<Integer> numberList01 = Arrays.asList(numberArr);
    System.out.println(numberList01);

    List<Integer> numberList02 = new ArrayList<>(Arrays.asList(numberArr));
    System.out.println(numberList02);

    List<Integer> numberList03 = Arrays.stream(numberArr)
        .collect(Collectors.toList());
    System.out.println(numberList03);
  }

  public static void convertArrayToList03() {
    String[] strArr = new String[] {"a", "b", "c", "d", "e"};

    List<String> strList01 = Arrays.asList(strArr);
    System.out.println(strList01);

    List<String> strList02 = new ArrayList<>(Arrays.asList(strArr));
    System.out.println(strList02);

    List<String> strList03 = Arrays.stream(strArr)
        .collect(Collectors.toList());
    System.out.println(strList03);
  }
}
