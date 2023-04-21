package stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example15 {

  public static void main(String args[]) {

    System.out.println("1. Stream을 이용하여 Map을 List로 변환");
    createStreamToMap01();

    System.out.println("2. List 생성자를 이용하여 Map을 List로 변환");
    createStreamToMap02();
  }

  /**
   * Stream을 이용하여 Map을 List로 변환할 수 있습니다.
   * <p/>
   * 아래와 같이 key와 value에 대한 Stream을 생성하고 List로 변환할 수 있습니다.
   */
  public static void createStreamToMap01() {

    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Python");
    map.put(2, "C");
    map.put(3, "Java");
    map.put(4, "C++");
    map.put(5, "Visual Basic");

    List<Integer> keys = map.keySet().stream().collect(Collectors.toList());
    List<String> values = map.values().stream().collect(Collectors.toList());

    System.out.println(keys);
    System.out.println(values);
  }

  /**
   * ArrayList의 생성자에 Map의 Key와 Value에 대한 Collection 객체를 인자로 전달하면, Set의 요소를 모두 갖고 있는 리스트가 생성됩니다.
   * <p/>
   * keySet()와 values() 함수는 각각 모든 key와 value를 갖고 있는 Collection 객체를 리턴합니다.
   */
  public static void createStreamToMap02() {

    Map<Integer, String> map = new HashMap<>();
    map.put(1, "Python");
    map.put(2, "C");
    map.put(3, "Java");
    map.put(4, "C++");
    map.put(5, "Visual Basic");

    List<Integer> keys = new ArrayList<>(map.keySet());
    List<String> values = new ArrayList<>(map.values());

    System.out.println(keys);
    System.out.println(values);
  }
}


