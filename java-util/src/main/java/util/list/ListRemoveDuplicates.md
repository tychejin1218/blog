# [JAVA] 리스트의 중복 요소 제거하기

자바에서는 리스트의 중복 요소를 제거하는 여러 가지 방법이 있습니다. `List`, `HashSet`, `Stream API`, `TreeSet`, 그리고 `LinkedHashSet`을 사용하는 방법이 있습니다. 각 방법에 대해 자세히 살펴보겠습니다.

## 1. List를 사용하여 중복 제거

`List`를 사용하여 중복을 제거하려면 우선 새로운 리스트를 만들고, 반복문을 사용하여 중복되는 요소가 없는지 확인하면서 요소를 추가해야 합니다.

다음은 `List`를 사용하여 중복을 제거하는 메서드입니다:

```java
import java.util.ArrayList;
import java.util.List;

/**
 * 리스트에서 중복 요소를 제거 (List 사용)
 *
 * @param list 중복 요소를 제거할 리스트
 * @param <T> 리스트 요소의 타입
 * @return 중복이 제거된 리스트
 */
public <T> List<T> removeDuplicatesUsingList(List<T> list) {
  List<T> result = new ArrayList<>();
  for (T element : list) {
    if (!result.contains(element)) {
      result.add(element);
    }
  }
  return result;
}
```

### **장점:**
- **심플함:** 기본적인 자바 리스트를 사용합니다.

### **단점:**
- **성능:** `contains` 메서드는 리스트 크기가 커질수록 성능이 저하됩니다.

## 2. HashSet을 사용하여 중복 제거

`HashSet`을 사용하면 중복 요소가 자동으로 제거됩니다. `HashSet`은 중복을 허용하지 않기 때문에 쉽게 중복을 처리할 수 있습니다.

다음은 `HashSet`을 사용하여 중복을 제거하는 메서드입니다:

```java
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * 리스트에서 중복 요소를 제거 (HashSet 사용)
 *
 * @param list 중복 요소를 제거할 리스트
 * @param <T> 리스트 요소의 타입
 * @return 중복이 제거된 리스트
 */
public <T> List<T> removeDuplicatesUsingSet(List<T> list) {
  return new ArrayList<>(new HashSet<>(list));
}
```

### **장점:**
- **효율성:** 해시 테이블을 기반으로 하기 때문에 성능이 좋습니다.

### **단점:**
- **순서 유지 불가:** 원래 리스트의 순서가 유지되지 않습니다.

## 3. Stream API를 사용하여 중복 제거

Java 8에서는 `Stream API`를 도입하여 컬렉션과 리스트를 처리하는 현대적이고 간결한 방법을 제공합니다. 스트림의 `distinct()` 메서드를 사용하여 중복을 제거할 수 있습니다.

다음은 `Stream API`를 사용한 방법입니다:

```java
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream API를 사용하여 리스트에서 중복 요소를 제거
 *
 * @param list 중복 요소를 제거할 리스트
 * @param <T> 리스트 요소의 타입
 * @return 중복이 제거된 리스트
 */
public <T> List<T> removeDuplicatesUsingStream(List<T> list) {
  return list.stream()
      .distinct()
      .collect(Collectors.toList());
}
```

### **장점:**
- **가독성:** 코드가 매우 간결하고 읽기 쉽습니다.

### **단점:**
- **성능 오버헤드:** 스트림은 약간의 성능 오버헤드를 수반하지만, 대부분의 경우 이는 무시할 수 있습니다.

## 4. TreeSet을 사용하여 중복 제거 및 정렬

`TreeSet`을 사용하면 중복 제거와 동시에 요소가 정렬됩니다. `TreeSet`은 `NavigableSet`의 구현체로, 요소가 정렬되어 저장됩니다.

다음은 `TreeSet`을 사용한 방법입니다:

```java
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * 리스트에서 중복 요소를 제거 (TreeSet 사용, 요소 정렬)
 *
 * @param list 중복 요소를 제거할 리스트
 * @param <T> 리스트 요소의 타입
 * @return 중복이 제거되고 정렬된 리스트
 */
public <T> List<T> removeDuplicatesUsingTreeSet(List<T> list) {
  return new ArrayList<>(new TreeSet<>(list));
}
```

### **장점:**
- **정렬:** 요소가 정렬됩니다.

### **단점:**
- **성능:** 요소가 정렬되면서 추가적인 비용이 발생합니다.

## 5. LinkedHashSet을 사용하여 중복 제거 및 삽입 순서 유지

`LinkedHashSet`을 사용하면 중복을 제거하면서 삽입 순서를 유지할 수 있습니다.

다음은 `LinkedHashSet`을 사용한 방법입니다:

```java
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * 리스트에서 중복 요소를 제거 (LinkedHashSet 사용, 삽입 순서 유지)
 *
 * @param list 중복 요소를 제거할 리스트
 * @param <T> 리스트 요소의 타입
 * @return 중복이 제거되고 삽입 순서가 유지된 리스트
 */
public <T> List<T> removeDuplicatesUsingLinkedHashSet(List<T> list) {
  return new ArrayList<>(new LinkedHashSet<>(list));
}
```

### **장점:**
- **순서 유지:** 원래 리스트의 삽입 순서가 유지됩니다.

### **단점:**
- **성능:** `LinkedHashSet`은 일반적인 `HashSet`보다 성능이 낮을 수 있습니다.

## 결론

결론적으로, 자바에서 리스트의 중복을 제거해야 할 때, 사용 사례에 따라 다양한 방법을 선택할 수 있습니다:
- **성능이 중요한 경우:** `HashSet`이나 `TreeSet`을 사용하는 것이 바람직합니다.
- **순서를 유지해야 하는 경우:** `LinkedHashSet`을 사용할 수 있습니다.
- **코드의 간결성이 중요한 경우:** `Stream API`를 사용하는 것이 좋습니다.

각 방법의 장단점을 고려하여 적절한 방법을 선택할 수 있습니다.
