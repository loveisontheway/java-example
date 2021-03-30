package com.muxi.java.example.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 常规流 VS 缓冲流
 *
 * - 常规流: FileInputStream、FileOutputStream
 * - 缓冲流: BufferedReader、BufferedWriter、BufferedInputStream、BufferedOutputStream
 *
 * @author jl.jiang 2021/3/30
 */
public class ReadWriteStream {

    private static final Logger log = LoggerFactory.getLogger(ReadWriteStream.class);

    public static void main(String[] args) {

        long begin = System.currentTimeMillis();

        /** 常规流（反例） */
        /*try {
            // 只能在开发工具中使用，部署之后无法读取。（不通用）
            File file = new File("src/main/resources/db.sql");
            FileInputStream input = new FileInputStream(file);
            FileOutputStream output = new FileOutputStream("E:/db-bak.sql");
            byte[] bytes = new byte[1024];
            int i = 0;
            while ((i = input.read(bytes)) != -1) {
                output.write(bytes, 0, i);
            }
        } catch (IOException e) {
            log.error("复制文件发生异常", e);
        }
        log.info("常规流读写，总共耗时ms：" + (System.currentTimeMillis() - begin));*/


        /** 缓冲流（正例） */
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        try {
            File file = new File("src/main/resources/db.sql");
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(new FileOutputStream("E:/db-bak2.sql"));
            int j = 0;
            while ((j = bis.read(bytes, 0, bytes.length)) != -1) {
                bais.write(bytes, 0, j);
            }
            bos.write(bais.toByteArray());
        } catch (IOException e) {
            log.error("复制文件发生异常", e);
        } finally {
            try {
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("总共耗时ms" + (System.currentTimeMillis() - begin));

/*        byte[] bytes = readFile("E:/db.sql");
        writeFile("E:/db-bak.sql", bytes);*/
    }

    public static byte[] readFile(String string) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        try {
            fis = new FileInputStream(string);
            bis = new BufferedInputStream(fis);
            int j = 0;
            while (-1 != (j = bis.read(bytes, 0, bytes.length))) {
                bao.write(bytes, 0, j);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bis.close();
                bytes = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bao.toByteArray();
    }

    public static void writeFile(String string, byte[] bytes) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(string);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.flush();
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
