package com.louis.mango.common.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import java.io.*;

/**
 * POI相关操作
 * Created by liyue
 * Time 2019-09-18 10:48
 */
public class PoiUtils {

    /**
     * 生成Excel文件
     * @param workbook 文件
     * @param fileName 文件名
     * @return
     */
    public static File createExcelFile(Workbook workbook,String fileName){
        OutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile(fileName, ".xlsx");
            stream = new FileOutputStream(file.getAbsoluteFile());
            workbook.write(stream);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(stream);
        }

        return file;
    }
}
