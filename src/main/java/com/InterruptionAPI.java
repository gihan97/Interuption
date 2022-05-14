package com;

import com.Interruption;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/InterruptionAPI")
public class InterruptionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Interruption interruptionObj = new Interruption();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InterruptionAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = interruptionObj.insertInterruption(request.getParameter("time"), request.getParameter("no_of_crew"),
				request.getParameter("vehicle_no"), request.getParameter("phone_no"), request.getParameter("province"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = interruptionObj.updateInterruption(paras.get("hidItemIDSave").toString(), paras.get("time").toString(),
				paras.get("no_of_crew").toString(), paras.get("vehicle_no").toString(), paras.get("phone_no").toString(), paras.get("province").toString());

		response.getWriter().write(output);
	}

	private Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = interruptionObj.deleteInterruption(paras.get("intID").toString());
		response.getWriter().write(output);

	}

}
