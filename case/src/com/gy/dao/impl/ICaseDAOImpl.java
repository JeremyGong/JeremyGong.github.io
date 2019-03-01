package com.gy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gy.dao.ICaseDAO;
import com.gy.entity.CaseInfo;
import com.gy.entity.Sequence;
import com.gy.utils.JDBCUtil;

public class ICaseDAOImpl implements ICaseDAO{
	
	private PreparedStatement stmt;
	private Connection conn;
	private ResultSet rs;
	
	/**
	 * 添加数据
	 */
	@Override
	public void add(CaseInfo caseInfo) {

		conn = JDBCUtil.getConnection();
		String sql1 = "INSERT INTO `caseinfo` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO `tag` VALUES (?, ?)";
		String sql3 = "INSERT INTO `multimedia` VALUES (?, ?)";
		String sql4 = "INSERT INTO `sequence` VALUES (?, ?, ?, ?)";
		String sql5 = "INSERT INTO `keyimg` VALUES (?, ?, ?)";
		String sql6 = "INSERT INTO `keyobj` VALUES (?, ?, ?)";
		String sql7 = "INSERT INTO `file` VALUES (?, ?, ?)";
		try {
			//预编译sql1,向caseinfo表中添加数据
			stmt = conn.prepareStatement(sql1);
			//为sql1设置参数
			stmt.setInt(1, caseInfo.getId());
			stmt.setString(2, caseInfo.getCaseName());
			stmt.setString(3, caseInfo.getCaseOrigin());
			stmt.setString(4, caseInfo.getCheckPoint());
			stmt.setString(5, caseInfo.getSex());
			stmt.setInt(6, caseInfo.getAge());
			stmt.setString(7, caseInfo.getImportTime());
			stmt.setString(8, caseInfo.getCaseDescription());
			stmt.setString(9, caseInfo.getCategory());
			stmt.setString(10, caseInfo.getType());
			
			stmt.executeUpdate();
			
			//预编译sql2语句，向表tag表中添加数据
			stmt = conn.prepareStatement(sql2);
			//循环设值
			int n = caseInfo.getTag().size();
			for(int i=0; i<n; i++){
				stmt.setString(1, caseInfo.getTag().get(i));
				stmt.setInt(2, caseInfo.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			
			//预编译sql3语句，向表multimedia表中添加数据
			stmt = conn.prepareStatement(sql3);
			int k = caseInfo.getMultimedia().size();
			for(int i=0;i<k;i++){
				stmt.setString(1, caseInfo.getMultimedia().get(i));
				stmt.setInt(2, caseInfo.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			
			//预编译sql4语句，向表sequence表中添加数据
			stmt = conn.prepareStatement(sql4);
			ArrayList<Sequence> sequenceList = caseInfo.getSequenceList();
			for (Sequence sequence : sequenceList) {
				stmt.setString(1, sequence.getSeqId());
				stmt.setString(2, sequence.getSeqName());
				stmt.setString(3, sequence.getThumbnail());
				stmt.setInt(4, caseInfo.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			
			//预编译sql5语句，向表keyimg中添加数据
			stmt = conn.prepareStatement(sql5);
			for (Sequence sequence : sequenceList) {
				int size = sequence.getKeyImg().size();
				for(int i=0;i<size;i++){
					stmt.setString(1, sequence.getKeyImg().get(i));
					stmt.setString(2, sequence.getSeqId());
					stmt.setInt(3, caseInfo.getId());
					stmt.addBatch();
				}
			}
			stmt.executeBatch();
			
			//预编译sql6语句，向表keyobj中添加数据
			stmt = conn.prepareStatement(sql6);
			for (Sequence sequence : sequenceList) {
				int size = sequence.getKeyObj().size();
				for(int i=0;i<size;i++){
					stmt.setString(1, sequence.getKeyObj().get(i));
					stmt.setString(2, sequence.getSeqId());
					stmt.setInt(3, caseInfo.getId());
					stmt.addBatch();
				}
			}
			stmt.executeBatch();
			
			//预编译sql7语句，向表file中添加数据
			stmt = conn.prepareStatement(sql7);
			for(Sequence sequence : sequenceList){
				int size = sequence.getFileName().size();
				for(int i=0; i<size; i++){
					stmt.setString(1, sequence.getFileName().get(i));
					stmt.setString(2, sequence.getSeqId());
					stmt.setInt(3, caseInfo.getId());
					stmt.addBatch();
				}
			}
			stmt.executeBatch();
			
			System.out.println("add success!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 通过id删除数据
	 */
	@Override
	public void deleteById(int id) {
		
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM caseinfo WHERE caseId = ?";
		
		try {
			
			//预编译sql
			stmt = conn.prepareStatement(sql);
			//为sql设置参数
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			System.out.println("delete success!");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	/**
	 * 根据id查找数据
	 */
	@Override
	public CaseInfo findById(int id) {

		conn = JDBCUtil.getConnection();
		String sql1 = "SELECT * FROM `caseinfo` where caseId=?";
		String sql2 = "SELECT tagName FROM `tag` where caseId=?";
		String sql3 = "SELECT mediaName FROM `multimedia` where caseId=?";
		String sql4 = "SELECT * FROM `sequence` where caseId=?";
	//	String sql5 = "SELECT keyImg FROM keyimg WHERE seqId=?";
		CaseInfo caseInfo = new CaseInfo();
		//预编译sql1,从表caseinfo中读取数据
		try {
			stmt = conn.prepareStatement(sql1);
			//为sql设置参数
			stmt.setInt(1, id);
			//执行查询
			rs = stmt.executeQuery();
			
			//遍历rs中的数据
			while(rs.next())
			{
				caseInfo.setId(rs.getInt("caseId"));
				caseInfo.setAge(rs.getInt("age"));
				caseInfo.setCaseName(rs.getString("caseName"));
				caseInfo.setCaseOrigin(rs.getString("caseOrigin"));
				caseInfo.setCheckPoint(rs.getString("checkPoint"));
				caseInfo.setSex(rs.getString("sex"));
				caseInfo.setCaseDescription(rs.getString("caseDescription"));
				caseInfo.setImportTime(rs.getString("importTime"));
				caseInfo.setCategory(rs.getString("category"));
				caseInfo.setType(rs.getString("type"));
			}
			
			//预编译sql2，从表tag中读取数据
			stmt = conn.prepareStatement(sql2);
			//为sql设置参数
			stmt.setInt(1, id);
			ArrayList<String> tags = new ArrayList<>();
			//执行查询
			rs = stmt.executeQuery();
			while(rs.next())
			{
				tags.add(rs.getString("tagName"));
			}
			caseInfo.setTag(tags);
			
			//预编译sql3，从表multimedia中读取数据
			stmt = conn.prepareStatement(sql3);
			//为sql设置参数
			stmt.setInt(1, id);
			ArrayList<String> medias = new ArrayList<>();
			//执行查询
			rs = stmt.executeQuery();
			while(rs.next())
			{
				medias.add(rs.getString("mediaName"));
			}
			caseInfo.setMultimedia(medias);
			
			//预编译sql4，从表sequence中读取数据
			stmt = conn.prepareStatement(sql4);
			//为sql设置参数
			stmt.setInt(1, id);
			
			//sequences列表
			ArrayList<Sequence> sequences = new ArrayList<>();
			Sequence sequence = null;
			//执行查询
			rs = stmt.executeQuery();
			while(rs.next())
			{
				sequence = new Sequence();
				sequence.setSeqId(rs.getString("seqId"));
				sequence.setSeqName(rs.getString("seqName"));
				sequence.setThumbnail(rs.getString("thumbnail"));
				
				//查询keyimg和keyobj,获取数据
				stmt = conn.prepareStatement("SELECT keyImg FROM keyimg WHERE seqId=? AND caseId=?");
				stmt.setString(1, rs.getString("seqId"));
				stmt.setInt(2, id);
				
				//保存keyImg列表
				ArrayList<String> keyImgs = new ArrayList<>();
				
				ResultSet imgRs = stmt.executeQuery();
				while(imgRs.next()){
					keyImgs.add(imgRs.getString("keyImg"));
				}
				//将keyImgs列表存入sequence对象中
				sequence.setKeyImg(keyImgs);
				
				//查询keyimg和keyobj,获取数据
				stmt = conn.prepareStatement("SELECT keyObj FROM keyobj WHERE seqId=? AND caseId=?");
				stmt.setString(1, rs.getString("seqId"));
				stmt.setInt(2, id);
				
				ArrayList<String> keyObjs = new ArrayList<>();
				ResultSet objRs = stmt.executeQuery();
				while(objRs.next()){
					keyObjs.add(objRs.getString("keyObj"));
				}
				//将keyObjs列表存入sequence对象中
				sequence.setKeyObj(keyObjs);
				
				stmt = conn.prepareStatement("SELECT fileName FROM file WHERE seqId=? AND caseId=?");
				stmt.setString(1, rs.getString("seqId"));
				stmt.setInt(2, id);
				
				ArrayList<String> files = new ArrayList<>();
				ResultSet fileRs = stmt.executeQuery();
				while(fileRs.next()){
					files.add(fileRs.getString("fileName"));
				}
				sequence.setFileName(files);
				
				//将sequence对象存入sequences列表中
				sequences.add(sequence);
			}
			
			//将sequences列表存入caseInfo对象中
			caseInfo.setSequenceList(sequences);
			
//			//从表sequence中获取seqId链表
//			stmt = conn.prepareStatement("SELECT seqId FROM sequence WHERE caseId = ?");
//			stmt.setInt(1, id);
//			//执行查询
//			rs = stmt.executeQuery();
//			
//			//保存seqId列表
//			ArrayList<String> seqIds = new ArrayList<>();
//			while(rs.next()){
//				seqIds.add(rs.getString("seqId"));
//			}
//			System.out.println(seqIds);
//			for (String seqId : seqIds) {
//				stmt = conn.prepareStatement(sql5);
//				stmt.setString(1, seqId);
//				rs = stmt.executeQuery();
//				while(rs.next()){
//					keyImgs.add(rs.getString("keyImg"));
//				}
//				sequence.setKeyImg(keyImgs);
//			}
			
		} catch (SQLException e) {
			System.out.println("查询出错");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return caseInfo;
	}
	
	/**
	 * 根据category查找
	 */
	@Override
	public ArrayList<CaseInfo> findByCategory(String category) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		
		ArrayList<CaseInfo> caseInfoList = new ArrayList<>();
		ICaseDAO dao = new ICaseDAOImpl();
		//思路：先找到对应category的所有id，然后再根据id查找
		String sql = "SELECT caseId FROM caseinfo WHERE category = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category);
			rs = stmt.executeQuery();
			ArrayList<Integer> caseIds = new ArrayList<>();
			while(rs.next()){
				//得到对应category的所有caseId，并将其存入caseIds列表
				caseIds.add(rs.getInt("caseId"));
			}
			
			//遍历caseIds列表，得到对应的caseInfo,并将其存入caseInfoList列表
			for (Integer caseId : caseIds) {
				CaseInfo caseInfo = dao.findById(caseId);
				caseInfoList.add(caseInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return caseInfoList;
	}
	
	/**
	 * 根据接收的sql查找
	 */
	@Override
	public ArrayList<CaseInfo> find(String sql){
		ICaseDAO dao = new ICaseDAOImpl();
		//用于存放得到的caseId值
		ArrayList<Integer> caseIds = new ArrayList<>();
		//用于存放根据id值查找到的caseInfo数据
		ArrayList<CaseInfo> caseInfo = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//得到id值，并存入
			while(rs.next()){
				caseIds.add(rs.getInt("caseId"));
			}
			
			//根据得到的id值，调用findById()方法得到caseInfo数据
			for (Integer id : caseIds) {
				caseInfo.add(dao.findById(id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, stmt, conn);
		}
		return caseInfo;
	}
	
	/**
	 * 更新数据
	 */
	@Override
	public void update(CaseInfo caseInfo) {
		
		ICaseDAO dao = new ICaseDAOImpl();
		//先将数据库中对应的删除，再添加
		dao.deleteById(caseInfo.getId());
		dao.add(caseInfo);
	}
	
	/**
	 * 模糊查询
	 */
	@Override
	public ArrayList<CaseInfo> findByKeyWord(String category, String keyWord) {
		ICaseDAO dao = new ICaseDAOImpl();
		ArrayList<CaseInfo> caseInfoList = new ArrayList<>();
		ArrayList<Integer> ids = new ArrayList<>();
		String sql = "select caseId from caseinfo where category=? and (caseId like ? or caseName like ? or caseOrigin like ? or checkPoint like ? or sex like ? or age like ? or importTime like ? or caseDescription like ? or type like ?)";
	
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category);
			stmt.setString(2, keyWord);
			stmt.setString(3, keyWord);
			stmt.setString(4, keyWord);
			stmt.setString(5, keyWord);
			stmt.setString(6, keyWord);
			stmt.setString(7, keyWord);
			stmt.setString(8, keyWord);
			stmt.setString(9, keyWord);
			stmt.setString(10, keyWord);
			
			rs = stmt.executeQuery();
			
			//得到ID列表
			while(rs.next()){
				ids.add(rs.getInt("caseId"));
			}
			
			//根据得到的id值，调用findById()方法得到caseInfo数据
			for (Integer id : ids) {
				caseInfoList.add(dao.findById(id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return caseInfoList;
	}
	/**
	 * 获取数据库中最大caseId值
	 */
	@Override
	public Integer getMaxCaseId() {
		conn = JDBCUtil.getConnection();
		String sql = "SELECT MAX(caseId) AS maxId FROM caseinfo";
		int maxCaseId=0;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				maxCaseId = rs.getInt("maxId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return maxCaseId;
	}
}
