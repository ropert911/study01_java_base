package com.xq.study.jdk.流Stream;

import com.xq.study.jdk.model.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by xq on 2019/2/24.
 */
public class StreamTest {
    public static void main(String[] args) {
        StreamTest t = new StreamTest();
        t.test1();      //filter,map,skip,limit,sort 等中间操作
        t.test2();      //forEach, collect

        t.test3();      //anyMatch,allMath, noneMathc等结果判断操作
        t.test4();      //findAny,findFirst 元素查找
        t.reduce();     //reduce: 它不适合做原始类型的操作 int,double等

        t.test6();      //原始流物化成 IntStream,DoubleStream等，从而方便做基本数据类型的操作 最大，最小等
        t.test7();      //生成流

        t.test8();      //分组， 规约, 汇总：求和，平均，计数，最大，最小，连接，
        t.test9();      //分区：只两个分组的分组功能

        //分治合并框架：ForkJoinTask(分治合并,这和Stream里的并行一样)
        //相应的还有 Spliterator 任务拆分，这里没有给示例
        t.test10(10);

    }

    //中间操作
    void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).sorted().forEach(System.out::println);
        numbers.stream().filter(i -> i % 2 == 0).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        List<Dish> menu = new ArrayList<>();
        List<String> dishNames = menu.stream()
                .filter(Dish::isVegetarian)
                .skip(2)                    //路过前2个
                .limit(3)                   //截断
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .map(Dish::getName)         //映射
                .distinct()                 //去重
                .collect(toList());

        //flatMap，流的扁平化 (flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流)
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))  //变成Stream<String[]>
                        .flatMap(Arrays::stream)        //变成Stream<String>
                        .distinct().collect(toList());
    }

    //终端操作
    void test2() {
        //forEach操作
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().forEach(System.out::println);  //numbers.forEach(System.out::println);

        //计数
        numbers.stream().count();

        //转 collection
        List<Dish> menu = new ArrayList<>();
        List<Dish> dishNames = menu.stream().collect(toList());
    }

    void test3() {
        List<Dish> menu = new ArrayList<>();

        //anyMatch: 至少匹配一个元素
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        //allMatch 检查是否所有都匹配
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        //noneMatch 检查所有都不匹配
        isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    void test4() {
        List<Dish> menu = new ArrayList<>();

        //findAny: 查找到任一个元素
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();

        //findFirst ：找到第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
    }

    void reduce() {
        List<Integer> numbers = Arrays.asList(1, 3, 5);

        numbers.stream().reduce(0, (a, b) -> a + b);  //求和:第一个是初始值，第二个是BinaryOperator
        numbers.stream().reduce(0, Integer::sum);                   //求和： 用现成的函数 Integer::sum
        Optional<Integer> max = numbers.stream().reduce(Integer::max);      //最大
        numbers.stream().reduce(Integer::min);                              //最小
    }

    void test6() {
        //生成流
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        //生成流
        List<Dish> menu = new ArrayList<>();
        int calories = menu.stream().mapToInt(Dish::getCalories)    //物化成IntSteam
                .sum();
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        //转回对象流
        Stream<Integer> stream = intStream.boxed();

        menu.stream().mapToInt(Dish::getCalories).sum();
        menu.stream().mapToInt(Dish::getCalories).max();
        menu.stream().mapToInt(Dish::getCalories).min();
        menu.stream().mapToInt(Dish::getCalories).average();

        System.out.println("======================");
        Stream<double[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.limit(5).forEach(t ->
                System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    void test7() {
        //空流
        Stream<String> emptyStream = Stream.empty();

        //由值创建流
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //由数据创建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //由文件生成流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
        }

        //由函数生成流
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
        IntStream twos = IntStream.generate(() -> {
            return 2;
        });
    }

    void test8() {
        List<Dish> menu = new ArrayList<>();
        //计数
        Long count = menu.stream().collect(Collectors.counting());

        //最大,最小值
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories).reversed();
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

        //和 平均
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));    //和
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories)); //平均

        //连接字串，每个 tostring后连接
        String j1 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String j2 = menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
        String j3 = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        //上面的规约都可以用一般的reduce方式实现：
        //1：初始值 2：转换方式  3：reduce方式
        int r1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        int r2 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));


        //分组功能
        Map<String, List<Dish>> t1 = menu.stream().collect(Collectors.groupingBy(Dish::getName));
        //二级分组
        Map<Integer, Map<Integer, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return 1;
                                    } else if (dish.getCalories() <= 700) {
                                        return 2;
                                    } else {
                                        return 3;
                                    }
                                })
                        )
                );
        //处理子组
        Map<Integer, Long> typesCount = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.counting()));
        Map<Integer, Optional<Dish>> mostCaloricByType = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    void test9() {
        List<Dish> menu = new ArrayList<>();

        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes =
                menu.stream().filter(Dish::isVegetarian).collect(toList());

        //产生如：{false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]},
        Map<Boolean, Map<Integer, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.groupingBy(Dish::getType)));

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                                        Optional::get)));
    }

    class ForkJoinSumCalculator extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int start;
        private final int end;
        public static final long THRESHOLD = 10_000;

        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers, 0, numbers.length);
        }

        private ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                return computeSequentially();
            }
            ForkJoinSumCalculator leftTask =
                    new ForkJoinSumCalculator(numbers, start, start + length / 2);
            leftTask.fork();
            ForkJoinSumCalculator rightTask =
                    new ForkJoinSumCalculator(numbers, start + length / 2, end);
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return leftResult + rightResult;
        }

        private long computeSequentially() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        }
    }

    long test10(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
