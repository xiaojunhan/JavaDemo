package com.foo.app.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * NIO核心之一  Channel的原理和使用方法
 * User: zongjh
 * Date: 13-6-7
 * Time: 下午3:19
 *
 * 1、NIO的通道类和流的区别：
 *      NIO通道可以从中通道读数据，又可以向通道写数据；但是流的读写只是单向的
 *      通道可以异步地读写
 *      通道中的数据总是要读到一个buffer，或者总是从一个buffer中写入。
 *  2、Chennel是个接口（继承了Closeable），下面是他们的实现类：
 *
 *      FileChannel
 *          position() FileChannel的某个特定位置进行数据的读/写操作.
 *          size()方法将返回该实例所关联文件的大小
 *          truncate()方法截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除
 *          force()方法将通道里尚未写入磁盘的数据强制写到磁盘上
        DatagramChannel
        SocketChannel
        ServerSocketChannel
        FileChannel 从文件中读写数据。
        DatagramChannel 能通过UDP读写网络中的数据。
        SocketChannel 能通过TCP读写网络中的数据。
        ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 *
 */
public class ChannelExample {

    public void readFromFileChannel(String filePullName,String rmFlag) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(filePullName,rmFlag);
        //给文件建立通道
        FileChannel fileChannel = accessFile.getChannel();
        //分配缓存空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        //从通道读取到缓存中
        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead != -1){
            System.out.println("Read " + bytesRead);
            //缓存反转
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }

            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);

        }

        accessFile.close();

    }

}
