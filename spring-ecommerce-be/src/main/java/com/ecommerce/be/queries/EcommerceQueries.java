package com.ecommerce.be.queries;

public class EcommerceQueries {

	public static final String CREATE_OR_UPDATE_USER = "INSERT INTO ecom.`user` (uid, name, email, picture,emailVerified) VALUES(:uid, :name, :email, :picture,:emailVerified) "
			+ "ON DUPLICATE KEY UPDATE name=:name, email=:email, picture=:picture, emailVerified=:emailVerified";
	public static final String GET_USER_BY_ID = "SELECT uid, name, email, `role`, picture, emailVerified "
			+ "FROM ecom.`user` WHERE uid=:uid";
	public static final String GET_ADMIN_BY_ID = "SELECT uid, name, email, `role`, picture, emailVerified "
			+ "FROM ecom.`user` WHERE uid=:uid AND role='admin'";
	public static final String GET_ALL_CATEGORIES = "SELECT slug, name FROM ecom.category";
	public static final String GET_CATEGORY_BY_ID = "SELECT slug, name FROM ecom.category WHERE slug=:slug";
	public static final String SAVE_CATEGORY = "INSERT INTO ecom.category (slug,name) VALUES(:slug,:name)";
	public static final String UPDATE_CATEGORY = "UPDATE ecom.category SET name=:name,slug=:slugUpdate WHERE slug=:slug";
	public static final String DELETE_CATEGORY = "DELETE FROM ecom.category WHERE slug=:slug";
	public static final String GET_ALL_SUB_CATEGORIES = "SELECT slug, name,parent FROM ecom.sub_category";
	public static final String GET_SUB_CATEGORY_BY_ID = "SELECT slug, name,parent FROM ecom.sub_category WHERE slug=:slug";
	public static final String SAVE_SUB_CATEGORY = "INSERT INTO ecom.sub_category (slug,name,parent) VALUES(:slug,:name,:parent)";
	public static final String UPDATE_SUB_CATEGORY = "UPDATE ecom.sub_category SET name=:name,slug=:slugUpdate, parent=:parent WHERE slug=:slug";
	public static final String DELETE_SUB_CATEGORY = "DELETE FROM ecom.sub_category WHERE slug=:slug";
	public static final String SAVE_PRODUCT = "INSERT INTO ecom.product "
			+ "(title, slug, description, price, category,quantity, sold, shipping, colour, brand) "
			+ "VALUES (:title, :slug, :description, :price, :category, :quantity, :sold, :shipping, :colour, :brand) ";
	public static final String GET_ALL_PRODUCTS = "SELECT title, slug, description, price, category, quantity, sold, shipping, colour, brand, created_dt, updated_dt "
			+ "FROM ecom.product";
	public static final String GET_ALL_SUB_CATEGORIES_BY_PARENT = "SELECT slug, name, parent "
			+ "FROM ecom.sub_category WHERE parent=:parent";
	public static final String SAVE_PRODUCT_SUB_CATEGORIES = "INSERT INTO ecom.product_sub_category_rel "
			+ "(product_slug, sub_category_slug)  VALUES(:slug,:sub_slug)";
	public static final String GET_ALL_PRODUCTS_SUB_CATEGORIES = "SELECT sub_category_slug "
			+ "FROM ecom.product_sub_category_rel WHERE product_slug=:productSlug";
	public static final String SAVE_PRODUCT_IMAGE = "INSERT INTO ecom.product_images "
			+ "(slug, img_id, img_url, created_dt, updated_dt) "
			+ "VALUES(:slug,:img_id, :img_url, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
	public static final String GET_PRODUCT_COUNT = "SELECT count(*) FROM  ecom.product";
	public static final String GET_ALL_PRODUCTS_LIST_SIZE = "SELECT title, slug, description, price, category, quantity, sold, shipping, colour, brand, created_dt, updated_dt FROM ecom.product LIMIT ";
}
