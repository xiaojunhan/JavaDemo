package com.foo.app.concurrent.domian;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-9
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public class Producer implements Runnable{
    private BlockingQueue blockingQueue;
    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;

    }

    @Override
    public void run() {
        try {
            blockingQueue.put("1");
            Thread.sleep(1000);
            blockingQueue.put("2");
            Thread.sleep(1000);
            blockingQueue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
