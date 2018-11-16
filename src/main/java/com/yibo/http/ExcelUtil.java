package com.yibo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.constant.CommonConstants;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static List<JSONObject> getReqData(String filePath, String sheetName) throws IOException {
        if (!StringUtils.hasText(sheetName)) {
            sheetName = "Sheet1";
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        List<JSONObject> list = new ArrayList<>();
        InputStream inputStream = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inputStream);
        inputStream.close();
        Sheet sheet = wb.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        if (rows < 1) {
            return null;
        }
        Row head = sheet.getRow(0);
        int columnSum = 0;//当前值是没有值的
        List<String> attributeList = new ArrayList<>();
        while (head.getCell(columnSum) != null &&
                StringUtils.hasText(head.getCell(columnSum).getStringCellValue())) {
            attributeList.add(head.getCell(columnSum).getStringCellValue());
            columnSum++;
        }
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            JSONObject item = new JSONObject();
            for (int j = 0; j < columnSum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                item.put(attributeList.get(j), cell.getStringCellValue());
            }
            list.add(item);
        }
        wb.close();
        return list;
    }

    /**
     * 仅适用于post接口
     *
     * @param filePath  测试数据完整路径名
     * @param sheetName excel的sheet名字
     * @param token     token
     * @param fullUrl   完整请求路径
     * @throws IOException IO异常
     */
    public static void autoTest(String filePath, String sheetName, String token, String fullUrl) throws IOException {
        if (!StringUtils.hasText(sheetName)) {
            sheetName = "Sheet1";
        }
        List<JSONObject> reqData = getReqData(filePath, sheetName);
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inputStream);
        inputStream.close();

        Sheet resultSheet = wb.createSheet(sheetName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        assert reqData != null;
        for (int i = 0; i <= reqData.size(); i++) {
            Row row = resultSheet.createRow(i);
            for (int j = 0; j < 4; j++) {
                row.createCell(j);
            }
        }
        Row row = resultSheet.getRow(0);
        row.getCell(0).setCellValue("请求参数");
        row.getCell(1).setCellValue("repCode");
        row.getCell(2).setCellValue("repMsg");
        row.getCell(3).setCellValue("repData");
        for (int i = 1; i <= reqData.size(); i++) {
            row = resultSheet.getRow(i);
            JSONObject jsonObject = reqData.get(i - 1);
            row.getCell(0).setCellValue(JSON.toJSONString(jsonObject, CommonConstants.FEATURES));
            Request<JSONObject> request = new Request<>(token, jsonObject);
            Result<JSONObject> result = ApiClientUtil.post(fullUrl, request);
            row.getCell(1).setCellValue(result.getRepCode());
            row.getCell(2).setCellValue(result.getRepMsg());
            row.getCell(3).setCellValue(JSON.toJSONString(result.getRepData(), CommonConstants.FEATURES));
        }

        resultSheet.setColumnWidth(0, 15 * 2 * 256);
        resultSheet.setColumnWidth(1, 5 * 2 * 256);
        resultSheet.setColumnWidth(2, 10 * 2 * 256);
        resultSheet.setColumnWidth(3, 20 * 2 * 256);
        OutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        outputStream.close();
        wb.close();
        System.out.println("测试执行完毕");
    }
}