package com.foo.app.concurrent;

import com.foo.app.concurrent.domian.Consumer;
import com.foo.app.concurrent.domian.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-9
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        //创建阻塞队列
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1024);
        //创建生产者
        Producer producer = new Producer(blockingQueue);
       //创建消费者
        Consumer consumer = new Consumer(blockingQueue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
