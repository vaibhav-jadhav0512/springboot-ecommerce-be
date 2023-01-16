package com.ecommerce.be.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.model.Product;
import com.ecommerce.be.model.SubCategory;
import com.ecommerce.be.service.EcommerceService;
import com.github.slugify.Slugify;

@RestController
@RequestMapping("/ecommerce")
public class EcommerceController {

	@Autowired
	private EcommerceService service;

	@PostMapping("/user/create-update")
	public ResponseEntity<Integer> createOrUpdateUser(@AuthenticationPrincipal User user) {
		return new ResponseEntity<>(service.createOrUpdateUser(user), HttpStatus.CREATED);
    }

	@PostMapping("/get/user")
	public ResponseEntity<User> getUserById(@AuthenticationPrincipal User user) {
		return new ResponseEntity<>(service.getUserById(user.getUid()), HttpStatus.OK);
	}

	@PostMapping("/get/admin")
	public ResponseEntity<User> getAdminById(@AuthenticationPrincipal User user) {
		return new ResponseEntity<>(service.getAdminById(user.getUid()), HttpStatus.OK);
	}

	/*
	 * CATEGORIES
	 */

	@GetMapping("/get/category/all")
	public ResponseEntity<List<Category>> getAllCategories() {
		return new ResponseEntity<>(service.getAllCategories(), HttpStatus.OK);
	}

	@GetMapping("/get/category/{slug}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(service.getCategoryById(slug), HttpStatus.OK);
	}

	@PostMapping("/category/save")
	public ResponseEntity<Category> saveCategory(@RequestBody @Valid Category category) {
		final Slugify slg = Slugify.builder().build();
		category.setSlug(slg.slugify(category.getName()));
		service.saveCategory(category);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	@PutMapping("/category/update")
	public ResponseEntity<Integer> updateCategory(@RequestBody Category category) {
		final Slugify slg = Slugify.builder().build();
		category.setSlugUpdate(slg.slugify(category.getName()));
		return new ResponseEntity<>(service.updateCategory(category), HttpStatus.OK);
	}

	@DeleteMapping("/category/delete/{slug}")
	public ResponseEntity<Integer> deleteCategory(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(service.deleteCategory(slug), HttpStatus.OK);
	}

	/*
	 * SUB-CATEGORIES
	 */

	@GetMapping("/get/sub-category/all")
	public ResponseEntity<List<SubCategory>> getAllSubCategories() {
		return new ResponseEntity<>(service.getAllSubCategories(), HttpStatus.OK);
	}

	@GetMapping("/get/sub-category/{slug}")
	public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(service.getSubCategoryById(slug), HttpStatus.OK);
	}

	@GetMapping("/get/sub-category/parent/{parent}")
	public ResponseEntity<List<SubCategory>> getSubCategoryByParent(@PathVariable("parent") String parent) {
		return new ResponseEntity<>(service.getSubCategoryByParent(parent), HttpStatus.OK);
	}

	@PostMapping("/sub-category/save")
	public ResponseEntity<SubCategory> saveSubCategory(@RequestBody @Valid SubCategory category) {
		final Slugify slg = Slugify.builder().build();
		category.setSlug(slg.slugify(category.getName()));
		service.saveSubCategory(category);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	@PutMapping("/sub-category/update")
	public ResponseEntity<Integer> updateSubCategory(@RequestBody SubCategory category) {
		final Slugify slg = Slugify.builder().build();
		category.setSlugUpdate(slg.slugify(category.getName()));
		return new ResponseEntity<>(service.updateSubCategory(category), HttpStatus.OK);
	}

	@DeleteMapping("/sub-category/delete/{slug}")
	public ResponseEntity<Integer> deleteSubCategory(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(service.deleteSubCategory(slug), HttpStatus.OK);
	}

	/*
	 * PRODUCT
	 */
	@PostMapping("/product/save")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
		final Slugify slg = Slugify.builder().build();
		product.setSlug(slg.slugify(product.getTitle()));
		List<SubCategory> subs = new ArrayList<>();
		for (int j = 0; j < product.getSubCategoriesStr().size(); j++) {
			SubCategory sub = new SubCategory();
			sub.setSlug(product.getSubCategoriesStr().get(j));
			subs.add(sub);
		}
		product.setSubCategories(subs);
		try {
			service.saveProduct(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get/product/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}

}
