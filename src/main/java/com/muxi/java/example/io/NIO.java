package com.muxi.java.example.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * NIO高效读写文件
 * NIO 是 IO的升级版
 * NIO是基于IO进化而来
 *
 * @author jl.jiang 2022/03/16
 */
public class NIO {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public NIO(String path) {
        super();
        this.path = path;
    }

    /**
     * NIO读文件
     *
     * @param allocate 分配值 1024
     */
    private void read(int allocate) throws IOException {

        // RandomAccessFile既可以读取文件内容，也可以向文件输出数据
        // 由于RandomAccessFile可以自由访问文件的任意位置
        // mode参数: 指定RandomAccessFile的访问模式，一共有4种模式
        /*
        "r"  : 以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
        "rw" : 打开以便读取和写入。
        "rws": 打开以便读取和写入。相对于 "rw"，"rws" 还要求对“文件的内容"或“元数据"的每个更新都同步写入到基础存储设备。
        "rwd": 打开以便读取和写入，相对于 "rw"，"rwd" 还要求对“文件的内容"的每个更新都同步写入到基础存储设备。
        */
        RandomAccessFile access = new RandomAccessFile(this.path, "r");
        // 管道
        FileChannel channel = access.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        CharBuffer charBuffer = CharBuffer.allocate(allocate);
        Charset charset = Charset.forName("GBK");
        CharsetDecoder decoder = charset.newDecoder();
        int length = channel.read(byteBuffer);
        while (length != -1) {
            byteBuffer.flip();  // 写模式 转换 读模式
            decoder.decode(byteBuffer, charBuffer, true);
            charBuffer.flip();
            System.out.println(">>>>>CharBuffer;" + charBuffer.toString());
            // clear session
            byteBuffer.clear();
            charBuffer.clear();
            // 再次读取文件内容
            length = channel.read(byteBuffer);
        }
        channel.close();
        if (access != null) {
            access.close();
        }
    }

    /**
     * NIO写文件
     *
     * @param context
     * @param allocate
     * @param chartName
     */
    public void write(String context, int allocate, String chartName) throws IOException {
//        FileOutputStream fos = new FileOutputStream(this.path);     // 文件内容覆盖模式 - 不推荐
        FileOutputStream fos = new FileOutputStream(this.path, true);     // 文件内容追加模式 - 推荐
        FileChannel channel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        byteBuffer.put(context.getBytes(chartName));
        byteBuffer.flip();  // 读模式 转换 写模式
        channel.write(byteBuffer);
        channel.close();
        if(fos != null){
            fos.close();
        }
    }

    /**
     * NIO文件拷贝
     * @param source
     * @param target
     * @param allocate
     */
    public static void nioCopy(String source, String target, int allocate) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        FileInputStream fis = new FileInputStream(source);
        FileChannel inChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(target);
        FileChannel outChannel = fos.getChannel();
        int length = inChannel.read(byteBuffer);
        while (length != -1) {
            byteBuffer.flip(); // read >> write
            outChannel.write(byteBuffer);
            // 清空缓存，等待下次写入
            byteBuffer.clear();
            // 再次读取文本内容
            length = inChannel.read(byteBuffer);
        }
        fos.close();
        outChannel.close();
        fis.close();
        inChannel.close();
    }

    /**
     * IO文件拷贝
     * @param sourcePath
     * @param destPath
     */
    public static void ioCopy(String sourcePath, String destPath) throws Exception {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws Exception{
        /*long start = System.currentTimeMillis();
        ioCopy("D:\\tool\\Redis-x64-3.2.100.zip", "D:\\tool\\Redis64.zip");
        long end = System.currentTimeMillis();
        System.out.println("用时为：" + (end-start));*/

        long start = System.currentTimeMillis();
        nioCopy("D:\\tool\\Redis-x64-3.2.100.zip", "D:\\tool\\Redis64.zip",1024);
        long end = System.currentTimeMillis();
        System.out.println("用时为：" + (end-start));
    }

}
