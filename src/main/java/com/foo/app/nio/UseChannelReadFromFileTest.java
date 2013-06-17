package com.foo.app.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-17
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class UseChannelReadFromFileTest {

    public static void main(String[] args) throws Exception {
        /** 首先获得input channel和output channel */
        FileInputStream fin = new FileInputStream("./src/ex.txt");
        FileChannel inChannel = fin.getChannel();

        FileOutputStream fout = new FileOutputStream("./src/ex_backup.txt");
        FileChannel outChannel = fout.getChannel();

        /** 然后开辟一个缓冲区 */
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        /** 然后从文件中读入数据到buffer中 */
        while (-1 != inChannel.read(buffer)) {

            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();

        }

        outChannel.close();
        fout.close();
        inChannel.close();
        fin.close();

    }

    /**
     * 将字节数组转换成字符数组，以便还原文件内容
     */
    private static char[] translate2Char(byte[] array, int len) {

        char[] seq = new char[len];

        for (int i = 0; i < len; i++) {
            seq[i] = (char) array[i];
        }

        return seq;
    }


}
