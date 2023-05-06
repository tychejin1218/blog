package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 배열(Array)을 리스트(List)로 변환 - 반복문, Arrays, Collection, Stream
 *
 * <p>배열을 리스트로 변환하는 방법은 반복문을 사용하거나
 * Arrays, Collection, Stream 클래스에세 제공하는 메서드를 사용하여 변환할 수 있습니다.</p>
 */
public class Example18 {

  /**
   * 방법 01. 반복문을 사용
   *
   * <p>빈 리스트를 생성한 후, 배열의 모든 요소를 리스트에 추가하는 방법입니다.</p>
   * <p>Integer, Double, String 등 여러 타입의 배열을 리스트로 변환하는 경우 제네릭 타입의 메서드를 정의하여 코드의 양을 줄일 수 있습니다.</p>
   */
  public static <T> List<T> convertArrayToList01(T arr[]) {
    List<T> list = new ArrayList<>();
    for (T t : arr) {
      list.add(t);
    }
    return list;
  }

  /**
   * 방법 02. Arrays.asList() 메서드를 사용
   *
   * <p>java.util 패키지에 속하는 Java Arrays 클래스의 메서드입니다.</p>
   */
  public static <T> List<T> convertArrayToList02(T arr[]) {
    List<T> list = Arrays.asList(arr);
    return list;
  }

  /**
   * 방법 03. Collection.addAll() 메서드를 사용
   *
   * <p>java.util 패키지에 속하는 Java Collections 클래스의 메서드입니다.</p>
   */
  public static <T> List<T> convertArrayToList03(T arr[]) {
    List<T> list = new ArrayList<>();
    Collections.addAll(list, arr);
    return list;
  }

  /**
   * 방법 04. Java8 Stream API를 사용
   */
  public static <T> List<T> convertArrayToList04(T arr[]) {
    return Arrays.stream(arr).collect(Collectors.toList());
  }
}
