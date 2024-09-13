# [JAVA] 배열 정렬하기

자바에서는 배열을 정렬하는 다양한 방법이 있습니다. 이번 문서에서는 `Arrays.sort`, `Arrays.parallelSort` 메서드 등을 사용하여 배열을 오름차순 및 내림차순으로 정렬하는 방법을 기본과 병렬 방식으로 구분하여 설명합니다.

## 메서드 설명

### 1. 정수 배열을 오름차순 정렬

`Arrays.sort` 메서드를 사용하여 정수 배열을 오름차순으로 정렬합니다.

```java
/**
 * Arrays.sort 메소드를 사용하여 배열을 오름차순으로 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public int[] arraySortAscending(int[] arr) {
    Arrays.sort(arr);
    return arr;
}
```

### 2. 정수 배열을 내림차순 정렬

`Arrays.sort`와 `Comparator.reverseOrder`를 사용하여 정수 배열을 내림차순으로 정렬합니다.

```java
/**
 * Arrays.sort 메소드를 사용하여 배열을 내림차순으로 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public int[] arraySortDescending(int[] arr) {

    // 원시형 배열을 래퍼 클래스 배열로 변환
    Integer[] boxedArr = Arrays.stream(arr)
        .boxed()
        .toArray(Integer[]::new);

    // 내림차순 정렬
    Arrays.sort(boxedArr, Comparator.reverseOrder());

    // 래퍼 클래스 배열을 원시형 배열로 변환
    return Arrays.stream(boxedArr)
        .mapToInt(Integer::intValue)
        .toArray();
}
```

### 3. 정수 배열을 병렬로 오름차순 정렬

`Arrays.parallelSort` 메서드를 사용하여 정수 배열을 병렬로 오름차순 정렬합니다.

```java
/**
 * Arrays.parallelSort 메소드를 사용하여 배열을 병렬로 오름차순 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public int[] parallelSortAscending(int[] arr) {
    Arrays.parallelSort(arr);
    return arr;
}
```

### 4. 정수 배열을 병렬로 내림차순 정렬

`Arrays.parallelSort`와 `Comparator.reverseOrder`를 사용하여 정수 배열을 병렬로 내림차순 정렬합니다.

```java
/**
 * Arrays.parallelSort 메소드를 사용하여 배열을 병렬로 내림차순 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public int[] parallelSortDescending(int[] arr) {

    // 원시형 배열을 래퍼 클래스 배열로 변환
    Integer[] boxedArr = Arrays.stream(arr)
        .boxed()
        .toArray(Integer[]::new);

    // 내림차순 정렬
    Arrays.parallelSort(boxedArr, Comparator.reverseOrder());

    // 래퍼 클래스 배열을 원시형 배열로 변환
    return Arrays.stream(boxedArr)
        .mapToInt(Integer::intValue)
        .toArray();
}
```

### 5. 문자열 배열을 오름차순 정렬

`Arrays.sort` 메서드를 사용하여 문자열 배열을 오름차순으로 정렬합니다.

```java
/**
 * Arrays.sort 메소드를 사용하여 문자열 배열을 오름차순으로 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public String[] arraySortAscending(String[] arr) {
    Arrays.sort(arr);
    return arr;
}
```

### 6. 문자열 배열을 내림차순 정렬

`Arrays.sort`와 `Comparator.reverseOrder`를 사용하여 문자열 배열을 내림차순으로 정렬합니다.

```java
/**
 * Arrays.sort 메소드를 사용하여 문자열 배열을 내림차순으로 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public String[] arraySortDescending(String[] arr) {
    Arrays.sort(arr, Comparator.reverseOrder());
    return arr;
}
```

### 7. 문자열 배열을 병렬로 오름차순 정렬

`Arrays.parallelSort` 메서드를 사용하여 문자열 배열을 병렬로 오름차순 정렬합니다.

```java
/**
 * Arrays.parallelSort 메소드를 사용하여 문자열 배열을 병렬로 오름차순 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public String[] parallelSortAscending(String[] arr) {
    Arrays.parallelSort(arr);
    return arr;
}
```

### 8. 문자열 배열을 병렬로 내림차순 정렬

`Arrays.parallelSort`와 `Comparator.reverseOrder`를 사용하여 문자열 배열을 병렬로 내림차순 정렬합니다.

```java
/**
 * Arrays.parallelSort 메소드를 사용하여 문자열 배열을 병렬로 내림차순 정렬
 *
 * @param arr 정렬할 배열
 * @return 정렬된 배열
 */
public String[] parallelSortDescending(String[] arr) {
    Arrays.parallelSort(arr, Comparator.reverseOrder());
    return arr;
}
```

## 장단점

### 장점
- **간단함:** `Arrays.sort` 메서드는 매우 직관적이고 이해하기 쉽습니다.
- **성능:** `Arrays.parallelSort`는 병렬 처리를 통해 대용량 데이터에서 성능을 향상시킬 수 있습니다.

### 단점
- **복잡성:** 병렬 정렬(특히 내림차순 정렬)은 코드가 더 복잡해질 수 있습니다.
- **오버헤드:** 병렬 처리는 특정 상황에서 성능 오버헤드를 초래할 수 있습니다.

## 결론
결론적으로, 자바에서 배열을 정렬하는 방법은 `Arrays.sort`와 `Arrays.parallelSort` 메서드를 통해 용이하게 구현할 수 있습니다.
