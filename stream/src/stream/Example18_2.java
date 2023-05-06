package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 배열(Array)을 리스트(List)로 변환 - 반복문, Arrays, Collection, Steram
 *
 * <p>배열을 리스트로 변환하는 방법은 반복문을 사용하거나
 * Arrays, Collection, Stream 클래스에세 제공하는 메서드를 사용하여 변환할 수 있습니다.</p>
 */
public class Example18_2 {

  public static void main(String[] args) {

    String arr[] = {"Python", "C", "Java", "C++", "Visual Basic"};
    System.out.println("배열: " + Arrays.toString(arr));

    List<String> list = convertArrayToList01(arr);
    System.out.println("리스트:" + list);
  }

  /**
   * 방법 02. Arrays.asList() 메서드를 사용
   *
   * <p>java.util 패키지에 속하는 Java Arrays 클래스의 메서드입니다.</p>
   */
  public static <T> List<T> convertArrayToList01(T arr[]) {
    List<T> list = Arrays.asList(arr);
    return list;
  }

  /**
   * 방법 02. Arrays.asList() 메서드를 사용
   *
   * <p>Arrays.asList()는 고정된 크기를 반환하기 때문에 원소를 추가하거나 삭제가 불가능합니다. 또한 배열의 값이 수정되면 리스트 값도 수정됩니다.</p>
   * <p>리스트의 값을 추가하거나 삭제가 필요한 경우, ArrayList 생성자 함수를 사용하여 배열을 리스트로 변환하시면 됩니다.</p>
   */
  public static <T> List<T> convertArrayToList02(T arr[]) {
    List<T> list = new ArrayList<>(Arrays.asList(arr));
    return list;
  }
}

