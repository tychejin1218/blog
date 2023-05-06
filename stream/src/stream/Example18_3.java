package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 배열(Array)을 리스트(List)로 변환 - 반복문, Arrays, Collection, Steram
 *
 * <p>배열을 리스트로 변환하는 방법은 반복문을 사용하거나
 * Arrays, Collection, Stream 클래스에세 제공하는 메서드를 사용하여 변환할 수 있습니다.</p>
 */
public class Example18_3 {

  public static void main(String[] args) {

    String arr[] = {"Python", "C", "Java", "C++", "Visual Basic"};
    System.out.println("배열: " + Arrays.toString(arr));

    List<String> list = convertArrayToList(arr);
    System.out.println("리스트:" + list);
  }

  /**
   * 방법 03. Collection.addAll() 메서드를 사용
   *
   * <p>java.util 패키지에 속하는 Java Collections 클래스의 메서드입니다.</p>
   */
  public static <T> List<T> convertArrayToList(T arr[]) {
    List<T> list = new ArrayList<>();
    Collections.addAll(list, arr);
    return list;
  }
}
