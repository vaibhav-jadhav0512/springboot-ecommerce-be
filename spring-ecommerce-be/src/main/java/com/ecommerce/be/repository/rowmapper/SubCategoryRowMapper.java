package com.ecommerce.be.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.be.model.SubCategory;

public class SubCategoryRowMapper implements RowMapper<SubCategory> {

	@Override
	public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubCategory sub = new SubCategory();
		sub.setSlug(rs.getString("slug"));
		sub.setName(rs.getString("name"));
		sub.setParent(rs.getString("parent"));
		return sub;
	}

}
