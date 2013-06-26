package com.foo.app.designPatterns.callback;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-26
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class B {
    public void execute(CallBack action){  //【背景3】
        getConnection();
        action.execute("a","b");  //【i call you back】
        releaseConnection();
    }

    public void getConnection(){
        System.out.println("获得连接...");
    }

    public void releaseConnection(){
        System.out.println("释放连接...");
    }
}
