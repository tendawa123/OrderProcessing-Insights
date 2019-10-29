package com.ordermanagement.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermanagement.model.order.Order;
import com.ordermanagement.model.order.OrderDetail;

@Repository
public class OrderRepo {

	@Autowired
	JdbcTemplate template;
	@Transactional
	public List<Order> getAllOrders() {
		List<Order> orders = template.query("SELECT * FROM ORDERMANAGER.ORDERS",
				(result, rowNum) -> new Order(result.getInt("id"), result.getInt("employee_Id"),
						result.getInt("customer_Id"), result.getDate("order_Date"), result.getDate("shipped_Date"),
						result.getDate("paid_Date"), result.getString("ship_Name"), result.getString("ship_Address1"),
						result.getString("ship_Address2"), result.getString("ship_City"),
						result.getString("ship_State"), result.getString("ship_Postal_Code"),
						result.getString("ship_Country"), result.getBigDecimal("shipping_Fee"),
						result.getString("payment_Type"), result.getString("order_Status")));
		return orders;
	}
	@Transactional
	public List<OrderDetail> getAllOrderDetails() {
		List<OrderDetail> orders = template.query("SELECT * FROM ORDERMANAGER.ORDER_DETAILS",
				(result, rowNum) -> new OrderDetail(result.getString("order_id"), result.getString("product_id"),
						result.getString("quantity"), result.getString("unit_price"), result.getString("discount"),
						result.getString("date_allocated"), result.getString("order_item_status"),
						result.getString("order_date"), result.getString("order_status"), result.getString("paid_date"),
						result.getString("payment_type"), result.getString("shipped_date"),
						result.getString("shipping_fee"), result.getString("ship_name"),
						result.getString("ship_address1"), result.getString("ship_address2"),
						result.getString("ship_city"), result.getString("ship_state"), result.getString("ship_country"),
						result.getString("product_code"), result.getString("product_name"),
						result.getString("category"), result.getString("description"), result.getString("list_price"),
						result.getString("customer_id"), result.getString("customer_name"),
						result.getString("customer_phone"), result.getString("customer_email"),
						result.getString("customer_company"), result.getString("employee_id"),
						result.getString("employee_name"), result.getString("employee_Department"),
						result.getString("employee_job_title")));
		return orders;
	}
}
