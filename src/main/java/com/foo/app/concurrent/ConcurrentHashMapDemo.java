package com.foo.app.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 资料参考：并发编程网  ifeve.com
 * ConcurrentHashMap 使用例子
 * User: zongjh
 * Date: 13-6-6
 * Time: 下午3:04
 *
 *     ConcurrentHashMap可以读取不加锁，其内部结构可以让其在进行写操作时候能够将锁保持尽量地小，
 *     不需要对整个ConcurrentHashMap加锁；
 *
 *     ConcurrentHashMap为了提高本身的并发能力，在内部采用了一个叫做Segment(Segment是一种可重入锁ReentrantLock)的结构，
 *     一个Segment里包含一个HashEntry数组，每个HashEntry是一个链表结构的元素， 每个Segment守护者一个HashEntry数组里的元素,
 *     当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
 *
 *     从ConcurrentHashMap定位一个元素的过程需要进行两次Hash操作，第一次Hash定位到Segment，第二次Hash定位
 *     到元素所在链表的头部，因此，这种结构的带来副作用就是Hash的过程要比普通的HashMap要长，带来的好处就是
 *     对元素所在的Segment进行加锁即可，不会影响别的Segment。
 *
 *      下面是段的数据结构：
 *           <pre>
 *              static final class Segment<K,V> extends ReentrantLock implements Serializable {
                    transient volatile int count;
                    transient int modCount;
                    transient int threshold;
                    transient volatile HashEntry<K,V>[] table;
                    final float loadFactor;
                }
 *          </pre>
 *          详细解释一下Segment里面的成员变量的意义：

                count：Segment中元素的数量
                modCount：对table的大小造成影响的操作的数量（比如put或者remove操作）
                threshold：阈值，Segment里面元素的数量超过这个值依旧就会对Segment进行扩容
                table：链表数组，数组中的每一个元素代表了一个链表的头部
                loadFactor：负载因子，用于确定threshold
 *     HashEntry
 *     Segment中的元素是以HashEntry的形式存放在链表数组中的，看一下HashEntry的结构：
 *        <pre>
 *          static final class HashEntry<K,V> {
 *              final K key;
 *              final int hash;
 *              volatile V value;
 *              final HashEntry<K,V> next;
 *           }
 *        </pre>
 *     ConcurrentHashMap的初始化一共有三个参数，一个initialCapacity，表示初始的容量，一个loadFactor，表示负载参数，
 *     最后一个是concurrentLevel，代表ConcurrentHashMap内部的Segment的数量，
 *     ConcurrentLevel一经指定，不可改变，后续如果ConcurrentHashMap的元素数量增加导致ConrruentHashMap需要扩容，
 *     ConcurrentHashMap不会增加Segment的数量，而只会增加Segment中链表数组的容量大小，
 *     这样的好处是扩容过程不需要对整个ConcurrentHashMap做rehash，
 *     而只需要对Segment里面的元素做一次rehash就可以了。
 *
 *     整个ConcurrentHashMap的初始化方法还是非常简单的，先是根据concurrentLevel来new出Segment，
 *     这里Segment的数量是不小于concurrentLevel的最大的2的指数，就是说Segment的数量永远是2的指数个，
 *     这样的好处是方便采用移位操作来进行hash，加快hash的过程。接下来就是根据intialCapacity确定Segment的容量的大小，
 *     每一个Segment的容量大小也是2的指数，同样使为了加快hash的过程。
 *
 *
 *     需要特别注意下他们get put remove size 的实现原理；
 *
 *
 *     下面说明下， Hashtable、synchronizedMap、ConcurrentHashMap 的比较 #
 *          三者共同点：都是线程安全的，只是在效率上存在差异；
 *
 *          区别
 *          Hashtable(效率低下的HashTable容器) 因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，
 *                      可能会进入阻塞或轮询状态。
 *          Collections.synchronizedMap(同步的包装器)
 *
 *
 *
 */
public class ConcurrentHashMapDemo {

    /**
     * 线程不安全的HashMap
     * 因为多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap
     */

    public void putHashMap() throws InterruptedException {
        final Map<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "HashMap" + i).start();
                }
            }
        }, "HashMap");
        t.start();
        t.join();
    }


    public static void main(String[] args) throws InterruptedException {
        new ConcurrentHashMapDemo().putHashMap();
    }
}
