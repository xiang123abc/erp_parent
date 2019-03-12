package cn.ecut.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;

public class TestDemo01 {
	private static IOrdersDao ordersDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;
	
//	private ISupplierDao supplierDao;
	
	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		//setBaseDao(ordersDao);
	}

	/*public static void main(String[] args) throws FileNotFoundException {
		
		OutputStream os = new FileOutputStream(new File("e:/temp/b.xls"));
		//创建一个工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				//获取订单
				//Orders orders = ordersDao.get("c8001");
				//List<Orderdetail> detailList = orders.getOrderdetails();
				String sheetName = "1";
				sheetName = "上海xxxxxxxxxx公司";
				if(Orders.TYPE_IN.equals(orders.getType())){
					sheetName = "采 购 单";
				}
				if(Orders.TYPE_OUT.equals(orders.getType())){
					sheetName = "销 售 单";
				}
				//创建一个工作表
				HSSFSheet sheet = wb.createSheet(sheetName);
				//创建一行,行的索引是从0开始
				HSSFRow row = sheet.createRow(0);
				//创建内容体的单元格的样式
				HSSFCellStyle style_content = wb.createCellStyle();
				style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
				style_content.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style_content.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				style_content.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				//对齐方式：水平居中
				style_content.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				//垂直居中
				style_content.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//创建内容样式的字体
				HSSFFont font_content = wb.createFont();
				//设置字体名称，相当选中哪种字符
				font_content.setFontName("宋体");
				//设置字体的大小
				font_content.setFontHeightInPoints((short)11);
				font_content.setBold(true);
				style_content.setFont(font_content);
				
				//设置日期格式
				HSSFCellStyle style_date = wb.createCellStyle();
				//把 style_content里样式复制到date_style		
				style_date.cloneStyleFrom(style_content);		
				DataFormat df = wb.createDataFormat();
				style_date.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
				
				//标题样式
				HSSFCellStyle style_title = wb.createCellStyle();
				style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				HSSFFont style_font = wb.createFont();
				style_font.setFontName("黑体");
				style_font.setFontHeightInPoints((short)18);
				//加粗
				style_font.setBold(true);
				style_title.setFont(style_font);
				
				
				
				//合并单元格
				//标题：采购单
				sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 9));
				//供应商订单明细
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 5));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 9));
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 4));
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 5));
				sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 9));
				sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
				sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 4));
				sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 5));
				sheet.addMergedRegion(new CellRangeAddress(5, 5, 6, 9));
				sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
				sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 4));
				sheet.addMergedRegion(new CellRangeAddress(6, 6, 5, 5));
				sheet.addMergedRegion(new CellRangeAddress(6, 6, 6, 9));

				
				//商品明细
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 0));	
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 1));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 2));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 3));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 4));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 5, 5));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 6, 6));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 7, 7));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 8, 8));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 9, 10));
				//创建矩阵 11行，4列
				int rowCount = 3 + 10;
				for(int i = 3; i <= rowCount; i++){
					row = sheet.createRow(i);
					for(int j = 0; j < 10; j++){
						//设置单元格的样式
						row.createCell(j).setCellStyle(style_content);
					}
					//row.setHeight((short)500);
				}
				//必须先有创建的行和单元格
				//创建标题单元格
				HSSFCell titleCell = sheet.createRow(0).createCell(1);
				//设置标题样式
				titleCell.setCellStyle(style_title);
				titleCell.setCellValue(sheetName);
				
				sheet.getRow(3).getCell(0).setCellValue("工号");
				sheet.getRow(4).getCell(0).setCellValue("材料");
				sheet.getRow(5).getCell(0).setCellValue("颜色");
				sheet.getRow(6).getCell(0).setCellValue("造型");
				sheet.getRow(3).getCell(5).setCellValue("客户名称");
				sheet.getRow(4).getCell(5).setCellValue("客户地址");
				sheet.getRow(5).getCell(5).setCellValue("下单日期");
				sheet.getRow(6).getCell(5).setCellValue("出货日期");
				//
				sheet.getRow(7).getCell(0).setCellValue("序号");
				sheet.getRow(7).getCell(1).setCellValue("高mm");
				sheet.getRow(7).getCell(2).setCellValue("宽mm");
				sheet.getRow(7).getCell(3).setCellValue("数量");
				sheet.getRow(7).getCell(4).setCellValue("开孔类型");
				sheet.getRow(7).getCell(5).setCellValue("面积㎡");
				sheet.getRow(7).getCell(6).setCellValue("单价 元");
				sheet.getRow(7).getCell(7).setCellValue("金额");
				sheet.getRow(7).getCell(8).setCellValue("备注");
				
				//设置行高
				//标题的行高
				sheet.getRow(0).setHeight((short)1500);
				//内容体的行高
				for(int i = 3; i <= rowCount; i++){
					sheet.getRow(i).setHeight((short)300);
				}
				//设置列宽
				for(int i = 0; i < 10; i++){
					sheet.setColumnWidth(i, 3000);
				}
				
				//缓存供应商编号与员工的名称, key=供应商的编号，value=供应商的名称
				Map<String, String> supplierNameMap = new HashMap<String, String>();
				//设置供应商
				String supplierName = null;
				if(orders.getSuppliercode()!=null && !orders.getSuppliercode().isEmpty()) {
				 supplierName = supplierDao.get(orders.getSuppliercode()).getName();
				}
//				sheet.getRow(2).getCell(1).setCellValue(getSupplierName(orders.getSuppliercode(), supplierNameMap));
				sheet.getRow(2).getCell(1).setCellValue(supplierName);
				//订单详情, 设置日期
			
				sheet.getRow(5).getCell(6).setCellStyle(style_date);
				sheet.getRow(6).getCell(6).setCellStyle(style_date);
				
				if(null != orders.getStarttime()){
					sheet.getRow(5).getCell(5).setCellValue(orders.getStarttime());
				}
				if(null != orders.getEndtime()){
					sheet.getRow(6).getCell(5).setCellValue(orders.getEndtime());
				}
				
				
				//缓存员工编号与员工的名称, key=员工的编号，value=员工的名称
				Map<Long, String> empNameMap = new HashMap<Long, String>();		
				//设置经办人
				sheet.getRow(3).getCell(3).setCellValue(getEmpName(orders.getCreater(),empNameMap, empDao));
				sheet.getRow(4).getCell(3).setCellValue(getEmpName(orders.getChecker(),empNameMap, empDao));
				sheet.getRow(5).getCell(3).setCellValue(getEmpName(orders.getStarter(),empNameMap, empDao));
				sheet.getRow(6).getCell(3).setCellValue(getEmpName(orders.getEnder(),empNameMap, empDao));
				
				//设置明细内容
				int index = 0;
				//Orderdetail od = null;
				for(int i = 8; i < rowCount; i++){
					//od = detailList.get(index);
					row = sheet.getRow(i);
					row.getCell(0).setCellValue("abce");
					row.getCell(1).setCellValue("abce");
					row.getCell(2).setCellValue("abce");
					row.getCell(3).setCellValue("abce");
					row.getCell(4).setCellValue("abce");
					row.getCell(5).setCellValue("abce");
					row.getCell(6).setCellValue("abce");
					row.getCell(7).setCellValue("abce");
					row.getCell(8).setCellValue("abce");
					index++;
				}
				//设置合计
				sheet.getRow(rowCount).getCell(0).setCellValue("合计");
				sheet.getRow(rowCount).getCell(7).setCellValue("abce");
				
				//写到输出流里去
				try {
					wb.write(os);
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					try {
						wb.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}*/
public static void main(String[] args) throws FileNotFoundException {
		
		OutputStream os = new FileOutputStream(new File("e:/temp/b.xls"));
		//创建一个工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				//获取订单
				//Orders orders = ordersDao.get("c8001");
				//List<Orderdetail> detailList = orders.getOrderdetails();
				String sheetName = "1";
				sheetName = "上海卡秋丹模压橱柜有限公司";
				//创建一个工作表
				HSSFSheet sheet = wb.createSheet(sheetName);
				//创建一行,行的索引是从0开始
				HSSFRow row = sheet.createRow(0);
				//创建内容体的单元格的样式
				HSSFCellStyle style_content = wb.createCellStyle();
				style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
				style_content.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
				style_content.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
				style_content.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
				style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				//对齐方式：水平居中
				style_content.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				//垂直居中
				style_content.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//创建内容样式的字体
				HSSFFont font_content = wb.createFont();
				//设置字体名称，相当选中哪种字符
				font_content.setFontName("宋体");
				//设置字体的大小
				font_content.setFontHeightInPoints((short)11);
				font_content.setBold(true);
				style_content.setFont(font_content);
				
				//设置日期格式
				HSSFCellStyle style_date = wb.createCellStyle();
				//把 style_content里样式复制到date_style		
				style_date.cloneStyleFrom(style_content);		
				DataFormat df = wb.createDataFormat();
				style_date.setDataFormat(df.getFormat("yyyy-MM-dd"));
				
				//标题样式
				HSSFCellStyle style_title = wb.createCellStyle();
				style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				HSSFFont style_font = wb.createFont();
				style_font.setFontName("黑体");
				style_font.setFontHeightInPoints((short)18);
				//加粗
				style_font.setBold(true);
				style_title.setFont(style_font);
				
				//合并单元格
				//标题：公司
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
				//供应商日期
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
				
				//创建矩阵
				int rowCount = 2 + 10;
				for(int i = 0; i <= rowCount; i++){
					row = sheet.createRow(i);
					for(int j = 0; j < 7; j++){
						//设置单元格的样式
						row.createCell(j).setCellStyle(style_content);
					}
					//row.setHeight((short)500);
				}
				//必须先有创建的行和单元格
				//创建标题单元格
				HSSFCell titleCell = sheet.createRow(0).createCell(1);
				//设置标题样式
				titleCell.setCellStyle(style_title);
				titleCell.setCellValue(sheetName);
				
				//
				//sheet.getRow(0).getCell(0).setCellValue("上海卡秋丹模压橱柜有限公司");
				//
				
				sheet.getRow(2).getCell(0).setCellValue("日期");
				sheet.getRow(2).getCell(1).setCellValue("单号");
				sheet.getRow(2).getCell(2).setCellValue("面积㎡");
				sheet.getRow(2).getCell(3).setCellValue("单价/元");
				sheet.getRow(2).getCell(4).setCellValue("金额/元");
				sheet.getRow(2).getCell(5).setCellValue("客户地址");
				
				//设置行高
				//标题的行高
				sheet.getRow(0).setHeight((short)1500);
				sheet.getRow(1).setHeight((short)1200);
				//内容体的行高
				for(int i = 3; i <= rowCount; i++){
					sheet.getRow(i).setHeight((short)300);
				}
				//设置列宽
				for(int i = 0; i < 7; i++){
					sheet.setColumnWidth(i, 3000);
				}
				
				//订单详情, 设置日期
			
				sheet.getRow(5).getCell(6).setCellStyle(style_date);
				sheet.getRow(6).getCell(6).setCellStyle(style_date);
				
		
				//设置明细内容
				int index = 0;
				for(int i = 3; i < rowCount; i++){
					//od = detailList.get(index);
					row = sheet.getRow(i);
					row.getCell(0).setCellValue("abce");
					row.getCell(1).setCellValue("abce");
					row.getCell(2).setCellValue("abce");
					row.getCell(3).setCellValue("abce");
					row.getCell(4).setCellValue("abce");
					row.getCell(5).setCellValue("abce");
					row.getCell(6).setCellValue("abce");
					index++;
				}
				//设置合计
				sheet.getRow(rowCount).getCell(0).setCellValue("合计");
				sheet.getRow(rowCount).getCell(6).setCellValue("abce");
				
				//写到输出流里去
				try {
					wb.write(os);
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					try {
						wb.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}
}
