package com.gy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gy.entity.CaseInfo;
import com.gy.service.ICaseService;
import com.gy.service.ICaseServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class FindByIdServlet
 */
@WebServlet("/FindByIdServlet")
public class FindByIdServlet extends HttpServlet {
	ICaseService service = new ICaseServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int caseId = Integer.parseInt(request.getParameter("id"));
		CaseInfo caseInfo = service.findById(caseId);
//		System.out.println("id: "+caseInfo.getId());
		
		JSONObject json = JSONObject.fromObject(caseInfo);
//		JSONObject json = new JSONObject();
//		json.put("caseId", caseInfo.getId());
//		json.put("title", caseInfo.getTitle());
//		json.put("caseName", caseInfo.getCaseName());
//		json.put("caseOrigin", caseInfo.getCaseOrigin());
//		json.put("checkPoint", caseInfo.getCheckPoint());
//		json.put("sex", caseInfo.getSex());
//		json.put("age", caseInfo.getAge());
//		json.put("importTime", caseInfo.getImportTime());
//		json.put("caseDescription", caseInfo.getCaseDescription());
//		json.put("category", caseInfo.getCategory());
//		json.put("type", caseInfo.getType());
//		json.put("tag", caseInfo.getTag());
//		json.put("sequenceList", caseInfo.getSequenceList());
//		json.put("multimedia", caseInfo.getMultimedia());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
    	response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    	response.setHeader("Access-Control-Allow-Methods", "GET");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
	//	System.out.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
