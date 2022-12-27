package com.ecommerce.be.repository;

import java.util.List;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.model.Category;

public interface EcommerceDao {

	int createOrUpdateUser(User user);

	User getUserById(String uid);

	User getAdminById(String uid);

	List<Category> getAllCategories();

	Category getCategoryById(String slug);

	int saveCategory(Category category);

	int updateCategory(Category category);

	int deleteCategory(String slug);

}
