package com.ordermanagement.model.product;

import java.math.BigDecimal;

public class Data {

	private String name;
	private BigDecimal value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Data(String name, BigDecimal value) {
		this.name = name;
		this.value = value;
	}
}
