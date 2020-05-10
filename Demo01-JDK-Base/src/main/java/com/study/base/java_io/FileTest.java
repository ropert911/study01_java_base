package com.study.base.java_io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {
    private static Logger logger = LoggerFactory.getLogger(FileTest.class);

    public static void main(String[] args) throws Exception {
        System.out.println("分隔符：" + File.separator);
        System.out.println("分隔符：" + File.separatorChar);
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.pathSeparator);


        File file = new File("C:\\Users\\Administrator\\Desktop\\..\\Desktop\\a.txt");
        System.out.println("是否是绝对路径：" + file.isAbsolute());
        System.out.println("文件名：" + file.getName());
        System.out.println("路径：" + file.getPath());
        System.out.println("完整路径：" + file.getAbsolutePath());
        System.out.println("去掉..等的唯一完整路径：" + file.getCanonicalPath());
        System.out.println("toURI的路径：" + file.toURI());
        System.out.println("toURL的路径：" + file.toURL());
        System.out.println("canRead：" + file.canRead());        //setReadOnly,setReadable
        System.out.println("canWrite：" + file.canWrite());      //setWritable
        System.out.println("canExecute：" + file.canExecute());  //setExecutable setExecutable
        System.out.println("exists：" + file.exists());
        System.out.println("isDirectory：" + file.isDirectory());
        System.out.println("isFile：" + file.isFile());
        System.out.println("isHidden：" + file.isHidden());
        System.out.println("lastModified：" + file.lastModified());  //setLastModified
        System.out.println("length：" + file.length());
        System.out.println("toPath：" + file.toPath());
        file.mkdir();   //建目录，父目录必须有
        file.mkdirs();  //建目录，如果父目录没有，也建
        for (String f : file.list()) {
            System.out.println("list111：" + f);
        }
        FileFilter filenameFilter = new FileFilter();
        for (String f : file.list(filenameFilter)) {
            System.out.println("list2222：" + f);
        }
        for (File f : file.listFiles()) {
            System.out.println("listFiles111：" + f);
        }
        for (File f : file.listFiles(filenameFilter)) {
            System.out.println("listFiles222：" + f);
        }
        //看有哪些根目录可用
        for (File f : file.listRoots()) {
            System.out.println("listRoots：" + f);
        }
        System.out.println("delete：" + file.delete());
        System.out.println("getTotalSpace：" + file.getTotalSpace() / (1024 * 1024 * 1024) + "G");
        System.out.println("getFreeSpace：" + file.getFreeSpace() / (1024 * 1024 * 1024) + "G");
        System.out.println("getUsableSpace：" + file.getUsableSpace() / (1024 * 1024 * 1024) + "G");

        file.deleteOnExit();


        System.out.println("父目录：" + file.getParent());
        System.out.println("父目录的文件名方式：" + file.getParentFile().getName());
        System.out.println("文件名：" + file.getParentFile().getParent());
//        file.getPrefixLength();

        file = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        boolean res = file.createNewFile();
        if (!res) System.out.println("创建失败！");
        file.delete();

        //临时文件，有指定路径地址
        file = new File("C:\\Users\\Administrator\\Desktop\\");
        file.createTempFile("msg", ".tmp", file);
        //没有路径，会在路径会在 C:\Users\Administrator\AppData\Local\Temp
        File temp = file.createTempFile("log", ".tmp");
        System.out.println("临时路径:" + temp.getPath());
        temp.delete();

    }
}

class FileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        //String的 endsWith(String str)方法  筛选出以str结尾的字符串
        if (name.endsWith(".txt"))
            return true;
        return false;
    }
}