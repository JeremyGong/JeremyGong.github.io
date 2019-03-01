package com.gy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gy.service.ICaseService;
import com.gy.service.ICaseServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetMaxIdServlet
 */
@WebServlet("/GetMaxIdServlet")
public class GetMaxIdServlet extends HttpServlet {
	ICaseService service = new ICaseServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMaxIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxCaseId = service.getMaxCaseId();
		System.out.println(maxCaseId);
		maxCaseId = maxCaseId+1;
		
		JSONObject maxIdJson = new JSONObject();
		maxIdJson.put("id", maxCaseId);
		
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(maxIdJson.toString());
    	
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
