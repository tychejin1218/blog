# [JAVA] 리스트 검색하기

자바에서는 리스트를 검색하는 다양한 방법이 있습니다. 이번 문서에서는 리스트를 검색하는 여러 가지 방법에 대해 설명하겠습니다. 주요 메서드로는 순차 검색, 이진 검색, 라이브러리 메서드를 사용한 검색, 특정 값의 모든 인덱스를 찾는 방법, 최대값 및 최소값을 찾는 방법, 최빈값(가장 빈번하게 나타나는 값)을 찾는 방법, 그리고 리스트에서 중복값을 찾는 방법이 있습니다.

## 1. 순차 검색

순차 검색을 사용하여 리스트에서 특정 값을 찾는 방법입니다. 리스트의 첫 번째 요소부터 시작하여 찾아야 하는 값을 순차적으로 비교합니다.

```java
/**
 * 순차 검색을 사용하여 리스트에서 특정 값을 찾음
 *
 * @param list 검색할 리스트
 * @param value 검색할 값
 * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int linearSearch(List<Integer> list, int value) {
    for (int i = 0; i < list.size(); i++) {
        if (list.get(i) == value) {
            return i;
        }
    }
    return -1;
}
```

## 2. 이진 검색

이진 검색을 사용하여 정렬된 리스트에서 특정 값을 찾는 방법입니다. 리스트가 정렬되어 있어야 하며, 중간 요소와 비교하여 검색 범위를 줄여나갑니다.

```java
/**
 * 리스트에서 이진 검색을 사용하여 정렬된 리스트에서 특정 값을 찾음
 *
 * @param list 정렬된 리스트
 * @param value 검색할 값
 * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int binarySearch(List<Integer> list, int value) {
    int left = 0;
    int right = list.size() - 1;

    while (left <= right) {
        int middle = left + (right - left) / 2;

        if (list.get(middle) == value) {
            return middle;
        }

        if (list.get(middle) < value) {
            left = middle + 1;
        } else {
            right = middle - 1;
        }
    }

    return -1;
}
```

## 3. `Collections.binarySearch` 메서드를 사용한 검색

`Collections.binarySearch` 메서드를 사용하여 정렬된 리스트에서 빠르게 값을 찾는 방법입니다.

```java
/**
 * Lists.binarySearch를 사용하여 정렬된 리스트에서 특정 값을 찾음
 *
 * @param list 정렬된 리스트
 * @param value 검색할 값
 * @return 리스트에서 값의 인덱스 (존재하지 않으면 -1)
 */
public int binarySearchUsingLibrary(List<Integer> list, int value) {
    int index = Collections.binarySearch(list, value);
    return index >= 0 ? index : -1;
}
```

## 4. 리스트에서 특정 값의 모든 인덱스 찾기

리스트에서 특정 값이 나타나는 모든 인덱스를 찾는 방법입니다.

```java
/**
 * 리스트에서 특정 값의 모든 인덱스를 찾음
 *
 * @param list 검색할 리스트
 * @param value 검색할 값
 * @return 값이 나타나는 모든 인덱스의 리스트
 */
public List<Integer> findAllIndices(List<Integer> list, int value) {
    List<Integer> indices = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
        if (list.get(i) == value) {
            indices.add(i);
        }
    }

    return indices;
}
```

## 5. 리스트에서 최대값 찾기

리스트에서 최대값을 찾는 방법입니다. 순차적으로 모든 요소를 비교하여 최대값을 찾습니다.

```java
/**
 * 리스트에서 최대값을 찾음
 *
 * @param list 검색할 리스트
 * @return 리스트에서 최대값
 * @throws NoSuchElementException 리스트가 비어있는 경우
 */
public int findMaxUsingForLoop(List<Integer> list) {
    if (list == null || list.isEmpty()) {
        throw new NoSuchElementException("List is empty");
    }

    int max = list.get(0);
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i) > max) {
            max = list.get(i);
        }
    }
    return max;
}

/**
 * 리스트에서 최대값을 찾음
 *
 * @param list 검색할 리스트
 * @return 리스트에서 최대값
 * @throws NoSuchElementException 리스트가 비어있는 경우
 */
public int findMax(List<Integer> list) {
    return list.stream()
        .max(Integer::compareTo)
        .orElseThrow(NoSuchElementException::new);
}
```

## 6. 리스트에서 최소값 찾기

리스트에서 최소값을 찾는 방법입니다. 순차적으로 모든 요소를 비교하여 최소값을 찾습니다.

```java
/**
 * 리스트에서 최소값을 찾음
 *
 * @param list 검색할 리스트
 * @return 리스트에서 최소값
 * @throws NoSuchElementException 리스트가 비어있는 경우
 */
public int findMinUsingForLoop(List<Integer> list) {
    if (list == null || list.isEmpty()) {
        throw new NoSuchElementException("List is empty");
    }

    int min = list.get(0);
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i) < min) {
            min = list.get(i);
        }
    }
    return min;
}

/**
 * 리스트에서 최소값을 찾음
 *
 * @param list 검색할 리스트
 * @return 리스트에서 최소값
 * @throws NoSuchElementException 리스트가 비어있는 경우
 */
public int findMin(List<Integer> list) {
    return list.stream()
        .min(Integer::compareTo)
        .orElseThrow(NoSuchElementException::new);
}
```

## 7. 리스트에서 최빈값 찾기

리스트에서 최빈값(가장 빈번하게 나타나는 값)을 찾는 방법입니다.

```java
/**
 * 리스트에서 최빈값(가장 빈번하게 나타나는 값)을 찾음
 *
 * @param list 검색할 리스트
 * @return 가장 빈번하게 나타나는 값
 * @throws NoSuchElementException 리스트가 비어있는 경우
 */
public int findMostFrequent(List<Integer> list) {
    if (list == null || list.isEmpty()) {
        throw new NoSuchElementException("List is empty");
    }

    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : list) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    return frequencyMap.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .get()
        .getKey();
}
```

## 8. 리스트에서 중복값 찾기

리스트에서 중복값을 찾는 방법입니다.

```java
/**
 * 리스트에서 중복값을 찾음
 *
 * @param list 검색할 리스트
 * @return 중복값의 집합
 */
public Set<Integer> findDuplicates(List<Integer> list) {
    Set<Integer> duplicates = new HashSet<>();
    Set<Integer> seen = new HashSet<>();

    for (int num : list) {
        if (!seen.add(num)) {
            duplicates.add(num);
        }
    }

    return duplicates;
}
```

## 장단점

### 장점
- **포괄적임:** 다양한 검색 방법을 제공하여 리스트에서의 검색 요구를 모두 충족할 수 있습니다.
- **효율적임:** 특정 상황에서는 이진 검색과 같은 고효율 검색 알고리즘을 사용할 수 있습니다.
- **고유 기능:** 최빈값 찾기, 중복값 찾기와 같은 유용한 추가 기능을 제공합니다.

### 단점
- **정렬 필요:** 이진 검색은 리스트가 정렬되어 있어야만 사용할 수 있습니다.
- **복잡도:** 특정 상황에서는 복잡한 라이브러리 호출이나 스트림 연산을 사용해야 할 수도 있습니다.

## 결론

자바에서 리스트를 검색하는 다양한 방법을 살펴보았습니다. 순차 검색, 이진 검색, `Collections.binarySearch` 메서드를 사용한 검색, 특정 값의 모든 인덱스를 찾는 방법, 최대값 및 최소값을 찾는 방법, 최빈값을 찾는 방법 그리고 중복값을 찾는 방법을 통해 리스트 데이터의 검색을 보다 효율적으로 처리할 수 있습니다.
