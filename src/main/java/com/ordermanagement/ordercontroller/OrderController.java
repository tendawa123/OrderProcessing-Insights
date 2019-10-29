package com.ordermanagement.ordercontroller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ordermanagement.customerror.CustomError;
import com.ordermanagement.model.order.Order;
import com.ordermanagement.model.order.OrderDetail;
import com.ordermanagement.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Orders" })
@RequestMapping("/api")
public class OrderController {
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ApiOperation(value = "List of Orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> listOfOrders = orderService.getAllOrders();
		if (listOfOrders.isEmpty()) {
			logger.error("Orders {} not found.", listOfOrders);
			return new ResponseEntity(new CustomError("Orders not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(listOfOrders, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Order Details")
	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public ResponseEntity<List<OrderDetail>> getOrderDetails() {
		List<OrderDetail> liOrderDetails = orderService.getAllOrderDetails();
		if (liOrderDetails.isEmpty()) {
			logger.error("OrderDetails {} not found.", liOrderDetails);
			return new ResponseEntity(new CustomError("OrderDetails not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<OrderDetail>>(liOrderDetails, HttpStatus.OK);
	}
}
