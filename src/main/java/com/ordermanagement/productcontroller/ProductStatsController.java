package com.ordermanagement.productcontroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ordermanagement.model.product.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Product" })
public class ProductStatsController {

	@Autowired
	JdbcTemplate template;

	@ApiOperation(value = "Product Stats")
	@RequestMapping(value = "/product-stats-by-quantity", method = RequestMethod.GET)
	public List<Data> getProductStatsByQuantity() {
		String sql = "select product_name as name, sum(quantity) as value from order_details group by product_name ";
		String countType = new String();
		long count;
		Data singleSerise;
		ArrayList<Data> dataItemList = new ArrayList<Data>();
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			singleSerise = new Data((String) row.get("name"), (BigDecimal) row.get("value"));
			dataItemList.add(singleSerise);
		}
		return dataItemList;
	}
}
