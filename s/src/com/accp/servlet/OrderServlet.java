package com.accp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accp.entity.Order;
import com.accp.service.OrderService;
@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Order o = OrderService.search(id);
		if (o != null) {

			response.getWriter().print(o.getGoodsName() + "_" + o.getQuantity() + "_"
					+ o.getTotalPrice());

		} else {
			response.getWriter().println("您输入的订单号不存在！");
		}
	}

}
