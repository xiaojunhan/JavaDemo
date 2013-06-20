package com.foo.app.rpc.impl;

import com.foo.app.rpc.ServicesHandler;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-20
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class HelloHandler implements ServicesHandler {
    @Override
    public String execute(String str) {
        return "hello "+str+"!";
    }
}
