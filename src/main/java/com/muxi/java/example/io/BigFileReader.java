package com.muxi.java.example.io;

import com.google.common.io.Files;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Scanner;

/**
 * 读取大文件
 *
 * @author jl.jiang 2022/03/16
 */
public class BigFileReader {

    private static final String path = "D:\\tool\\WebStorm-2020.1.1.exe";
//    private static final String path = "D:\\tool\\dingsheng.txt";

    /**
     * 1. guava reader
     *
     * Time: 20s
     * CPU: 30%
     * Heap: 1.75 GB
     */
    public void guava() {
        try {
            Files.readLines(new File(path), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. Apache Commons IO普通方式
     *
     * Time: 13s
     * CPU: 45%
     * Heap: 1.9 GB
     */
    public void apa()  {
        try {
            FileUtils.readLines(new File(path), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. java file flow
     * Time: 26s
     * CPU: 9%
     * Heap: 710 MB
     */
    public void fileFlow() {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);

            sc = new Scanner(inputStream, String.valueOf(Charsets.UTF_8));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(">>>>>>>" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }

    }

    /**
     * 4. Apache Commons IO离效方式
     * Time: 11s
     * CPU: 9%
     * Heap: 260 MB
     */
    public void high() {
        LineIterator li = null;
        try {
            li = FileUtils.lineIterator(new File(path), String.valueOf(Charsets.UTF_8));
            while (li.hasNext()) {
                String line = li.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            LineIterator.closeQuietly(li);
        }
    }

    /**
     * 5. nio
     * Time: -23s
     * CPU: 0%
     * Heap: 260 MB
     */
    public void nio(int allocate) throws IOException {
        RandomAccessFile access = new RandomAccessFile(path, "r");

        //FileInputStream inputStream = new FileInputStream(this.file);
        FileChannel channel = access.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);

        CharBuffer charBuffer = CharBuffer.allocate(allocate);
        Charset charset = Charset.forName("GBK");
        CharsetDecoder decoder = charset.newDecoder();
        int length = channel.read(byteBuffer);
        while (length != -1) {
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, true);
            charBuffer.flip();
            System.out.println(charBuffer.toString());
            // 清空缓存
            byteBuffer.clear();
            charBuffer.clear();
            // 再次读取文本内容
            length = channel.read(byteBuffer);
        }
        channel.close();
        if (access != null) {
            access.close();
        }
    }

    public static void main(String[] args) {
        BigFileReader bfr = new BigFileReader();
//            bfr.guava();
//            bfr.apa();
//        bfr.fileFlow();
//            bfr.high();

        try {
            bfr.nio(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
