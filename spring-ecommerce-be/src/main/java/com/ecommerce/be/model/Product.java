package com.ecommerce.be.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.ecommerce.be.utils.enums.Brand;
import com.ecommerce.be.utils.enums.Colour;
import com.ecommerce.be.utils.enums.Shipping;

import lombok.Data;

@Data
public class Product {

	private String slug;
	@NotNull(message = "Title is required")
	private String title;
	@NotNull(message = "Description is required")
	private String description;
	@NotNull(message = "price is required")
	private long price;
	@NotNull(message = "Category is required")
	private String category;
	private List<SubCategory> subCategories;
	private List<String> subCategoriesStr;
	private List<Map<String, String>> images;
	private int quantity;
	private int sold;
	private Shipping shipping;
	private Colour colour;
	private Brand brand;
	private Timestamp createdDt;
	private Timestamp updatedDt;
}
