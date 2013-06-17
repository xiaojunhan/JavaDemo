Java NIO 由以下几个核心部分组成：
    Channels
    Buffers
    Selectors

    Channel 有点象流。 数据可以从Channel读到Buffer中，也可以从Buffer 写到Channel中;

    |---------|              |-----------|
    | Channel | -----------> | Buffer    |
    |---------|              |-----------|

    |---------|              |-----------|
    | Channel | <----------- | Buffer    |
    |---------|              |-----------|

    这些是Java NIO中最重要的通道的实现：

    FileChannel
    DatagramChannel
    SocketChannel
    ServerSocketChannel
    FileChannel 从文件中读写数据。

    DatagramChannel 能通过UDP读写网络中的数据。

    SocketChannel 能通过TCP读写网络中的数据。

    ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。


    Buffer的基本用法

    使用Buffer读写数据一般遵循以下四个步骤：

    写入数据到Buffer
    调用flip()方法
    从Buffer中读取数据
    调用clear()方法或者compact()方法

    为了理解Buffer的工作原理，需要熟悉它的三个属性：

    capacity   Buffer有一个固定的大小值
    position   position表示当前的位置
    limit   表示你最多能往Buffer里写多少数据

    Buffer的类型

    Java NIO 有以下Buffer类型

    ByteBuffer
    MappedByteBuffer
    CharBuffer
    DoubleBuffer
    FloatBuffer
    IntBuffer
    LongBuffer
    ShortBuffer

    flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

    Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。
    通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position。
    equals()

    当满足下列条件时，表示两个Buffer相等：

    有相同的类型（byte、char、int等）。
    Buffer中剩余的byte、char等的个数相等。
    Buffer中所有剩余的byte、char等都相同。

    compareTo()方法

    compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：

    第一个不相等的元素小于另一个Buffer中对应的元素 。
    所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。

    分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中
    聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel
    因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。

               |-------------|
               |   Thread    |
               |-------------|
                     |
               |-------------|
               |   Selector  |
               |-------------|
                      |
        ——————————————————————————————————————————
       |                      |                   |
 |-------------|      |-------------|    |-------------|
 |   Channel   |      |   Channel   |    |   Channel   |
 |-------------|      |-------------|    |-------------|