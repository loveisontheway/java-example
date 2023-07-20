package com.muxi.java.example.office;

import com.muxi.java.example.domain.Demo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jjl
 * @date 2023/6/14
 */
public class Excel {

    void demo() {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        List<Demo> list = new ArrayList<>();
        d1.setName("jack");
        d1.setAge("20");
        d1.setPhone("155****7777");
        d2.setName("UI");
        d2.setAge("30");
        d2.setPhone("188****9999");
        list.add(d1);
        list.add(d2);
        InputStream is = this.getClass().getResourceAsStream("/测试.xlsx");
        try {
            XSSFWorkbook wk = new XSSFWorkbook(is);
            XSSFSheet sheet = wk.getSheetAt(0);
            XSSFRow row = sheet.getRow(0);
            int cells = row.getPhysicalNumberOfCells();
            for (int i = 0; i < list.size(); i++) {
                XSSFRow xssfRow = sheet.createRow(i+1);
                Demo demo = list.get(i);
                for (int j = 0; j < cells; j++) {
                    XSSFCell titleCell = row.getCell(j);
                    if (titleCell != null) {
                        String cellVal = row.getCell(j).getStringCellValue();
                        XSSFCell xssfCell = row.createCell(j);
                        if ("姓名".equals(cellVal)) {
                            xssfCell.setCellValue(demo.getName());
                        } else if ("年龄".equals(cellVal)) {
                            xssfCell.setCellValue(demo.getAge());
                        } else if ("电话".equals(cellVal)) {
                            xssfCell.setCellValue(demo.getPhone());
                        }
                    }
                }
            }
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                    "测试.xls",
                    "utf-8"));
            response.setHeader("Cache-Control", "No-cache");
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Excel excel = new Excel();
        excel.demo();
    }
}
