package com.study.base.数据处理_容器container;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * Table 一种有两个key的HashMap
 *
 * @author sk-qianxiao
 * @date 2021/9/6
 */
public class HashBasedTableTests {
    public static void main(String[] args) {
        // 一批用户，同时按年龄和性别分组
        Table<Integer, String, String> table = HashBasedTable.create();
        table.put(18, "男", "yideng");
        table.put(18, "女", "Lily");
        // 输出 yideng
        System.out.println(table.get(18, "男"));

        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row(18);
        // 输出 {"男":"yideng","女":"Lily"}
        System.out.println(row);

        // 查看列数据
        Map<Integer, String> column = table.column("男");
        // 输出 {18:"yideng"}
        System.out.println(column);
    }
}
