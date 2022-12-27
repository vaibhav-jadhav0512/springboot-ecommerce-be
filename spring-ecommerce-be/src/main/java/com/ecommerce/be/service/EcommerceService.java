package com.ecommerce.be.service;

import java.util.List;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.model.SubCategory;

public interface EcommerceService {

	int createOrUpdateUser(User user);

	User getUserById(String uid);

	User getAdminById(String uid);

	List<Category> getAllCategories();

	Category getCategoryById(String slug);

	int saveCategory(Category category);

	int updateCategory(Category category);

	int deleteCategory(String slug);

	List<SubCategory> getAllSubCategories();

	SubCategory getSubCategoryById(String slug);

	int saveSubCategory(SubCategory category);

	int updateSubCategory(SubCategory category);

	int deleteSubCategory(String slug);

}
