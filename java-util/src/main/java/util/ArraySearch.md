# [JAVA] 배열 검색하기

자바에서는 배열을 검색하는 다양한 방법이 있습니다. 이번 문서에서는 배열을 검색하는 여러 가지 방법에 대해 설명합니다. 주요 메서드로는 순차 검색, 이진 검색, 라이브러리 메서드를 사용한 검색, 그리고 2차원 배열에서 값의 존재 여부를 확인하는 방법이 있습니다. 추가로 배열에서 최대 및 최소값을 찾는 방법도 설명합니다.

## 메서드 설명

### 1. 순차 검색

순차 검색을 사용하여 배열에서 특정 값을 찾는 방법입니다. 배열의 첫 번째 요소부터 시작하여 찾아야 하는 값을 순차적으로 비교합니다.

```java
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 순차 검색을 사용하여 배열에서 특정 값을 찾음
 *
 * @param array 검색할 배열
 * @param value 검색할 값
 * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int linearSearch(int[] array, int value) {
  for (int i = 0; i < array.length; i++) {
    if (array[i] == value) {
      return i;
    }
  }
  return -1;
}
```

### 2. 이진 검색

이진 검색을 사용하여 정렬된 배열에서 특정 값을 찾는 방법입니다. 배열이 정렬되어 있어야 하며, 중간 요소와 비교하여 검색 범위를 줄여나갑니다.

```java
/**
 * 이진 검색을 사용하여 정렬된 배열에서 특정 값을 찾음
 *
 * @param array 정렬된 배열
 * @param value 검색할 값
 * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int binarySearch(int[] array, int value) {
  int left = 0;
  int right = array.length - 1;

  while (left <= right) {
    int middle = left + (right - left) / 2;

    if (array[middle] == value) {
      return middle;
    }

    if (array[middle] < value) {
      left = middle + 1;
    } else {
      right = middle - 1;
    }
  }

  return -1;
}
```

### 3. Arrays.binarySearch 메서드를 사용한 검색

`Arrays.binarySearch` 메서드를 사용하여 정렬된 배열에서 빠르게 값을 찾는 방법입니다.

```java
/**
 * Arrays.binarySearch 메서드를 사용하여 정렬된 배열에서 특정 값을 찾음
 *
 * @param array 정렬된 배열
 * @param value 검색할 값
 * @return 배열에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int binarySearchUsingLibrary(int[] array, int value) {
  int index = Arrays.binarySearch(array, value);
  return index >= 0 ? index : -1;
}
```

### 4. 2차원 배열에서 특정 값의 존재 여부 확인

2차원 배열에서 특정 값이 존재하는지 확인하는 방법입니다. 각 서브 배열을 순차적으로 검색합니다.

```java
/**
 * 2차원 배열에서 특정 값이 존재하는지 확인
 *
 * @param array 검색할 2차원 배열
 * @param value 검색할 값
 * @return 값이 존재하면 true, 그렇지 않으면 false
 */
public boolean containsIn2DArray(int[][] array, int value) {
  for (int[] subArray : array) {
    if (Arrays.stream(subArray).anyMatch(i -> i == value)) {
      return true;
    }
  }
  return false;
}
```

### 5. 배열에서 최대값 찾기

배열에서 최대값을 찾는 방법입니다. 순차적으로 모든 요소를 비교하여 최대값을 찾습니다.

```java
/**
 * 배열에서 최대값을 찾음
 *
 * @param array 검색할 배열
 * @return 배열에서 최대값
 * @throws NoSuchElementException 배열이 비어있는 경우
 */
public int findMaxUsingForLoop(int[] array) {
  if (array == null || array.length == 0) {
    throw new NoSuchElementException("Array is empty");
  }

  int max = array[0];
  for (int i = 1; i < array.length; i++) {
    if (array[i] > max) {
      max = array[i];
    }
  }
  return max;
}

/**
 * 배열에서 최대값을 찾음
 *
 * @param array 검색할 배열
 * @return 배열에서 최대값
 * @throws NoSuchElementException 배열이 비어있는 경우
 */
public int findMax(int[] array) {
  return Arrays.stream(array)
      .max()
      .orElseThrow(NoSuchElementException::new);
}
```

### 6. 배열에서 최소값 찾기

배열에서 최소값을 찾는 방법입니다. 순차적으로 모든 요소를 비교하여 최소값을 찾습니다.

```java
/**
 * 배열에서 최소값을 찾음
 *
 * @param array 검색할 배열
 * @return 배열에서 최소값
 * @throws NoSuchElementException 배열이 비어있는 경우
 */
public int findMinUsingForLoop(int[] array) {
  if (array == null || array.length == 0) {
    throw new NoSuchElementException("Array is empty");
  }

  int min = array[0];
  for (int i = 1; i < array.length; i++) {
    if (array[i] < min) {
      min = array[i];
    }
  }
  return min;
}

/**
 * 배열에서 최소값을 찾음
 *
 * @param array 검색할 배열
 * @return 배열에서 최소값
 * @throws NoSuchElementException 배열이 비어있는 경우
 */
public int findMin(int[] array) {
  return Arrays.stream(array)
      .min()
      .orElseThrow(NoSuchElementException::new);
}
```

## 장단점

### 장점
- **직관적임:** 순차 검색과 같은 단순 검색 방법은 이해하기 쉽습니다.
- **효율적임:** 정렬된 배열에서는 이진 검색을 사용하여 높은 검색 효율을 얻을 수 있습니다.
- **유연함:** 2차원 배열에서도 값을 검색할 수 있는 유연한 방법을 제공합니다.

### 단점
- **속도 제한:** 순차 검색은 배열의 크기가 클 경우 검색 속도가 느려질 수 있습니다.
- **정렬 필요:** 이진 검색은 배열이 정렬되어 있어야만 사용할 수 있는 제한이 있습니다.

## 결론

자바에서 배열을 검색하는 다양한 방법을 살펴보았습니다. 순차 검색, 이진 검색, `Arrays.binarySearch` 메서드를 사용한 검색, 그리고 2차원 배열에서 특정 값의 존재 여부를 확인하는 방법을 통해 배열 데이터의 검색을 보다 효율적으로 처리할 수 있습니다. 또한, 배열에서 최대값과 최소값을 찾는 방법도 포함하였습니다.
