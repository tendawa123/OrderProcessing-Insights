package com.ordermanagement.model.product;

public class Product {
	
	private Integer id;
	private String productCode;
	private String productName;
	private String description;
	private Long standardCost;
	private Long listPrice;
	private Integer targetLevel;
	private Integer reorderLevel;
	private Integer minimumReorderQuantity;
	private String quantityPerUnit;
	private Integer discontinued;
	
	public Product() {
	}
	public Product(Integer id, String productCode, String productName, String description, Long standardCost,
			Long listPrice, Integer targetLevel, Integer reorderLevel, Integer minimumReorderQuantity,
			String quantityPerUnit, Integer discontinued) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.description = description;
		this.standardCost = standardCost;
		this.listPrice = listPrice;
		this.targetLevel = targetLevel;
		this.reorderLevel = reorderLevel;
		this.minimumReorderQuantity = minimumReorderQuantity;
		this.quantityPerUnit = quantityPerUnit;
		this.discontinued = discontinued;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(Long standardCost) {
		this.standardCost = standardCost;
	}

	public Long getListPrice() {
		return listPrice;
	}

	public void setListPrice(Long listPrice) {
		this.listPrice = listPrice;
	}

	public Integer getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(Integer targetLevel) {
		this.targetLevel = targetLevel;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getMinimumReorderQuantity() {
		return minimumReorderQuantity;
	}

	public void setMinimumReorderQuantity(Integer minimumReorderQuantity) {
		this.minimumReorderQuantity = minimumReorderQuantity;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public Integer getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Integer discontinued) {
		this.discontinued = discontinued;
	}

	
}
