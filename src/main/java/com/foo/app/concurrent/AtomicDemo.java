package com.foo.app.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资料参考：并发编程网  ifeve.com
 * 原子（atom）本意是“不能被进一步分割的最小粒子”，
 * 而原子操作（atomic operation）意为”不可被中断的一个或一系列操作” 。
 * User: zongjh
 * Date: 13-6-7
 * Time: 下午4:47
 *  术语定义
 术语名称	英文	解释
 缓存行	Cache line	缓存的最小操作单位
 比较并交换	Compare and Swap	CAS操作需要输入两个数值，一个旧值（期望操作前的值）和一个新值，在操作期间先比较下在旧值有没有发生变化，如果没有发生变化，才交换成新值，发生了变化则不交换。
 CPU流水线	CPU pipeline	CPU流水线的工作方式就象工业生产上的装配流水线，在CPU中由5~6个不同功能的电路单元组成一条指令处理流水线，然后将一条X86指令分成5~6步后再由这些电路单元分别执行，
                           这样就能实现在一个CPU时钟周期完成一条指令，因此提高CPU的运算速度。
 内存顺序冲突	Memory order violation	内存顺序冲突一般是由假共享引起，假共享是指多个CPU同时修改同一个缓存行的不同部分而引起其中一个CPU的操作无效，当出现这个内存顺序冲突时，CPU必须清空流水线。
 处理器如何实现原子操作
 32位IA-32处理器使用基于对缓存加锁或总线加锁的方式来实现多处理器之间的原子操作。
 处理器自动保证基本内存操作的原子性
 使用总线锁保证原子性  所谓总线锁就是使用处理器提供的一个LOCK＃信号，当一个处理器在总线上输出此信号时，
                   其他处理器的请求将被阻塞住,那么该处理器可以独占使用共享内存。

 使用缓存锁保证原子性
     在同一时刻我们只需保证对某个内存地址的操作是原子性即可，但总线锁定把CPU和内存之间通信锁住了，这使得锁定期间，
     其他处理器不能操作其他内存地址的数据，所以总线锁定的开销比较大，
     最近的处理器在某些场合下使用缓存锁定代替总线锁定来进行优化。

     所谓“缓存锁定”就是如果缓存在处理器缓存行中内存区域在LOCK操作期间被锁定，
     当它执行锁操作回写内存时，处理器不在总线上声言LOCK＃信号，而是修改内部的内存地址，
     并允许它的缓存一致性机制来保证操作的原子性，因为缓存一致性机制会阻止同时修改被两个以上处理器缓存的内存区域数据，
     当其他处理器回写已被锁定的缓存行的数据时会起缓存行无效，
     在例1中，当CPU1修改缓存行中的i时使用缓存锁定，那么CPU2就不能同时缓存了i的缓存行。
 */
public class AtomicDemo {

    private AtomicInteger atomicInteger = new AtomicInteger();
    private int i=0;
    public static void main(String[] args){
        final AtomicDemo counter = new AtomicDemo();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        counter.count();
                        counter.safeCount();

                    }
                }
            });

            ts.add(t);

        }

        for(Thread thread:ts){
            thread.start();
        }

        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicInteger.get());

        System.out.println(System.currentTimeMillis() - start);


        }

    private void safeCount() {
        for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }


    }

    private void count() {
        i++;

    }

}
