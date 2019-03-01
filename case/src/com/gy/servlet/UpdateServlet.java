package com.gy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gy.entity.CaseInfo;
import com.gy.entity.Sequence;
import com.gy.service.ICaseService;
import com.gy.service.ICaseServiceImpl;
import com.gy.utils.GetJSONString;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	
	ICaseService service = new ICaseServiceImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
CaseInfo caseInfo = new CaseInfo();
		
		//获得请求的字符串
		String jsonString = GetJSONString.readJSONData(request);
		//System.out.println("jsonString: "+jsonString);
		//将获得的字符串转换成json格式的数据
		JSONObject json = JSONObject.fromObject(jsonString);
		//System.out.println("json: "+json);
		
		ArrayList<String> tags = new ArrayList<>();
		ArrayList<Sequence> sequenceArrayList = new ArrayList<>();
		ArrayList<String> multimedias = new ArrayList<>();
		
		//解析json
		//id
		int id = json.optInt("id");
		caseInfo.setId(id);
		
		//caseName
		String caseName = json.optString("caseName");
		caseInfo.setCaseName(caseName);
		
		//caseOrigin
		String caseOrigin = json.optString("caseOrigin");
		caseInfo.setCaseOrigin(caseOrigin);
		
		//checkPoint
		String checkPoint = json.optString("checkPoint");
		caseInfo.setCheckPoint(checkPoint);
		
		//sex
		String sex = json.optString("sex");
		caseInfo.setSex(sex);
		
		//age
		int age = json.optInt("age");
		caseInfo.setAge(age);
		
		//importTime
		String importTime = json.optString("importTime");
		caseInfo.setImportTime(importTime);
		
		//caseDescription
		String caseDescription = json.optString("caseDescription");
		caseInfo.setCaseDescription(caseDescription);
		
		//category
		String category = json.optString("category");
		caseInfo.setCategory(category);
		
		//type
		String type = json.optString("type");
		caseInfo.setType(type);
		
		//tag数组
		JSONArray tagArray = json.optJSONArray("tag");
		for (Object tag : tagArray) {
			tags.add((String) tag);
		}
		caseInfo.setTag(tags);
		
		//sequence对象
		JSONArray sequenceList = json.optJSONArray("sequenceList");
		for(int i=0;i<sequenceList.size();i++){
			Sequence sequence = new Sequence();
			
			String seq = sequenceList.getString(i);
			JSONObject sequenceJson = JSONObject.fromObject(seq);
			
			String seqId = sequenceJson.optString("seqId");
			sequence.setSeqId(seqId);
			
			String seqName = sequenceJson.optString("seqName");
			sequence.setSeqName(seqName);
			
			String thumbnail = sequenceJson.optString("thumbnail");
			sequence.setThumbnail(thumbnail);
			
			JSONArray fileArray = sequenceJson.optJSONArray("file");
			ArrayList<String> files = new ArrayList<>();
			for (Object file : fileArray) {
				files.add((String)file);
			}
			sequence.setFileName(files);
			
			JSONArray keyImgArray = sequenceJson.optJSONArray("keyImg");
			ArrayList<String> keyImgs = new ArrayList<>();
			for (Object keyImg : keyImgArray) {
				keyImgs.add((String) keyImg);
			}
			sequence.setKeyImg(keyImgs);
			
			JSONArray keyObjArray = sequenceJson.optJSONArray("keyObj");
			ArrayList<String> keyObjs = new ArrayList<>();
			for (Object keyObj : keyObjArray) {
				keyObjs.add((String) keyObj);
			}
			sequence.setKeyObj(keyObjs);
			
			sequenceArrayList.add(sequence);
		}
		
		caseInfo.setSequenceList(sequenceArrayList);
		
		//multimedia数组
		JSONArray multimediaJson = json.optJSONArray("multimedia");
		for (Object multimedia : multimediaJson) {
			multimedias.add((String) multimedia);
		}
		caseInfo.setMultimedia(multimedias);
		
		//调用服务中的方法
		service.updateService(caseInfo);
		
		JSONObject msgJson = new JSONObject();
		response.setCharacterEncoding("utf-8");
		msgJson.put("status", "1");
		msgJson.put("msg", "保存成功");
		response.getWriter().write(msgJson.toString());
		
    	response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    	response.addHeader("Access-Control-Allow-Methods", "GET,POST");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
