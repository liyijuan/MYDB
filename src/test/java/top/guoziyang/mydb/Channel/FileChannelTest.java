package top.guoziyang.mydb.Channel;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileChannelTest {
    @Test
    public void testFileChannel() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("tmp/mydb.log"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(5);
        while(channel.read(buffer) != -1){
            buffer.flip(); // 翻转缓冲区为读模式
            System.out.println(new String(buffer.array()));
            buffer.clear(); // 反转缓冲区为写模式，从通道接收数据
        }
        channel.close();
    }

    @Test
    public void testBufferWrap(){
        byte[] arr = {1,2,3};
        ByteBuffer buffer = ByteBuffer.wrap(arr,0,arr.length);
        System.out.println(Arrays.toString(buffer.array()));
        System.out.println(buffer.capacity());
        byte[] arr1 = new byte[3]; // 改成 5 试试
        buffer.get(arr1); // 从 buffer 读取到指定数组中
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("src/test/java/top/guoziyang/mydb/Channel/a.txt","rw");
        byte[] arr = new byte[5];
        raf.seek(5);
        raf.read(arr);
        System.out.println(Arrays.toString(arr));
    }
}
