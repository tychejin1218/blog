# [JAVA] 배열의 중복 요소 제거하기

자바에서는 배열의 중복 요소를 제거하는 여러 가지 방법이 있습니다. `HashSet`을 사용하는 방법, `Stream API`를 사용하는 방법, 그리고 중첩 루프를 사용하는 방법이 있습니다.

## 1. HashSet을 사용하여 중복 제거

`HashSet`을 사용하는 것은 배열의 중복 요소를 제거하는 가장 간단하고 효율적인 방법 중 하나입니다. `HashSet`은 중복 요소를 허용하지 않기 때문에 쉽게 중복을 처리할 수 있습니다.

다음은 `HashSet`을 사용하여 중복을 제거하는 메서드입니다:

```java
import java.util.HashSet;
import java.util.Set;

public class ArrayRemoveDuplicates {

  public static int[] removeDuplicatesUsingSet(int[] array) {
    if (array == null) {
      return null;
    }

    Set<Integer> set = new HashSet<>();
    for (int value : array) {
      set.add(value);
    }

    int[] result = new int[set.size()];
    int i = 0;
    for (int value : set) {
      result[i++] = value;
    }

    return result;
  }
}
```

### **장점:**
- **효율성:** 해시 테이블을 기반으로 하기 때문에 성능이 좋습니다.
- **간단함:** 코드가 이해하기 쉽고 구현하기 쉽습니다.

### **단점:**
- **순서 유지 불가:** 원래 배열의 순서가 유지되지 않습니다.

## 2. Stream API를 사용하여 중복 제거

Java 8은 `Stream API`를 도입하여 컬렉션과 배열을 처리하는 현대적이고 간결한 방법을 제공합니다. 스트림의 `distinct()` 메서드를 사용하여 중복을 제거할 수 있습니다.

다음은 `Stream API`를 사용한 방법입니다:

```java
import java.util.Arrays;

public class ArrayRemoveDuplicates {

  public static int[] removeDuplicatesUsingStream(int[] array) {
    if (array == null) {
      return null;
    }

    return Arrays.stream(array)
        .distinct()
        .toArray();
  }
}
```

### **장점:**
- **가독성:** 코드가 매우 간결하고 읽기 쉽습니다.
- **함수형 프로그래밍:** 최신 자바에서 선호되는 함수형 프로그래밍 패러다임을 활용합니다.

### **단점:**
- **성능 오버헤드:** 스트림은 약간의 성능 오버헤드를 수반하지만, 대부분의 경우 이는 무시할 수 있습니다.

## 3. 중첩 루프를 사용한 중복 제거 (비효율적)

중첩 루프를 사용하여 중복을 제거할 수도 있습니다. 앞서 소개한 방법들보다 비효율적입니다.

다음은 중첩 루프를 사용한 방법입니다:

```java
public class ArrayRemoveDuplicates {

  public static int[] removeDuplicatesUsingLoops(int[] array) {
    if (array == null) {
      return null;
    }

    int n = array.length;
    int[] temp = new int[n];
    int j = 0;

    for (int i = 0; i < n; i++) {
      boolean isDuplicate = false;
      for (int k = 0; k < j; k++) {
        if (array[i] == temp[k]) {
          isDuplicate = true;
          break;
        }
      }
      if (!isDuplicate) {
        temp[j++] = array[i];
      }
    }

    int[] result = new int[j];
    System.arraycopy(temp, 0, result, 0, j);

    return result;
  }
}
```

### **장점:**
- **순서 유지:** 원래 배열의 순서가 유지됩니다.

### **단점:**
- **비효율성:** 이 방법은 시간 복잡도가 O(n^2)로, 큰 데이터셋에는 적합하지 않습니다.

## 결론

결론적으로, 자바에서 배열의 중복을 제거해야 할 때, 일반적으로 `HashSet`이나 `Stream API`를 사용하는 것이 효율성과 간단함 때문에 가장 좋은 선택입니다. 중첩 루프는 순서를 유지해야 하는 특수한 경우에만 사용하는 것이 좋습니다.
