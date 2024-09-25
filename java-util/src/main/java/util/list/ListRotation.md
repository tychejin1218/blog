# [JAVA] 리스트 회전하기

자바에서는 리스트를 회전시키는 다양한 방법들이 있습니다. 이 문서에서는 `Collections.rotate()` 메서드, 전통적인 for 문, 스트림 API, 헬퍼 메서드를 사용하는 방법을 살펴보겠습니다.

## 1. Collections.rotate() 메서드를 사용하여 리스트를 회전

`Collections.rotate()` 메서드를 사용하면 간편하게 리스트를 회전시킬 수 있습니다.

다음은 `Collections.rotate()` 메서드를 사용한 방법입니다:

```java
package util.list;

import java.util.Collections;
import java.util.List;

public class ListRotation {
  /**
   * Collections.rotate() 메서드를 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithCollections(List<T> list, int k) {
    Collections.rotate(list, k);
    return list;
  }
}
```

### **장점:**
- **간편함:** 한 줄의 코드로 리스트를 회전시킬 수 있습니다.

### **단점:**
- **명확성:** 내부에서 어떤 일이 벌어지는지 명확하지 않을 수 있습니다.

## 2. 반복문을 사용하여 리스트를 회전

전통적인 for 문을 사용하여 리스트를 회전하는 방법은 직관적이고 이해하기 쉽습니다.

다음은 반복문을 사용한 방법입니다:

```java
package util.list;

import java.util.ArrayList;
import java.util.List;

public class ListRotation {
  /**
   * 반복문을 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithLoop(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    k = k % n;
    List<T> rotated = new ArrayList<>(n);

    for (int i = 0; i < n; i++) {
      rotated.add(null);
    }

    for (int i = 0; i < n; i++) {
      rotated.set((i + k) % n, list.get(i));
    }

    return rotated;
  }
}
```

### **장점:**
- **직관성:** 단순하고 이해하기 쉽습니다.

### **단점:**
- **코드 길이:** 리스트의 길이가 길어질수록 코드가 복잡해질 수 있습니다.

## 3. 스트림 API를 사용하여 리스트를 회전

Java 8부터 도입된 스트림 API를 사용하면 더 간결하고 함수형 프로그래밍 스타일의 코드를 작성할 수 있습니다.

다음은 스트림 API를 사용한 방법입니다:

```java
package util.list;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListRotation {
  /**
   * 스트림을 사용하여 리스트를 회전
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithStreams(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    int shift = k % n;

    return IntStream.range(0, n)
        .mapToObj(i -> list.get((i + (n - shift)) % n))
        .collect(Collectors.toList());
  }
}
```

### **장점:**
- **가독성:** 코드가 매우 간결하고 읽기 쉽습니다.
- **함수형 프로그래밍:** 최신 자바에서 선호되는 함수형 프로그래밍 패러다임을 활용합니다.

### **단점:**
- **성능 오버헤드:** 스트림은 약간의 성능 오버헤드를 수반할 수 있습니다.

## 4. 헬퍼 메서드를 사용하여 리스트를 회전

헬퍼 메서드를 사용하여 리스트를 회전시키는 방법은 리스트를 역순으로 뒤집어 회전 효과를 얻을 수 있습니다.

다음은 헬퍼 메서드를 사용한 방법입니다:

```java
package util.list;

import java.util.List;
import java.util.stream.Collectors;

public class ListRotation {
  /**
   * 헬퍼 메서드를 사용하여 리스트를 회전시킵니다. 리스트를 역순으로 뒤집는 방법을 사용
   *
   * @param list 회전할 리스트
   * @param k    회전할 위치 수
   * @return 회전된 리스트
   */
  public <T> List<T> rotateWithHelperMethods(List<T> list, int k) {
    int n = list.size();
    if (n == 0) {
      return list;
    }
    k = k % n;

    List<T> result = list.stream().collect(Collectors.toList());
    reverse(result, 0, n - 1);
    reverse(result, 0, k - 1);
    reverse(result, k, n - 1);

    return result;
  }

  /**
   * 리스트의 일부를 뒤집는 헬퍼 메서드
   *
   * @param list  회전할 리스트
   * @param start 뒤집을 시작 인덱스
   * @param end   뒤집을 끝 인덱스
   */
  private <T> void reverse(List<T> list, int start, int end) {
    while (start < end) {
      T temp = list.get(start);
      list.set(start, list.get(end));
      list.set(end, temp);
      start++;
      end--;
    }
  }
}
```

### **장점:**
- **효율성:** 추가 리스트 저장소 없이 리스트 회전이 가능합니다.

### **단점:**
- **복잡성:** 세 단계로 리스트를 뒤집어야 하므로 직관적이지 않을 수 있습니다.

## 결론

결론적으로, 자바에서 리스트 회전을 구현하는 방법은 다양합니다. 상황에 따라 가장 적합한 방법을 선택하여 사용할 수 있습니다. 일반적으로 `Collections.rotate()` 메서드는 간편함을 제공하며, 전통적인 for 문도 여전히 널리 사용됩니다. 더 복잡한 회전 로직이 필요한 경우 헬퍼 메서드를 사용하는 것이 효율적일 수 있습니다.
