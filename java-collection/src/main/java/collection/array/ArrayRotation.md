# [JAVA] 배열 회전하기

자바에서는 배열을 회전시키는 여러 가지 방법이 있습니다. 전통적인 for 문, 스트림 API, 그리고 헬퍼 메서드를 사용하는 방법이 있습니다.

## 1. 반복문을 사용하여 배열을 회전

전통적인 for 문를 사용하여 배열을 회전하는 방법은 직관적이고 간단합니다.

다음은 전통적인 for 문를 사용한 방법입니다:

```java
/**
 * 반복문을 사용하여 배열을 회전
 *
 * @param nums 회전할 배열
 * @param k    회전할 위치 수
 * @return 회전된 배열
 */
public int[] rotateWithLoop(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
        return nums;
    }
    k = k % n;
    int[] rotated = new int[n];

    for (int i = 0; i < n; i++) {
        rotated[(i + k) % n] = nums[i];
    }

    return rotated;
}
```

### **장점:**
- **직관성:** 단순하고 이해하기 쉽습니다.

### **단점:**
- **코드 길이:** 더 복잡한 경우 코드가 길어질 수 있습니다.

## 2. 스트림 API를 사용하여 배열을 회전

Java 8부터 도입된 스트림 API를 사용하면 더 간결하고 함수형 프로그래밍 스타일의 코드를 작성할 수 있습니다.

다음은 스트림 API를 사용한 방법입니다:

```java
import java.util.stream.IntStream;

/**
 * 스트림을 사용하여 배열을 회전
 *
 * @param nums 회전할 배열
 * @param k    회전할 위치 수
 * @return 회전된 배열
 */
public int[] rotateWithStreams(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
        return nums;
    }
    k = k % n;
    final int finalK = k;

    return IntStream.range(0, n)
            .map(i -> nums[(i + (n - finalK)) % n])
            .toArray();
}
```

### **장점:**
- **가독성:** 코드가 매우 간결하고 읽기 쉽습니다.
- **함수형 프로그래밍:** 최신 자바에서 선호되는 함수형 프로그래밍 패러다임을 활용합니다.

### **단점:**
- **성능 오버헤드:** 스트림은 약간의 성능 오버헤드를 수반할 수 있습니다.

## 3. 헬퍼 메서드를 사용하여 배열을 회전

헬퍼 메서드를 사용하여 배열을 회전시키는 방법은 배열을 역순으로 뒤집어 회전 효과를 얻을 수 있습니다.

다음은 헬퍼 메서드를 사용한 방법입니다:

```java
/**
 * 헬퍼 메서드를 사용하여 배열을 회전시킵니다. 배열을 역순으로 뒤집는 방법을 사용
 *
 * @param nums 회전할 배열
 * @param k    회전할 위치 수
 * @return 회전된 배열
 */
public int[] rotateWithHelperMethods(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
        return nums;
    }
    k = k % n;

    int[] result = nums.clone();
    reverse(result, 0, n - 1);
    reverse(result, 0, k - 1);
    reverse(result, k, n - 1);

    return result;
}

/**
 * 배열의 일부를 뒤집는 헬퍼 메서드
 *
 * @param nums  회전할 배열
 * @param start 뒤집을 시작 인덱스
 * @param end   뒤집을 끝 인덱스
 */
private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```

### **장점:**
- **효율성:** 추가 배열 저장소 없이 배열 회전이 가능합니다.

### **단점:**
- **복잡성:** 세 단계로 배열을 뒤집어야 하므로 직관적이지 않을 수 있습니다.

## 결론

결론적으로, 자바에서 배열 회전을 구현하는 방법은 다양합니다. 상황에 따라 가장 적합한 방법을 선택하여 사용할 수 있습니다. 일반적으로 스트림 API는 간결성과 가독성 측면에서 많이 선호되며, 전통적인 for 문 또한 여전히 널리 사용됩니다. 더 복잡한 회전 로직이 필요한 경우 헬퍼 메서드를 사용하는 것이 효율적일 수 있습니다.
