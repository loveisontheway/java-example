package com.muxi.java.example.io;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 通过IO流实现文件上传、批量上传、下载、批量下载
 *
 * @author jl.jiang 2020/12/22
 */
public abstract class FileIOStream {

    private static final Logger log = LoggerFactory.getLogger(FileIOStream.class);

    /**
     * 上传文件
     *
     * @param file 文件
     * @return String
     */
    public static String upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "file is empty";
            }
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            log.info("upload file name: " + fileName);
            String filePath = "C:\\upload\\";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测目录是否存在
            if (!dest.getParentFile().exists()) {
                // create folder
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return "upload success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload failure";
    }

    /**
     * 批量上传
     *
     * @param request 请求对象
     * @return String
     */
    public static String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "C:\\upload\\";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String of = filePath + file.getOriginalFilename();
                    FileOutputStream fos = new FileOutputStream(new File(of));
                    stream = new BufferedOutputStream(fos);
                    stream.write(bytes);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "the " + i + " file upload failure";
                }
            } else {
                return "the " + i + " file is empty";
            }
        }
        return "upload multipart file";
    }

    /**
     * 下载
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return String
     */
    @SneakyThrows
    public static String download(HttpServletRequest request, HttpServletResponse response) {
        String realPath = "C:\\upload\\";
        String fileName = "test.txt";
        if (fileName != null) {
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] bytes = new byte[1024];
                // 创建缓冲输入流
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                OutputStream os = null;
                try {
                    // 待下载文件的路径
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    os = response.getOutputStream();
                    int i = bis.read(bytes);
                    // 通过while循环写入到指定文件夹中
                    while (i != -1) {
                        os.write(bytes, 0, i);
                        i = bis.read(bytes);
                    }
                    return "download success";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 先开后关
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                        if (bis != null) {
                            bis.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "download failure";
    }

    /**
     * 批量下载
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return String
     */
    public static String batchDownload(HttpServletRequest request, HttpServletResponse response) {
        List<String> list = new ArrayList<>();
        list.add("C:\\upload\\test.txt");
        list.add("C:\\upload\\car.png");
        list.add("C:\\upload\\admin.conf");
        // 生成zip文件存放位置
        long timeMillis = System.currentTimeMillis();
        String strZipPath = "D:\\download\\" + timeMillis + ".zip";
        File file = new File("D:\\download\\");
        // 文件存放位置目录不存在则创建
        if (!file.isDirectory() && !file.exists()) {
            file.mkdirs();
        }
        try {
            // 通过response的outputStream输出文件
            ServletOutputStream outputStream = response.getOutputStream();
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            FileInputStream fis = null;
            BufferedInputStream bufis = null;
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                File f = new File(str);
                // 加入文件名称
                out.putNextEntry(new ZipEntry(f.getName()));
                // 写入文件内容
                byte[] bytes = new byte[1024];
                try {
                    fis = new FileInputStream(f);
                    bufis = new BufferedInputStream(fis);
                    int read = bufis.read(bytes);
                    while (read != -1) {
                        out.write(bytes, 0, read);
                        read = bufis.read(bytes);
                    }
                    out.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(strZipPath));
            // 将输入流的数据拷贝到输入流输出
            FileCopyUtils.copy(bis, outputStream);
            return "batch download success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "batch download failure";
    }

}
