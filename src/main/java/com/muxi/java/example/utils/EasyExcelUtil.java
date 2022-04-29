package com.muxi.java.example.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * EasyExcel Util
 *
 * @author jjl on 2022/4/25
 */
public abstract class EasyExcelUtil {

    private static final Logger log = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * excel export
     *
     * @param response 响应
     * @param sheet    表格
     * @param clazz    object
     * @param list     输出数据
     */
    public static <T> void export(HttpServletResponse response, String sheet, Class<T> clazz, List<T> list) {
        // 设置相应数据类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
        response.setCharacterEncoding("utf-8" );
        try {
            // 数据写入Excel并响应
            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet(sheet)
                    .doWrite(list);
        } catch (Exception e) {
            log.error("导出Excel异常{}" , e.getMessage());
            response.reset();   // 重置response
            response.setContentType("application/json" );
            response.setCharacterEncoding("utf-8" );
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status" , "failure" );
            map.put("message" , "下载文件失败" + e.getMessage());
            try {
                response.getWriter().println(JSON.toJSONString(map));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
