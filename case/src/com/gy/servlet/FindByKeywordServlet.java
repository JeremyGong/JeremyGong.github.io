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
 * 模糊查询servlet
 */
@WebServlet("/FindByKeywordServlet")
public class FindByKeywordServlet extends HttpServlet {
	ICaseService service = new ICaseServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindByKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到前端发送的json数据
		String jsonData = GetJSONString.readJSONData(request);
		JSONObject json = JSONObject.fromObject(jsonData);
		
		//解析json，得到类别和模糊查询关键字
		String category = json.optString("category");
		String keyword = json.optString("keyword");
		
		//拼接模糊查询关键字
		keyword = "%"+keyword+"%";
		
		//调用服务中的方法开启模糊查询,得到模糊查询结果,注意数组要用JSONArray
		ArrayList<CaseInfo> resultArray = service.findByKeyWord(category, keyword);
		JSONArray result = JSONArray.fromObject(resultArray);
		
		//将模糊查询结果发送给前端
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result.toString());
		
		//跨域
		response.addHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
