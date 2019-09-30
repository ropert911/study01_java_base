package com.xq.study.guava.demoguava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.junit.Test;

public class BasicUtilTest {
    /**
     * Optional的使用
     */
    @Test
    public void test1() {
        Optional<Integer> possible = Optional.of(5);
        /**判断是否存在*/
        possible.isPresent();
        /**返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException*/
        possible.get();
        /**返回Optional所包含的引用，若引用缺失，返回指定的值*/
        System.out.println(possible.or(8));
    }

    /**
     * 前置条件检查功能
     */
    @Test
    public void test2() {
        int i = 3, j = 4;
        /**检查boolean是否为true，用来检查传递给方法的参数*/
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        Preconditions.checkArgument(i < j, "Expected i < j, but %s > %s", i, j);

        /**检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull*/
        Integer abc = 1;
        Preconditions.checkNotNull(abc);

        /**用来检查对象的某些状态*/
        Preconditions.checkState(1 == 1);

        /**检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size **/
        Preconditions.checkElementIndex(1, 3);
        /**检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size */
        Preconditions.checkPositionIndex(1, 3);
        /**检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*/
        Preconditions.checkPositionIndexes(1, 3, 4);
    }

    /**
     * 常见Object方法
     */
    @Test
    public void test3() {
        /**JDK7引入的Objects类提供了一样的方法Objects.equals*/
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
    }
}
