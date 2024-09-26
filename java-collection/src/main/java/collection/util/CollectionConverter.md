# [JAVA] 컬렉션 간의 변환 방법

자바 프로젝트를 진행하다 보면 배열, 리스트, 셋, 맵 등의 컬렉션 타입들을 상호 변환해야 하는 경우가 자주 발생합니다. 이번 문서에서는 이러한 변환 작업을 어떻게 수행할 수 있는지에 대해 다뤄보겠습니다.

## 1. 배열을 List로 변환하기

배열을 List로 변환할 때 가장 간단한 방법은 `Arrays.asList()` 메서드를 사용하는 것입니다.

```java
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
```

## 2. 배열을 Set으로 변환하기

배열을 Set으로 변환하는 방법은 배열을 먼저 리스트로 변환한 후 `HashSet` 생성자에 전달하는 것입니다.

```java
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
```

순서가 보장된 Set이 필요하다면 `LinkedHashSet`을 사용할 수 있습니다.

```java
public static <T> Set<T> arrayToOrderedSet(T[] array) {
    return new LinkedHashSet<>(Arrays.asList(array));
}
```

## 3. List를 Set으로 변환하기

`List`를 `Set`으로 변환하는 방법은 파라미터로 리스트를 받는 `HashSet` 생성자를 사용하는 것입니다.

```java
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
```

## 4. List를 배열로 변환하기

리스트를 배열로 변환할 때는 `List` 인터페이스의 `toArray` 메서드를 사용합니다. 배열의 타입을 지정하여 새로운 배열을 생성합니다.

```java
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
```

## 5. Map의 Key들을 List로 변환하기

맵의 키를 `List`로 변환하는 방법은 `Map`의 `keySet` 메서드를 사용하여 키의 집합을 가져온 다음, 이를 새로운 리스트 객체에 전달하는 것입니다.

```java
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
```

## 6. Map의 Value들을 List로 변환하기

맵의 값을 `List`로 변환할 때는 `values` 메서드를 사용하여 값을 수집한 후, 이를 리스트로 변환합니다.

```java
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
```

## 7. Map의 Entry들을 List로 변환하기

맵의 엔트리를 `List`로 변환하는 방법은 `entrySet` 메서드를 사용하여 엔트리 집합을 가져온 후 리스트로 변환하는 것입니다.

```java
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
```

## 8. Set을 List로 변환하기

`Set`을 `List`로 변환할 때는 리스트 생성자에 셋을 전달합니다.

```java
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
```

순서가 보장된 Set이 필요하다면 `LinkedHashSet`을 사용할 수 있습니다.

```java
public static <T> List<T> orderedSetToList(Set<T> set) {
    return new ArrayList<>(new LinkedHashSet<>(set));
}
```
