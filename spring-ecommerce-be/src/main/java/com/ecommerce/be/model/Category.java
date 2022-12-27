package com.ecommerce.be.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Category {

	private String slug;
	private String slugUpdate;
	@NotNull(message = "Category name is required!")
	private String name;
}
