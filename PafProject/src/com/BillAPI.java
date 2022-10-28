package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bills;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
	Bills BillsObj = new Bills(); 
	
    public BillAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//NOT USED
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		String output = BillsObj.insertBills(
				request.getParameter("accNo"),
				request.getParameter("usage1"),
				request.getParameter("vat"),
				request.getParameter("value"),
				request.getParameter("total"));
		
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
    {
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
	 
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
				String[] p = param.split("=");
				map.put(p[0], p[1]);
		    }
		 }
				
		 catch (Exception e)
	     {
		 }
		 
		return map;
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = BillsObj.updateBills(paras.get("hidItemIDSave").toString(),
				
				paras.get("accNo").toString(),
				paras.get("usage1").toString(),
				paras.get("vat").toString(),
				paras.get("value").toString(),
				paras.get("total").toString());
			
		response.getWriter().write(output);
	}
			
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = BillsObj.deleteBills(paras.get("billID").toString());
		response.getWriter().write(output);
	}
	
	////////////
	

}
	
	


