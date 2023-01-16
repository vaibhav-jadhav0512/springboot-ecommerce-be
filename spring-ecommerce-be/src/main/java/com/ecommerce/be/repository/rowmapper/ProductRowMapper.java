package com.ecommerce.be.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.be.model.Product;
import com.ecommerce.be.utils.enums.Brand;
import com.ecommerce.be.utils.enums.Colour;
import com.ecommerce.be.utils.enums.Shipping;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setSlug(rs.getString("slug"));
		product.setTitle(rs.getString("title"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getLong("price"));
		product.setCategory(rs.getString("category"));
		product.setQuantity(rs.getInt("quantity"));
		product.setSold(rs.getInt("sold"));
		product.setShipping(Shipping.valueOf(rs.getString("shipping")));
		product.setColour(Colour.valueOf(rs.getString("colour")));
		product.setBrand(Brand.valueOf(rs.getString("brand")));
		product.setCreatedDt(rs.getTimestamp("created_dt"));
		product.setUpdatedDt(rs.getTimestamp("updated_dt"));
		return product;
	}

}
