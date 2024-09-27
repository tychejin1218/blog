package collection.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionConverter {

  /**
   * 배열을 List로 변환
   *
   * @param array 변환할 배열
   * @param <T>   배열의 원소 타입
   * @return 변환된 List
   */
  public static <T> List<T> arrayToList(T[] array) {
    return Arrays.asList(array);
  }

  /**
   * 배열을 List로 변환 후 Set으로 변환
   *
   * @param array 변환할 배열
   * @param <T>   배열의 원소 타입
   * @return 변환된 Set
   */
  public static <T> Set<T> arrayToSet(T[] array) {
    return new HashSet<>(Arrays.asList(array));
  }

  /**
   * List를 Set으로 변환
   *
   * @param list 변환할 List
   * @param <T>  List의 원소 타입
   * @return 변환된 Set
   */
  public static <T> Set<T> listToSet(List<T> list) {
    return new HashSet<>(list);
  }

  /**
   * List를 배열로 변환
   *
   * @param list  변환할 List
   * @param array 배열로 변환할 객체
   * @param <T>   List의 원소 타입
   * @return 변환된 배열
   */
  public static <T> T[] listToArray(List<T> list, T[] array) {
    return list.toArray(array);
  }

  /**
   * Map의 Key들을 List로 변환
   *
   * @param map 변환할 Map
   * @param <K> Map의 Key 타입
   * @param <V> Map의 Value 타입
   * @return Key로 이루어진 List
   */
  public static <K, V> List<K> mapKeysToList(Map<K, V> map) {
    return new ArrayList<>(map.keySet());
  }

  /**
   * Map의 Value들을 List로 변환
   *
   * @param map 변환할 Map
   * @param <K> Map의 Key 타입
   * @param <V> Map의 Value 타입
   * @return Value로 이루어진 List
   */
  public static <K, V> List<V> mapValuesToList(Map<K, V> map) {
    return new ArrayList<>(map.values());
  }

  /**
   * Map의 Entry들을 List로 변환
   *
   * @param map 변환할 Map
   * @param <K> Map의 Key 타입
   * @param <V> Map의 Value 타입
   * @return Entry로 이루어진 List
   */
  public static <K, V> List<Map.Entry<K, V>> mapEntriesToList(Map<K, V> map) {
    return new ArrayList<>(map.entrySet());
  }

  /**
   * Set을 List로 변환
   *
   * @param set 변환할 Set
   * @param <T> Set의 원소 타입
   * @return 변환된 List
   */
  public static <T> List<T> setToList(Set<T> set) {
    return new ArrayList<>(set);
  }
}
