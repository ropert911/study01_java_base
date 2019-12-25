package com.xq.study.javax_script;

import javax.script.*;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static ScriptEngine getJavaScriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
//        manager.getEngineByExtension("js");
//        manager.getEngineByMimeType("text/javascript");
        if (engine == null) {
            throw new RuntimeException("找不到JavaScript语言执行环境");
        }

        return engine;
    }

    public static void excuteJs() {
        try {
            ScriptEngine engine = getJavaScriptEngine();
            engine.eval("print ('Hello!');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void paramTest() {
        //参数绑定
        try {
            ScriptEngine engine = getJavaScriptEngine();
            //参数绑定方式
            engine.put("name", "Alex");
            engine.eval("var message = 'Hello,' + name");
            engine.eval("print (message);");
            Object obj = engine.get("message");
            System.out.println(obj);

            //自定义绑定方式
            Bindings bindings = new SimpleBindings();
            bindings.put("hobby", "eating");
            engine.eval("print ('I like ' + hobby);", bindings);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void contextTest() {
        try {
            //脚本执行上下文
            ScriptEngine engine = getJavaScriptEngine();

            //输入输出
            ScriptContext context = engine.getContext();
            context.setWriter(new FileWriter("output.txt"));
//                engine.eval("print (message);");

            //自定义属性
            context.setAttribute("name", "Alex", ScriptContext.GLOBAL_SCOPE);
            context.setAttribute("name", "Bob", ScriptContext.ENGINE_SCOPE);
            System.out.println(context.getAttribute("name"));

            //绑定
            Bindings bindings1 = engine.createBindings();
            bindings1.put("name", "Alex");
            context.setBindings(bindings1, ScriptContext.GLOBAL_SCOPE);

            Bindings bindings2 = engine.createBindings();
            bindings2.put("name", "Bob");
            context.setBindings(bindings2, ScriptContext.ENGINE_SCOPE);

            engine.eval("print (name);");
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CompiledScript compile(String sciptTest) throws ScriptException {
        ScriptEngine engine = getJavaScriptEngine();
        if (engine instanceof Compilable) {
            CompiledScript script = ((Compilable) engine).compile(sciptTest);
            return script;
        }
        return null;
    }

    public static void compileTest() {
        String scipTest = "print ('xxxx');";
        try {
            CompiledScript compiledScript = compile(scipTest);
            if (compiledScript != null) {
                for (int i = 0; i < 5; i++) {

                    compiledScript.eval();

                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void funcTest() {

        try {
            ScriptEngine engine = getJavaScriptEngine();
            String scripText = "function gree(name){print ('Hello ' + name)}";
            engine.eval(scripText);
            Invocable invocable = (Invocable) engine;

            invocable.invokeFunction("gree", "肖迁");

        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void objfunTest() {
        try {
            ScriptEngine engine = getJavaScriptEngine();

            String scripText = "var obj = {getGreeting: function(name){print ('Hello ' + name)}};";
            engine.eval(scripText);

            Invocable invocable = (Invocable) engine;
            Object scope = engine.get("obj");
            Object result = invocable.invokeMethod(scope, "getGreeting", "xiaoqian");
//            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void interfaceTest() {
        try {
            ScriptEngine engine = getJavaScriptEngine();
            String scripText = "function gree(name){print ('Hello ' + name)}";
            engine.eval(scripText);

            Invocable invocable = (Invocable) engine;
            Greet abc = invocable.getInterface(Greet.class);
            abc.gree("使用java接口");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        excuteJs();
        //参数
        paramTest();
        //上下文
        contextTest();

        //编译后执行
        compileTest();

        //方法调用
        funcTest();

        //调用对接中的方法
        objfunTest();

        //使用java接口，调用js实现
        interfaceTest();
    }
}
