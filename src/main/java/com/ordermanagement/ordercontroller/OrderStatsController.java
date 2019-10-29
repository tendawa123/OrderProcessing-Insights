package com.ordermanagement.ordercontroller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.customerror.CustomError;
import com.ordermanagement.model.product.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Order" })
@RequestMapping("/api/orderStates")
public class OrderStatsController {
	public static final Logger logger = LoggerFactory.getLogger(OrderStatsController.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Order Stats")
	@RequestMapping(value = "/order-stats/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Data>> getOrderStats(@PathVariable("type") String type) {
		String fieldName = "";
		if (type.equalsIgnoreCase("status") || type.equalsIgnoreCase("order_status")) {
			fieldName = " order_status ";
		} else if (type.equalsIgnoreCase("paytype") || type.equalsIgnoreCase("payment_type")) {
			fieldName = " payment_type ";
		} else if (type.equalsIgnoreCase("country") || type.equalsIgnoreCase("ship_country")) {
			fieldName = " ship_country ";
		} else {
			fieldName = " order_status ";
		}
		String sql = "select count(*) as value, " + fieldName + " as name from orders group by " + fieldName;
		String countType = new String();
		long count;
		Data singleSerise;
		ArrayList<Data> dataItemList = new ArrayList<Data>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : list) {
			singleSerise = new Data((String) row.get("name"), new BigDecimal((long) row.get("value")));
			dataItemList.add(singleSerise);
		}
		if (dataItemList.isEmpty()) {
			logger.error("orderstats {} not found.", dataItemList);
			return new ResponseEntity(new CustomError("Customers not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<Data>>(dataItemList, HttpStatus.OK);

	}

}
