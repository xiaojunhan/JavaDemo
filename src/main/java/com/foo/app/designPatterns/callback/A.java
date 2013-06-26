package com.foo.app.designPatterns.callback;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-26
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
public class A implements CallBack {
    private B b = new B();
    @Override
    public void execute(String... args) {
        System.out.println("invoke A.execute args.length="+args.length);
    }
    public void add(){
        b.execute(this);//【you call me】
    }

    public static void main(String[] args){
        new A().add();
    }
}
