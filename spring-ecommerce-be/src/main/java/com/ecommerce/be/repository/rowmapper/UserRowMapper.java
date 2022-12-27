package com.ecommerce.be.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.be.auth.models.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUid(rs.getString("uid"));
		user.setEmail(rs.getString("email"));
		user.setEmailVerified(rs.getBoolean("emailVerified"));
		user.setName(rs.getString("name"));
		user.setPicture(rs.getString("picture"));
		user.setRole(rs.getString("role"));
		return user;
	}

}
