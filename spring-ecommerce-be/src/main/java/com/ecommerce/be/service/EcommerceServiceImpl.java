package com.ecommerce.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.exception.AdminNotFoundException;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.model.SubCategory;
import com.ecommerce.be.repository.EcommerceDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EcommerceServiceImpl implements EcommerceService {

	@Autowired
	private EcommerceDao dao;

	@Override
	public int createOrUpdateUser(User user) {
		return dao.createOrUpdateUser(user);
	}

	@Override
	public User getUserById(String uid) {
		return dao.getUserById(uid);
	}

	@Override
	public User getAdminById(String uid) {
		User user = new User();
		try {
			user = dao.getAdminById(uid);
		} catch (EmptyResultDataAccessException e) {
			log.error("Admin not found:{}", uid);
			throw new AdminNotFoundException("Admin user not found");
		}
		return user;
	}

	@Override
	public List<Category> getAllCategories() {
		return dao.getAllCategories();
	}

	@Override
	public Category getCategoryById(String slug) {
		return dao.getCategoryById(slug);
	}

	@Override
	public int saveCategory(Category category) {
		log.info("Creating category:{}", category.toString());
		return dao.saveCategory(category);
	}

	@Override
	public int updateCategory(Category category) {
		return dao.updateCategory(category);
	}

	@Override
	public int deleteCategory(String slug) {
		return dao.deleteCategory(slug);
	}

	@Override
	public List<SubCategory> getAllSubCategories() {
		return dao.getAllSubCategories();
	}

	@Override
	public SubCategory getSubCategoryById(String slug) {
		return dao.getSubCategoryById(slug);
	}

	@Override
	public int saveSubCategory(SubCategory category) {
		return dao.saveSubCategory(category);
	}

	@Override
	public int updateSubCategory(SubCategory category) {
		return dao.updateSubCategory(category);
	}

	@Override
	public int deleteSubCategory(String slug) {
		return dao.deleteSubCategory(slug);
	}

}
