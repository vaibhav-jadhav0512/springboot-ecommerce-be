package com.ecommerce.be.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.model.Product;
import com.ecommerce.be.model.SubCategory;

public interface EcommerceDao {

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

	int saveProduct(Product product);

	Page<Product> getAllProducts(Pageable page);

	List<SubCategory> getSubCategoryByParent(String parent);

}
