package main.java.com.foo.app.concurrent;

/**
 * ConcurrentHashMap 使用例子
 * User: zongjh
 * Date: 13-6-6
 * Time: 下午3:04
 * <p>
 *     ConcurrentHashMap可以读取不加锁，其内部结构可以让其在进行写操作时候能够将锁保持尽量地小，
 *     不需要对整个ConcurrentHashMap加锁；
 *
 *     ConcurrentHashMap为了提高本身的并发能力，在内部采用了一个叫做Segment(段)的结构，
 *     一个Segment其实就是一个类HashTable的结构，
 *     Segment内部维护了一个链表数组，我们用下面这一幅图来看下ConcurrentHashMap的内部结构：
 *
 *
 * </p>
 */
public class ConcurrentHashMapDemo {


}
