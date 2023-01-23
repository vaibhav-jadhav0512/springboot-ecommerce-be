package com.ecommerce.be.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.be.auth.models.User;
import com.ecommerce.be.exception.CategoryAlreadyExistsException;
import com.ecommerce.be.model.Category;
import com.ecommerce.be.model.Product;
import com.ecommerce.be.model.SubCategory;
import com.ecommerce.be.queries.EcommerceQueries;
import com.ecommerce.be.repository.rowmapper.CategoryRowMapper;
import com.ecommerce.be.repository.rowmapper.ProductRowMapper;
import com.ecommerce.be.repository.rowmapper.ProductSubCategoryRowMapper;
import com.ecommerce.be.repository.rowmapper.SubCategoryRowMapper;
import com.ecommerce.be.repository.rowmapper.UserRowMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
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

	@Override
	public List<SubCategory> getAllSubCategories() {
		return template.query(EcommerceQueries.GET_ALL_SUB_CATEGORIES, new SubCategoryRowMapper());
	}

	@Override
	public SubCategory getSubCategoryById(String slug) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", slug);
		return template.queryForObject(EcommerceQueries.GET_SUB_CATEGORY_BY_ID, params, new SubCategoryRowMapper());
	}

	@Override
	public int saveSubCategory(SubCategory category) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", category.getSlug());
		params.put("name", category.getName());
		params.put("parent", category.getParent());
		try {
			return template.update(EcommerceQueries.SAVE_SUB_CATEGORY, params);
		} catch (Exception e) {
			throw new CategoryAlreadyExistsException("Sub Category already exists!");
		}
	}

	@Override
	public int updateSubCategory(SubCategory category) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", category.getSlug());
		params.put("slugUpdate", category.getSlugUpdate());
		params.put("name", category.getName());
		params.put("parent", category.getParent());
		return template.update(EcommerceQueries.UPDATE_SUB_CATEGORY, params);
	}

	@Override
	public int deleteSubCategory(String slug) {
		Map<String, Object> params = new HashMap<>();
		params.put("slug", slug);
		return template.update(EcommerceQueries.DELETE_SUB_CATEGORY, params);
	}

	@Override
	public int saveProduct(Product product) {
		log.info("Product:{}", product.toString());
		Map<String, Object> params = new HashMap<>();
		params.put("slug", product.getSlug());
		params.put("title", product.getTitle());
		params.put("description", product.getDescription());
		params.put("price", product.getPrice());
		params.put("category", product.getCategory());
		params.put("quantity", product.getQuantity());
		params.put("sold", product.getSold());
		params.put("shipping", product.getShipping().toString());
		params.put("colour", product.getColour().toString());
		params.put("brand", product.getBrand().toString());
		int res = template.update(EcommerceQueries.SAVE_PRODUCT, params);
		for (SubCategory sub : product.getSubCategories()) {
			params.put("sub_slug", sub.getSlug());
			template.update(EcommerceQueries.SAVE_PRODUCT_SUB_CATEGORIES, params);
			params.remove("sub_slug");
		}
		for (Map<String, String> element : product.getImages()) {
			for (String key : element.keySet()) {
				params.put("img_id", key);
				params.put("img_url", element.get(key));
				template.update(EcommerceQueries.SAVE_PRODUCT_IMAGE, params);
				params.remove("img_id");
				params.remove("img_url");
			}
		}
		return res;
	}

	@Override
	public Page<Product> getAllProducts(Pageable page) {
		List<Product> prods = template.query(
				EcommerceQueries.GET_ALL_PRODUCTS_LIST_SIZE
						+ page.getPageSize() + " OFFSET " + page.getOffset(),
				new ProductRowMapper());
		Map<String, Object> params = new HashMap<>();
		for (Product prod : prods) {
			params.put("productSlug", prod.getSlug());
			prod.setSubCategories(template.query(EcommerceQueries.GET_ALL_PRODUCTS_SUB_CATEGORIES, params,
					new ProductSubCategoryRowMapper()));
			params.remove("productSlug");
		}
//		return prods;
		return new PageImpl<>(prods, page, count());
	}

	public int count() {
		Map<String, Object> params = new HashMap<>();
		return template.queryForObject(EcommerceQueries.GET_PRODUCT_COUNT, params, Integer.class);
	}

	@Override
	public List<SubCategory> getSubCategoryByParent(String parent) {
		Map<String, Object> params = new HashMap<>();
		params.put("parent", parent);
		return template.query(EcommerceQueries.GET_ALL_SUB_CATEGORIES_BY_PARENT, params, new SubCategoryRowMapper());
	}

}
