package com.ecommerce.be.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.exception.CategoryAlreadyExistsException;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.queries.EcommerceQueries;
import com.ecommerce.be.repository.rowmapper.CategoryRowMapper;
import com.ecommerce.be.repository.rowmapper.UserRowMapper;

@Repository
public class EcommerceDaoImpl implements EcommerceDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public int createOrUpdateUser(User user) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", user.getUid());
		params.put("name", user.getName());
		params.put("email", user.getEmail());
		params.put("picture", user.getPicture());
		params.put("emailVerified", user.isEmailVerified());
		return template.update(EcommerceQueries.CREATE_OR_UPDATE_USER, params);
	}

	@Override
	public User getUserById(String uid) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		return template.queryForObject(EcommerceQueries.GET_USER_BY_ID, params, new UserRowMapper());
	}

	@Override
	public User getAdminById(String uid) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		return template.queryForObject(EcommerceQueries.GET_ADMIN_BY_ID, params, new UserRowMapper());
	}

	@Override
	public List<Category> getAllCategories() {
		return template.query(EcommerceQueries.GET_ALL_CATEGORIES, new CategoryRowMapper());
	}

	@Override
	public Category getCategoryById(String slug) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", slug);
		return template.queryForObject(EcommerceQueries.GET_CATEGORY_BY_ID, params, new CategoryRowMapper());
	}

	@Override
	public int saveCategory(Category category) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", category.getSlug());
		params.put("name", category.getName());
		try {
			return template.update(EcommerceQueries.SAVE_CATEGORY, params);
		} catch (Exception e) {
			throw new CategoryAlreadyExistsException("Category already exists!");
		}
	}

	@Override
	public int updateCategory(Category category) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", category.getSlug());
		params.put("slugUpdate", category.getSlugUpdate());
		params.put("name", category.getName());
		return template.update(EcommerceQueries.UPDATE_CATEGORY, params);
	}

	@Override
	public int deleteCategory(String slug) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", slug);
		return template.update(EcommerceQueries.DELETE_CATEGORY, params);
	}

}