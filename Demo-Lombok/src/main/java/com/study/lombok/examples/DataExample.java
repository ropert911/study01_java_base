package com.study.lombok.examples;

import lombok.*;

//会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
//@Data集合了@ToString、@EqualsAndHashCode、@Getter/@Setter、@RequiredArgsConstructor的所有特性
@Data
@Builder    //支持build
public class DataExample {
    @NonNull        //非空
    private final String name;      //final没有set方法，只有build可用
    @Setter(AccessLevel.PACKAGE)  //只支持包内设置
    private int age;
    private double score;
    private String[] tags;

    @ToString(includeFieldNames = true)

    @Data(staticConstructor = "of")
    public static class Exercise<T> {
        private final String name;
        private final T value;
    }
}