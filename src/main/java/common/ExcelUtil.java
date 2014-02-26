/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:common.ExcelUtil
 * @description:excel导出工具
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-11     caiwenming       v1.0.0         create
 * 2014-2-15     caiwenming       v1.0.0         modify
 *
 */
package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	/**
	 * 
	 *
	 * @Description:用于输出excel文件
	 *
	 * @param head 表头
	 * @param dataList 需要打印的数据
	 * @param fileName 输出到那个文件
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 下午8:37:23
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public static boolean printExcel(Vector<String> head,List<Vector<String>>dataList,String fileName){
		try{
			HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
			HSSFSheet hssfSheet=hssfWorkbook.createSheet();
			HSSFCellStyle hssfCellStyle=hssfWorkbook.createCellStyle();
			HSSFCell hssfCell =null;
			HSSFRow hssfRow =null;
			
			/** 设置单元格样式 */
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
			hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			hssfSheet.autoSizeColumn(4,true);//列宽自适应
			
			/** 输出表头 */
			HSSFRow headrRow = hssfSheet.createRow(0);
			for(int i=0;i<head.size();i++){
				hssfCell=headrRow.createCell(i);
				hssfCell.setCellStyle(hssfCellStyle);
				hssfCell.setCellValue(head.get(i));
			}
			
			/** 输出数据 */
			for (int i = 0; i < dataList.size(); i++) {
				hssfRow=hssfSheet.createRow(i+1);
				Vector<String> oneRow=dataList.get(i);
				for(int j=0;j<oneRow.size();j++){
					hssfCell=hssfRow.createCell(j);
					hssfCell.setCellStyle(hssfCellStyle);
					/** 当标题长度大于单元格长度时，列宽为标题长度，否则为该单元格的值的长度 */
					if(head.get(j).getBytes().length>=oneRow.get(j).getBytes().length){
						hssfSheet.setColumnWidth(j,head.get(j).getBytes().length*256);
					}else {
						hssfSheet.setColumnWidth(j,oneRow.get(j).getBytes().length*256);
					}
					hssfCell.setCellValue(oneRow.get(j));
				}
			}
//			
//			ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
//			hssfWorkbook.write(arrayOutputStream);
//			arrayOutputStream.flush();
//			byte[] buf=arrayOutputStream.toByteArray();
//			excelStream=new ByteArrayInputStream(buf, 0, buf.length);
//			arrayOutputStream.close();
			
			File file =new File(fileName);
			try{
				OutputStream outputStream =new FileOutputStream(file);
				/** 写入File */
				hssfWorkbook.write(outputStream);
				outputStream.close();
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *
	 * @Description:导出excel时，生成文件名称，防止重名
	 *
	 * @param calssName 导出数据的数据表名
	 * 
	 * @return 文件名
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-15 上午8:22:15
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-15    caiwenming      v1.0.0         create
	 */
	public static String createFileName(String calssName) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();
        sb.append(calssName);
        /** 获取年月日时分秒  */
        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));  
        /** 毫秒  */
        String milli = String.valueOf(date.getTime() % 1000);  
        while (milli.length() < 3) {  
            milli = "0" + milli;  
        }  
        sb.append(milli);  
        /** 四位随机数  */
        String rondom = String.valueOf(new Random().nextInt(10000));  
        while (rondom.length() < 4) {  
            rondom = "0" + rondom;  
        }  
        sb.append(rondom);  
        return sb.toString();  
    }  
}
