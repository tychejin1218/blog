package collection.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 * Map의 내용을 다양한 방법으로 출력함
 */
public class MapOutput {

  /**
   * entrySet을 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingEntrySet(Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
    }
  }

  /**
   * keySet을 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingKeySet(Map<String, String> map) {
    for (String key : map.keySet()) {
      String value = map.get(key);
      System.out.println("[key]:" + key + ", [value]:" + value);
    }
  }

  /**
   * entrySet Iterator를 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingEntrySetIterator(Map<String, String> map) {
    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
    }
  }

  /**
   * keySet Iterator를 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingKeySetIterator(Map<String, String> map) {
    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
      String key = iterator.next();
      String value = map.get(key);
      System.out.println("[key]:" + key + ", [value]:" + value);
    }
  }

  /**
   * Lambda를 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingLambda(Map<String, String> map) {
    map.forEach((key, value) -> {
      System.out.println("[key]:" + key + ", [value]:" + value);
    });
  }

  /**
   * Stream를 사용하여 map의 키-값 쌍 출력
   */
  public void outputUsingStream(Map<String, String> map) {
    map.entrySet().stream()
        .forEach(entry -> {
          System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
        });
  }

  /**
   * Stream을 사용하여 키를 기준으로 내림차순 정렬, 그 후 map의 키-값 쌍 출력
   */
  public void outputUsingStreamSortedByKeyDescendingOrder(Map<String, String> map) {
    map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey()).forEach(entry -> {
          System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
        });
  }

  /**
   * Stream을 사용하여 키를 기준으로 오름차순 정렬, 그 후 map의 키-값 쌍 출력
   */
  public void outputUsingStreamSortedByKeyAscendingOrder(Map<String, String> map) {
    map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .forEach(entry -> {
          System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
        });
  }
}
