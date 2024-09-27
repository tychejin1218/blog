# [JAVA] 리스트 정렬하기 : 오름차순, 내림차순, 병렬 정렬

자바에서는 리스트를 정렬하는 다양한 방법이 있습니다. 이번 문서에서는 `ListSort` 클래스의 메서드를 사용하여 리스트를 오름차순 및 내림차순으로 정렬하는 방법을 기본과 병렬 방식으로 구분하여 설명하겠습니다.

## 1. 리스트를 `Collections.sort` 메서드를 사용하여 오름차순 정렬

`Collections.sort` 메서드를 사용하여 정수 리스트를 오름차순으로 정렬합니다.

```java
/**
 * Collections.sort 메소드를 사용하여 리스트를 오름차순으로 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> listSortAscending(List<T> list) {
    Collections.sort(list);
    return list;
}
```

## 2. 리스트를 `Collections.sort` 메서드를 사용하여 내림차순 정렬

`Collections.sort`와 `Comparator.reverseOrder`를 사용하여 정수 리스트를 내림차순으로 정렬합니다.

```java
/**
 * Collections.sort 메소드를 사용하여 리스트를 내림차순으로 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> listSortDescending(List<T> list) {
    list.sort(Comparator.reverseOrder());
    return list;
}
```

## 3. 리스트를 `Stream`을 사용하여 오름차순 정렬

`Stream`을 사용하여 정수 리스트를 오름차순으로 정렬합니다.

```java
/**
 * Stream을 사용하여 리스트를 오름차순으로 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> streamSortAscending(List<T> list) {
    return list.stream()
        .sorted()
        .collect(Collectors.toList());
}
```

## 4. 리스트를 `Stream`을 사용하여 내림차순 정렬

`Stream`과 `Comparator.reverseOrder`를 사용하여 정수 리스트를 내림차순으로 정렬합니다.

```java
/**
 * Stream을 사용하여 리스트를 내림차순으로 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> streamSortDescending(List<T> list) {
    return list.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
}
```

## 5. 리스트를 `Parallel Stream`을 사용하여 병렬로 오름차순 정렬

`Parallel Stream`을 사용하여 정수 리스트를 병렬로 오름차순 정렬합니다.

```java
/**
 * Parallel Stream을 사용하여 리스트를 병렬로 오름차순 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> parallelStreamSortAscending(List<T> list) {
    return list.parallelStream()
        .sorted()
        .collect(Collectors.toList());
}
```

## 6. 리스트를 `Parallel Stream`을 사용하여 병렬로 내림차순 정렬

`Parallel Stream`과 `Comparator.reverseOrder`를 사용하여 정수 리스트를 병렬로 내림차순 정렬합니다.

```java
/**
 * Parallel Stream을 사용하여 리스트를 병렬로 내림차순 정렬
 *
 * @param list 정렬할 리스트
 * @param <T> 정렬할 리스트의 타입
 * @return 정렬된 리스트
 */
public <T extends Comparable<? super T>> List<T> parallelStreamSortDescending(List<T> list) {
    return list.parallelStream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
}
```

## 장단점

### 장점

- **간단함:** `Collections.sort`, `Stream.sort`, `Parallel Stream.sort` 메서드는 매우 직관적이고 이해하기 쉽습니다.
- **성능:** `Parallel Stream`은 병렬 처리를 통해 대용량 데이터에서 성능을 향상시킬 수 있습니다.

### 단점

- **복잡성:** 병렬 정렬(특히 내림차순 정렬)은 코드가 더 복잡해질 수 있습니다.
- **오버헤드:** 병렬 처리는 특정 상황에서 성능 오버헤드를 초래할 수 있습니다.

## 결론

자바에서 리스트를 정렬하는 방법을 `Collections.sort`, `Stream`, `Parallel Stream` 메서드를 통해 용이하게 구현할 수 있습니다. 각각의 방법은 정렬할 데이터의 양과 정렬 속도 요구 사항에 따라 적절히 선택할 수 있습니다.
