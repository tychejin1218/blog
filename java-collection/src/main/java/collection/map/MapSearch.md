# [JAVA] 맵 검색하기

자바에서는 맵을 검색하는 다양한 방법이 있습니다. 이번 문서에서는 맵을 검색하는 여러 가지 방법에 대해 설명하겠습니다. 주요 메서드로는 키가 포함되어 있는지 검사, 값이 포함되어 있는지 검사, 특정 키에 연관된 값을 가져오는 방법, 특정 값에 연관된 키를 가져오는 방법, 특정 조건에 맞는 엔트리를 찾는 방법이 있습니다.

## 1. 키가 포함되어 있는지 확인

맵에 특정 키가 포함되어 있는지 확인할 때, `containsKey` 메서드를 사용합니다.

```java
/**
 * 맵에 특정 키가 포함되어 있는지 확인
 *
 * @param map 검색할 맵
 * @param key 검색할 키
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 맵에 키가 포함되어 있으면 true, 그렇지 않으면 false
 */
public <K, V> boolean containsKey(Map<K, V> map, K key) {
  return map.containsKey(key);
}
```

## 2. 값이 포함되어 있는지 확인

맵에 특정 값이 포함되어 있는지 확인할 때, `containsValue` 메서드를 사용합니다.

```java
/**
 * 맵에 특정 값이 포함되어 있는지 확인
 *
 * @param map 검색할 맵
 * @param value 검색할 값
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 맵에 값이 포함되어 있으면 true, 그렇지 않으면 false
 */
public <K, V> boolean containsValue(Map<K, V> map, V value) {
  return map.containsValue(value);
}
```

## 3. 키와 연관된 값 조회

주어진 키와 연관된 값을 조회할 때, `getValueByKey` 메서드를 사용합니다.

```java
/**
 * 주어진 키와 연관된 값을 조회
 *
 * @param map 검색할 맵
 * @param key 검색할 키
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 키와 연관된 값, 없으면 null
 */
public <K, V> V getValueByKey(Map<K, V> map, K key) {
  return map.get(key);
}
```

## 4. 값과 연관된 키 조회

주어진 값과 연관된 키를 조회할 때, `getKeyByValue` 메서드를 사용합니다.

```java
/**
 * 주어진 값과 연관된 키를 조회
 *
 * @param map 검색할 맵
 * @param value 검색할 값
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 값과 연관된 키, 없으면 null
 */
public <K, V> K getKeyByValue(Map<K, V> map, V value) {
  for (Map.Entry<K, V> entry : map.entrySet()) {
    if (entry.getValue().equals(value)) {
      return entry.getKey();
    }
  }
  return null;
}
```

## 5. 조건에 부합하는 첫 번째 엔트리 조회

주어진 조건에 부합하는 첫 번째 엔트리를 조회할 때, `filter`를 사용하여 조건에 맞는 요소를 찾습니다.

```java
/**
 * 조건에 부합하는 첫 번째 엔트리를 조회
 *
 * @param map 검색할 맵
 * @param condition 검색 조건
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 조건에 부합하는 첫 번째 엔트리
 */
public <K, V> Optional<Map.Entry<K, V>> findEntryByCondition(
  Map<K, V> map, Predicate<Entry<K, V>> condition) {
  return map.entrySet()
    .stream()
    .filter(condition)
    .findFirst();
}
```

위의 코드에서 `filter` 메서드는 주어진 `Predicate` 조건에 맞는 엔트리를 걸러냅니다. 그런 다음, `findFirst` 메서드를 사용하여 첫 번째 엔트리를 반환합니다.

## 6. 패턴 조건에 맞는 첫 번째 엔트리 조회

주어진 패턴 조건에 맞는 값을 가진 첫 번째 엔트리를 조회할 때, `filter`를 사용하여 조건에 맞는 요소를 찾습니다.

```java
/**
 * 패턴 조건에 맞는 첫 번째 엔트리를 조회
 *
 * @param map 검색할 맵
 * @param patternCondition 값의 패턴 조건
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 * @return 패턴 조건에 맞는 첫 번째 엔트리
 */
public <K, V> Optional<Map.Entry<K, V>> findEntryByPattern(
  Map<K, V> map, Predicate<V> patternCondition) {
  return map.entrySet()
    .stream()
    .filter(entry -> patternCondition.test(entry.getValue()))
    .findFirst();
}
```

위의 코드에서 `filter` 메서드는 주어진 패턴 조건에 맞는 값을 가진 엔트리를 걸러냅니다. `Predicate` 조건은 `entry.getValue()`를 테스트하여 이를 확인합니다. 그런 다음, `findFirst` 메서드를 사용하여 첫 번째 엔트리를 반환합니다.
