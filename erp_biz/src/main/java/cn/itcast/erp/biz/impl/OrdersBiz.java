package cn.itcast.erp.biz.impl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import cn.ecut.utils.UuidHelper;
import cn.itcast.erp.biz.IOrdersBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.dao.IOrdersDao;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Orderdetail;
import cn.itcast.erp.entity.Orders;
import cn.itcast.erp.entity.Supplier;
/**
 * 订单业务逻辑类
 * @author Administrator
 *
 */
public class OrdersBiz  implements IOrdersBiz {

	private IOrdersDao ordersDao;
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
	/*private IWallbillService wallbillService;
	
	public void setWallbillService(IWallbillService wallbillService) {
		this.wallbillService = wallbillService;
	}*/


	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/*public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}*/

	
	
	
	/**
	 * 保存订单
	 * @param orders
	 * @param ordersuuid
	 * @throws Exception 
	 */
	public void add(Orders orders,String suppliercode) throws Exception{
		
		/*if(suppliercode ==null || suppliercode.isEmpty()) {
			
		}
		*/
		//1. 设置订单的状态
		orders.setState("0");
		//2. 订单的类型
		orders.setType("0");
		//3. 下单时间
		orders.setStarttime(new Date());
		
		String orderscode = orders.getOrdercode();
		//orders.setSuppliercode(suppliercode);
		// 合计金额
		double total = 0;
		double totalarea = 0;
		String uuid =null;
		uuid = UuidHelper.uuidByBase64();
		orders.setUuid(uuid);
		for(Orderdetail detail : orders.getOrderdetails()){		
			detail.setOrderscode(orderscode);
			//设置新的UUID保证每个orderdetail不同
			uuid = UuidHelper.uuidByBase64();
			detail.setUuid(uuid);
			//累计金额
			total += detail.getMoney();
			//累计面积
			totalarea += detail.getArea();
			//明细的状态
			detail.setState("0");
			//跟订单的关系
			detail.setOrders(orders);
		}
		//设置订单总金额
		orders.setTotalmoney(total);
		//设置订单总面积
		orders.setTotalarea(totalarea);
		
		//保存到DB
		ordersDao.add(orders);
	}
	
	/**
	 * 查询全部
	 * @param orders1
	 * @reordersurn
	 */
	public List<Orders> getList(){
		return ordersDao.getList();
	}

	/**
	 * 条件查询
	 * @param orders1
	 * @reordersurn
	 */
	public List<Orders> getList(Orders orders1,Orders orders2,Object param){
		List list =  ordersDao.getList(orders1,orders2,param);	
		return list;
	}




	/**
	 * 删除
	 */
	public void delete(String uuid){
		ordersDao.delete(uuid);
	}
	
	/**
	 * 通过编号查询对象
	 * @param uuid
	 * @reordersurn
	 */
	public Orders get(String uuid){
		return  (Orders)ordersDao.get(uuid);
	}
	
	/**
	 * 更新
	 */
	public void update(Orders orders){
		ordersDao.update(orders);
	}

	

	public long getCount(Orders t1, Orders t2, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	public void doCheck(Long id, Long ordersuuid) {
		// TODO Auto-generated method stub
		
	}

	public void doStart(Long id, Long ordersuuid) {
		// TODO Auto-generated method stub
		
	}

	public void export(Long id, InputStream in, OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void doCheck(Long id, int ordersuuid) {
		// TODO Auto-generated method stub
		
	}

	public void doStart(Long id, int ordersuuid) {
		// TODO Auto-generated method stub
		
	}


	public void add(Orders t) {
		// TODO Auto-generated method stub
		
	}


	public void doCheck(String id, int empuuid) {
		// TODO Auto-generated method stub
		
	}


	public void doStart(String id, String empuuid) {
		// TODO Auto-generated method stub
		
	}


	public void export(OutputStream os, String orderscode) {
		//OutputStream os = new FileOutputStream(new File("e:/temp/b.xls"));
		//创建一个工作簿
		
		HSSFWorkbook wb = new HSSFWorkbook();
		String sheetName = "1";
		sheetName = "上海xxxxxxxxxx公司";
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
		
		Orders orders =  ordersDao.get(orderscode);
		List<Orderdetail> orderdetails = orders.getOrderdetails();
		
		//创建矩阵 11行，4列
		int rowCount = 8 + orderdetails.size();
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
		
		//设置orderscode
		sheet.getRow(3).getCell(2).setCellValue(orderscode);
		//
		sheet.getRow(4).getCell(2).setCellValue(orders.getMaterial());
		//
		sheet.getRow(5).getCell(2).setCellValue(orders.getColor());
		//
		sheet.getRow(6).getCell(2).setCellValue(orders.getModel());
		//设置供应商
		String supplierName = null;
		String supplierAddress = null;
		String  supplierCode = orders.getSuppliercode();
		if(supplierCode!=null && !supplierCode.isEmpty()) {
		 Supplier supplier = supplierDao.get(supplierCode);
		 supplierName = supplier.getName();
		 supplierAddress = supplier.getAddress();
		}
		sheet.getRow(3).getCell(6).setCellValue(supplierName);
		//地址
		sheet.getRow(4).getCell(6).setCellValue(supplierAddress);
		//订单详情, 设置日期
		sheet.getRow(5).getCell(6).setCellStyle(style_date);
		sheet.getRow(6).getCell(6).setCellStyle(style_date);
		if(null != orders.getStarttime()){
			sheet.getRow(5).getCell(6).setCellValue(orders.getStarttime());
		}
		if(null != orders.getEndtime()){
			sheet.getRow(6).getCell(6).setCellValue(orders.getEndtime());
		}
		
		//缓存员工编号与员工的名称, key=员工的编号，value=员工的名称
		Map<Long, String> empNameMap = new HashMap<Long, String>();		

		//设置明细内容
		int index = 0;
		Orderdetail od = null;
		Double totalmoney = 0.0;
		for(int i = 8; i < rowCount; i++){
			od = orderdetails.get(index);
			row = sheet.getRow(i);
			row.getCell(0).setCellValue(od.getGoodsname());
			row.getCell(1).setCellValue(od.getHeight());
			row.getCell(2).setCellValue(od.getWeight());
			row.getCell(3).setCellValue(od.getNum());
			row.getCell(4).setCellValue(od.getGoodstype());
			row.getCell(5).setCellValue(od.getArea());
			row.getCell(6).setCellValue(od.getPrice());
			row.getCell(7).setCellValue(od.getMoney());
			row.getCell(8).setCellValue(od.getNote());
			totalmoney = totalmoney +od.getMoney();
			index++;
		}
		//设置合计
		sheet.getRow(rowCount).getCell(0).setCellValue("合计");
		sheet.getRow(rowCount).getCell(7).setCellValue(totalmoney);
		
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


	public void export(String id, InputStream in, OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 获取供应商名称
	 * @param uuid 供应商编号
	 * @param supplierNameMap 缓存供应商编号与供应商的名称 
	 * @return 返回供应商名称
	 */
	/*private String getSupplierName(String orderscode, Map<String, String> supplierNameMap){
		if(null == orderscode){
			return null;
		}
		String supplierName = supplierNameMap.get(orderscode);
		if(null == supplierName){
			//如果没有找供应商的名称，则进行数据库查询
			supplierName = supplierDao.get(orderscode).getName();
			//存入缓存中
			supplierNameMap.put(orderscode, supplierName);
		}
		return supplierName;
	}*/

	public void exportAll(FileInputStream fin ,OutputStream out, Orders orders) throws IOException {
		 Date starttime = orders.getStarttime();
	     Date endtime = orders.getEndtime();
	     String suppliercode = orders.getSuppliercode();
	     List<Orders> list = ordersDao.getListBySuppliercoeAndStarttimeAndEndtime(suppliercode, starttime, endtime);
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
	    String Strstarttime = formatter.format(starttime);
//创建一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		//供应商日期
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		
		
		
		//创建矩阵
		int rowCount = 3 + list.size();
		for(int i = 0; i <= rowCount; i++){
			row = sheet.createRow(i);
			for(int j = 0; j < 6; j++){
				//设置单元格的样式
				row.createCell(j).setCellStyle(style_content);
			}
			//row.setHeight((short)500);
		}
		//必须先有创建的行和单元格
		//创建标题单元格
		HSSFCell titleCell = sheet.createRow(0).createCell(0);
		//设置标题样式
		titleCell.setCellStyle(style_title);
		titleCell.setCellValue(sheetName);
		
		//
		sheet.getRow(1).getCell(0).setCellValue(suppliercode+" "+Strstarttime);
		//
		
		sheet.getRow(2).getCell(0).setCellValue("日期");
		sheet.getRow(2).getCell(1).setCellValue("单号");
		sheet.getRow(2).getCell(2).setCellValue("面积㎡");
		sheet.getRow(2).getCell(3).setCellValue("单价/元");
		sheet.getRow(2).getCell(4).setCellValue("金额/元");
		sheet.getRow(2).getCell(5).setCellValue("客户地址");
		
		//设置行高
		//标题的行高
		sheet.getRow(0).setHeight((short)1200);
		sheet.getRow(1).setHeight((short)800);
		//内容体的行高
		for(int i = 3; i <= rowCount; i++){
			sheet.getRow(i).setHeight((short)300);
		}
		//设置列宽
		for(int i = 0; i < 5; i++){
			sheet.setColumnWidth(i, 4000);
		}
		sheet.setColumnWidth(5, 6000);
		
		//订单详情, 设置日期
		for(int i = 3;i<rowCount;i++) {
			sheet.getRow(i).getCell(0).setCellStyle(style_date);
		}
		//sheet.getRow(5).getCell(6).setCellStyle(style_date);
		//sheet.getRow(6).getCell(6).setCellStyle(style_date);
		
		Double TotalMoney =0.0;
		//设置明细内容
		int index = 0;
		for(int i = 3; i < rowCount; i++){	
			Orders od = list.get(index);
			row = sheet.getRow(i);
			TotalMoney+=od.getTotalmoney();
			String money = od.getTotalmoney()+"";
			row.getCell(0).setCellValue(od.getStarttime());
			row.getCell(1).setCellValue(od.getOrdercode());
			if(od.getTotalarea()!=null) {
			row.getCell(2).setCellValue(od.getTotalarea());
			}
			row.getCell(3).setCellValue("abce");
			row.getCell(4).setCellValue(money);
			row.getCell(5).setCellValue("");
			index++;
		}
		//设置合计
		DecimalFormat dft = new DecimalFormat("#.00");
		String strTotalMoney = dft.format(TotalMoney);
		sheet.getRow(rowCount).getCell(0).setCellValue("合计");
		sheet.getRow(rowCount).getCell(4).setCellValue(strTotalMoney);
		
		//写到输出流里去
		try {
			wb.write(out);
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
