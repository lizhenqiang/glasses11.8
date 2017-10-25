package com.accp.service;

import java.util.ArrayList;
import java.util.List;

import com.accp.entity.Order;

public class OrderService {
	private static List<Order> orders = new ArrayList<Order>();

	static {

		Order o = new Order();
		o.setId(1);
		o.setQuantity(5);
		o.setTotalPrice(10099);
		o.setGoodsName("Һ������");
		o.setCustomer(1001);
		
		
		Order o1 = new Order();
		o1.setId(2);
		o1.setQuantity(28);
		o1.setTotalPrice(3945);
		o1.setGoodsName("TCL����");
		o1.setCustomer(1002);
		
		orders.add(o);
		orders.add(o1);

	}

	/**
	 * ���ݶ�����Ų�ѯ��������
	 * @param id
	 * @return
	 */
	public static Order search(int id) {
		for (Order o : orders) {
			if (o.getId() == id) {
				return o;
			}
		}
		return null;

	}
	
}
