package com.muxi.java.example.web;

import com.muxi.java.example.io.FileIOStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * File upload and download
 *
 * @author jl.jiang 2020/12/21
 */
@RestController
public class FileController {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return String
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return FileIOStream.upload(file);
    }

    /**
     * 批量上传
     *
     * @param request 请求对象
     * @return String
     */
    @PostMapping("/batchUpload")
    public String batchUpload(HttpServletRequest request) {
        return FileIOStream.batchUpload(request);
    }

    /**
     * 下载
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return String
     */
    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        return FileIOStream.download(request, response);
    }

    /**
     * 批量下载
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return String
     */
    @RequestMapping("/batchDownload")
    public String batchDownload(HttpServletRequest request, HttpServletResponse response) {
        return FileIOStream.batchDownload(request, response);
    }

}
