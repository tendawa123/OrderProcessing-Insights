package com.ordermanagement.ordercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.model.order.Order;
import com.ordermanagement.model.order.OrderDetail;
import com.ordermanagement.repo.OrderRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Orders" })
public class OrderController {
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ApiOperation(value = "List of Orders")
	public List<Order> getAllOrders() {
		return orderRepo.getAllOrders();
	}

	@ApiOperation(value = "Order Details")
	@RequestMapping(value = "/order-details", method = RequestMethod.GET)
	public List<OrderDetail> getOrderDetails() {
		return orderRepo.getAllOrderDetails();
	}
}
