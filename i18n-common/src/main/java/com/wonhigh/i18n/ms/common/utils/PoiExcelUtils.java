package com.wonhigh.i18n.ms.common.utils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * TODO: 增加描述
 * 
 * @author user
 * @date 2017年12月5日 上午11:46:05
 * @version 0.1.0 
 * @copyright wonhigh.cn
 */
public class PoiExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoiExcelUtils.class);

    /**
     * 添加列表信息
     * sheet excelSheet
     * list 导出主要信息
     * fieldName 属性名称>数组对于表头 扩展属性格式extra.key
     * contextStyle 内容样式
     * isHaveSerial 是否添加序号
     */
    public static <T> void addContextByList(XSSFSheet sheet, List<T> list, 
            String[] fieldName, XSSFCellStyle contextStyle, boolean isHaveSerial) {
        try {
            XSSFRow row = null;
            XSSFCell cell = null;
            if (list != null) {
                List<T> tList = (List<T>) list;
                T t = null;
                String value = "";
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + 2);
                    for (int j = 0; j < fieldName.length; j++) {
                        t = tList.get(i);
                        value = objectToString(getFieldValueByName(fieldName[j], t));
                        if("status".equals(fieldName[j])){
                            if("0".equals(value)){
                                value = "未处理";
                            }
                            if("1".equals(value)){
                                value = "已处理";
                            }
                        }
                        if(isHaveSerial){
                            //首列加序号
                            if(row.getCell(0)!=null && row.getCell(0).getStringCellValue()!=null){
                                cell = row.createCell(0);
                                cell.setCellValue(""+i);
                            }
                            cell = row.createCell(j+1);
                            cell.setCellValue(value);    
                        }else{
                            cell = row.createCell(j);
                            cell.setCellValue(value);    
                        }
                        cell.setCellStyle(contextStyle);
                    }
                }
                for (int j = 1; j < fieldName.length; j++) {
                    sheet.autoSizeColumn(j); // 单元格宽度 以最大的为准
                }
            } else {
                row = sheet.createRow(2);
                cell = row.createCell(0);
            }
        } catch (Throwable e) {
            LOGGER.error("填充内容出现错误：" + e.getMessage(), e);
        }
    }
    
    public static <T> void addContextByList(Sheet sheet, List<T> list, String[] fieldName, CellStyle cellStyle, boolean isHaveSerial, int startLine) {
        try {
            Row row = null;
            Cell cell = null;
            if (list != null) {
                List<T> tList = (List<T>) list;
                T t = null;
                String value = "";
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + startLine);
                    for (int j = 0; j < fieldName.length; j++) {
                        t = tList.get(i);
                        value = objectToString(getFieldValueByName(fieldName[j], t));
                        
                        if("status".equals(fieldName[j])){
                            if("0".equals(value)){
                                value = "未处理";
                            }
                            if("1".equals(value)){
                                value = "已处理";
                            }
                        }
                        if(isHaveSerial){
                            //首列加序号
                            if(row.getCell(0)!=null && row.getCell(0).getStringCellValue()!=null){
                                cell = row.createCell(0);
                                cell.setCellValue(""+i);
                            }
                            cell = row.createCell(j+1);
                            cell.setCellValue(value);    
                        }else{
                            cell = row.createCell(j);
                            cell.setCellValue(value);    
                        }
                        cell.setCellStyle(cellStyle);
                    }
                }
                for (int j = 1; j < fieldName.length; j++) {
                    sheet.autoSizeColumn(j); // 单元格宽度 以最大的为准
                }
            } else {
                row = sheet.createRow(2);
                cell = row.createCell(0);
            }
        } catch (Throwable e) {
            LOGGER.error("填充内容出现错误：" + e.getMessage(), e);
        }
    }
    
    /**
     * <P>Object转成String类型，便于填充单元格</P>
     * */
    public static String objectToString(Object object){
        String str = "";
        if(object==null){
        }else if(object instanceof Date){
                DateFormat fromType = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                Date date = (Date)object;
                str = fromType.format(date);
        }else if(object instanceof String){
            str = (String)object;
        }else if(object instanceof Integer){
            str = ((Integer)object).intValue()+"";
        }else if(object instanceof Double){
            str = ((Double)object).doubleValue()+"";
        }else if(object instanceof Long){
            str = Long.toString(((Long)object).longValue());
        }else if(object instanceof Float){
            str = Float.toHexString(((Float)object).floatValue());
        }else if(object instanceof Boolean){
            str = Boolean.toString((Boolean)object);
        }else if(object instanceof Short){
            str = Short.toString((Short)object);
        }
        return str;
    }
    
    /**
     * 添加标题(第一行)与表头(第二行)
     * 
     * @param 
     * sheet excelSheet
     * assettitle 表头>数组
     * titleName 标题 
     * headerStyle 标题样式
     * contextStyle  表头样式
     */ 
    public static void addTitle(Sheet sheet, String[] assettitle, String titleName,
            CellStyle header, CellStyle style) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, assettitle.length - 1));
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(titleName);
        cell.setCellStyle(header);
        row = sheet.createRow(1);
        for (int i = 0; i < assettitle.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(assettitle[i]);
            cell.setCellStyle(style);
        }
    }
    
    /*public static void addTitle(XSSFSheet sheet, String[] assettitle, String titleName,
            XSSFCellStyle headerStyle, XSSFCellStyle contextStyle) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, assettitle.length - 1));
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(titleName);
        cell.setCellStyle(headerStyle);
        row = sheet.createRow(1);
        for (int i = 0; i < assettitle.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(assettitle[i]);
            cell.setCellStyle(contextStyle);
        }
    }*/

    /**
     * <p>
     * 根据属性名获取属性值
     * </p>
     * fieldName 属性名 object 属性所属对象
     * 支持Map扩展属性, 不支持List类型属性，
     * return 属性值
     */
    @SuppressWarnings("unchecked")
    public static Object getFieldValueByName(String fieldName, Object object) {
        try {
            Object fieldValue = null;
            if (StringUtils.hasLength(fieldName) && object != null) {
                String firstLetter = ""; // 首字母
                String getter = ""; // get方法
                Method method = null; // 方法
                String extraKey = null;
                // 处理扩展属性 extraData.key
                if (fieldName.indexOf(".") > 0) {
                    String[] extra = fieldName.split("\\.");
                    fieldName = extra[0];
                    extraKey = extra[1];
                }
                firstLetter = fieldName.substring(0, 1).toUpperCase();
                getter = "get" + firstLetter + fieldName.substring(1);
                method = object.getClass().getMethod(getter, new Class[] {});
                fieldValue = method.invoke(object, new Object[] {});
                if (extraKey != null) {
                    Map<String, Object> map = (Map<String, Object>) fieldValue;
                    fieldValue = map==null ? "":map.get(extraKey);
                }
            }
            return fieldValue;
        } catch (Throwable e) {
            LOGGER.error("获取属性值出现异常：" + e.getMessage(), e);
            return null;
        }
    }
    
    
	//标题样式
	public static CellStyle getHeader(SXSSFWorkbook workbook) {
		CellStyle format = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); //加粗
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 16);
		format.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		format.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		format.setFont(font);
		return format;
	}

	//内容样式
	public static CellStyle getContext(SXSSFWorkbook workbook) {
		CellStyle format = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("宋体");
		format.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		format.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		format.setFont(font);
		return format;
	}


}
