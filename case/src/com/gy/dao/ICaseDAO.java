package com.gy.dao;

import java.util.ArrayList;

import com.gy.entity.CaseInfo;

public interface ICaseDAO {
	/**
	 * 增加
	 */
	public void add(CaseInfo caseInfo);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteById(int id);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public CaseInfo findById(int id);
	
	/**
	 * 根据category查找
	 * @param category
	 * @return
	 */
	public ArrayList<CaseInfo> findByCategory(String category);
	
	/**
	 * 接收sql语句进行查找
	 * @param sql
	 * @return
	 */
	public ArrayList<CaseInfo> find(String sql);
	/**
	 * 更新
	 * @param caseInfo
	 */
	public void update(CaseInfo caseInfo);
	
	/**
	 * 寻找数据库中最大的caseId值
	 * @return
	 */
	public Integer getMaxCaseId();
	
	/**
	 * 模糊查询
	 * @return
	 */
	public ArrayList<CaseInfo> findByKeyWord(String category,String keyWord);
}
