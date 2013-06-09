package com.foo.app.concurrent.domian;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-9
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class Consumer implements Runnable {
    private BlockingQueue blockingQueue;
    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;

    }
    @Override
    public void run() {
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
