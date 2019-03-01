package com.gy.service;

import java.util.ArrayList;

import com.gy.entity.CaseInfo;

public interface ICaseService {
	/**
	 * 添加
	 * @param caseInfo
	 */
	public void addService(CaseInfo caseInfo);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteByIdService(int id);
	
	/**
	 * 修改
	 * @param caseInfo
	 */
	public void updateService(CaseInfo caseInfo);
	
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
	 * 根据传入的CaseInfo对象，拼接合适的sql进行查找
	 * @param caseInfo
	 * @return
	 */
	public ArrayList<CaseInfo> find(CaseInfo caseInfo);
	
	/**
	 * 得到最大的id值
	 * @return
	 */
	public Integer getMaxCaseId();
	
	/**
	 * 模糊查询
	 * @param category
	 * @param keyWord
	 * @return
	 */
	public ArrayList<CaseInfo> findByKeyWord(String category,String keyWord);
}
