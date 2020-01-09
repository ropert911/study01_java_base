package com.xq.study.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式进行字符串提取，正则表达式可看百度说明
 *
 * @author xq
 * @data 2020/1/9
 **/
public class PatternTest {
    public static void main(String[] args) {
        {
            String text = "链接：             https://pan.baidu.com/s/14Ohd4jLuMWLRtqIt6eUNKg\n" +
                    "提取码：    5dlw\n" +
                    "复制这段内容后打开百度网盘手机App，操作更方便哦";
            String str = getString("链接：\\s*(http.*)", text);
            System.out.println(str);
            str = getString("提取码：\\s*(.*)", text);
            System.out.println(str);
        }
        {
            String text = "0.15%|0.16%";
            List<String> r = getmutiString("([0-9,\\.]+).*([0-9,\\.]+)", text);
            System.out.println(r);
        }
        {
            String text = "1.50%（每年";
            String r = getString("([0-9,\\.]+)%\\（每年", text);
            System.out.println(r);
        }
        {
            String text = "<tr><td class=\"th\">---</td><td>小于等于6天</td><td>1.50%</td></tr>";
            List<String> r = getmutiString("<td>(.+)</td><td>([0-9,\\.]+)%", text);
            System.out.println(r);
        }

        {
            String text = "0.08%|0.09%";
            List<String> r = getmutiString("([0-9,\\.]+)%\\|([0-9,\\.]+)%", text);
            System.out.println(r);
        }
    }

    public static String getString(String pstr, String text) {
        final Pattern pattern = Pattern.compile(pstr);
        Matcher m = pattern.matcher(text);
        String str = "";
        if (m.find()) {
            str = m.group(1);
        }
        return str;
    }

    public static List<String> getmutiString(String pstr, String text) {
        final Pattern pattern = Pattern.compile(pstr);
        Matcher m = pattern.matcher(text);
        List<String> r = new ArrayList<>();
        if (m.find()) {
            int n = m.groupCount();
            String str = "";
            for (int i = 1; i <= n; ++i) {
                str = m.group(i);
                r.add(str);
            }
        }
        return r;
    }
}
