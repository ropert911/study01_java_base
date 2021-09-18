package com.study.base.数据处理_stream;

import com.study.base.数据处理_stream.model.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by xq on 2019/2/24.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("示例：生成流");
        test7();
        System.out.println("");

        System.out.println("示例：IntStream,DoubleStream等，从而方便做基本数据类型的操作 最大，最小等");
        test6();
        System.out.println("");

        System.out.println("示例：filter,map,skip,limit,sort 等中间操作");
        test1();
        System.out.println("");

        System.out.println("示例：anyMatch,allMath, noneMathc,findAny,findFirst等结果判断操作,findAny,findFirst 元素查找");
        test3();
        System.out.println("");


        System.out.println("示例：分组， 规约, 汇总：求和，平均，计数，最大，最小，连接");
        test8();
        System.out.println("");

        test10();
    }

    /**
     * 生成流
     */
    public static void test7() {
        //空流
        Stream<String> emptyStream = Stream.empty();

        //由值创建流
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        System.out.println("Stream.of->Stream<String>：" + stream.map(String::toUpperCase).collect(toList()));


        //由数据创建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        List<Double> b = Arrays.stream(numbers).mapToDouble(a -> (double) a).boxed().collect(toList());
        System.out.println(" Arrays.stream->IntStream：" + b);

        //由文件生成流
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\sk-qianxiao\\Desktop\\常用操作.txt"), Charset.defaultCharset())) {
            System.out.println(" Files.lines->Stream<String> 第一行：" + lines.findFirst());
        } catch (IOException e) {
        }

        //由函数生成流
        System.out.println("Stream.iterate函数生成=>" + Stream.iterate(0, n -> n + 2).limit(10).collect(toList()));
        System.out.println("Stream.generate随机生成=>" + Stream.generate(Math::random).limit(5).collect(toList()));
        IntStream twos = IntStream.generate(() -> {
            return 2;
        });
        System.out.println("IntStream.generate指定生成函数生成=>" + twos.limit(2).boxed().collect(toList()));
    }

    /**
     * 原始流物化成 IntStream,DoubleStream等，从而方便做基本数据类型的操作 最大，最小等
     */
    public static void test6() {
        //生成流
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println("count计数：" + evenNumbers.count());

        List<Dish> menu = getDishes();

        //流化成IntSteam后，再转成 Stream<Integer>
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
//        DoubleStream doubleStream = intStream.mapToDouble(a->(double)a);
//        Stream<Integer> stream = intStream.boxed();

        System.out.println("IntStream后 求和：" + menu.stream().mapToInt(Dish::getCalories).sum());
        System.out.println("IntStream后 最大：" + menu.stream().mapToInt(Dish::getCalories).max());
        System.out.println("IntStream后 最小：" + menu.stream().mapToInt(Dish::getCalories).min());
        System.out.println("IntStream后 平均：" + menu.stream().mapToInt(Dish::getCalories).average());

        Stream<double[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 5).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 5).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples.skip(2).limit(3).forEach(t -> System.out.println("勾股数：" + t[0] + ", " + t[1] + ", " + t[2]));
    }

    /**
     * filter,map,skip,limit,sort 等中间操作
     */
    public static void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        System.out.print("原序输出： ");
        numbers.stream().filter(i -> i % 2 == 0).sorted().forEach(a -> System.out.print(a + " "));
        System.out.println("");
        System.out.print("反序输出： ");
        numbers.stream().filter(i -> i % 2 == 0).sorted(Comparator.reverseOrder()).forEach(a -> System.out.print(a + " "));
        System.out.println("");
        System.out.println("计数：" + numbers.stream().count());

        //flatMap，流的扁平化 (flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流)
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters =
                words.stream()
                        //变成Stream<String[]>
                        .map(w -> w.split(" "))
                        //变成Stream<String>
                        .flatMap(Arrays::stream)
                        .distinct().collect(toList());
        System.out.print("flatMap后输出： ");
        uniqueCharacters.forEach(a -> System.out.print(" " + a));
        System.out.println("");
    }


    public static List<Dish> getDishes() {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish(1, 1, "葡萄"));
        menu.add(new Dish(1, 1, "苹果"));
        menu.add(new Dish(1, 1, "梨"));
        menu.add(new Dish(2, 2, "白菜"));
        menu.add(new Dish(2, 2, "萝卜"));
        return menu;
    }

    /**
     * anyMatch,allMath, noneMathc等结果判断操作,findAny,findFirst 元素查找
     */
    public static void test3() {
        List<Dish> menu = getDishes();

        //anyMatch: 至少匹配一个元素
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("列表里有蔬菜");
        }
        //allMatch 检查是否所有都匹配
        if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("所有的都符合分类<1000");
        }
        //noneMatch 检查所有都不匹配
        if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("没有一个符合分类>=1000");
        }

        //findAny: 查找到任一个元素
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        if (dish.isPresent()) {
            System.out.println("findAny找到任一个：" + dish.get().getName());
        }

        //findFirst ：找到第一个元素
        dish = menu.stream().filter(Dish::isVegetarian).findFirst();
        if (dish.isPresent()) {
            System.out.println("findFirst第一个元素：" + dish.get().getName());
        }
    }


    /**
     * 分组， 规约, 汇总：求和，平均，计数，最大，最小，连接
     */
    public static void test8() {
        List<Dish> menu = getDishes();

        //数学型的：最大，最小，求和，平均
        Long count = menu.stream().collect(Collectors.counting());
        System.out.println("计数 Collectors.counting：" + count);
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories).reversed();
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        if (mostCalorieDish.isPresent()) {
            System.out.println("最大 Collectors.maxBy：" + mostCalorieDish.get().getName());
        }
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("和 Collectors.summingInt：" + totalCalories);
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println("平均 Collectors.averagingInt：" + avgCalories);

        //连接字串，每个 tostring后连接
        String j1 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println("字符拼接 Collectors.joining：" + j1);
        String j2 = menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
        System.out.println("字符拼接 Collectors.reducing：" + j2);
        String j3 = menu.stream().collect(Collectors.reducing("", Dish::getName, String::join));
        System.out.println("字符拼接 Collectors.reducing指定段：" + j3);
        String j4 = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        System.out.println("字符拼接 Collectors.reducing指定段：" + j4);

        //分组功能
        Map<Integer, List<Dish>> t1 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println("分组 Collectors.groupingBy：" + t1.keySet());
        Map<Integer, Long> typesCount = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println("分组 Collectors.groupingBy分组后计数：" + typesCount.values());
        Map<Integer, Optional<Dish>> mostCaloricByType = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("分组 Collectors.groupingBy分组后求最大：" + mostCaloricByType.values());
        //partitioningBy
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(a -> a.getType() >= 1));
        System.out.println("分组 Collectors.partitioningBy中分成2个分组：" + partitionedMenu.keySet());

        //reduce操作
        List<Integer> numbers = Arrays.asList(1, 3, 5);
        System.out.println("Reduce求和：" + numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println("Reduce求和：" + numbers.stream().reduce(0, Integer::sum));
        System.out.println("Reduce最大：" + numbers.stream().reduce(Integer::max).get());
        System.out.println("Reduce最小：" + numbers.stream().reduce(Integer::min).get());
    }

    /**
     * 分治合并框架：ForkJoinTask(分治合并,这和Stream里的并行一样)
     * 相应的还有 Spliterator 任务拆分，这里没有给示例
     *
     * @return
     */
    public static void test10() {
        long n = 10;
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        new ForkJoinPool().invoke(task);
    }
}
