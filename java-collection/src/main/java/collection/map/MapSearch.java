package collection.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;

public class MapSearch {

  /**
   * 주어진 맵에 특정 키가 포함되어 있는지 확인
   *
   * @param map 키가 포함되어 있는지 확인할 맵
   * @param key 확인할 키
   * @param <K> 키의 타입
   * @param <V> 값의 타입
   * @return 맵이 특정 키를 포함하고 있으면 {@code true}, 그렇지 않으면 {@code false}
   */
  public <K, V> boolean containsKey(Map<K, V> map, K key) {
    return map.containsKey(key);
  }

  /**
   * 주어진 맵에 특정 값이 포함되어 있는지 확인
   *
   * @param map   값이 포함되어 있는지 확인할 맵
   * @param value 확인할 값
   * @param <K>   키의 타입
   * @param <V>   값의 타입
   * @return 맵이 특정 값을 포함하고 있으면 {@code true}, 그렇지 않으면 {@code false}
   */
  public <K, V> boolean containsValue(Map<K, V> map, V value) {
    return map.containsValue(value);
  }

  /**
   * 주어진 키와 연관된 값을 맵에서 조회
   *
   * @param map 값을 가져올 맵
   * @param key 해당 값과 연관된 키
   * @param <K> 키의 타입
   * @param <V> 값의 타입
   * @return 주어진 키와 연관된 값, 또는 키에 대한 매핑이 없으면 {@code null}
   */
  public <K, V> V getValueByKey(Map<K, V> map, K key) {
    return map.get(key);
  }

  /**
   * 주어진 값과 연관된 키를 맵에서 조회
   *
   * @param map   키를 가져올 맵
   * @param value 해당 키와 연관된 값
   * @param <K>   키의 타입
   * @param <V>   값의 타입
   * @return 주어진 값과 연관된 키, 또는 값에 대한 매핑이 없으면 {@code null}
   */
  public <K, V> K getKeyByValue(Map<K, V> map, V value) {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (entry.getValue().equals(value)) {
        return entry.getKey();
      }
    }
    return null;
  }

  /**
   * 주어진 조건에 부합하는 첫 번째 엔트리를 조회
   *
   * @param map       검색할 맵
   * @param condition 검색 조건
   * @param <K>       키의 타입
   * @param <V>       값의 타입
   * @return 조건에 부합하는 첫 번째 엔트리를 포함하는 {@code Optional}, 조건에 부합하는 엔트리가 없으면 빈 {@code Optional}
   */
  public <K, V> Optional<Map.Entry<K, V>> findEntryByCondition(
      Map<K, V> map, Predicate<Entry<K, V>> condition) {
    return map.entrySet()
        .stream()
        .filter(condition)
        .findFirst();
  }

  /**
   * 주어진 패턴 조건에 맞는 값을 가진 첫 번째 엔트리를 조회
   *
   * @param map              검색할 맵
   * @param patternCondition 검색할 패턴 조건
   * @param <K>              키의 타입
   * @param <V>              값의 타입
   * @return 패턴에 부합하는 값을 가진 첫 번째 엔트리를 포함하는 {@code Optional}, 패턴 조건에 맞는 엔트리가 없으면 빈 {@code Optional}
   */
  public <K, V> Optional<Map.Entry<K, V>> findEntryByPattern(
      Map<K, V> map, Predicate<V> patternCondition) {
    return map.entrySet()
        .stream()
        .filter(entry -> patternCondition.test(entry.getValue()))
        .findFirst();
  }
}
