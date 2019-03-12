package cn.itcast.erp.biz;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.itcast.erp.entity.Orders;
/**
 * 订单业务逻辑层接口
 * @author Administrator
 *
 */
public interface IOrdersBiz extends IBaseBiz<Orders>{
	/**
	 * 保存订单
	 * @param orders
	 * @param empuuid
	 * @throws Exception 
	 */
	public void add(Orders orders,String empuuid) throws Exception;
	/**
	 * 订单审核
	 */
	public void doCheck(String id,int empuuid);
	/**
	 * 订单确认
	 */
	public void doStart(String id,String empuuid);
	/**
	 * 订单导出
	 * @param id
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public void export(String id,InputStream in,OutputStream out ) throws IOException;
	
	public void export(OutputStream out ,String id) throws IOException;
	
	public void exportAll(FileInputStream fin ,OutputStream out ,Orders orders) throws IOException;
	/**
	 * 根据运单号获取运单详情
	 * @param sn
	 * @return
	 *//*
	public List<Waybilldetail> getWaybilldetailBySn(String sn);*/
}

