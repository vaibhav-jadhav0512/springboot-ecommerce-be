package com.ecommerce.be.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.be.model.SubCategory;

public class ProductSubCategoryRowMapper implements RowMapper<SubCategory>
{

	@Override
	public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubCategory prodsub = new SubCategory();
		prodsub.setSlug(rs.getString("sub_category_slug"));
		return prodsub;
	}

}

