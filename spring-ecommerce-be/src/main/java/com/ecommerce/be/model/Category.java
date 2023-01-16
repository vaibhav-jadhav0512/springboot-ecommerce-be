package com.ecommerce.be.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Category {

	private String slug;
	@JsonIgnore
	private String slugUpdate;
	@NotNull(message = "Category name is required!")
	private String name;
}
