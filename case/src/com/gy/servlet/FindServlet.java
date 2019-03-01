package com.gy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gy.entity.CaseInfo;
import com.gy.service.ICaseService;
import com.gy.service.ICaseServiceImpl;
import com.gy.utils.GetJSONString;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	ICaseService service = new ICaseServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CaseInfo caseInfo = new CaseInfo();
		//获得请求的字符串
		String jsonString = GetJSONString.readJSONData(request);
		System.out.println("jsonString: "+jsonString);
		//将获得的字符串转换成json格式的数据
		JSONObject json = JSONObject.fromObject(jsonString);
		System.out.println("json: "+json);
		
		String category = json.optString("category");
		caseInfo.setCategory(category);
		String type = json.optString("type");
		caseInfo.setType(type);
		String sex = json.optString("sex");
		caseInfo.setSex(sex);
		String caseOrigin = json.optString("caseOrigin");
		caseInfo.setCaseOrigin(caseOrigin);
		String checkPoint = json.optString("checkPoint");
		caseInfo.setCheckPoint(checkPoint);
		
		//得到查询结果数据
		ArrayList<CaseInfo> caseInfoArray = service.find(caseInfo);
		JSONArray resultArray = JSONArray.fromObject(caseInfoArray);
		System.out.println(resultArray);
	
		//发送给前端
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(resultArray.toString());
		
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    	response.addHeader("Access-Control-Allow-Methods", "*");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
