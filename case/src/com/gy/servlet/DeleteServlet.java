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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	ICaseService service = new ICaseServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int caseId = Integer.parseInt(request.getParameter("id"));
		service.deleteByIdService(caseId);
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		json.put("status", "1");
		json.put("msg", "删除成功");
    	response.getWriter().write(json.toString());
    	response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    	response.setHeader("Access-Control-Allow-Methods", "GET");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
