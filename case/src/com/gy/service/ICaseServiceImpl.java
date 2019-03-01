package com.gy.service;

import java.util.ArrayList;
import com.gy.dao.ICaseDAO;
import com.gy.dao.impl.ICaseDAOImpl;
import com.gy.entity.CaseInfo;

public class ICaseServiceImpl implements ICaseService {
	ICaseDAO dao = new ICaseDAOImpl();
	
	/**
	 * 增加数据
	 */
	@Override
	public void addService(CaseInfo caseInfo) {
		dao.add(caseInfo);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deleteByIdService(int id) {
		dao.deleteById(id);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void updateService(CaseInfo caseInfo) {
		dao.update(caseInfo);
	}

	/** 
	 * 根据id查找
	 */
	@Override
	public CaseInfo findById(int id) {
		return dao.findById(id);
	}
	
	/** 
	 * 根据category查找
	 */
	@Override
	public ArrayList<CaseInfo> findByCategory(String category) {
		return dao.findByCategory(category);
	}
	
	/**
	 * 精确查找
	 */
	@Override
	public ArrayList<CaseInfo> find(CaseInfo caseInfo) {
		ICaseDAO dao = new ICaseDAOImpl();
		String sql = "SELECT caseId FROM `caseinfo` where category='"+caseInfo.getCategory()+"' ";
		
		String type = caseInfo.getType();
		String caseOrigin = caseInfo.getCaseOrigin();
		String sex = caseInfo.getSex();
		String checkPoint = caseInfo.getCheckPoint();
		
		if(type!=null && !type.equals("all") && type.length()>0){
			sql = sql+"AND type='"+type+"' ";
		}
		
		if(caseOrigin!=null && !caseOrigin.equals("all") && caseOrigin.length()>0){
			sql = sql+"AND caseOrigin='"+caseOrigin+"' ";
		}
		
		if(sex!=null && !sex.equals("all") && sex.length()>0){
			sql = sql+"AND sex='"+sex+"' ";
		}
		
		if(checkPoint!=null && !checkPoint.equals("all") && checkPoint.length()>0){
			sql = sql+"AND checkPoint='"+checkPoint+"' ";
		}
		//System.out.println("sql: "+sql);
		return dao.find(sql);
	}

	/**
	 * 得到最大的id值
	 */
	@Override
	public Integer getMaxCaseId() {
		return dao.getMaxCaseId();
	}
	
	/**
	 * 模糊查询
	 */
	@Override
	public ArrayList<CaseInfo> findByKeyWord(String category, String keyWord) {
		
		return dao.findByKeyWord(category, keyWord);
	}
	
	
	
}
