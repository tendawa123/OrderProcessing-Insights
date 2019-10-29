package com.ordermanagement.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermanagement.model.product.Product;

@Repository
public class ProductRepo {
	@Autowired
	JdbcTemplate template;

	@Transactional
	public List<Product> getAllProducts() {
		List<Product> products = template.query("SELECT * FROM ORDERMANAGER.PRODUCTS ",
				(result, rowNum) -> new Product(result.getInt("id"), result.getString("product_code"),
						result.getString("product_name"), result.getString("description"),
						result.getLong("standard_cost"), result.getLong("list_price"), result.getInt("target_level"),
						result.getInt("reorder_level"), result.getInt("minimum_reorder_quantity"),
						result.getString("quantity_per_unit"), result.getInt("discontinued")));
		return products;
	}

	@Transactional
	public int addNewProduct(Product product) {
		return template.update(
				"INSERT INTO PRODUCTS (id, product_code, product_name,description,standard_cost,list_price,target_level,reorder_level,minimum_reorder_quantity,quantity_per_unit,discontinued)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] {});
	}

	@Transactional
	public int deleteCustomer(int id) {
		String query = "DELETE FROM PRODUCTS WHERE ID =?";
		return template.update(query, id);
	}
}
