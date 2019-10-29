package com.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.model.order.Order;
import com.ordermanagement.model.order.OrderDetail;
import com.ordermanagement.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

	public List<Order> getAllOrders() {
		List<Order> listOfOrders = orderRepo.getAllOrders();
		return listOfOrders;
	}

	public List<OrderDetail> getAllOrderDetails() {
		List<OrderDetail> liOrderDetails = orderRepo.getAllOrderDetails();
		return liOrderDetails;
	}
}
