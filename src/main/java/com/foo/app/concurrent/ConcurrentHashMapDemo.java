package com.foo.app.concurrent;

/**
 * ConcurrentHashMap 使用例子
 * User: zongjh
 * Date: 13-6-6
 * Time: 下午3:04
 *
 *     ConcurrentHashMap可以读取不加锁，其内部结构可以让其在进行写操作时候能够将锁保持尽量地小，
 *     不需要对整个ConcurrentHashMap加锁；
 *
 *     ConcurrentHashMap为了提高本身的并发能力，在内部采用了一个叫做Segment(段)的结构，
 *     一个Segment其实就是一个类HashTable的结构，Segment内部维护了一个链表数组;
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
 *
 *
 */
public class ConcurrentHashMapDemo {


}
